package pages;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SheetMainPage {

    public static final Faker faker = new Faker();
    public static final String searchQ = faker.chuckNorris().fact();

    private SelenideElement searchButton = $(".icon-search"),
                            searchInputQ = $(".search-form__input");

    public void openMainPage() {
        open("https://www.sheethappenspublishing.com/");
    }

    public SheetMainPage searchInputClick() {
        $(searchButton).click();
        return this;
    }

    public SheetMainPage searchInputQ() {
        $(searchInputQ).setValue(searchQ).pressEnter();
        return this;
    }
}
