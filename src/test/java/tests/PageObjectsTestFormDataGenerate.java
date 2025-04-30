package tests;

import org.junit.jupiter.api.*;
import pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.sleep;
import static tests.TestData.*;
import static tests.practiceTest.selectedGender;

public class PageObjectsTestFormDataGenerate extends TestBase {


    RegistrationPage registrationPage = new RegistrationPage();;

    @Test
    void formTest1() {

        registrationPage.openPage();

        registrationPage
                .setFirstName(firstName)
                .setLastName(TestData.lastName)
                .setEmail(TestData.email)
                .selectRandomGender();

        registrationPage.setPhoneNumber(faker.number().digits(10));

        registrationPage.setRandomBirthDate(TestData.getRandomBirthDate());

        registrationPage
                .getRandomSubjects();

        registrationPage
                .setRandomHobbies();

        registrationPage
                .uploadPicture()
                .setAdress(TestData.adress)
                .selectRandomStateAndCity();

        registrationPage
                .submitButton();


        registrationPage
                .checkTableResult("Student Name", firstName + " " + lastName)
                .checkTableResult("Student Email", email);

        registrationPage.checkTableResult("Gender", selectedGender);

        registrationPage
                .checkTableResult("Mobile", phone);

        sleep (9000);
//                .checkTableResult("Date of Birth", "25 July,1990")
//                .checkTableResult("Subjects", "Chemistry")
//                .checkTableResult("Hobbies", "Reading, Sports")
//                .checkTableResult("Picture", "test_img.jpg")
//                .checkTableResult("Address", "Улица Пушкина, дом Колотушкина")
//                .checkTableResult("State and City", "Uttar Pradesh Agra");
    }

}