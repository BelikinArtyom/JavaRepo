import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class testGit {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "2560x1440";
        System.setProperty("selenide.holdBrowserOpen", "true");
        // Configuration.pageLoadStrategy = "eager";
    }


    @Test
    void hoverTest() {

        open("https://github.com/");
        $$(".HeaderMenu-nav").findBy(Condition.text("Soultions"))
                .hover();


    }


}
