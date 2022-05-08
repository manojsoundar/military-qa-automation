package drupal.ui.pages.media;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddInstagramPage extends AddMediaBasePage {

    private static final String URL_REGEX = "/media/add/instagram";
    private static final SelenideElement INSTAGRAM_PAGE_H_1_TITLE = $("#block-pagetitle h1").as("Instagram page title");

    public AddInstagramPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Instagram Page not loaded..");
        assertEquals(INSTAGRAM_PAGE_H_1_TITLE.getText()
                                             .trim(), "Add Instagram", "Page title not loaded..");
        log.info("Instagram Page loaded..");
    }

}
