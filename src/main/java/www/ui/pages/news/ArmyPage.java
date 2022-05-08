package www.ui.pages.news;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class ArmyPage extends GlobalHeaderComponent {

    private static final String URL = "/army";
    private static final SelenideElement ARMY_H_1_TITLE = $("div.block.block--page--title h1 span").as("Army Page Para Title");

    public ArmyPage() {
        log.info("Verify if ArmyPage Para title is displayed correctly.");
        assertTrue(verifyURLLoaded(URL), "ArmyPage Page not loaded");
        assertTrue(ARMY_H_1_TITLE.isDisplayed(), "Correct Para title not displayed.");
    }

}
