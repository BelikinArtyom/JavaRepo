package tests;

import com.github.javafaker.Faker;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class TestData {

    public static final Faker faker = new Faker();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");

    public static final String firstName = faker.name().firstName();
    public static final String lastName = faker.name().lastName();
    public static final String email = faker.internet().emailAddress();
    public static final String adress = faker.address().fullAddress();

    private static final List<String> SUBJECTS = Arrays.asList(
            "Maths", "Physics", "Chemistry", "Computer Science",
            "English", "Economics", "Biology", "History", "Civics",
            "Social Studies", "Accounting", "Arts", "Hindi", "Commerce"
    );

    public List<String> getRandomSubjects(int count) {
        List<String> shuffled = new ArrayList<>(SUBJECTS);
        Collections.shuffle(shuffled);
        return shuffled.subList(0, count);
    }

    public static Map<String, List<String>> STATE_CITY_MAP = Map.of(
            "NCR", List.of("Delhi", "Gurgaon", "Noida"),
            "Uttar Pradesh", List.of("Agra", "Lucknow", "Merrut"),
            "Haryana", List.of("Karnal", "Panipat"),
            "Rajasthan", List.of("Jaipur", "Jaiselmer")
    );

}

