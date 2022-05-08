package www.ui.pages.militaryLife;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class MilitaryLifeHomePage extends GlobalHeaderComponent {

    private static final String URL = "/military-life";
    private static final SelenideElement MILITARY_LIFE_PARA_TITLE = $("div#pagewrapper > div.dialog-off-canvas-main-canvas > div > main > div > div.block.block--page--title > h1 > span").as("Military Life Page Para Title");

    public MilitaryLifeHomePage() {
        assertTrue(verifyURLLoaded(URL), "Page not loaded");
        MILITARY_LIFE_PARA_TITLE.should(appear);

        log.info("Verify if Page Para title is displayed correctly.");
        assertTrue(MILITARY_LIFE_PARA_TITLE.isDisplayed(), "Correct Military Life Para title not displayed.");

    }

}
