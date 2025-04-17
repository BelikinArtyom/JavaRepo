import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;

public class Task5 {

    @BeforeAll
    static void setUp() {
      //  Configuration.browserSize = "2560x1440";
      //  Configuration.pageLoadStrategy = "eager";
      //  System.setProperty("selenide.holdBrowserOpen", "true");
    }


    @Test
    void enterpriseHover () {

        open("https://github.com/");
        $(".HeaderMenu-nav").$(byText("Solutions")).hover();
        $$("a.HeaderMenu-dropdown-link").findBy(text("Enterprises")).click();
        $("#hero-section-brand-heading").shouldHave(text("The AI-powered developer platform"));





      //  sleep(6000000);








    }


}
