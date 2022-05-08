package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.ComponentsMenu;
import drupal.enums.content.ContentMenu;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceAudioModel;
import drupal.ui.pages.add_content.landing_page.AddLandingPage;
import drupal.ui.pages.add_content.landing_page.AddLandingPageModel;
import drupal.ui.pages.components.flexible_space.AddFlexibleSpaceContentPage;
import drupal.ui.pages.components.flexible_space.EditFlexibleSpacePage;
import drupal.ui.pages.components.flexible_space.FlexibleSpaceBaseModel;
import drupal.ui.pages.components.flexible_space.FlexibleSpaceLandingPage;
import drupal.ui.pages.components.flexible_space.FlexibleSpacesPage;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static common.CommonMethods.timeStampFormat;
import static drupal.enums.content.FlexibleContentSpacePosition.CONTENT;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.models.UserModel.loadUserModel;

public class TC250653_Create_Sidebar_Components_Add_Audio_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionSidebarWithAudio() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "setSidebarAudioData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "250653")})
    @TestCaseId("250653")
    public void createSidebarWithAudio(List<ISectionDataModel> sidebarData, List<ISectionDataModel> landingPageData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Create Flexible Space with Audio");
        logStep("Hover the Content Tab");
        logStep("Hover Components");
        logStep("Select Flexible space (landing page sidebars)");
        logStep("Click 'Add Flexible space' button");
        AddFlexibleSpaceContentPage addFlexibleSpaceContentPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.COMPONENTS, ComponentsMenu.FLEXIBLE_SPACE), FlexibleSpacesPage.class)
                                                                          .clickFlexibleSpaceTab();

        logStep("Enter test name in the Title field");
        logStep("In the Content section, click Add Audio");
        logStep("Click Add Existing Media Item");
        logStep("Enter the test title- Test Audio yyyyMMddhhmmss");
        logStep("Enter the Test Audio name and and select the 1st option");
        logStep("Click Add Media Item");
        logStep("Click save");
        logStep("Validate: Drupal Regular Flexible space is created: " + Configuration.baseUrl + "/admin/structure/eck/entity/flexible_space/####");
        FlexibleSpaceBaseModel flexibleSpaceBaseData = sidebarData.stream()
                                                                  .filter(d -> d instanceof FlexibleSpaceBaseModel)
                                                                  .map(c -> (FlexibleSpaceBaseModel) c)
                                                                  .findFirst()
                                                                  .get();
        FlexibleContentSpaceAudioModel flexibleContentSpaceAudioModel = sidebarData.stream()
                                                                                   .filter(d -> d instanceof FlexibleContentSpaceAudioModel)
                                                                                   .map(c -> (FlexibleContentSpaceAudioModel) c)
                                                                                   .findFirst()
                                                                                   .get();
        FlexibleSpaceLandingPage flexibleSpaceLandingPage = addFlexibleSpaceContentPage.addFlexibleSpace(sidebarData)
                                                                                       .saveFlexibleSpaceLanding()
                                                                                       .verifyDrupalFlexibleSpaceCreated()
                                                                                       .verifyFlexibleSpaceBlock(flexibleContentSpaceAudioModel.getAudioTitle());

        logStep("We need to confirm by adding the flexible space to a test Landing Page");
        logStep("Select Content > Add Content > Landing Page from the Navigation Menu");
        logStep("Enter the Landing Page Title");
        logStep("Goto to sidebar section, Enter the created FlexibleSpace title to add in the Landing Page");
        logStep("Click Save");
        logStep("Validation: Audio is added in the Landing Page sidebar");
        AddLandingPage addLandingPage = flexibleSpaceLandingPage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.LANDING_PAGE), AddLandingPage.class);
        AddLandingPageModel addLandingPageData = landingPageData.stream()
                                                                .filter(d -> d instanceof AddLandingPageModel)
                                                                .map(c -> (AddLandingPageModel) c)
                                                                .findFirst()
                                                                .get();
        addLandingPageData.setSideBars(List.of(flexibleSpaceBaseData.getTitle()));
        EditFlexibleSpacePage editFlexibleSpacePage = addLandingPage.fillIn(landingPageData)
                                                                    .clickSaveButton()
                                                                    .validateLandingPage(addLandingPageData)
                                                                    .verifySidebarTitle(flexibleContentSpaceAudioModel.getAudioTitle())
                                                                    .clickOnEditSidebarLink();

        logStep("Validate Edit Sidebar(flexible space) functionality");
        flexibleSpaceBaseData.setTitle("Edit Flexible Space " + timeStampFormat(PATTERN));
        flexibleContentSpaceAudioModel.setAudioTitle("Edit Test Audio " + timeStampFormat(PATTERN));
        flexibleContentSpaceAudioModel.setMediaName("Edit - Test Audio File");
        editFlexibleSpacePage.editFlexibleSpaceData(sidebarData)
                             .saveFlexibleSpaceLanding()
                             .verifyDrupalFlexibleSpaceCreated()
                             .verifyFlexibleSpaceBlock(flexibleContentSpaceAudioModel.getAudioTitle());
    }

    @DataProvider
    public Object[][] setSidebarAudioData() {
        return new Object[][]{
                {
                        List.of(
                                new FlexibleSpaceBaseModel(),
                                new FlexibleContentSpaceAudioModel(CONTENT, 0, "Test Audio " + timeStampFormat(PATTERN), true, "Test Audio File", null, null)
                        ),
                        List.of(
                                AddLandingPageModel.getLandingPageData()
                        )
                }
        };
    }

}

