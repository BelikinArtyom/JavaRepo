package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;
import java.util.UUID;

public class SheetTestBase {

    @BeforeAll
    public static void beforeAll() {

        String BROWSER_SIZE = System.getProperty("browser.size", "2560x1440");

        Configuration.browserSize = BROWSER_SIZE;
        Configuration.baseUrl = "https://www.sheethappenspublishing.com/";
        Configuration.pageLoadStrategy = "eager";
    }
}
