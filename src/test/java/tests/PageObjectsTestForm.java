package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;
import pages.RegistrationPage;


import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PageObjectsTestForm {

    RegistrationPage registrationPage = new RegistrationPage();

    File file = new File("src/test/resources/test_img.jpg");

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "2560x1440";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void formTest1() {
        registrationPage.openPage()
               .setFirstName("Паша")
               .setLastName("Техник")
               .setEmail("xanax@techique.com")
               .genderRadio("Male")
               .setPhoneNumber("8800555353")
               .setDateOfBirth("10","December","1999");

        $("#subjectsContainer").click();
        $("#subjectsInput").setValue("Chemistry").pressEnter();
        $("body").click(); // снимаем фокус с поля, без этого тест не идет дальше по полям (синий трактор едет к нам...)
        $("label[for='hobbies-checkbox-2']").click();
        $("label[for='hobbies-checkbox-1']").click();
        $("#hobbies-checkbox-2").shouldBe(selected);
        $("#hobbies-checkbox-1").shouldBe(selected);
        $("#uploadPicture").uploadFromClasspath("test_img.jpg");
        $("#currentAddress").setValue("Улица Пушкина, дом Колотушкина");
        $("#state").click();
        $("#react-select-3-input").setValue("Uttar Pradesh").pressEnter();
        $("#city").click();
        $("#react-select-4-input").setValue("Agra").pressEnter();
        $("#submit").click();

        registrationPage.tableCheckResult("Student Name","Паша Техник")
                .tableCheckResult("Student Name","Паша Техник")
                .tableCheckResult("Student Email","xanax@techique.com")
                .tableCheckResult("Gender","Male")
                .tableCheckResult("Mobile","8800555353")
                .tableCheckResult("Date of Birth","24 July,1990")
                .tableCheckResult("Mobile","8800555353")
                .tableCheckResult("Subjects","Chemistry")
                .tableCheckResult("Hobbies","Reading, Sports")
                .tableCheckResult("Picture","test_img.jpg")
                .tableCheckResult("Address","Улица Пушкина, дом Колотушкина")
                .tableCheckResult("State and City","Uttar Pradesh Agra");

//        $("table").shouldHave(text("Student Name"), text("Паша Техник"));
//        $("table").shouldHave(text("Student Email"), text("xanax@techique.com"));
//        $("table").shouldHave(text("Gender"), text("Male"));
//        $("table").shouldHave(text("Mobile"), text("8800555353"));
//        $("table").shouldHave(text("Date of Birth"), text("24 July,1990"));
//        $("table").shouldHave(text("Subjects"), text("Chemistry"));
//        $("table").shouldHave(text("Hobbies"), text("Паша Техник"));
//        $("table").shouldHave(text("Picture"), text("test_img.jpg"));
//        $("table").shouldHave(text("Address"), text("Улица Пушкина, дом Колотушкина"));
//        $("table").shouldHave(text("State and City"), text("Uttar Pradesh Agra"));


    }



}