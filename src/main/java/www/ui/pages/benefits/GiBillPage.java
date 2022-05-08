package www.ui.pages.benefits;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class GiBillPage extends GlobalHeaderComponent {

    private static final String URL = "/education";
    private static final SelenideElement BENEFITS_GI_Bill_PARA_TITLE_NAME = $("div.block.block--page--title h1 span").as("Benefits GI Bill Menu");

    public GiBillPage() {
        log.info("Verify if Benefits GI BILL Page is displayed correctly.");
        assertTrue(verifyURLLoaded(URL), "Benefits GI BILL page not loaded");
        assertTrue(BENEFITS_GI_Bill_PARA_TITLE_NAME.isDisplayed(), "Correct Para title not displayed.");
    }

}
