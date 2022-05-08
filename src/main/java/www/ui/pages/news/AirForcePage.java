package www.ui.pages.news;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AirForcePage extends GlobalHeaderComponent {

    private static final String URL = "/air-force";
    private static final SelenideElement AIR_FORCE_H_1_TITLE = $("div.block.block--page--title h1 span").as("AirForce Page Para Title");

    public AirForcePage() {
        log.info("Verify if AirForce Page Para title is displayed correctly.");
        assertTrue(verifyURLLoaded(URL), "AirForce Page not loaded");
        assertTrue(AIR_FORCE_H_1_TITLE.isDisplayed(), "Correct Para title not displayed.");
    }

}
