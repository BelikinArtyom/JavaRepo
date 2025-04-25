package pages.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class textBoxResults {

    public void reqResults() {
        $("#output").shouldHave(text("Name"), text("Jack the Ripper"));
        $("#output").shouldHave(text("Email"), text("jack@ripper.com"));
        $("#output").shouldHave(text("Current Address"), text("Whitechapel, 69"));
        $("#output").shouldHave(text("Permananet Address"), text("Mental assylum"));

    }

}
