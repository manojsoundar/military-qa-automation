package drupal.ui.pages.media;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddFacebookPage extends AddMediaBasePage {

    private static final String URL_REGEX = "/media/add/facebook";
    private static final SelenideElement FACEBOOK_PAGE_H_1_TITLE = $("#block-pagetitle h1").as("Facebook page title");

    public AddFacebookPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Facebook Page not loaded.");
        assertEquals(FACEBOOK_PAGE_H_1_TITLE.should(visible, enabled)
                                            .getText()
                                            .trim(), "Add Facebook", "Page title not loaded..");
        log.info("Facebook Page loaded.");
    }

}
