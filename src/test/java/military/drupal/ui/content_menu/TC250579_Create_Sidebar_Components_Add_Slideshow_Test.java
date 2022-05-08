package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.ComponentsMenu;
import drupal.enums.content.ContentMenu;
import drupal.models.ContentSearchModel;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceSlideshowModel;
import drupal.ui.pages.add_content.landing_page.AddLandingPage;
import drupal.ui.pages.add_content.landing_page.AddLandingPageModel;
import drupal.ui.pages.add_content.landing_page.ResultLandingPage;
import drupal.ui.pages.components.flexible_space.AddFlexibleSpaceContentPage;
import drupal.ui.pages.components.flexible_space.FlexibleSpaceBaseModel;
import drupal.ui.pages.components.flexible_space.FlexibleSpaceLandingPage;
import drupal.ui.pages.components.flexible_space.FlexibleSpacesPage;
import drupal.ui.pages.content.ContentPage;
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
import static drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceSlideshowModel.getSidebarWithSlideshowData;

public class TC250579_Create_Sidebar_Components_Add_Slideshow_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionSidebarWithSlideshow() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "setSidebarBlockData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "250579")})
    @TestCaseId("250579")
    public void createSidebarWithSlideshow(List<ISectionDataModel> sidebarData, List<ISectionDataModel> landingPageData, List<ISectionDataModel> editSidebarData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Create Flexible Space with Slideshow");
        logStep("Hover the Content Tab");
        logStep("Hover Components");
        logStep("Select Flexible space (landing page sidebars)");
        logStep("Click 'Add Flexible space' button");
        AddFlexibleSpaceContentPage addFlexibleSpaceContentPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.COMPONENTS, ComponentsMenu.FLEXIBLE_SPACE), FlexibleSpacesPage.class)
                                                                          .clickFlexibleSpaceTab();

        logStep("Enter test name in the Title field");
        logStep("In the Content section, click Add Slideshow");
        logStep("Enter the title and caption");
        logStep("Click Attach Image Button");
        logStep("Click on the Existing Image tab, Input the image Title. Click search.");
        logStep("Select the Images and Click Save");
        logStep("Click Create Slideshow button");
        logStep("Click save");
        logStep("Validate: Drupal Regular Flexible space is created: " + Configuration.baseUrl + "/admin/structure/eck/entity/flexible_space/####");
        FlexibleSpaceBaseModel flexibleSpaceBaseData = sidebarData.stream()
                                                                  .filter(d -> d instanceof FlexibleSpaceBaseModel)
                                                                  .map(c -> (FlexibleSpaceBaseModel) c)
                                                                  .findFirst()
                                                                  .get();
        FlexibleContentSpaceSlideshowModel flexibleContentSpaceSlideshowData = sidebarData.stream()
                                                                                          .filter(d -> d instanceof FlexibleContentSpaceSlideshowModel)
                                                                                          .map(c -> (FlexibleContentSpaceSlideshowModel) c)
                                                                                          .findFirst()
                                                                                          .get();
        FlexibleSpaceLandingPage flexibleSpaceLandingPage = addFlexibleSpaceContentPage.addFlexibleSpace(sidebarData)
                                                                                       .saveFlexibleSpaceLanding()
                                                                                       .verifyDrupalFlexibleSpaceCreated()
                                                                                       .verifyFlexibleSpaceSlideshow(flexibleContentSpaceSlideshowData);

        logStep("We need to confirm by adding the flexible space to a test Landing Page");
        logStep("Select Content > Add Content > Landing Page from the Navigation Menu");
        logStep("Enter the Landing Page Title");
        logStep("Goto to sidebar section, Enter the created FlexibleSpace title to add in the Landing Page");
        logStep("Click Save");
        logStep("Validation: Slideshow is added in the Landing Page sidebar");
        AddLandingPage addLandingPage = flexibleSpaceLandingPage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.LANDING_PAGE), AddLandingPage.class);
        AddLandingPageModel addLandingPageData = landingPageData.stream()
                                                                .filter(d -> d instanceof AddLandingPageModel)
                                                                .map(c -> (AddLandingPageModel) c)
                                                                .findFirst()
                                                                .get();
        addLandingPageData.setSideBars(List.of(flexibleSpaceBaseData.getTitle()));
        addLandingPage.fillIn(landingPageData)
                      .clickSaveButton()
                      .validateLandingPage(addLandingPageData)
                      .verifySidebarSlideshow(flexibleContentSpaceSlideshowData.getNewSlideshowCaption())
                      .clickOnEditSidebarLink()
                      .editFlexibleSpaceData(editSidebarData)
                      .saveFlexibleSpaceLanding();

        FlexibleContentSpaceSlideshowModel editSlideshowModel = editSidebarData.stream()
                                                                               .filter(d -> d instanceof FlexibleContentSpaceSlideshowModel)
                                                                               .map(c -> (FlexibleContentSpaceSlideshowModel) c)
                                                                               .findFirst()
                                                                               .get();

        flexibleSpaceLandingPage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.CONTENT), ContentPage.class)
                                .fillSearchCriteriaAndFilter(new ContentSearchModel(addLandingPageData.getLandingPageTitle(), null, null, null))
                                .clickContentLink(ResultLandingPage.class, addLandingPageData.getLandingPageTitle())
                                .verifySidebarTitle(editSlideshowModel.getNewSlideshowTitle());
    }

    @DataProvider
    public Object[][] setSidebarBlockData() {
        return new Object[][]{
                {
                        List.of(
                                new FlexibleSpaceBaseModel(),
                                new FlexibleContentSpaceSlideshowModel(CONTENT, 0, true, "Test Slideshow " + timeStampFormat(PATTERN), "Test Slideshow", getSidebarWithSlideshowData(), false, null)
                        ),
                        List.of(
                                AddLandingPageModel.getLandingPageData()
                        ),
                        List.of(
                                new FlexibleSpaceBaseModel(),
                                new FlexibleContentSpaceSlideshowModel(CONTENT, 0, true, "Edit Test Slideshow " + timeStampFormat(PATTERN), "Edit Test Slideshow", null, false, "Yes")
                        )
                }
        };
    }

}
