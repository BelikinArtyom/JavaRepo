package tests;

import org.junit.jupiter.api.Test;
import pages.MainApplePage;

import static com.codeborne.selenide.Selenide.sleep;


public class AppleTest {

    private final static String HEADER_TEXT = "Чем будут отличаться iPhone 17 Pro и iPhone 17 Pro Max";


    MainApplePage applePage = new MainApplePage();


    @Test
    public void testApple() {

        applePage.openMainPage();
        applePage.searchInputClick();
        applePage.searchInputQ();

        sleep(4000);

    }










}
