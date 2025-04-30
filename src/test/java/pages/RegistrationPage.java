package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultTable;
import tests.TestBase;

import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;



public class  RegistrationPage extends TestBase {

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

    protected final Random random = new Random();

    private ElementsCollection genderOptions() {
        return $$("div.custom-radio input[name='gender'] + label");
    }

    public void selectRandomGender() {
        if (!genderOptions().isEmpty()) {
            genderOptions().get(random.nextInt(genderOptions().size())).click();
        }
    }


    public RegistrationPage openPage() {

        open("/automation-practice-form");
        return this;
    }

    public RegistrationPage invalidEmail (String email) {
        emailInput.setValue(email);
        return this;
    }

    public RegistrationPage setFirstName(String firstName) {

        firstNameInput.setValue(firstName);
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

    public RegistrationPage setLastName(String lastName) {

        lastNameInput.setValue(lastName);
        return this;
    }


    public RegistrationPage setEmail(String value) {

        emailInput.setValue(value);
        return this;
    }

    public RegistrationPage genderRadio() {

        genderRadio.click();
        return this;
    }

    public RegistrationPage setPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("\\d{7}")) {
            userPhone.setValue("+7812" + phoneNumber);
        } else {
            userPhone.setValue(phoneNumber);
        }
        return this;
    }

//    public RegistrationPage setPhoneNumber(String phoneNumber) {
//
//        userPhone.setValue(phoneNumber);
//        return this;
//    }

    public RegistrationPage setAdress(String value) {

        adress.setValue(value);
        return this;
    }

    public RegistrationPage setSubjects(String value) {
        subjectCont.click();
        subject.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage setHobbies(String String) {
        $("label[for='hobbies-checkbox-2']").click();
        $("label[for='hobbies-checkbox-1']").click();
        $("#hobbies-checkbox-2").shouldBe(selected);
        $("#hobbies-checkbox-1").shouldBe(selected);
        return this;
    }

    public RegistrationPage uploadPicture() {
        $("#uploadPicture").uploadFromClasspath("test_img.jpg");
        return this;
    }

    public void setDateOfBirth(String day, String month, String year) {
        new CalendarComponent().setDate(month, year, day);

    }

    public RegistrationPage checkTableResult(String key, String value) {
        new ResultTable().checkTableResult(key, value);
        return this;
    }

    public RegistrationPage submitButton() {
        submit.click();
        return this;
    }

    public RegistrationPage resultsAbsent() {
        noResults.shouldNotBe(visible);
        return this;
    }
}