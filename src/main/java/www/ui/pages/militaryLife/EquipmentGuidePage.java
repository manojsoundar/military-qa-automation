package www.ui.pages.militaryLife;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EquipmentGuidePage extends GlobalHeaderComponent {

    private static final String URL = "/equipment";
    private static final SelenideElement EQUIPMENT_GUIDE_PARA_TITLE = $("div#pagewrapper > div.dialog-off-canvas-main-canvas > div > main > div > div.block.block--page--title > h1 > span").as("Equipment guide Page Para Title");

    public EquipmentGuidePage() {
        assertTrue(verifyURLLoaded(URL), "Page not loaded");
        EQUIPMENT_GUIDE_PARA_TITLE.should(appear);

        log.info("Verify if Page Para title is displayed correctly.");
        assertTrue(EQUIPMENT_GUIDE_PARA_TITLE.isDisplayed(), "Correct Equipment guide Para title not displayed.");


    }

}
