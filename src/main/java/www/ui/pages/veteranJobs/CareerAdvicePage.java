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
public class CareerAdvicePage extends GlobalHeaderComponent {

    public final String URL = "/career-advice";
    @SuppressWarnings("FieldCanBeLocal")
    private final SelenideElement CAREER_ADVICE_H_1_TITLE = $(".block--page--title h1");

    public CareerAdvicePage() {
        assertTrue(verifyURLLoaded(URL), "Career Advice Page not loaded..");
        assertEquals(CAREER_ADVICE_H_1_TITLE.should(visible, enabled)
                                            .getText()
                                            .trim(), "Career Advice", "Page title not displayed");
        log.info("Career Advice Page loaded..");
    }

}
