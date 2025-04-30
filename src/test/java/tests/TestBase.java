package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    public static void beforeAll() {
        Configuration.browserSize = "2560x1440";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";


//        Faker faker = new Faker();
//
//        String firstName = faker.name().firstName();
//        String lastName = faker.name().lastName();
//        String streetAddress = faker.address().streetAddress();

    }
}