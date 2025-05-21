package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultTable;
import tests.TestBase;
import tests.TestData;
import java.util.*;

import static com.codeborne.selenide.Selenide.*;

public class  RegistrationPage extends TestBase {

    private final SelenideElement subjectsInput = $("#subjectsInput");
    private String enteredPhone;
    public String selectedBirthDateFormatted;
    public List<String> selectedSubjects;
    private ResultTable resultTable = new ResultTable();
    private TestData testData;
    CalendarComponent calendar = new CalendarComponent();


    final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            userPhone = $("#userNumber"),
            adress = $("#currentAddress"),
            submit = $("#submit");


    public RegistrationPage setRandomBirthDateWithFaker() {
        selectedBirthDateFormatted = calendar.setFakerDate();
        return this;
    }

    public RegistrationPage checkBirthDateInResult(ResultTable resultTable) {
        if (selectedBirthDateFormatted == null) {
            throw new IllegalStateException("Selected birth date is null");
        }
        resultTable.checkTableResult("Date of Birth", selectedBirthDateFormatted);
        return this;
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