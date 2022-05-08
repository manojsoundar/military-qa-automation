package www.ui.pages.benefits;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class MilitaryPayPage extends GlobalHeaderComponent {

    private static final String URL = "/benefits/military-pay";
    private static final SelenideElement BENEFITS_MILITARY_PAY_PAGE_PARA_TITLE_NAME = $("div.block.block--page--title h1 span").as("Benefits Main Menu");

    public MilitaryPayPage() {
        log.info("Verify if Benefits Military Pay Page is displayed correctly.");
        assertTrue(verifyURLLoaded(URL), "Benefits military pay page not loaded");
        assertTrue(BENEFITS_MILITARY_PAY_PAGE_PARA_TITLE_NAME.isDisplayed(), "Correct Para title not displayed.");
    }

}
