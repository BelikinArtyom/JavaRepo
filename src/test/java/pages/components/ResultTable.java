package pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultTable {

    public final SelenideElement tableResult = $(".table-responsive");

    public void checkTableResult(String key, String value) {
        tableResult.$(byText(key))
                .parent()
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text(value));
    }
}
