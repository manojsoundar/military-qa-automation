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
public class UpcomingJobFairsPage extends GlobalHeaderComponent {

    public final String URL = "/upcoming-job-fairs";
    @SuppressWarnings("FieldCanBeLocal")
    private final SelenideElement UPCOMING_JOB_FAIRS_H_1_TITLE = $(".block--page--title h1");


    public UpcomingJobFairsPage() {
        assertTrue(verifyURLLoaded(URL), "Upcoming Job Fairs Page not loaded..");
        assertEquals(UPCOMING_JOB_FAIRS_H_1_TITLE.should(visible, enabled)
                                                 .getText()
                                                 .trim(), "Upcoming Job Fairs", "Page title not displayed");
        log.info("Upcoming Job Fairs Page loaded..");
    }

}
