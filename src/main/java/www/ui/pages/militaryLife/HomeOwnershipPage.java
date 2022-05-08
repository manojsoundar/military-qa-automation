package www.ui.pages.militaryLife;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class HomeOwnershipPage extends GlobalHeaderComponent {

    private static final String URL = "money/home-ownership";
    private static final SelenideElement HOME_OWNERSHIP_PARA_TITLE = $("div#pagewrapper > div.dialog-off-canvas-main-canvas > div > main > div > div.block.block--page--title > h1 > span").as("Home ownership Page Para Title");

    public HomeOwnershipPage() {
        assertTrue(verifyURLLoaded(URL), "Page not loaded");
        HOME_OWNERSHIP_PARA_TITLE.should(appear);

        log.info("Verify if Page Para title is displayed correctly.");
        assertTrue(HOME_OWNERSHIP_PARA_TITLE.isDisplayed(), "Correct Home ownership Para title not displayed.");

    }

}
