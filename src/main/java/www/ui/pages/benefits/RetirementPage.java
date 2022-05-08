package www.ui.pages.benefits;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class RetirementPage extends GlobalHeaderComponent {

    private static final String URL = "/money/retirement";
    private static final SelenideElement BENEFITS_RETIREMENT_PARA_TITLE_NAME = $("div.block.block--page--title h1 span").as("Benefits Main Menu");

    public RetirementPage() {
        log.info("Verify if Benefits Retirement Page is displayed correctly.");
        assertTrue(verifyURLLoaded(URL), "Benefits Retirement page not loaded");
        assertTrue(BENEFITS_RETIREMENT_PARA_TITLE_NAME.isDisplayed(), "Correct Para title not displayed.");
    }

}
