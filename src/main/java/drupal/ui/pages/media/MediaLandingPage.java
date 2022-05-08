package drupal.ui.pages.media;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class MediaLandingPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/media";
    private static final SelenideElement MEDIA_LANDING_PAGE_H_1_TAG = $("div.block--page--title h1");
    private static final SelenideElement EDIT_TAB = $(".menu--tabs a[href*='edit']");
    private static final SelenideElement DELETE_TAB = $(".menu--tabs a[href*='delete']");

    public MediaLandingPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Media Landing Page not loaded.");
        log.info("Media Landing Page loaded properly.");
    }

    public MediaLandingPage verifyMediaLandingPage(String mediaName) {

        assertTrue(MEDIA_LANDING_PAGE_H_1_TAG.should(exist, appear)
                                             .getText()
                                             .contains(mediaName), "Correct page is not displayed.");
        return this;
    }

    public EditMediaPage clickEditTab() {

        log.info("clicking on Edit Tab");
        EDIT_TAB.should(exist, appear, enabled)
                .click();
        return new EditMediaPage();
    }

}
