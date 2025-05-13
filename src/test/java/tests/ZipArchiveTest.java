package tests;

import com.opencsv.CSVReader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class ZipArchiveTest {


    private ClassLoader cl = ZipArchiveTest.class.getClassLoader();

    @Test
    void zipTesting() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("testZip.zip")
        )) {

            ZipEntry entry;
            int count = 0;

            while ((entry = zis.getNextEntry()) != null) {
                String name = entry.getName();
                System.out.println("Found in ZIP: " + name);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    baos.write(buffer, 0, len);
                }
                ByteArrayInputStream entryStream = new ByteArrayInputStream(baos.toByteArray());

                if (name.endsWith(".csv")) {
                    checkCsv(entryStream);
                } else if (name.endsWith(".xls") || name.endsWith(".xlsx")) {
                    checkExcel(entryStream);
               } else if (name.endsWith(".pdf")) {
                    checkPdf(entryStream);
                }

                count++;
            }

            assertEquals(3, count, "Ожидалось 3 файла в архиве");
        }
    }

    void checkCsv(InputStream is) throws Exception {
        try (CSVReader reader = new CSVReader(new InputStreamReader(is))) {
            List<String[]> rows = reader.readAll();

            List<String[]> expected = List.of(
                    new String[]{"Country", "County"},
                    new String[]{"England", "Buckinghamshire"},
                    new String[]{"England", "Cambridgeshire"},
                    new String[]{"England", "Cheshire"},
                    new String[]{"England", "Cleveland"},
                    new String[]{"England", "Cornwall"},
                    new String[]{"England", "Cumbria"},
                    new String[]{"England", "Derbyshire"},
                    new String[]{"England", "Devon"}

            );
            assertEquals(expected.size(), rows.size());

            for (int i = 0; i < expected.size(); i++) {
                assertArrayEquals(expected.get(i), rows.get(i));
            }
        }
    }

    void checkExcel(InputStream is) throws Exception {
        try (Workbook workbook = WorkbookFactory.create(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(2);
            Cell cell = row.getCell(1);

            assertEquals("Bagpipes are awesome.", cell.getStringCellValue());
        }
    }

    void checkPdf(InputStream is) throws Exception {
        try (PDDocument document = PDDocument.load(is)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            assertTrue(text.contains("This is a simple PDF file. Fun fun fun."));
        }
    }
}


