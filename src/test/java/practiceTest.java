import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;

public class practiceTest {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "2560x1440";
        System.setProperty("selenide.holdBrowserOpen", "true");
        // Configuration.pageLoadStrategy = "eager";
    }


    @Test
    void closingButton () {

        open("https://www.otpbank.ru/retail/bank-services/");
        $(".cookies-notification").shouldHave(Condition.text("Закрыть"))
              .click();

        sleep(8999999);


    }

}

