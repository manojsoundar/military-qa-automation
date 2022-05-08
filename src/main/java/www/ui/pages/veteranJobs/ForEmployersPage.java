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
public class ForEmployersPage extends GlobalHeaderComponent {

    public final String URL = "/hiring-veterans";
    @SuppressWarnings("FieldCanBeLocal")
    private final SelenideElement HIRING_VETERANS_H_1_TITLE = $(".block--page--title h1");

    public ForEmployersPage() {
        assertTrue(verifyURLLoaded(URL), "For Employers Page not loaded..");
        assertEquals(HIRING_VETERANS_H_1_TITLE.should(visible, enabled)
                                              .getText()
                                              .trim(), "Employer Resource Center", "Page title not displayed");
        log.info("For Employers Page loaded..");
    }

}
