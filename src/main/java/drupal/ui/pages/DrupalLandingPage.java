package drupal.ui.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.assertTrue;

@Log4j2
public class DrupalLandingPage extends MasterPage {

    private static final String URL_REGEX = "/militarystg.prod.acquia-sites.com/";
    private static final SelenideElement MILITARY_HEADLINES_SECTION_TITLE = $x("//h2[contains(text(),'Headlines')]").as("Military Headlines Para Title");

    public DrupalLandingPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Drupal Landing page not loaded.");
        assertTrue(MILITARY_HEADLINES_SECTION_TITLE.should(appear, exist).isDisplayed(), "Correct Para title not displayed.");
        log.info("Drupal Landing page loaded properly.");
    }

}
