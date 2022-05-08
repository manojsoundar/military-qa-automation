package www.ui.pages.militaryLife;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class PcsRelocationPage extends GlobalHeaderComponent {

    private static final String URL = "/pcs";
    private static final SelenideElement PCS_RELOCATION_PARA_TITLE = $("div#pagewrapper > div.dialog-off-canvas-main-canvas > div > main > div > div.block.block--page--title > h1 > span").as("PCS Relocation Page Para Title");

    public PcsRelocationPage() {
        assertTrue(verifyURLLoaded(URL), "Page not loaded");
        PCS_RELOCATION_PARA_TITLE.should(appear);

        log.info("Verify if Page Para title is displayed correctly.");
        assertTrue(PCS_RELOCATION_PARA_TITLE.isDisplayed(), "Correct PCS Relocation Para title not displayed.");

    }

}
