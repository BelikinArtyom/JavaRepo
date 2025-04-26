package pages.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CalendarComponent {

    public void setDate (String month, String year, String day) {
        $("#dateOfBirthInput").clear();
        $(".react-datepicker__year-select").selectOptionByValue(String.valueOf(year));
        $(".react-datepicker__month-select").selectOption(month);
        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)")
                .findBy(text(String.valueOf(day)))
                .click();

    }

}
