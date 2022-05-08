package drupal.ui.pages.add_content.event;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.add_content.landing_page.EditLandingPage;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class UpcomingJobFairsPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/upcoming-job-fairs";
    private static final SelenideElement UPCOMING_JOB_FAIRS_PAGE_H_1_TAG = $(".block--page--title h1").as("Upcoming Job Fairs Page H1 Tag");
    private static final SelenideElement EVENT_MESSAGE = $(".messages--status em a").as("Upcoming Job Fairs Page validation message");
    private static final SelenideElement EDIT_TAB = $("ul.menu.menu--tabs a[href*='edit']");
    private static final SelenideElement UPDATED_STATUS_MESSAGE = $(".messages--status");

    public UpcomingJobFairsPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Upcoming Job Fairs Page not loaded..");
        assertTrue(UPCOMING_JOB_FAIRS_PAGE_H_1_TAG.getText()
                                                  .trim()
                                                  .contains("Upcoming Job Fairs"), "Upcoming job fairs title: " + UPCOMING_JOB_FAIRS_PAGE_H_1_TAG.getText() + "not displayed,should contain('Upcoming Job Fairs')");
        log.info("Upcoming Job Fairs Page loaded..");
    }

    public UpcomingJobFairsPage eventValidation(EventDataModel eventModel) {
        log.info("verification");
        assertEquals(EVENT_MESSAGE.should(visible, enabled)
                                  .getText()
                                  .trim(), eventModel.getName()
                                                     .trim(), "Event message" + EVENT_MESSAGE.getText() + "not displayed should contain" + eventModel.getName());
        log.info(format("Event : %s  created..", eventModel.getName()
                                                           .trim()));
        return this;
    }

    public EditLandingPage clickEditTab() {

        EDIT_TAB.should(enabled, visible)
                .click();

        log.info("Edit button clicked");
        return new EditLandingPage();
    }

    public UpcomingJobFairsPage verifyUpdatedMessage() {

        assertTrue(UPDATED_STATUS_MESSAGE.should(visible, appear)
                                         .getText()
                                         .contains("updated"), "Event updated message: " + UPDATED_STATUS_MESSAGE.getText() + "not displayed,should contain('updated')");
        log.info("Event updated");
        return this;
    }
}
