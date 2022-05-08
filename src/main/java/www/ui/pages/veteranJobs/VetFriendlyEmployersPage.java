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
public class VetFriendlyEmployersPage extends GlobalHeaderComponent {

    public final String URL = "/veteran-employers";
    @SuppressWarnings("FieldCanBeLocal")
    private final SelenideElement VETERAN_EMPLOYERS_H_1_TITLE = $(".block--page--title h1");

    public VetFriendlyEmployersPage() {

        assertTrue(verifyURLLoaded(URL), "Vet Friendly Employers Page not loaded..");
        assertEquals(VETERAN_EMPLOYERS_H_1_TITLE.should(visible, enabled)
                                                .getText()
                                                .trim(), "Jobs for Veterans", "Page title not displayed");
        log.info("Vet Friendly Employers Page loaded..");
    }

}
