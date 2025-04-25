package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import pages.components.textBoxResults;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class textBoxPage {

    private SelenideElement
            nameInput = $("#userName"),
            emailInput = $("#userEmail"),
            address = $("#currentAddress"),
            perAdress = $("#permanentAddress"),
            submit = $("#submit"),
            results = $("#output");


    public textBoxPage beforeAll() {
        Configuration.browserSize = "2560x1440";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        return this;
    }

    public textBoxPage openPage() {

        open("/text-box");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public textBoxPage setName(String value) {

        nameInput.setValue("Jack the Ripper");
        return this;
    }

    public textBoxPage setEmail(String value) {

        emailInput.setValue("jack@ripper.com");
        return this;
    }

    public textBoxPage setAddress(String value) {
        address.setValue("Whitechapel, 69");
        return this;
    }
    public textBoxPage setPerAdress(String value) {
        perAdress.setValue("Mental assylum");
        return this;
    }
    public textBoxPage formSubmit(String value) {
        submit.click();
        return this;
    }

    public void textBoxResults(){
       new textBoxResults().reqResults();

    }


}
