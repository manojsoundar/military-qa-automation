package www.ui.pages.benefits;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class InsurancePage extends GlobalHeaderComponent {

    private static final String URL = "/money/insurance";
    private static final SelenideElement BENEFITS_INSURANCE_PARA_TITLE_NAME = $("div.block.block--page--title h1 span").as("Benefits Main Menu");

    public InsurancePage() {
        log.info("Verify if Benefits Insurance Page is displayed correctly.");
        assertTrue(verifyURLLoaded(URL), "Benefits Insurance page not loaded");
        assertTrue(BENEFITS_INSURANCE_PARA_TITLE_NAME.isDisplayed(), "Correct Para title not displayed.");
    }

}
