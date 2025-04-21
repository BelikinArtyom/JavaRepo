import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class DragNDrop {



    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "2560x1440";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void dragnDropactiontest() {

        open("https://the-internet.herokuapp.com/drag_and_drop");
       // actions().moveToElement($("#column-a")).clickAndHold().moveToElement($("#column-b")).release().perform(); Один из вариантов прохождения Drag'n'Drop
        $("#column-a").dragAndDropTo($("#column-b"));
        $("#column-b").shouldHave(Condition.text("A"));
    }


}
