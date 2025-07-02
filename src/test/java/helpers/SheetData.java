package helpers;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SheetData {


    public final SelenideElement searchQOnPage = $("h1");

    public void checkResult() {
        searchQOnPage.shouldHave(text(searchQOnPage.getText()));
    }

}
