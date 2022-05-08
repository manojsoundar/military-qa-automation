package drupal.ui.pages.media;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddTwitterPage extends AddMediaBasePage {

    private static final String URL_REGEX = "/media/add/twitter";
    private static final SelenideElement TWITTER_PAGE_H_1_TITLE = $("#block-pagetitle h1").as("Twitter page title");

    public AddTwitterPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Twitter Page not loaded.");
        assertTrue(TWITTER_PAGE_H_1_TITLE.should(exist, appear)
                                         .has(text("Add Twitter")), "Page title not loaded..");
        log.info("Twitter Page loaded.");
    }

}
