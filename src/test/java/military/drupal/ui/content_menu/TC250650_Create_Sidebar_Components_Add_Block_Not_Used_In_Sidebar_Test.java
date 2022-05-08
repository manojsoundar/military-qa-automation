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
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceBlockModel;
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

public class TC250650_Create_Sidebar_Components_Add_Block_Not_Used_In_Sidebar_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateSidebarWithBlock() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "setSidebarBlockData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "250650")})
    @TestCaseId("250650")
    public void createSidebarWithBlock(List<ISectionDataModel> sidebarData, List<ISectionDataModel> landingPageData, List<ISectionDataModel> flexibleEditData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Create Flexible Space with Block");
        logStep("Hover the Content Tab");
        logStep("Hover Components");
        logStep("Select Flexible space (landing page sidebars)");
        logStep("Click 'Add Flexible space' button");
        AddFlexibleSpaceContentPage addFlexibleSpaceContentPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.COMPONENTS, ComponentsMenu.FLEXIBLE_SPACE), FlexibleSpacesPage.class)
                                                                          .clickFlexibleSpaceTab();

        logStep("Enter test name in the Title field");
        logStep("In the Content section, click Add Block");
        logStep("Enter the Block title");
        logStep("Fill the remaining fields in Block section");
        logStep("Click save");
        logStep("Validate: Drupal Regular Flexible space is created: " + Configuration.baseUrl + "/admin/structure/eck/entity/flexible_space/####");
        FlexibleSpaceBaseModel flexibleSpaceBaseData = sidebarData.stream()
                                                                  .filter(d -> d instanceof FlexibleSpaceBaseModel)
                                                                  .map(c -> (FlexibleSpaceBaseModel) c)
                                                                  .findFirst()
                                                                  .get();
        FlexibleContentSpaceBlockModel flexibleSpaceBlockData = sidebarData.stream()
                                                                           .filter(d -> d instanceof FlexibleContentSpaceBlockModel)
                                                                           .map(c -> (FlexibleContentSpaceBlockModel) c)
                                                                           .findFirst()
                                                                           .get();
        FlexibleSpaceLandingPage flexibleSpaceLandingPage = addFlexibleSpaceContentPage.addFlexibleSpace(sidebarData)
                                                                                       .saveFlexibleSpaceLanding()
                                                                                       .verifyFlexibleSpaceBlock(flexibleSpaceBlockData.getAddBlockTitle());

        logStep("We need to confirm by adding the flexible space to a test Landing Page");
        logStep("Select Content > Add Content > Landing Page from the Navigation Menu");
        logStep("Enter the Landing Page Title");
        logStep("Goto to sidebar section, Enter the created FlexibleSpace title to add in the Landing Page");
        logStep("Click Save");
        logStep("Validation: Block is added in the Landing Page sidebar");
        AddLandingPage addLandingPage = flexibleSpaceLandingPage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.LANDING_PAGE), AddLandingPage.class);
        AddLandingPageModel addLandingPageData = landingPageData.stream()
                                                                .filter(d -> d instanceof AddLandingPageModel)
                                                                .map(c -> (AddLandingPageModel) c)
                                                                .findFirst()
                                                                .get();
        FlexibleContentSpaceBlockModel flexibleContentSpaceBlockModel = flexibleEditData.stream()
                                                                                        .filter(d -> d instanceof FlexibleContentSpaceBlockModel)
                                                                                        .map(c -> (FlexibleContentSpaceBlockModel) c)
                                                                                        .findFirst()
                                                                                        .get();
        addLandingPageData.setSideBars(List.of(flexibleSpaceBaseData.getTitle()));
        addLandingPage.fillIn(landingPageData)
                      .clickSaveButton()
                      .validateLandingPage(addLandingPageData)
                      .verifySidebarTitle(flexibleSpaceBlockData.getAddBlockTitle())
                      .clickOnEditSidebarLink()
                      .editFlexibleSpaceData(flexibleEditData)
                      .saveFlexibleSpaceLanding();
        ContentPage contentPage = flexibleSpaceLandingPage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.CONTENT), ContentPage.class);
        contentPage.fillSearchCriteriaAndFilter(ContentSearchModel.landingPageSearchData())
                   .clickContentLink(ResultLandingPage.class, addLandingPageData.getLandingPageTitle())
                   .verifySidebarTitle(flexibleContentSpaceBlockModel.getAddBlockTitle());


    }

    @DataProvider
    public Object[][] setSidebarBlockData() {
        return new Object[][]{
                {List.of(new FlexibleSpaceBaseModel(),
                        new FlexibleContentSpaceBlockModel(CONTENT, 0, "Block Title " + timeStampFormat(PATTERN), "AppNexus", "AppNexus", false, "Top - 728x90, 970x90, Brandscape Top")),
                        List.of(AddLandingPageModel.getSideBarBlockData()),
                        List.of(new FlexibleContentSpaceBlockModel(CONTENT, 0, "Edited Block Title " + timeStampFormat(PATTERN), null, null, false, null))
                }
        };
    }
}

