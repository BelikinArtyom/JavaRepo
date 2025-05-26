package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import pages.RegistrationPage;
import pages.components.ResultTable;
import java.util.Collections;
import static io.qameta.allure.Allure.step;
import static tests.TestData.*;

public class PageObjectsTestFormDataGenerate extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    TestData testData = new TestData();
    ResultTable resultTable = new ResultTable();

    @AfterEach
    void Attach() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.getVideoUrl();
        Attach.addVideo();
    }

    @Test
    @Feature("Регистрация пользователей")
    @Story("Форма регистрации")
    @Owner("belikinA")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Прохождение формы случайными данными")
    @Tag("Homework")
    void formTest1() {

        SelenideLogger.addListener("allure", new AllureSelenide());
        TestData data = new TestData();
        data.generateRandomHobbies();
        data.generateRandomPictureName();

        step("Открываем форму", () -> {
            registrationPage.openPage();
        });

        step("Заполняем форму", () -> {
            registrationPage.setTestData(data);
            registrationPage
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setEmail(email)
                    .selectGender(TestData.gender);
            registrationPage.setPhoneNumber(faker.number().digits(6));
            registrationPage.setRandomBirthDateWithFaker();
            registrationPage.selectSubjects(Collections.singletonList(singleSubject));
            registrationPage.selectHobbies(data.selectedHobbySelectors);
            registrationPage.uploadPicture(data.getUploadedPictureName());
            registrationPage.setAdress(adress);
            testData.setRandomStateAndCity();
        });

        step("Сабмитим форму", () -> {
            registrationPage
                    .submitButton();
        });

        step("Выполняем проверку введенных значений", () -> {
            registrationPage
                    .checkTableResult("Student Name", firstName + " " + lastName)
                    .checkTableResult("Student Email", email)
                    .checkTableResult("Gender", TestData.gender)
                    .checkTableResult("Mobile", registrationPage.getEnteredPhone());
            registrationPage.checkBirthDateInResult(resultTable);
            registrationPage
                    .checkSubjectsInResult();
            registrationPage
                    .checkHobbiesInResult();
            registrationPage
                    .checkStateAndCityInResult(testData.selectedStateAndCity);
            registrationPage.checkTableResult("Address", adress);
        });


    }

//        TestData data = new TestData();
//        data.generateRandomHobbies();
//        data.generateRandomPictureName();
//        registrationPage.openPage();
//        registrationPage.setTestData(data);
//        registrationPage
//                .setFirstName(firstName)
//                .setLastName(lastName)
//                .setEmail(email)
//                .selectGender(TestData.gender);
//        registrationPage.setPhoneNumber(faker.number().digits(6));
//        registrationPage.setRandomBirthDateWithFaker();
//        registrationPage.selectSubjects(Collections.singletonList(singleSubject));
//        registrationPage.selectHobbies(data.selectedHobbySelectors);
//        registrationPage.uploadPicture(data.getUploadedPictureName());
//        registrationPage.setAdress(adress);
//        testData.setRandomStateAndCity();
//        registrationPage
//                .submitButton();
//        registrationPage
//                .checkTableResult("Student Name", firstName + " " + lastName)
//                .checkTableResult("Student Email", email)
//                .checkTableResult("Gender", TestData.gender)
//                .checkTableResult("Mobile", registrationPage.getEnteredPhone());
//        registrationPage.checkBirthDateInResult(resultTable);
//        registrationPage
//                .checkSubjectsInResult();
//        registrationPage
//                .checkHobbiesInResult();
//        registrationPage
//                .checkStateAndCityInResult(testData.selectedStateAndCity);
//        registrationPage.checkTableResult("Address", adress);
//

    }
