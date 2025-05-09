package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import tests.data.addresses;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;



public class MuztorgAdressTests extends TestBase {


    @BeforeEach
    void setUp() {
        open("https://www.muztorg.ru/shops");
    }

    @EnumSource(addresses.class)
    @ParameterizedTest(name="При открытии страницы города {0} должен отобразиться адрес магазина")
    @DisplayName("Проверка отображения адреса магазина при выборе города")
    void brandNameTest(addresses shopAdress) {
        $$(".shops-grid a").find(text(shopAdress.name())).click();
        $(".shops-shop__address").shouldHave(text(shopAdress.shopAdress));

    }

}

