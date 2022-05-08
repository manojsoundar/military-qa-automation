package www.ui.pages.news;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class MarineCorpsPage extends GlobalHeaderComponent {

    private static final String URL = "/marine-corps";
    private static final SelenideElement MARINE_CORPS_H_1_TITLE = $("div.block.block--page--title h1 span").as("MarineCorps Page Para Title");

    public MarineCorpsPage() {
        log.info("Verify if MarineCorps Page Para title is displayed correctly.");
        assertTrue(verifyURLLoaded(URL), "MarineCorps Page not loaded");
        assertTrue(MARINE_CORPS_H_1_TITLE.isDisplayed(), "Correct Para title not displayed.");

    }

}
