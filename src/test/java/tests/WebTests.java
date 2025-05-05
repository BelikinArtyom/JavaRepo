package tests;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class WebTests {

    @BeforeEach
    void setUp() {
        open("https://duckduckgo.com/");
    }


//    @CsvSource (value = {
//            "Selenide, https://ru.selenide.org",
//            "Junit5, https://junit.org"
//    })

    @CsvFileSource(resources = "/testData/searchResults.csv")
    @ParameterizedTest (name ="Наличие поисковой выдачи по квери {0} ссылки {1}")
    @Tag("BLOCKER")
    @DisplayName("Наличие корректного URL в поисковой выдаче")
    void searchResultsShouldHaveURL(String searchQ, String expectedURL)  {
        $("#searchbox_input").setValue(searchQ).pressEnter();
        $("[data-testid='mainline'] li[data-layout='organic']").shouldHave(text(expectedURL));

        }

//    @Test
//    @Tag("BLOCKER")
//    @DisplayName("Наличие корректного URL в поисковой выдаче")
//    void searchResultsShouldHaveURL1()  {
//        $("#searchbox_input").setValue("junit").pressEnter();
//        $$("[data-testid='mainline'] li[data-layout='organic']").shouldBe(CollectionCondition.sizeGreaterThan(0));
//        $("[data-testid='mainline'] li[data-layout='organic']").shouldHave(text("https://selenide.org/"));
//    }

//    @Test
//    @Tag("BLOCKER")
//    @DisplayName("Наличие поисковой выдачи по квери Junit")
//    void successfulDuckJunitSearchTest() {
//        $("#searchbox_input").setValue("junit").pressEnter();
//        $$("[data-testid='mainline'] li[data-layout='organic']").shouldBe(CollectionCondition.sizeGreaterThan(0));
//    }



//    @Test
//    @Tag("BLOCKER")
//    @DisplayName("Отображение поисковой выдачи в картинках")
//    void successfulPhotoSearchTest() {
//        $("#searchbox_input").setValue("selenide").pressEnter();
//        $("#react-duckbar").shouldHave(Condition.text("Изображения")).click();
//
//        }
    }