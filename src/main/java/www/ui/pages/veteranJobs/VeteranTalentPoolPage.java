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
public class VeteranTalentPoolPage extends GlobalHeaderComponent {

    public final String URL = "/veteran-talent-pool";
    @SuppressWarnings("FieldCanBeLocal")
    private final SelenideElement VETERAN_TALENT_POOL_V_1_TITLE = $(".text-content h3");

    public VeteranTalentPoolPage() {
        assertTrue(verifyURLLoaded(URL), "Veteran Talent Pool Page not loaded..");
        assertEquals(VETERAN_TALENT_POOL_V_1_TITLE.should(visible, enabled)
                                                  .getText()
                                                  .trim(), "Master Class for Your Military Transition", "Page title not displayed");
        log.info("Veteran Talent Pool Page loaded..");
    }

}
