package tests;

import org.junit.jupiter.api.Test;
import pages.textBoxPage;

import static com.codeborne.selenide.Selenide.sleep;

public class textBoxTest {

    textBoxPage textBox = new textBoxPage();

    @Test
    void textboxTesting() {



        textBox.beforeAll()
                .openPage()
                .removeBanner();

        textBox
                .setName("Jack the Ripper")
                .setEmail("jack@ripper.com")
                .setAddress("Whitechapel, 69")
                .setPerAdress("Mental assylum")
                .formSubmit("")
                .textBoxResults();

        sleep(9000);

    }

}
