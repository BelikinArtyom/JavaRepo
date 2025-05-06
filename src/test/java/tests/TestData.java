package tests;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TestData {

    public String selectedStateAndCity;
    private String uploadedPictureName;
    public String selectedBirthDateFormatted;
    public static final Faker faker = new Faker();
    public LocalDate randomBirthDate;


    public static final String firstName = faker.name().firstName();
    public static final String lastName = faker.name().lastName();
    public static final String email = faker.internet().emailAddress();
    public static final String adress = faker.address().fullAddress();
    public static final String gender = faker.options().option("Male", "Female", "Other");
    public static final String singleSubject = faker.options().option(
            "Maths", "Accounting", "Arts", "Social Studies", "Physics", "Chemistry",
            "Computer Science", "English", "Economics", "History", "Biology"
    );

    public List<String> selectedHobbyLabels;
    public List<String> selectedHobbySelectors;

    private static final Map<String, String> hobbyMap = Map.of(
            "label[for='hobbies-checkbox-1']", "Sports",
            "label[for='hobbies-checkbox-2']", "Reading",
            "label[for='hobbies-checkbox-3']", "Music"
    );


    public void setRandomStateAndCity() {
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
    }

    public void generateRandomPictureName() {
        List<String> availablePictures = Arrays.asList("test_img.jpg", "shilo.jpg", "tyler.jpg");
        uploadedPictureName = availablePictures.get(new Random().nextInt(availablePictures.size()));
    }

    public String getUploadedPictureName() {
        return uploadedPictureName;
    }

    public void generateRandomHobbies() {
        List<String> allSelectors = new ArrayList<>(hobbyMap.keySet());
        Collections.shuffle(allSelectors);

        int count = 1 + new Random().nextInt(allSelectors.size());

        selectedHobbySelectors = new ArrayList<>();
        selectedHobbyLabels = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String selector = allSelectors.get(i);
            selectedHobbySelectors.add(selector);
            selectedHobbyLabels.add(hobbyMap.get(selector));
        }
    }

    public String getHobbiesAsText() {
        return String.join(", ", selectedHobbyLabels);
    }

    public void generateRandomBirthDate() {
        LocalDate start = LocalDate.of(1980, 1, 1);  // Начало диапазона
        LocalDate end = LocalDate.now();  // Текущая дата
        long randomDay = ThreadLocalRandom.current().nextLong(start.toEpochDay(), end.toEpochDay());
        randomBirthDate = LocalDate.ofEpochDay(randomDay);

        // Форматируем дату в нужный формат
        selectedBirthDateFormatted = randomBirthDate.format(DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH));
    }

}




