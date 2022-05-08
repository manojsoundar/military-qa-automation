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
public class SecurityClearanceJobsPage extends GlobalHeaderComponent {

    public final String URL = "/security-clearance-jobs";

    @SuppressWarnings("FieldCanBeLocal")
    private final SelenideElement SECURITY_CLEARANCE_JOBS_H_1_TITLE = $(".block--page--title h1");

    public SecurityClearanceJobsPage() {
        assertTrue(verifyURLLoaded(URL), "Security Clearance Jobs Page not loaded..");
        assertEquals(SECURITY_CLEARANCE_JOBS_H_1_TITLE.should(visible, enabled)
                                                      .getText()
                                                      .trim(), "Security Clearance Jobs", "Page title not displayed");
        log.info("Security Clearance Jobs Page loaded..");
    }

}
