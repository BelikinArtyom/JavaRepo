package tests;

import org.junit.jupiter.api.*;
import pages.RegistrationPage;

public class PageObjectsTestForm extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();;

    @Test
    void formTest1() {

        registrationPage.openPage();

        registrationPage
                .setFirstName("Павел")
                .setLastName("Ивлев")
                .setEmail("xanax@techique.com")
                .genderRadio()
                .setPhoneNumber("8800555353")
                .setDateOfBirth("25", "July", "1990");

        registrationPage.setSubjects("Chemistry")
//                .setHobbies("Reading, Sports")
                .uploadPicture()
                .setAdress("Улица Пушкина, дом Колотушкина")
                .setState("Uttar Pradesh")
                .setCity("Agra")
                .submitButton();

        registrationPage.checkTableResult("Student Name", "Павел Ивлев")
                .checkTableResult("Student Email", "xanax@techique.com")
                .checkTableResult("Gender", "Male")
                .checkTableResult("Mobile", "8800555353")
                .checkTableResult("Date of Birth", "25 July,1990")
                .checkTableResult("Subjects", "Chemistry")
                .checkTableResult("Hobbies", "Reading, Sports")
                .checkTableResult("Picture", "test_img.jpg")
                .checkTableResult("Address", "Улица Пушкина, дом Колотушкина")
                .checkTableResult("State and City", "Uttar Pradesh Agra");

    }

    @Test
    void requiredFieldsTest() {

        registrationPage.openPage();

        registrationPage
                .setFirstName("Павел")
                .setLastName("Ивлев")
                .genderRadio()
                .setPhoneNumber("8800555353");

        registrationPage.submitButton();

        registrationPage.checkTableResult("Student Name", "Павел Ивлев")
                .checkTableResult("Mobile", "8800555353")
                .checkTableResult("Gender", "Male");

    }
    @Test
    void negativeScenario() {

        registrationPage
                .openPage();

        registrationPage.setFirstName("Паша")
                .setLastName("Техник")
                .genderRadio()
                .setPhoneNumber("8800555353")
                .invalidEmail("test.com")
                .submitButton()
                .resultsAbsent();
    }

}