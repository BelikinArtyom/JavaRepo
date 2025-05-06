package tests;

import com.github.javafaker.Faker;
import pages.components.ResultTable;

import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TestData {

    public String selectedStateAndCity;
    private String uploadedPictureName;

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

    public List<String> selectedHobbyLabels;
    public List<String> selectedHobbySelectors;

    private static final Map<String, String> hobbyMap = Map.of(
            "label[for='hobbies-checkbox-1']", "Sports",
            "label[for='hobbies-checkbox-2']", "Reading",
            "label[for='hobbies-checkbox-3']", "Music"
    );

    private ResultTable resultTable = new ResultTable();

    public List<String> getRandomSubjects(int count) {
        List<String> shuffled = new ArrayList<>(SUBJECTS);
        Collections.shuffle(shuffled);
        return shuffled.subList(0, count);
    }


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

    public void setUploadedPictureName(String pictureName) {
        this.uploadedPictureName = pictureName;
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

    public void setRandomHobbies() {
        Map<String, String> hobbyMap = Map.of(
                "label[for='hobbies-checkbox-1']", "Sports",
                "label[for='hobbies-checkbox-2']", "Reading",
                "label[for='hobbies-checkbox-3']", "Music"
        );

        List<String> allSelectors = new ArrayList<>(hobbyMap.keySet());
        Collections.shuffle(allSelectors);

        int count = 1 + new Random().nextInt(allSelectors.size()); // от 1 до 3

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


}


