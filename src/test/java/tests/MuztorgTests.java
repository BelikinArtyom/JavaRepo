package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.devtools.v133.autofill.model.Address;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MuztorgTests  extends TestBase {

    @BeforeEach
    void setUp() {
        open("https://www.muztorg.ru/");

    }

    @CsvSource(value = {
            "ibanez rg421, IBANEZ",
            "jackson js22, JACKSON",
            "focusrite scarlett, FOCUSRITE"

    })
    @ParameterizedTest(name = "При указании модели {0}, в списке фильтров должен отображаться бренд {1}")
    @Tag("Regression")
    @DisplayName("При указании конкретной модели товара, в фильтрах бренда должно отображаться название бренда")
    void instrumentSearchTest(String brandQuery, String brandName) {
        $("#search-product-input").setValue(brandQuery).pressEnter();
        $("#filters-form").shouldBe(visible).shouldHave(text(brandName));

    }

}

//    static Stream<Arguments> brandNameTest(){
//
//        return Stream.of(
//                Arguments.of(),
//                Arguments.of()
//
//        );
//    }
