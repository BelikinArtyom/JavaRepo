package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
//import io.netty.util.internal.ThreadLocalRandom;
import pages.components.ResultTable;
import tests.TestBase;
import tests.TestData;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class  RegistrationPage extends TestBase {

    private final SelenideElement subjectsInput = $("#subjectsInput");
    private String enteredPhone;
    private String selectedBirthDateFormatted;
    public List<String> selectedSubjects;
    private ResultTable resultTable = new ResultTable();
    private SelenideElement tableResult = $(".table-responsive");
    private TestData testData;


    final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            userPhone = $("#userNumber"),
            adress = $("#currentAddress"),
            submit = $("#submit"),
            dateOfBirthInput = $("#dateOfBirthInput");


    public RegistrationPage setRandomBirthDate() {

        LocalDate start = LocalDate.of(1980, 1, 1);
        LocalDate end = LocalDate.now();
        long randomDay = ThreadLocalRandom.current().nextLong(start.toEpochDay(), end.toEpochDay());
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

        dateOfBirthInput.click();

        $(".react-datepicker__month-select").selectOption(randomDate.getMonth().getValue() - 1); // 0-based
        $(".react-datepicker__year-select").selectOption(String.valueOf(randomDate.getYear()));

        String dayText = String.valueOf(randomDate.getDayOfMonth());
        $(".react-datepicker").$(byText(dayText)).click();

        selectedBirthDateFormatted = randomDate.format(DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH));

        return this;
    }

    public void checkBirthDateInResult() {
        new ResultTable().checkTableResult("Date of Birth", selectedBirthDateFormatted);
    }

    public RegistrationPage selectGender(String gender) {
        $$("#genterWrapper .custom-control").findBy(Condition.text(gender)).click();
        return this;
    }

    public RegistrationPage selectSubjects(List<String> subjects) {
        this.selectedSubjects = subjects;
        for (String subject : subjects) {
            subjectsInput.setValue(subject).pressEnter();
        }
        return this;
    }

    public RegistrationPage checkSubjectsInResult() {
        String expectedSubjects = String.join(", ", selectedSubjects);
        resultTable.checkTableResult("Subjects", expectedSubjects);
        return this;
    }

    public RegistrationPage openPage() {

        open("/automation-practice-form");
        return this;
    }

    public RegistrationPage setFirstName(String firstName) {

        firstNameInput.setValue(firstName);
        return this;
    }


    public void checkStateAndCityInResult(String selectedStateAndCity) {
        resultTable.checkTableResult("State and City", selectedStateAndCity);
    }

    public RegistrationPage setLastName(String lastName) {

        lastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationPage setEmail(String value) {

        emailInput.setValue(value);
        return this;
    }

    public RegistrationPage setPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("\\d{6}")) {
            enteredPhone = "7812" + phoneNumber;
            userPhone.setValue(enteredPhone);
        }
        return this;
    }

    public String getEnteredPhone() {
        return enteredPhone;

    }

    public RegistrationPage setAdress(String fullAddress) {
        adress.setValue(fullAddress);
        return this;
    }

    public RegistrationPage selectHobbies(List<String> hobbySelectors) {
        for (String selector : hobbySelectors) {
            $(selector).click();
        }
        return this;
    }

    public RegistrationPage setTestData(TestData data) {
        this.testData = data;
        return this;
    }

    public void checkHobbiesInResult() {
        if (testData == null || testData.selectedHobbyLabels == null) {
            throw new IllegalStateException("TestData или хобби не инициализированы.");
        }
        resultTable.checkTableResult("Hobbies", testData.getHobbiesAsText());
    }

    public RegistrationPage uploadPicture(String pictureFileName) {
        $("#uploadPicture").uploadFromClasspath(pictureFileName);
        return this;
    }


    public RegistrationPage checkTableResult(String key, String value) {
        new ResultTable().checkTableResult(key, value);
        return this;
    }

    public RegistrationPage submitButton() {
        submit.click();
        return this;
    }

}