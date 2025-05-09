package tests;

import org.junit.jupiter.api.Test;
import pages.textBoxPage;

public class textBoxTest extends TestBase {

    textBoxPage textBox = new textBoxPage();

    @Test
    void textboxTesting() {

        textBox.openPage();

        textBox
                .setName("Jack the Ripper")
                .setEmail("jack@ripper.com")
                .setAddress("Whitechapel, 69")
                .setPerAdress("Mental assylum")
                .formSubmit("")
                .textBoxResults();
    }

    public enum ShopAddresses {

        Izhevsk("г. Ижевск, ул. Карла Маркса, д. 300А"),
        SPB("г. Санкт-Петербург, ул. Марата, д. 53");

        public final String address;

        ShopAddresses(String address){
        this.address = address;

        }


    }
}
