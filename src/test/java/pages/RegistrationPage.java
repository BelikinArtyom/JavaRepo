package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.components.calendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationPage {

    private SelenideElement firstNameInput = $("#firstName"),
                            lastNameInput = $("#lastName"),
                            emailInput = $("#userEmail"),
                            genderRadio = $("label[for='gender-radio-1']"),
                            userPhone = $("#userNumber");

    public RegistrationPage openPage () {

        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;

    }


    public RegistrationPage setFirstName(String value) {

        firstNameInput.setValue("Паша");
        return this;
    }
    public RegistrationPage setLastName(String value) {

        lastNameInput.setValue("Техник");
        return this;
    }


    public RegistrationPage setEmail(String value) {

        emailInput.setValue("xanax@techique.com");
        return this;
    }

    public RegistrationPage genderRadio (String value) {

        genderRadio.click();
        return this;
    }

    public RegistrationPage setPhoneNumber (String value) {

        userPhone.setValue(value);
        return this;
    }

    public void setDateOfBirth(String day, String month, String year) {
        new calendarComponent().setDate(day, month, year);

    }

    public RegistrationPage tableCheckResult(String key, String value) {
        $("table").$(byText(key)).parent().shouldHave(text(value));
        return this;
    }


}
