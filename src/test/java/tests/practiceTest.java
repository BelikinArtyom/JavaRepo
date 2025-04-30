package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class practiceTest {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "2560x1440";
        System.setProperty("selenide.holdBrowserOpen", "true");
        Configuration.pageLoadStrategy = "eager";
    }
    protected static String selectedGender;

    @Test
    void closingButton () {

        open("https://demoqa.com/automation-practice-form");


        sleep(1000);

    }

}

