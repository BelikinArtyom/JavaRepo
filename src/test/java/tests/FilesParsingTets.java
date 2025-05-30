package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import json.models.JaySon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FilesParsingTets {

    private ClassLoader cl = FilesParsingTets.class.getClassLoader();
    private static final Gson gson = new Gson();


    @Test
    void pdfTest() throws Exception {

        open("https://junit.org/junit5/docs/current/user-guide/");
        File downloaded = $("[href='junit-user-guide-5.12.2.pdf']").download();

        PDF pdf = new PDF(downloaded);
        Assertions.assertEquals("Stefan Bechtold, Sam Brannen, Johannes Link, Matthias Merdes, Marc Philipp, Juliette de Rancourt, Christian Stein", pdf.author);
        System.out.println();
    }


    @Test
    void excelTest() throws Exception {

        open("https://samplelib.com/ru/sample-xls.html");
        File downloaded = $("[href='https://download.samplelib.com/xls/sample-simple-2.xls']").download();

        XLS xls = new XLS(downloaded);

        String actualValue = xls.excel.getSheetAt(1).getRow(0).getCell(0).getStringCellValue();
        Assertions.assertTrue(actualValue.contains("test2"));

        System.out.println(xls);

    }

    @Test
    void csvTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("searchResults.csv");
             CSVReader reader = new CSVReader(new InputStreamReader(is))) {

            List<String[]> data = reader.readAll();
            Assertions.assertEquals(2, data.size());
            Assertions.assertArrayEquals(
                    new String[] {"Selenide","https://ru.selenide.org"} ,
                    data.get(0));

            Assertions.assertArrayEquals(
                    new String[] {"Junit5","https://junit.org"} ,
                    data.get(1));
        }

    }

    @Test
    void zipTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("sample-2.zip")
        ))  {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {

                System.out.println(entry.getName());

            }

        }
    }

    @Test
    void jsonTest() throws Exception {

        try (Reader reader = new InputStreamReader(cl.getResourceAsStream("JaySon.json")))
        {
       JsonObject actual = gson.fromJson(reader, JsonObject.class);

       Assertions.assertEquals("John Smith", actual.get("name").getAsString());
       Assertions.assertEquals(20223, actual.get("sku").getAsInt());

       JsonObject inner =actual.get("shipTo").getAsJsonObject();

       Assertions.assertEquals("Pretendville", inner.get("city").getAsString());
       Assertions.assertEquals(12345, inner.get("zip").getAsInt());

    }
}

    @Test
    void jsonTestImprovedGson() throws Exception {

        try (Reader reader = new InputStreamReader(cl.getResourceAsStream("JaySon.json")))
        {
            JaySon actual = gson.fromJson(reader, JaySon.class);

            Assertions.assertEquals("John Smith", actual.getName());
            Assertions.assertEquals(20223, actual.getSku());

            Assertions.assertEquals("Pretendville", actual.getShipTo().getCity());
            Assertions.assertEquals(12345, actual.getShipTo().getZip());

        }
    }

}

