package www.ui.pages.news;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class LeftOfBoomPodcastPage extends GlobalHeaderComponent {

    private static final String URL = "/left-of-boom";
    private static final SelenideElement LEFT_OF_BOOM_PODCAST_H_1_TITLE = $("div.block.block--page--title h1 span").as("LeftOfBoomPodcast Page Para Title");

    public LeftOfBoomPodcastPage() {
        log.info("Verify if LeftOfBoomPodcast Page Para title is displayed correctly.");
        assertTrue(verifyURLLoaded(URL), "LeftOfBoomPodcast Page not loaded");
        assertTrue(LEFT_OF_BOOM_PODCAST_H_1_TITLE.isDisplayed(), "Correct Para title not displayed.");

    }

}
