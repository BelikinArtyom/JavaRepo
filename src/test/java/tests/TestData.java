package tests;


import com.codeborne.selenide.ElementsCollection;
import com.github.javafaker.Faker;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$$;


public class TestData {

    public static final Faker faker = new Faker();

    public static final String firstName = faker.name().firstName();
    public static final String lastName = faker.name().lastName();
    public static final String email = faker.internet().emailAddress();
    public static final String phoneNumber = faker.phoneNumber().cellPhone();

    protected Random random = new Random();

    protected void selectRandomGender() {
        ElementsCollection genderOptions = $$("div.custom-radio input[name='gender'] + label");
        if (!genderOptions.isEmpty()) {
            genderOptions.get(random.nextInt(genderOptions.size())).click();
        }




    }
}
