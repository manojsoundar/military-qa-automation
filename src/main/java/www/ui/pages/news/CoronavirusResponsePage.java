package www.ui.pages.news;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class CoronavirusResponsePage extends GlobalHeaderComponent {

    private static final String URL = "/us-military-coronavirus-response";
    private static final SelenideElement CORONAVIRUS_RESPONSE_H_1_TITLE = $("div.block.block--page--title h1 span").as("CoronavirusResponse Page Para Title");

    public CoronavirusResponsePage() {
        log.info("Verify if CoronavirusResponse Page Para title is displayed correctly.");
        assertTrue(verifyURLLoaded(URL), "CoronavirusResponse Page not loaded");
        assertTrue(CORONAVIRUS_RESPONSE_H_1_TITLE.isDisplayed(), "Correct Para title not displayed.");

    }

}
