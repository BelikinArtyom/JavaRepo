package tests;


/*
Фильтр принимает только положительные значения
* Зайти на https://trendrealty.ru/objects/list
* Открыть расширенный фильтр (Кнопка "Все фильтры")
* Раскрыть фильтр "Этаж"
* Инпут "Этаж с" принимает только положительные значения
 */


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.WebDriverRunner;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.*;


public class TAtests {



        @Test
        public void filterTest () {

            open("https://trendrealty.ru/objects/list");
            Cookie ck = new Cookie("selected_city", "58c665588b6aa52311afa01b");
            WebDriverRunner.getWebDriver().manage().addCookie(ck);


            open("https://trendrealty.ru/objects/list");
            $(".filter__open-extend").shouldHave(text("Все фильтры")).click();
            $("[name='floor']").closest(".field__element").click();

            $("[name=from]").closest(".field-wrapper").click();
            $(Selectors.byAttribute("placeholder","Этаж c"))
                    .setValue("-5")
                    .shouldHave(value("5"));

//            System.out.println(ck);

        }
}
