package tests;

import com.codeborne.selenide.Configuration;

public class TestBase {

    static void setUp(){
        Configuration.browserSize = "2560x1440";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";

    }

}