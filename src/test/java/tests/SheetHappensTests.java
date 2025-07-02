package tests;

import helpers.SheetData;
import org.junit.jupiter.api.Test;
import pages.SheetMainPage;

public class SheetHappensTests extends SheetTestBase {

    SheetMainPage sheetPage = new SheetMainPage();
    SheetData SheetData = new SheetData();

    @Test
    public void noResultsShownTest() {

        sheetPage.openMainPage();
        sheetPage.searchInputClick();
        sheetPage.searchInputQ();
        SheetData.checkResult();
    }
}
