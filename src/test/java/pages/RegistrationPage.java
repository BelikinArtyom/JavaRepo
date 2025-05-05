package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.netty.util.internal.ThreadLocalRandom;
import pages.components.CalendarComponent;
import pages.components.ResultTable;
import tests.TestBase;
import tests.TestData;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class  RegistrationPage extends TestBase {

    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final TestData testDataHelper = new TestData();
    private String selectedGender;
    private String enteredPhone;
    private final Random random = new Random();
    private String selectedBirthDateFormatted;
    private List<String> selectedSubjects;
    private List<String> selectedHobbies;
    private String uploadedPictureName;
    private String selectedStateAndCity;

    private static final Map<String, List<String>> stateCityMap = Map.of(
            "NCR", List.of("Delhi", "Gurgaon", "Noida"),
            "Uttar Pradesh", List.of("Agra", "Lucknow", "Merrut"),
            "Haryana", List.of("Karnal", "Panipat"),
            "Rajasthan", List.of("Jaipur", "Jaiselmer")
    );

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


    private ElementsCollection genderOptions() {
        return $$("div.custom-radio input[name='gender'] + label");
    }

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

    public RegistrationPage setRandomSubjects(int count) {
        selectedSubjects = testDataHelper.getRandomSubjects(count);

        for (String subject : selectedSubjects) {
            subjectsInput.shouldBe(visible, enabled).click();
            subjectsInput.setValue(subject);
            $(".subjects-auto-complete__menu-list").shouldBe(visible);
            subjectsInput.pressEnter();
        }

        return this;
    }

    public RegistrationPage checkSubjectsInResult() {
        String joinedSubjects = String.join(", ", selectedSubjects);
        return checkTableResult("Subjects", joinedSubjects);
    }

    public RegistrationPage openPage() {

        open("/automation-practice-form");
        return this;
    }

    public RegistrationPage invalidEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public RegistrationPage setFirstName(String firstName) {

        firstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationPage setRandomStateAndCity() {
        Map<String, List<String>> stateCityMap = new HashMap<>();
        stateCityMap.put("NCR", Arrays.asList("Delhi", "Gurgaon", "Noida"));
        stateCityMap.put("Uttar Pradesh", Arrays.asList("Agra", "Lucknow", "Merrut"));
        stateCityMap.put("Haryana", Arrays.asList("Karnal", "Panipat"));
        stateCityMap.put("Rajasthan", Arrays.asList("Jaipur", "Jaiselmer"));

        List<String> states = new ArrayList<>(stateCityMap.keySet());
        String selectedState = states.get(new Random().nextInt(states.size()));
        List<String> cities = stateCityMap.get(selectedState);
        String selectedCity = cities.get(new Random().nextInt(cities.size()));

        $("#state").click();
        $(byText(selectedState)).click();

        $("#city").click();
        $(byText(selectedCity)).click();

        selectedStateAndCity = selectedState + " " + selectedCity;

        return this;
    }

    public RegistrationPage checkStateAndCityInResult() {
        new ResultTable().tableResult.shouldBe(Condition.visible);

        new ResultTable().checkTableResult("State and City", selectedStateAndCity);

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

    public RegistrationPage setSubjects(String value) {
        subjectCont.click();
        subject.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage setRandomHobbies() {
        Map<String, String> hobbyLabels = Map.of(
                "label[for='hobbies-checkbox-1']", "Sports",
                "label[for='hobbies-checkbox-2']", "Reading",
                "label[for='hobbies-checkbox-3']", "Music"
        );

        List<String> checkboxSelectors = new ArrayList<>(hobbyLabels.keySet());
        Collections.shuffle(checkboxSelectors);

        int count = 1 + new Random().nextInt(checkboxSelectors.size()); // от 1 до 3
        selectedHobbies = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String locator = checkboxSelectors.get(i);
            $(locator).click();
            selectedHobbies.add(hobbyLabels.get(locator));
        }

        return this;
    }

    public RegistrationPage checkHobbiesInResult() {
        String joinedHobbies = String.join(", ", selectedHobbies);
        return checkTableResult("Hobbies", joinedHobbies);
    }

    public RegistrationPage uploadRandomPicture() {
        List<String> availablePictures = Arrays.asList("test_img.jpg", "shilo.jpg", "tyler.jpg");
        uploadedPictureName = availablePictures.get(new Random().nextInt(availablePictures.size()));
        $("#uploadPicture").uploadFromClasspath(uploadedPictureName);
        return this;
    }

    public RegistrationPage checkPictureInResult() {
        return checkTableResult("Picture", uploadedPictureName);
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