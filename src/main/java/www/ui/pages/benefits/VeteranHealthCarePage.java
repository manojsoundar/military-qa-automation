package www.ui.pages.benefits;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class VeteranHealthCarePage extends GlobalHeaderComponent {

    private static final String URL = "/benefits/veterans-health-carel";
    private static final SelenideElement BENEFITS_VETERAN_HEALTHCARE_PARA_TITLE_NAME = $("div.block.block--page--title h1 span").as("Benefits Main Menu");

    public VeteranHealthCarePage() {
        log.info("Verify if Benefits Veteran Health Care Page is displayed correctly.");
        assertTrue(verifyURLLoaded(URL), "Benefits Veteran Health Care page not loaded");
        assertTrue(BENEFITS_VETERAN_HEALTHCARE_PARA_TITLE_NAME.isDisplayed(), "Correct Para title not displayed.");
    }

}
