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

public class TestBase {

//    private static final String SELENOID_URL = System.getProperty("selenoid.url");
//    private static final String SELENOID_LOGIN = System.getProperty("selenoid.login");
//    private static final String SELENOID_PASSWORD = System.getProperty("selenoid.password");



    @BeforeAll
    public static void beforeAll() {

        String selenoidHost = System.getProperty("selenoid_host", "selenoid.autotests.cloud");
        String selenoidLogin = System.getProperty("selenoid_login", "user1");
        String selenoidPassword = System.getProperty("selenoid_password", "1234");
        String browserVersion = System.getProperty("browserVersion", "127.0");
        String BROWSER = System.getProperty("browser", "chrome");
        String BROWSER_SIZE = System.getProperty("browser.size", "1920x1080");
//        System.out.println("SELENOID_URL = " + SELENOID_URL);
//        System.out.println("SELENOID_LOGIN = " + SELENOID_LOGIN);
//        System.out.println("SELENOID_PASSWORD = " + SELENOID_PASSWORD);
//        System.out.println("BROWSER = " + BROWSER);
//        System.out.println("BROWSER_SIZE = " + BROWSER_SIZE);

        Configuration.browser = BROWSER;
        Configuration.browserVersion = browserVersion;
        Configuration.browserSize = BROWSER_SIZE;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.headless = true;
        Configuration.holdBrowserOpen = false;
        Configuration.remote = String.format("https://%s:%s@%s/wd/hub",
                selenoidLogin,
                selenoidPassword,
                selenoidHost);




//      Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true,
                "name", "Test: " + UUID.randomUUID()
        ));
        Configuration.browserCapabilities = capabilities;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
}
