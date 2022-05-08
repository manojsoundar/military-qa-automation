package www.ui.pages.militaryLife;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class FitnessPage extends GlobalHeaderComponent {

    private static final String URL = "/military-fitness";
    private static final SelenideElement FITNESS_PARA_TITLE = $("div#pagewrapper > div.dialog-off-canvas-main-canvas > div > main > div > div.block.block--page--title > h1 > span").as("Fitness Page Para Title");

    public FitnessPage() {
        assertTrue(verifyURLLoaded(URL), "Page not loaded");
        FITNESS_PARA_TITLE.should(appear);

        log.info("Verify if Page Para title is displayed correctly.");
        assertTrue(FITNESS_PARA_TITLE.isDisplayed(), "Correct Fitness Para title not displayed.");

    }

}
