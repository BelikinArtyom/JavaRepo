import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class Contributor {


    @BeforeAll
    static void setUp() {
        System.setProperty("selenide.holdBrowserOpen", "true");
        Configuration.browserSize = "2560x1440";
    }

    @Test
    void andreiSolntsevShouldBeTheFirstContributor() {

        open("https://github.com/selenide/selenide");
        $("div.Layout-sidebar").$(Selectors.byText("Contributors"))
                .closest("h2").sibling(0).$$("li").first().hover();
        $$(".Popover-message").first().shouldHave(text("Andrei Solntsev"));




    }
}