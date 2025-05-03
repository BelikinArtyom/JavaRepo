package tests;


import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;


public class TestData {

    public static final Faker faker = new Faker();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");

    public static final String firstName = faker.name().firstName();
    public static final String lastName = faker.name().lastName();
    public static final String email = faker.internet().emailAddress();
    public static final String adress = faker.address().fullAddress();
    public static final String phone = faker.phoneNumber().phoneNumber();


    protected static Random random = new Random();

    private static final List<String> SUBJECTS = Arrays.asList(
            "Maths", "Physics", "Chemistry", "Computer Science",
            "English", "Economics", "Biology", "History", "Civics",
            "Social Studies", "Accounting", "Arts", "Hindi", "Commerce"
    );

    public List<String> getRandomSubjects(int count) {
        Collections.shuffle(SUBJECTS);
        return SUBJECTS.subList(0, 1);
    }

    public static String getRandomBirthDate() {
        int minYear = 2009;
        int maxYear = LocalDate.now().getYear();
        int year = minYear + random.nextInt(maxYear - minYear + 1);
        int month = 1 + random.nextInt(12);
        LocalDate date = LocalDate.of(year, month, 1);
        int day = 1 + random.nextInt(date.lengthOfMonth() - 1);
        return LocalDate.of(year, month, day).format(formatter);
    }

//    protected void selectRandomOption(SelenideElement dropdown, String optionsLocator) {
//        dropdown.click();
//        List<SelenideElement> options = $$(optionsLocator)
//                .filter(visible);
//        if (!options.isEmpty()) {
//            options.get(random.nextInt(options.size())).click();
//        }
    }

