package tests;

import org.junit.jupiter.api.*;
import pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.sleep;
import static tests.TestData.*;
import static tests.practiceTest.selectedGender;

public class PageObjectsTestFormDataGenerate extends TestBase {


    RegistrationPage registrationPage = new RegistrationPage();
    String expectedGender = registrationPage.getSelectedGender();

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
                .uploadPicture()
                .setAdress(adress)
                .selectRandomStateAndCity();

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

//                .checkTableResult("Picture", "test_img.jpg")
//                .checkTableResult("Address", "Улица Пушкина, дом Колотушкина")
//                .checkTableResult("State and City", "Uttar Pradesh Agra");
    }

}