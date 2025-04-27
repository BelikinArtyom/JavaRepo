package pages.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ResultTable {

    public void checkTableResult(String key, String value) {
            $(".table-responsive").shouldHave(text(value));
        }
}
