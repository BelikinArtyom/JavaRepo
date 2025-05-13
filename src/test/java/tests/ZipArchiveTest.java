package tests;

import com.codeborne.pdftest.PDF;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class ZipArchiveTest {


    private ClassLoader cl = ZipArchiveTest.class.getClassLoader();


    @Test
    void zipTesting() throws Exception {
        ClassLoader cl = getClass().getClassLoader();

        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("testZip.zip"))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String name = entry.getName();
                System.out.println("Found file: " + name);

                // Если это нужный CSV-файл
                if (name.equals("country_UK.csv")) {
                    // Чтение CSV прямо из ZipInputStream
                    CSVReader reader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> rows = reader.readAll();

                    // Проверка, что есть строки с конкретной страной
                    List<String> countries = rows.stream()
                            .skip(1) // пропускаем заголовок
                            .map(row -> row[0])
                            .collect(Collectors.toList());

                    assertTrue(countries.contains("Wales"));
                    assertTrue(countries.contains("Scotland"));
                    assertTrue(countries.contains("Northern Ireland"));
                    assertTrue(countries.contains("England"));

                    // Проверка, что присутствует конкретная пара Country/County
                    boolean hasCounty = rows.stream().anyMatch(row ->
                            row[0].equals("Scotland") && row[1].equals("Glasgow City"));
                    assertTrue(hasCounty);

                    }

                }
            }
        }
    }


