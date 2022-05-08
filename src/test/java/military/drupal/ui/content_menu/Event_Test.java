package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.attribute.Attributes;
import com.epam.reportportal.annotations.attribute.MultiValueAttribute;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.ContentMenu;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.event.EventAddressInputModel;
import drupal.ui.pages.add_content.event.EventDataModel;
import drupal.ui.pages.add_content.event.EventDateTimeModel;
import drupal.ui.pages.add_content.event.EventJobFairTypeModel;
import drupal.ui.pages.add_content.event.EventPage;
import drupal.ui.pages.add_content.event.UpcomingJobFairsPage;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static drupal.models.UserModel.loadUserModel;
import static java.lang.String.format;

public class Event_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateEvent() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "eventData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(multiValueAttributes = {@MultiValueAttribute(key = "TestCaseIds", values = {"249520", "249522"})})
    public void createEvent(String testCase, List<ISectionDataModel> eventModel) {

        logStep(format("Running Test Case : %s", testCase));
        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over Add Content");
        logStep("Click on Event");
        EventPage eventPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.EVENT), EventPage.class);

        logStep("Create event Virtual or Location with Display Time");
        logStep("Validate event : " + Configuration.baseUrl + "/veteran-jobs/career-advice/job-hunting/upcoming-job-fairs");
        EventDataModel eventDataModel = eventModel.stream()
                                                  .filter(d -> d instanceof EventDataModel)
                                                  .map(c -> (EventDataModel) c)
                                                  .findFirst()
                                                  .get();
        UpcomingJobFairsPage upcomingJobFairsPage = eventPage.createEvent(eventModel)
                                                             .eventValidation(eventDataModel);
        String updateBlurbText = "Attending a job fair is a proactive way of getting to know companies or industries you're interested in.";
        upcomingJobFairsPage.clickEditTab()
                            .editEventLandingSpace(updateBlurbText)
                            .verifyUpdatedMessage();

    }

    @DataProvider
    public Object[][] eventData() {
        return new Object[][]{
                {
                        "TC249520",
                        List.of(
                                new EventDataModel(),
                                new EventDateTimeModel(),
                                new EventAddressInputModel(),
                                new EventJobFairTypeModel()
                        )
                },
                {
                        "TC249522",
                        List.of(
                                new EventDataModel(),
                                new EventDateTimeModel().typeDateTimeVirtualJob(),
                                new EventJobFairTypeModel().selectVirtualJobLocation()
                        )
                }
        };

    }

}
