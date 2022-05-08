package www.ui.pages.benefits;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class BenefitsHomePage extends GlobalHeaderComponent {

    private static final String URL = "/benefits";
    private static final SelenideElement BENEFITS_HOME_PAGE_PARA_TITLE_NAME = $("div.block.block--page--title h1 span").as("Benefits Main Menu");

    public BenefitsHomePage() {
        log.info("Verify if Benefits Home Page is displayed correctly.");
        assertTrue(verifyURLLoaded(URL), "Benefits Home page not loaded");
        assertTrue(BENEFITS_HOME_PAGE_PARA_TITLE_NAME.isDisplayed(), "Correct Para title not displayed.");
    }

}