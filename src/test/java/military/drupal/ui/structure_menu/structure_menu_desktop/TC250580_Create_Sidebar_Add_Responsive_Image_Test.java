package military.drupal.ui.structure_menu.structure_menu_desktop;

import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.ContentMenu;
import drupal.enums.structure.StructureMenu;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceResponsiveImageModel;
import drupal.ui.pages.add_content.landing_page.AddLandingPage;
import drupal.ui.pages.add_content.landing_page.AddLandingPageModel;
import drupal.ui.pages.components.flexible_space.AddFlexibleSpaceContentPage;
import drupal.ui.pages.components.flexible_space.FlexibleSpaceBaseModel;
import drupal.ui.pages.components.flexible_space.FlexibleSpaceLandingPage;
import drupal.ui.pages.components.flexible_space.FlexibleSpacesPage;
import drupal.ui.pages.structure.eck_entity_types.ECKEntityTypesPage;
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

public class TC250580_Create_Sidebar_Add_Responsive_Image_Test extends AdminUITestBase {
    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateSidebarAddResponsiveImage() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "setData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "250580")})
    @TestCaseId("250580")
    public void createSidebarAddResponsiveImage(List<ISectionDataModel> sideBarData, List<ISectionDataModel> landingPageData, List<ISectionDataModel> editSideBarData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Structure Tab");
        logStep("Click the ECK Entity Types");
        ECKEntityTypesPage eckEntityTypes = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.STRUCTURE, StructureMenu.ECK_ENTITY_TYPES), ECKEntityTypesPage.class);

        logStep("Go to FLEXIBLE SPACE and select 'Content List' Button under the OPERATIONS column");
        FlexibleSpacesPage flexibleSpacesPage = eckEntityTypes.clickEntityType(ECKEntityTypesPage.ECKEntityType.FLEXIBLE_SPACE, ECKEntityTypesPage.ECKEntityOperationType.CONTENT_LIST, FlexibleSpacesPage.class);

        logStep("Click Add Flexible space button and navigate to 'Add Flexible space content' page");
        AddFlexibleSpaceContentPage addFlexibleSpaceContentPage = flexibleSpacesPage.clickFlexibleSpaceTab();

        logStep("Add Flexible Space Content with Responsive Image");
        logStep("Verify Flexible Space Responsive Image ");
        FlexibleSpaceLandingPage flexibleSpaceLandingPage = addFlexibleSpaceContentPage.addFlexibleSpace(sideBarData)
                                                                                       .saveFlexibleSpaceLanding()
                                                                                       .verifyFlexibleSpaceResponsiveImage();

        FlexibleSpaceBaseModel flexibleSpaceBaseData = sideBarData.stream()
                                                                  .filter(d -> d instanceof FlexibleSpaceBaseModel)
                                                                  .map(c -> (FlexibleSpaceBaseModel) c)
                                                                  .findFirst()
                                                                  .get();

        logStep("Edit Flexible Space Content with Responsive Image");
        logStep("Verify Flexible Space Responsive Image ");
        flexibleSpaceBaseData.setTitle("Edit Title" + timeStampFormat(PATTERN));
        flexibleSpaceLandingPage.clickEditTab()
                                .fillIn(List.of(flexibleSpaceBaseData))
                                .saveFlexibleSpaceLanding()
                                .verifyFlexibleSpaceResponsiveImage();

        logStep("We need to confirm by adding the flexible space to a test Landing Page");
        logStep("Select Content > Add Content > Landing Page from the Navigation Menu");
        logStep("Enter the Landing Page Title");
        logStep("Goto to sidebar section, Enter the created FlexibleSpace title to add in the Landing Page");
        logStep("Click Save");
        logStep("Validation: Responsive Image is added in the Landing Page sidebar");
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
                      .verifyResponsiveSidebarImage()
                      .clickOnEditSidebarLink()
                      .editFlexibleSpaceData(editSideBarData)
                      .saveFlexibleSpaceLanding();

        addLandingPage = flexibleSpaceLandingPage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.LANDING_PAGE), AddLandingPage.class);
        addLandingPageData.setSideBars(List.of(flexibleSpaceBaseData.getTitle()));
        addLandingPage.fillIn(landingPageData)
                      .clickSaveButton()
                      .validateLandingPage(addLandingPageData);
    }

    @DataProvider
    public Object[][] setData() {
        return new Object[][]{
                {
                        List.of(new FlexibleSpaceBaseModel(), new FlexibleContentSpaceResponsiveImageModel(CONTENT, 0, "Test Image", "Test Image", "Test Image")),
                        List.of(AddLandingPageModel.getLandingPageData()),
                        List.of(new FlexibleSpaceBaseModel("Edit Test Title " + timeStampFormat(PATTERN)), new FlexibleContentSpaceResponsiveImageModel(CONTENT, 0,null, "Edit Test Image", null)),
                }
        };
    }
}
