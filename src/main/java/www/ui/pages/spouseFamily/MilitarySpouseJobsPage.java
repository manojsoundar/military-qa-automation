package www.ui.pages.spouseFamily;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class MilitarySpouseJobsPage extends GlobalHeaderComponent {

    public final String URL = "/career-advancement";
    @SuppressWarnings("FieldCanBeLocal")
    private final SelenideElement CAREER_ADVANCEMENT_H_1_TITLE = $(".block--page--title h1");

    public MilitarySpouseJobsPage() {
        assertTrue(verifyURLLoaded(URL), "Military Spouse Jobs Page not loaded..");
        assertEquals(CAREER_ADVANCEMENT_H_1_TITLE.should(enabled, visible)
                                                 .getText()
                                                 .trim(), "Military Spouse Employment", "Page title not displayed");
        log.info("Military Spouse Jobs Page loaded..");
    }

}
