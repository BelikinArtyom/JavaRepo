package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultTable;
import tests.TestBase;
import tests.TestData;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static tests.TestData.faker;


public class  RegistrationPage extends TestBase {

    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final TestData testDataHelper = new TestData();
    private String selectedGender;



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
            noResults = $(".table-responsive"),
            dateOfBirthInput = $("#dateOfBirthInput");

    protected final Random random = new Random();

    private ElementsCollection genderOptions() {
        return $$("div.custom-radio input[name='gender'] + label");
    }

    public void setRandomBirthDate(String randomBirthDate) {
        dateOfBirthInput.click();

        $(".react-datepicker__year-select").selectOptionContainingText(
                String.valueOf(2005 + random.nextInt(2010 - 2005 + 1))
        );
        $(".react-datepicker__month-select").selectOption(
                random.nextInt(12)
        );
        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)")
                .get(random.nextInt(28))
                .click();
    }

//    public void selectRandomGender() {
//        if (!genderOptions().isEmpty()) {
//            genderOptions().get(random.nextInt(genderOptions().size())).click();
//            String gender = $("input[name='gender']:checked + label").getText();
//        }
//    }

    public RegistrationPage selectRandomGender() {
        if (!genderOptions().isEmpty()) {
            SelenideElement randomOption = genderOptions()
                    .get(random.nextInt(genderOptions().size()));
            this.selectedGender = randomOption.getText(); // Сохраняем текст
            randomOption.click();
        }
        return this;
    }

    public String getSelectedGender() {
        return this.selectedGender;
    }

//    private ElementsCollection genderOptions() {
//        return $$("input[name='gender'] + label");
//    }

    public void getRandomSubjects() {
        testDataHelper.getRandomSubjects(2).forEach(subject -> {
            subjectsInput.shouldBe(visible, enabled).click();
            subjectsInput.setValue(subject);
            $(".subjects-auto-complete__menu-list").shouldBe(visible);
            subjectsInput.pressEnter();
        });
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
        if (phoneNumber.matches("\\d{10}")) {
            userPhone.setValue("7812" + phoneNumber);
        } else {
            userPhone.setValue(phoneNumber);
        }
        return this;
    }

    public RegistrationPage setAdress(String fullAddress) {

        adress.setValue(fullAddress);
        return this;
    }

    public RegistrationPage setSubjects(String value) {
        subjectCont.click();
        subject.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage setRandomHobbies() {

        List<String> hobbyCheckboxLocators = Arrays.asList(
                "label[for='hobbies-checkbox-1']",
                "label[for='hobbies-checkbox-2']",
                "label[for='hobbies-checkbox-3']"
        );
        Collections.shuffle(hobbyCheckboxLocators);
        int count = 1 + new Random().nextInt(hobbyCheckboxLocators.size());
        for (int i = 0; i < count; i++) {
            String locator = hobbyCheckboxLocators.get(i);
            $(locator).click();
        }
        return this;
    }

    public RegistrationPage uploadPicture() {
        $("#uploadPicture").uploadFromClasspath("test_img.jpg");
        return this;
    }

    public void setDateOfBirth(String day, String month, String year) {
        new CalendarComponent().setDate(month, year, day);

    }

    public void selectRandomStateAndCity() {
        // Открываем выбор штата
        $("#state").click();
        // Выбираем случайный штат из видимых вариантов
        $$("div[id*='react-select'][tabindex='-1']").get(random().nextInt(4)).click();

        // Ждем активации поля города
        $("#city").shouldBe(enabled).click();
        // Выбираем случайный город из видимых вариантов
        $$("div[id*='react-select'][tabindex='-1']").get(random().nextInt(3)).click();
    }

    private static Random random() {
        return new Random();
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