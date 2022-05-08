package www.ui.pages.militaryLife;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class BaseGuidesPage extends GlobalHeaderComponent {

    private static final String URL = "/base-guide";
    private static final SelenideElement BASE_GUIDE_PARA_TITLE = $("div#paragraph-25761 > div > h3").as("Base guide Page Para Title");

    public BaseGuidesPage() {
        assertTrue(verifyURLLoaded(URL), "Page not loaded");
        BASE_GUIDE_PARA_TITLE.should(appear);

        log.info("Verify if Page Para title is displayed correctly.");
        assertTrue(BASE_GUIDE_PARA_TITLE.isDisplayed(), "Correct Base guide Para title not displayed.");

    }

}
