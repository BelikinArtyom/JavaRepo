package pages.components;

import com.github.javafaker.Faker;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CalendarComponent {

    Faker faker = new Faker();
    public String setFakerDate() {
        String day = String.format("%02d", faker.number().numberBetween(1, 28));
        String month = faker.options().option("January", "February", "March",
                "April", "May", "June", "July",
                "August", "September", "October", "November", "December");
        String year = String.valueOf(faker.number().numberBetween(1990, 2023));

        $("#dateOfBirthInput").click();

        // ВАЖНО: сначала выбираем год, потом месяц (правильные селекторы!)
        $(".react-datepicker__year-select").selectOptionByValue(year);
        $(".react-datepicker__month-select").selectOption(month);

        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)")
                .findBy(text(String.valueOf(Integer.parseInt(day))))
                .click();

        return String.format("%s %s,%s", Integer.parseInt(day), month, year);
    }
}
