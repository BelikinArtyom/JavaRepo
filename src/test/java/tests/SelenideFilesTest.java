package tests;

import com.codeborne.selenide.Condition;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.impl.Html.text;
import static java.lang.Thread.sleep;

public class SelenideFilesTest extends TestBase {

    @Test
    void fileDownloadTest() throws Exception {

        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloaded = $(".react-blob-header-edit-and-raw-actions [href*='https://github.com/junit-team/junit5/raw/refs/heads/main/README.md']").download();

        try(InputStream is = new FileInputStream(downloaded)) {

            byte[] data = is.readAllBytes();
            String dataAsString = new String(data, StandardCharsets.UTF_8);
            Assertions.assertTrue(dataAsString.contains("Contributions to JUnit 5 are both welcomed and appreciated."));
        }
            String dataAsString = FileUtils.readFileToString(downloaded, StandardCharsets.UTF_8);
      }


      @Test
      void fileUploadTest() throws Exception {

          open("https://practice.expandtesting.com/upload");
          $("input[type='file']").uploadFromClasspath("tyler.jpg");
          $("#fileSubmit").click();
          $("#uploaded-files").shouldHave(Condition.text("tyler.jpg"));
//          sleep(90000);




      }

    }
