package www.ui.pages.news;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class NavyPage extends GlobalHeaderComponent {

    private static final String URL = "/navy";
    private static final SelenideElement NAVY_H_1_TITLE = $("div.block.block--page--title h1 span").as("Navy Page Para Title");

    public NavyPage() {
        log.info("Verify if Navy Page Para title is displayed correctly.");
        assertTrue(verifyURLLoaded(URL), "Navy Page not loaded");
        assertTrue(NAVY_H_1_TITLE.isDisplayed(), "Correct Para title not displayed.");

    }

}
