package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class RequiredFieldsTest {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void requiredFielstest () {

        registrationPage.beforeAll();

        registrationPage.openPage()
                .setFirstName("Паша")
                .setLastName("Техник")
                .genderRadio("Male")
                .setPhoneNumber("8800555353")
                .submitButton();

        registrationPage.checkRequiredFields("Label", "value");

    }

}
