
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class Task5 {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "2560x1440";
        Configuration.pageLoadStrategy = "eager";
    }


    @Test
    void enterpriseHover () {

        open("https://github.com/");
        $(".HeaderMenu-nav").$(byText("Solutions")).hover();
        $$("a.HeaderMenu-dropdown-link").findBy(text("Enterprises")).click();
        $("#hero-section-brand-heading").shouldHave(text("The AI-powered developer platform"));

    }


}
