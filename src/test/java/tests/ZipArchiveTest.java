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
    void pdfFileTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("testZip.zip"))) {
            ZipEntry entry;
            boolean pdfFound = false;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) {
                    checkPdf(new ByteArrayInputStream(zis.readAllBytes()));
                    pdfFound = true;
                    break;
                }
            }
            assertTrue(pdfFound, "PDF file not found in ZIP archive");
        }
    }

    @Test
    void csvFileTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("testZip.zip"))) {
            ZipEntry entry;
            boolean csvFound = false;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".csv")) {
                    checkCsv(new ByteArrayInputStream(zis.readAllBytes()));
                    csvFound = true;
                    break;
                }
            }
            assertTrue(csvFound, "CSV file not found in ZIP archive");
        }
    }

    @Test
    void excelFileTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("testZip.zip"))) {
            ZipEntry entry;
            boolean excelFound = false;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xls") || entry.getName().endsWith(".xlsx")) {
                    checkExcel(new ByteArrayInputStream(zis.readAllBytes()));
                    excelFound = true;
                    break;
                }
            }
            assertTrue(excelFound, "Excel file not found in ZIP archive");
        }
    }

    private void checkCsv(InputStream is) throws Exception {
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

    private void checkExcel(InputStream is) throws Exception {
        try (Workbook workbook = WorkbookFactory.create(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(2);
            Cell cell = row.getCell(1);

            assertEquals("Bagpipes are awesome.", cell.getStringCellValue());
        }
    }

    private void checkPdf(InputStream is) throws Exception {
        try (PDDocument document = PDDocument.load(is)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            assertTrue(text.contains("This is a simple PDF file. Fun fun fun."));
        }
    }
}


