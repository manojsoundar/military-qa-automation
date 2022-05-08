package www.ui.pages.benefits;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class ScholarshipsForVetsPage extends GlobalHeaderComponent {

    private static final String URL = "/scholarships-for-veterans";
    private static final SelenideElement BENEFITS_SCHOLARSHIPS_FOR_VETS_PARA_TITLE_NAME = $("div.block.block--page--title h1 span").as("Benefits Main Menu");

    public ScholarshipsForVetsPage() {
        log.info("Verify if Benefits Scholarships for VETS Page is displayed correctly.");
        assertTrue(verifyURLLoaded(URL), "Benefits Scholarships for VETS Page not loaded");
        assertTrue(BENEFITS_SCHOLARSHIPS_FOR_VETS_PARA_TITLE_NAME.isDisplayed(), "Correct Para title not displayed.");
    }

}
