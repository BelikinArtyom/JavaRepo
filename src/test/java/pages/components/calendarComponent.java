package pages.components;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class calendarComponent {

    public void setDate (String day, String month, String year) {
        $("#dateOfBirthInput").clear();
        $(".react-datepicker__year-select").selectOptionByValue("1990");
        $(".react-datepicker__month-select").selectOptionByValue("6");
        $$(".react-datepicker__day").findBy(Condition.text("24")).click();

    }

}
