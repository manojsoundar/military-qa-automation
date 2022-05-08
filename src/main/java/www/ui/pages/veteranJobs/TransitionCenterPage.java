package www.ui.pages.veteranJobs;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class TransitionCenterPage extends GlobalHeaderComponent {

    public final String URL = "/military-transition";
    @SuppressWarnings("FieldCanBeLocal")
    private final SelenideElement MILITARY_TRANSITION_H_1_TITLE = $(".block--page--title h1");

    public TransitionCenterPage() {
        assertTrue(verifyURLLoaded(URL), "Transition Center Page not loaded..");
        assertEquals(MILITARY_TRANSITION_H_1_TITLE.should(visible, enabled)
                                                  .getText()
                                                  .trim(), "Complete Military-to-Civilian Transition Support", "Page title not displayed");
        log.info("Transition Center Page loaded..");
    }

}
