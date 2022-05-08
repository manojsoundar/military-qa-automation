package www.ui.pages.militaryLife;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class SpecialOperationsPage extends GlobalHeaderComponent {

    private static final String URL = "/special-operations";
    private static final SelenideElement SPECIAL_OPERATIONS_PARA_TITLE = $("div#pagewrapper > div.dialog-off-canvas-main-canvas > div > main > div > div.block.block--page--title > h1 > span").as("Special Operations Page Para Title");

    public SpecialOperationsPage() {
        assertTrue(verifyURLLoaded(URL), "Page not loaded");
        SPECIAL_OPERATIONS_PARA_TITLE.should(appear);

        log.info("Verify if Page Para title is displayed correctly.");
        assertTrue(SPECIAL_OPERATIONS_PARA_TITLE.isDisplayed(), "Correct Special Operations Para title not displayed.");

    }

}
