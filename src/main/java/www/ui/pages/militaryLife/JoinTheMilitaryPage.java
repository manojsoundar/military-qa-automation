package www.ui.pages.militaryLife;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class JoinTheMilitaryPage extends GlobalHeaderComponent {

    private static final String URL = "/join-armed-forces";
    private static final SelenideElement JOIN_MILITARY_PARA_TITLE = $("div#pagewrapper > div.dialog-off-canvas-main-canvas > div > main > div > div.block.block--page--title > h1 > span").as("Join the MilitaryPage Para Title");

    public JoinTheMilitaryPage() {
        assertTrue(verifyURLLoaded(URL), "Page not loaded");
        JOIN_MILITARY_PARA_TITLE.should(appear);

        log.info("Verify if Page Para title is displayed correctly.");
        assertTrue(JOIN_MILITARY_PARA_TITLE.isDisplayed(), "Correct Join Military Para title not displayed.");

    }

}
