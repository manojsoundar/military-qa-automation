package www.ui.pages.benefits;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class VAeBenefitsPage extends GlobalHeaderComponent {

    private static final String URL = "/the-ebenefits-program.html'";
    private static final SelenideElement BENEFITS_VA_E_BENEFITS_PARA_TITLE_NAME = $("div.block.block--page--title h1 span']").as("Benefits Main Menu");

    public VAeBenefitsPage() {
        log.info("Verify if VA eBenefits Page is displayed correctly.");
        assertTrue(verifyURLLoaded(URL), "Benefits VA eBenefits page not loaded");
        assertTrue(BENEFITS_VA_E_BENEFITS_PARA_TITLE_NAME.isDisplayed(), "Correct Para title not displayed.");
    }

}
