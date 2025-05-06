package tests;

import org.junit.jupiter.api.*;
import pages.RegistrationPage;

import java.util.Collections;

import static tests.TestData.*;

public class PageObjectsTestFormDataGenerate extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    TestData testData = new TestData();


    @Test
    void formTest1() {

        TestData data = new TestData();
        data.generateRandomHobbies();
        data.generateRandomPictureName();

        registrationPage.openPage();
        registrationPage.setTestData(data);

        registrationPage
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .selectGender(TestData.gender);

        registrationPage.setPhoneNumber(faker.number().digits(6));

        registrationPage.setRandomBirthDate();

        registrationPage.selectSubjects(Collections.singletonList(singleSubject));

        registrationPage.selectHobbies(data.selectedHobbySelectors);

        registrationPage.uploadPicture(data.getUploadedPictureName());

        registrationPage.setAdress(adress);

        testData.setRandomStateAndCity();

        registrationPage
                .submitButton();



        registrationPage
                .checkTableResult("Student Name", firstName + " " + lastName)
                .checkTableResult("Student Email", email)
                .checkTableResult("Gender", TestData.gender)
                .checkTableResult("Mobile", registrationPage.getEnteredPhone())
                .checkBirthDateInResult();
        registrationPage
                .checkSubjectsInResult();
        registrationPage
                .checkHobbiesInResult();
        registrationPage
                .checkStateAndCityInResult(testData.selectedStateAndCity);
        registrationPage.checkTableResult("Address", adress);
    }

}