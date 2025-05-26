package tests.data;

import io.qameta.allure.Step;
import pages.RegistrationPage;
import pages.components.ResultTable;
import tests.TestData;

import java.util.Collections;
import static tests.TestData.*;
import static tests.TestData.adress;

public class WebSteps {

    RegistrationPage registrationPage = new RegistrationPage();
    TestData testData = new TestData();
    ResultTable resultTable = new ResultTable();
    TestData data = new TestData();


    @Step("Открываем форму")
    public void openPageStep() {
        registrationPage.openPage();
    }

    @Step("Заполняем форму")
    public void formFill() {
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
    }

    @Step("Сабмитим форму")
    public void formSubmit() {
        registrationPage
                .submitButton();
    }

    @Step("Выполняем проверку введенных значений")
    public void formCheck() {
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
    }
}