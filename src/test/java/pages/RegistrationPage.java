package pages;

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
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class  RegistrationPage extends TestBase {

    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final TestData testDataHelper = new TestData();
    private String selectedGender;
    private String enteredPhone;
    private String selectedBirthDate;
    private final Random random = new Random();
    private String selectedBirthDateFormatted;


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
            dateOfBirthInput = $("#dateOfBirthInput"),
            monthSelect = $(".react-datepicker__month-select"),
            yearSelect = $(".react-datepicker__year-select"),
            calendarDayContainer = $(".react-datepicker");

//    protected final Random random = new Random();

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

//    public String setRandomBirthDate() {
//        // Открываем date picker
//        dateOfBirthInput.click();
//
//        // Генерируем случайные значения
//        int year = 2005 + random.nextInt(6); // 2005-2010
//        int monthIndex = random.nextInt(12); // 0-11
//        int dayIndex = random.nextInt(28);   // 0-27 (дни 1-28)
//
//        // Выбираем дату
//        $(".react-datepicker__year-select").selectOptionContainingText(String.valueOf(year));
//        $(".react-datepicker__month-select").selectOption(monthIndex);
//        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)").get(dayIndex).click();
//
//        // Получаем название выбранного месяца
//        String monthName = $(".react-datepicker__month-select").getSelectedText();
//
//        // Форматируем дату (например "22 May,2025")
//        this.selectedBirthDate = String.format("%d %s,%d", dayIndex + 1, monthName, year);
//        return this.selectedBirthDate;
//    }


//    public void setRandomBirthDate(String randomBirthDate) {
//        this.selectedBirthDate = randomBirthDate;
//        dateOfBirthInput.click();
//
//        $(".react-datepicker__year-select").selectOptionContainingText(
//                String.valueOf(2005 + random.nextInt(2010 - 2005 + 1))
//        );
//        $(".react-datepicker__month-select").selectOption(
//                random.nextInt(12)
//        );
//        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)")
//                .get(random.nextInt(28))
//                .click();
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
        if (phoneNumber.matches("\\d{6}")) {
            enteredPhone = "7812" + phoneNumber;
            userPhone.setValue(enteredPhone);
        }
        return this;
    }

    public String getEnteredPhone (String enteredPhone) {
        return enteredPhone;
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
        $$("div[id*='react-select'][tabindex='-1']").get(random().nextInt(2)).click();
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