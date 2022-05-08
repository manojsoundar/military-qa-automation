package military.drupal.ui.content_menu;

import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.ContentMenu;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.webinar.WebinarDataModel;
import drupal.ui.pages.add_content.webinar.WebinarImageModel;
import drupal.ui.pages.add_content.webinar.WebinarPage;
import drupal.ui.pages.add_content.webinar.WebinarVeteranEmploymentPage;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.models.UserModel.loadUserModel;

public class TC250657_Create_A_Webinar_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateWebinar() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "webinarData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "250657")})
    @TestCaseId("250657")
    public void createWebinar(List<ISectionDataModel> webinarData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over Add content");
        logStep("Select Webinar");
        logStep("Enter 'Test Webinar' in the Title field");
        logStep("Enter 'testwebiner_123' in the Webinar ID field");
        logStep("Enter '3pm PDT / 6pm EDT' in the Webinar Time field");
        logStep("Enter 'Test Host' in the Host field");
        logStep("Enter 'This is a test webinar.' in the Webinar Details field");
        logStep("Click 'Attach an image' in the Lead Image block");
        logStep("Click 'Existing image' in the Attach an Image modal window");
        logStep("Enter 'adobe stock' in the Media name filed and click Search");
        logStep("Click on any image found in the search results");
        logStep("Click 'Select images'");
        logStep("Click 'Save'");
        WebinarPage webinarPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.WEBINAR), WebinarPage.class);
        WebinarDataModel webinarDataModel = webinarData.stream()
                                                       .filter(d -> d instanceof WebinarDataModel)
                                                       .map(c -> (WebinarDataModel) c)
                                                       .findFirst()
                                                       .get();
        logStep("Verify:Message 'Webinar Test Webinar has been created ' is displayed");
        WebinarVeteranEmploymentPage webinarVeteranEmploymentPage = webinarPage.addWebinar(webinarData)
                                                                               .clickSaveButton()
                                                                               .validateLandingPage(webinarDataModel.getTitle())
                                                                               .validateWebinarDetails(webinarDataModel);
        logStep("Validate Edit functionality");
        String updateWebinarTitle = "Updated Test Webinar " + timeStampFormat(PATTERN);
        webinarDataModel.setTitle(updateWebinarTitle);
        webinarVeteranEmploymentPage.clickEditTab()
                                    .editWebinarPage(List.of(webinarDataModel))
                                    .validateLandingPage(updateWebinarTitle);
    }

    @DataProvider
    public Object[][] webinarData() {
        return new Object[][]{
                {List.of(new WebinarImageModel(), new WebinarDataModel("Test Webinar " + timeStampFormat(PATTERN), "testwebiner_123", "3pm PDT / 6pm EDT", "Test Host", "This is a test webinar."))}};
    }
}