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
public class VeteranJobSearchPage extends GlobalHeaderComponent {

    private static final String URL = "/veteran-jobs";

    @SuppressWarnings("FieldCanBeLocal")
    private final SelenideElement VETERAN_JOB_SEARCH_H_1_TITLE = $(".block--page--title h1");

    public VeteranJobSearchPage() {
        assertTrue(verifyURLLoaded(URL), "Veteran Job Search Page not loaded..");
        assertEquals(VETERAN_JOB_SEARCH_H_1_TITLE.should(enabled, visible)
                                                 .getText()
                                                 .trim(), "Veteran Jobs", "Page title not displayed");
        log.info("Veteran JobSearch Page loaded..");

    }

}
