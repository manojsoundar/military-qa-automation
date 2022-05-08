package drupal.ui.pages.add_content.webinar;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class WebinarVeteranEmploymentPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/veteran-employment-project/";
    private static final SelenideElement WEBINAR_RESULT_PAGE_H_1_TITLE = $(".block--page--title h1 span");

    // TODO rework selector
    private static final SelenideElement HOST_NAME_LABEL = $x("//article/div/div[2]/div[1]");
    // TODO rework selector
    private static final SelenideElement TIME_LABEL = $x("//article/div/div[2]/div[3]");
    private static final SelenideElement EDIT_TAB = $("ul.menu.menu--tabs a[href*='edit']");

    public WebinarVeteranEmploymentPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Webinar Landing Page not loaded..");
        log.info("Webinar Landing Page loaded");
    }

    public WebinarVeteranEmploymentPage validateLandingPage(String webinarTitle) {

        assertEquals(WEBINAR_RESULT_PAGE_H_1_TITLE.getText()
                                                  .trim(), webinarTitle, "Webinar:" + webinarTitle + "not displayed");
        log.info(format("Webinar Page : %s created..", webinarTitle));

        return this;
    }

    public WebinarVeteranEmploymentPage validateWebinarDetails(WebinarDataModel webinarDataModel) {

        assertTrue(HOST_NAME_LABEL.getText()
                                  .trim()
                                  .contains(webinarDataModel.getHost()), "Host name:" + webinarDataModel.getHost() + "is not displayed in webinar result page");
        assertTrue(TIME_LABEL.getText()
                             .trim()
                             .contains(webinarDataModel.getTime()), "Webinar time:" + webinarDataModel.getTime() + " is not displayed in webinar result page");
        return this;
    }

    public EditWebinarPage clickEditTab() {

        EDIT_TAB.should(enabled, visible)
                .click();
        log.info("Edit button clicked");

        return new EditWebinarPage();
    }
}
