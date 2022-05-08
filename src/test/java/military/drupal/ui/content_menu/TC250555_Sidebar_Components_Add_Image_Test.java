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
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceImageModel;
import drupal.ui.pages.add_content.landing_page.AddLandingPage;
import drupal.ui.pages.add_content.landing_page.AddLandingPageModel;
import drupal.ui.pages.components.flexible_space.AddFlexibleSpaceContentPage;
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

public class TC250555_Sidebar_Components_Add_Image_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionSidebarAddImage() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "setSidebarBlockData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "250555")})
    @TestCaseId("250555")
    public void createSidebarWithImage(List<ISectionDataModel> sidebarData, List<ISectionDataModel> landingPageData, List<ISectionDataModel> sidebarEditData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Create Flexible Space with Image");
        logStep("Hover the Content Tab");
        logStep("Hover Components");
        logStep("Select Flexible space (landing page sidebars)");
        logStep("Click 'Add Flexible space' button");
        AddFlexibleSpaceContentPage addFlexibleSpaceContentPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.COMPONENTS, ComponentsMenu.FLEXIBLE_SPACE), FlexibleSpacesPage.class)
                                                                          .clickFlexibleSpaceTab();

        logStep("Enter test name in the Title field");
        logStep("In the Content section, click Add Image");
        logStep("Enter the Image title");
        logStep("Click Attach Image Button");
        logStep("Click on the Existing Image tab, Input the image Title. Click search.");
        logStep("Select the Image and Click Save");
        logStep("Click save");
        logStep("Validate: Drupal Regular Flexible space is created: " + Configuration.baseUrl + "/admin/structure/eck/entity/flexible_space/####");
        FlexibleSpaceBaseModel flexibleSpaceBaseData = sidebarData.stream()
                                                                  .filter(d -> d instanceof FlexibleSpaceBaseModel)
                                                                  .map(c -> (FlexibleSpaceBaseModel) c)
                                                                  .findFirst()
                                                                  .get();
        FlexibleContentSpaceImageModel flexibleSpaceImageData = sidebarData.stream()
                                                                           .filter(d -> d instanceof FlexibleContentSpaceImageModel)
                                                                           .map(c -> (FlexibleContentSpaceImageModel) c)
                                                                           .findFirst()
                                                                           .get();
        FlexibleSpaceLandingPage flexibleSpaceLandingPage = addFlexibleSpaceContentPage.addFlexibleSpace(sidebarData)
                                                                                       .saveFlexibleSpaceLanding()
                                                                                       .verifyFlexibleSpaceImage(flexibleSpaceImageData.getImagesTitle());

        logStep("We need to confirm by adding the flexible space to a test Landing Page");
        logStep("Select Content > Add Content > Landing Page from the Navigation Menu");
        logStep("Enter the Landing Page Title");
        logStep("Goto to sidebar section, Enter the created FlexibleSpace title to add in the Landing Page");
        logStep("Click Save");
        logStep("Validation: Image is added in the Landing Page sidebar");
        AddLandingPage addLandingPage = flexibleSpaceLandingPage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.LANDING_PAGE), AddLandingPage.class);
        AddLandingPageModel addLandingPageData = landingPageData.stream()
                                                                .filter(d -> d instanceof AddLandingPageModel)
                                                                .map(c -> (AddLandingPageModel) c)
                                                                .findFirst()
                                                                .get();
        FlexibleSpaceBaseModel flexibleSpaceEditedBaseData = sidebarEditData.stream()
                                                                            .filter(d -> d instanceof FlexibleSpaceBaseModel)
                                                                            .map(c -> (FlexibleSpaceBaseModel) c)
                                                                            .findFirst()
                                                                            .get();
        addLandingPageData.setSideBars(List.of(flexibleSpaceBaseData.getTitle()));

        addLandingPage.fillIn(landingPageData)
                      .clickSaveButton()
                      .validateLandingPage(addLandingPageData)
                      .verifySidebarTitle(flexibleSpaceImageData.getImagesTitle())
                      .clickOnEditSidebarLink()
                      .editFlexibleSpaceData(sidebarEditData)
                      .saveFlexibleSpaceLanding();
        logStep("Goto to sidebar section, Edit the created FlexibleSpace title");
        logStep("Add in the Landing Page with edited Title and Click Save");
        logStep("Validation: Should be able to create the Landing Page with edited sidebar");
        addLandingPageData.setSideBars(List.of(flexibleSpaceEditedBaseData.getTitle()));
        flexibleSpaceLandingPage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.LANDING_PAGE), AddLandingPage.class)
                                .fillIn(landingPageData)
                                .clickSaveButton()
                                .validateLandingPage(addLandingPageData);
    }

    @DataProvider
    public Object[][] setSidebarBlockData() {
        return new Object[][]{
                {
                        List.of(
                                new FlexibleSpaceBaseModel(),
                                new FlexibleContentSpaceImageModel(CONTENT, 0, "Test Image " + timeStampFormat(PATTERN), null, "Test Image")
                        ),
                        List.of(
                                AddLandingPageModel.getLandingPageData()
                        ),
                        List.of(
                                new FlexibleSpaceBaseModel("Edit Test Title " + timeStampFormat(PATTERN)),
                                new FlexibleContentSpaceImageModel(CONTENT, 0, "Edit Test Image " + timeStampFormat(PATTERN), null, "Edit Test Image")
                        )
                }
        };
    }

}
