package pages;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import com.mifmif.common.regex.Main;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainApplePage {

    public static final Faker faker = new Faker();

    public static final String searchQ = faker.chuckNorris().fact();

    private SelenideElement searchButton = $(".search-form"),
                            searchInputQ = $("input[name='s']");

    public void openMainPage() {
            open("https://appleinsider.ru/");
    }

    public MainApplePage searchInputClick() {
        $(searchButton).click();
        return this;
    }

    public MainApplePage searchInputQ() {
        $(searchInputQ).setValue(searchQ).pressEnter();
        return this;
    }

}
