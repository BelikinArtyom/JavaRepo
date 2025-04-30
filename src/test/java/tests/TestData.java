package tests;

import com.github.javafaker.Faker;

public class TestData {

        public static final Faker faker = new Faker();
        public static final String firstName = faker.name().firstName();
        public static final String lastName = faker.name().lastName();
        public static final String email = faker.internet().emailAddress();
    }

