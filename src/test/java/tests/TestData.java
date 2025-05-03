package tests;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;



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
        List<String> shuffled = new ArrayList<>(SUBJECTS);
        Collections.shuffle(shuffled);
        return shuffled.subList(0, count);
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

}

