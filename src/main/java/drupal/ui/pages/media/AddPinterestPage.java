package drupal.ui.pages.media;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddPinterestPage extends AddMediaBasePage {

    private static final String URL_REGEX = "/media/add/pinterest";
    private static final SelenideElement PINTEREST_PAGE_H_1_TITLE = $("#block-pagetitle h1").as("Pinterest page title");

    public AddPinterestPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Pinterest Page not loaded.");
        assertEquals(PINTEREST_PAGE_H_1_TITLE.should(visible, enabled)
                                             .getText()
                                             .trim(), "Add Pinterest", "Page title not loaded..");
        log.info("Pinterest Page loaded.");
    }

}
