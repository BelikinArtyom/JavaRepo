package tests;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.*;


public class NewTestsWybor extends TestBase  {


    @BeforeEach
    void setUp() {
        open("https://wybor-battery.com/");
    }


//    @CsvSource(value = {
//            "Selenide, https://ru.selenide.org",
//            "Junit5, https://junit.org"
//    })

    @ValueSource(strings = {"Аккумулятор тяговый", "CSB", "Мото аккумулятор"
    })
    @ParameterizedTest(name ="При вводе поискового запроса {0} отображаются карточки товара")
    @Tag("Smoke")
    @DisplayName("Отображение карточек товара после выполнения поиска")
    void smartPhoneSearchSuggests(String query) {
        $("#edit-search-api-views-fulltext").setValue(query).pressEnter();
        $$("#views-bootstrap-grid-1").shouldBe(CollectionCondition.sizeGreaterThan(0));


    }
}
