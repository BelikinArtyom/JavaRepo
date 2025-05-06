package tests;

import org.junit.jupiter.api.*;
import pages.RegistrationPage;

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

        registrationPage
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .selectRandomGender();

        registrationPage.setPhoneNumber(faker.number().digits(6));

        registrationPage.setRandomBirthDate();

        registrationPage
                .setRandomSubjects(2);

        registrationPage.setTestData(data);
        registrationPage.selectHobbies(data.selectedHobbySelectors);

        registrationPage.uploadPicture(data.getUploadedPictureName());

        registrationPage.setAdress(adress);

        testData.setRandomStateAndCity();

        registrationPage
                .submitButton();

        registrationPage
                .checkTableResult("Student Name", firstName + " " + lastName)
                .checkTableResult("Student Email", email);

        registrationPage.checkTableResult("Gender", registrationPage.getSelectedGender());

        registrationPage.checkTableResult("Mobile", registrationPage.getEnteredPhone());

        registrationPage.checkBirthDateInResult();

        registrationPage.checkSubjectsInResult();

        registrationPage.checkHobbiesInResult();

        registrationPage.checkStateAndCityInResult(testData.selectedStateAndCity);

        registrationPage.checkTableResult("Address", adress);
    }

}