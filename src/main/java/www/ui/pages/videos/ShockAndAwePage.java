package www.ui.pages.videos;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class ShockAndAwePage extends GlobalHeaderComponent {

    private static final String URL = "/video/shock-and-awe";
    private static final SelenideElement SHOCK_AND_AWE_VIDEOS_TITLE = $("#bodyContent .field.name.field--label-hidden");

    public ShockAndAwePage() {
        assertTrue(verifyURLLoaded(URL), "Shock and Awe page not loaded.");
        assertEquals(SHOCK_AND_AWE_VIDEOS_TITLE.should(exist, appear)
                                               .getText()
                                               .trim(), "Shock and Awe",
                     "Correct page title not displayed.");
        log.info("Shock and Awe page loaded properly.");
    }

}
