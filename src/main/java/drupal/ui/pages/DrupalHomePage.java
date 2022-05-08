package drupal.ui.pages;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class DrupalHomePage extends AdministrationToolbar {

    private static final String URL_REGEX = "/user/";
    private static final SelenideElement DRUPAL_MENU_CONTENT_LINK = $("a.toolbar-icon-system-admin-content");

    public DrupalHomePage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Drupal Home page not loaded.");
        assertTrue(DRUPAL_MENU_CONTENT_LINK.should(exist, appear)
                                           .exists(), "Correct page is not displayed.");
        log.info("Drupal Home Page loaded properly.");
    }

}
