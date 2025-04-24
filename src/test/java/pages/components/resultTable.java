package pages.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class resultTable {

    public void results() {

        $("table").shouldHave(text("Student Name"), text("Паша Техник"));
        $("table").shouldHave(text("Student Email"), text("xanax@techique.com"));
        $("table").shouldHave(text("Gender"), text("Male"));
        $("table").shouldHave(text("Mobile"), text("8800555353"));
        $("table").shouldHave(text("Date of Birth"), text("24 July,1990"));
        $("table").shouldHave(text("Subjects"), text("Chemistry"));
        $("table").shouldHave(text("Hobbies"), text("Паша Техник"));
        $("table").shouldHave(text("Picture"), text("test_img.jpg"));
        $("table").shouldHave(text("Address"), text("Улица Пушкина, дом Колотушкина"));
        $("table").shouldHave(text("State and City"), text("Uttar Pradesh Agra"));
    }



}
