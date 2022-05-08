package www.ui.pages.news;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class SpaceForcePage extends GlobalHeaderComponent {

    private static final String URL = "/space-force";
    private static final SelenideElement SPACE_FORCE_H_1_TITLE = $("div.block.block--page--title h1 span").as("SpaceForce Page Para Title");

    public SpaceForcePage() {
        log.info("Verify if SpaceForce Page Para title is displayed correctly.");
        assertTrue(verifyURLLoaded(URL), "SpaceForce Page not loaded");
        assertTrue(SPACE_FORCE_H_1_TITLE.isDisplayed(), "Correct Para title not displayed.");

    }

}
