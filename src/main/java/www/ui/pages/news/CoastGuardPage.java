package www.ui.pages.news;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class CoastGuardPage extends GlobalHeaderComponent {

    private static final String URL = "/coast-guard";
    private static final SelenideElement COAST_GUARD_H_1_TITLE = $("div.block.block--page--title h1 span").as("CoastGuard Page Para Title");

    public CoastGuardPage() {
        log.info("Verify if CoastGuard Page Para title is displayed correctly.");
        assertTrue(verifyURLLoaded(URL), "CoastGuard Page not loaded");
        assertTrue(COAST_GUARD_H_1_TITLE.isDisplayed(), "Correct Para title not displayed.");

    }

}
