package tests;

import org.junit.jupiter.api.*;
import pages.RegistrationPage;
import java.io.File;


public class PageObjectsTestForm {

    RegistrationPage registrationPage = new RegistrationPage();

    File file = new File("src/test/resources/test_img.jpg");

    @Test
    void formTest1() {
        registrationPage.beforeAll();

        registrationPage.openPage()
                        .removeBanner();

        registrationPage
                .setFirstName("Паша")
                .setLastName("Техник")
                .setEmail("xanax@techique.com")
                .genderRadio("Male")
                .setPhoneNumber("8800555353")
                .setDateOfBirth("10", "December", "1999");


        registrationPage.setSubjects("Chemistry")
                .setHobbies("Sports, Reading")
                .uploadPicture("test_img.jpg")
                .setAdress("Улица Пушкина, дом Колотушкина")
                .setState("Uttar Pradesh")
                .setCity("Agra")
                .submitButton()
                .checkTableResult();

    }

        @Test
        void requiredFieldsTest() {

        registrationPage.beforeAll()
                        .openPage()
                        .removeBanner();

            registrationPage
                    .setFirstName("Паша")
                    .setLastName("Техник")
                    .genderRadio("Male")
                    .setPhoneNumber("8800555353");


            registrationPage
                    .submitButton()
                    .checkTableResult();
        }


        @Test
        void negativeScenario() {

            registrationPage.beforeAll()
                            .openPage()
                            .removeBanner();

            registrationPage.setFirstName("Паша")
                            .setLastName("Техник")
                            .genderRadio("Male")
                            .setPhoneNumber("8800555353")
                            .invalidEmail("test.com")
                            .submitButton()
                            .resultsAbsent();

        }



    }
