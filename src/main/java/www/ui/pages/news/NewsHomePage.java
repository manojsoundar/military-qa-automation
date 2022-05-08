package www.ui.pages.news;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class NewsHomePage extends GlobalHeaderComponent {

    private static final String URL = "/daily-news";
    private static final SelenideElement NEWS_HOME_H_1_TITLE = $("div.block.block--page--title h1 span").as("NewsHome Page Para Title");

    public NewsHomePage() {
        log.info("Verify if NewsHome Page Para title is displayed correctly.");
        assertTrue(verifyURLLoaded(URL), "NewsHome Page not loaded");
        assertTrue(NEWS_HOME_H_1_TITLE.isDisplayed(), "Correct Para title not displayed.");

    }

}
