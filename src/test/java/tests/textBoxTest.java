package tests;

import org.junit.jupiter.api.Test;
import pages.textBoxPage;


public class textBoxTest {

    textBoxPage textBox = new textBoxPage();

    @Test
    void textboxTesting() {

        TestBase.setUp();

        textBox
                .openPage()
                .removeBanner();

        textBox
                .setName("Jack the Ripper")
                .setEmail("jack@ripper.com")
                .setAddress("Whitechapel, 69")
                .setPerAdress("Mental assylum")
                .formSubmit("")
                .textBoxResults();
    }

}
