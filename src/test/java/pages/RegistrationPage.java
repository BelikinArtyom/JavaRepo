package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import pages.components.calendarComponent;
import pages.components.resultTable;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;


public class RegistrationPage {

    final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderRadio = $("label[for='gender-radio-1']"),
            userPhone = $("#userNumber"),
            subject = $("#subjectsInput"),
            subjectCont = $("#subjectsContainer"),
            adress = $("#currentAddress"),
            submit = $("#submit"),
            noResults = $(".table-responsive");



    public RegistrationPage beforeAll() {
        Configuration.browserSize = "2560x1440";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        return this;
    }

    public RegistrationPage openPage() {

        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;

    }

    public RegistrationPage invalidEmail (String value) {
        emailInput.setValue(value);
        return this;

    }

    public RegistrationPage setFirstName(String value) {

        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setCity(String value) {

        $("#city").click();
        $("#react-select-4-input").setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage setState(String value) {
        $("#state").click();
        $("#react-select-3-input").setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage setLastName(String value) {

        lastNameInput.setValue(value);
        return this;
    }


    public RegistrationPage setEmail(String value) {

        emailInput.setValue(value);
        return this;
    }

    public RegistrationPage genderRadio(String value) {

        genderRadio.click();
        return this;
    }

    public RegistrationPage setPhoneNumber(String value) {

        userPhone.setValue(value);
        return this;
    }

    public RegistrationPage setAdress(String value) {

        adress.setValue(value);
        return this;
    }

    public RegistrationPage setSubjects(String value) {
        subjectCont.click();
        subject.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage setHobbies(String value) {
        $("label[for='hobbies-checkbox-2']").click();
        $("label[for='hobbies-checkbox-1']").click();
        $("#hobbies-checkbox-2").shouldBe(selected);
        $("#hobbies-checkbox-1").shouldBe(selected);
        return this;
    }

    public RegistrationPage uploadPicture(String value) {
        $("#uploadPicture").uploadFromClasspath("test_img.jpg");
        return this;
    }

    public void setDateOfBirth(String day, String month, String year) {
        new calendarComponent().setDate(day, month, year);

    }

    public RegistrationPage submitButton() {
        submit.click();
        return this;
    }

    public RegistrationPage resultsAbsent() {
        noResults.shouldNotBe(visible);
        return this;

    }

    public void  checkTableResult(){
        new resultTable();
    }

    public RegistrationPage removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;

    }
}