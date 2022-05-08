package www.ui.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.assertTrue;

@Log4j2
public class MilitaryLandingPage extends GlobalHeaderComponent {

    private static final String URL = "https://www.military.com/";
    private static final SelenideElement MILITARY_HEADLINES_SECTION_TITLE = $x("//h2[contains(text(),'Headlines')]").as("Military Headlines Para Title");

    public MilitaryLandingPage() {
        assertTrue(verifyURLLoaded(URL), "Home page not loaded.");
        assertTrue(MILITARY_HEADLINES_SECTION_TITLE.isDisplayed(), "Correct Para title not displayed.");
        log.info("Military Home Page loaded properly.");
    }

}
