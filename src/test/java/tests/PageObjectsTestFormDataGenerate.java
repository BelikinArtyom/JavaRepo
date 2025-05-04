package tests;

import org.junit.jupiter.api.*;
import pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.sleep;
import static tests.TestData.*;

public class PageObjectsTestFormDataGenerate extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void formTest1() {

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

        registrationPage
                .setRandomHobbies();

        registrationPage
                .uploadRandomPicture()
                .setAdress(adress)
                .setRandomStateAndCity();

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

        registrationPage.checkPictureInResult();

        registrationPage.checkStateAndCityInResult();

        registrationPage.checkTableResult("Address", adress);
//        sleep(4000);
    }

}