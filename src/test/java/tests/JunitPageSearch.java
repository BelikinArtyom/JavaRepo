package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class JunitPageSearch {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "2560x1440";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void junitsearch (){

        open("https://github.com/selenide/selenide");
        $("#wiki-tab").click();
        $(".js-wiki-more-pages-link").click();
        $$(".js-wiki-sidebar-toggle-display").last().shouldHave(text("SoftAssertions"));
        $(byText("SoftAssertions")).click();
        $(".markdown-body").$(Selectors.byText("@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}"));
    }

}
