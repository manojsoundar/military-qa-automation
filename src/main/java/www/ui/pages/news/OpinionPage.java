package www.ui.pages.news;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class OpinionPage extends GlobalHeaderComponent {

    private static final String URL = "/daily-news/opinion";
    private static final SelenideElement OPINION_H_1_TITLE = $("div.block.block--page--title h1 span").as("Opinion Page Para Title");

    public OpinionPage() {
        log.info("Verify if Opinion Page Para title is displayed correctly.");
        assertTrue(verifyURLLoaded(URL), "Opinion Page not loaded");
        assertTrue(OPINION_H_1_TITLE.isDisplayed(), "Correct Para title not displayed.");

    }

}
