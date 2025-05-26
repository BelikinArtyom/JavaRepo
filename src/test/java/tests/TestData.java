package tests;

import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TestData {

    public String selectedStateAndCity;
    private String uploadedPictureName;
    public String selectedBirthDateFormatted;
    public static final Faker faker = new Faker();

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
        Faker faker = new Faker();
        String selectedState;
        String selectedCity;

        selectedState = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");

        switch (selectedState) {
            case "NCR":
                selectedCity = faker.options().option("Delhi", "Gurgaon", "Noida");
                break;
            case "Uttar Pradesh":
                selectedCity = faker.options().option("Agra", "Lucknow", "Merrut");
                break;
            case "Haryana":
                selectedCity = faker.options().option("Karnal", "Panipat");
                break;
            case "Rajasthan":
                selectedCity = faker.options().option("Jaipur", "Jaiselmer");
                break;
            default:
                throw new IllegalStateException("Unexpected state: " + selectedState);
        }

        $("#state").click();
        $(byText(selectedState)).click();

        $("#city").click();
        $(byText(selectedCity)).click();

        selectedStateAndCity = selectedState + " " + selectedCity;
    }

    public void generateRandomPictureName() {
        uploadedPictureName = faker.options().option("test_img.jpg", "shilo.jpg", "tyler.jpg");
    }

    public String getUploadedPictureName() {
        return uploadedPictureName;
    }

    public void generateRandomHobbies() {
        String[] allSelectors = hobbyMap.keySet().toArray(new String[0]);

        int count = faker.number().numberBetween(1, allSelectors.length + 1);

        Set<String> uniqueSelectors = new LinkedHashSet<>();
        while (uniqueSelectors.size() < count) {
            uniqueSelectors.add(faker.options().option(allSelectors));
        }

        selectedHobbySelectors = new ArrayList<>(uniqueSelectors);
        selectedHobbyLabels = selectedHobbySelectors.stream()
                .map(hobbyMap::get)
                .collect(Collectors.toList());
    }

    public String getHobbiesAsText() {
        return String.join(", ", selectedHobbyLabels);
    }
}




