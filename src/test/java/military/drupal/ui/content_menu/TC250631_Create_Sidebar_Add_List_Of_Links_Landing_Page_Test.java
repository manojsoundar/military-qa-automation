package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.Category;
import drupal.enums.content.ComponentsMenu;
import drupal.enums.content.ContentMenu;
import drupal.models.ContentSearchModel;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListOfLinksModel;
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
import java.util.Map;

import static common.CommonMethods.timeStampFormat;
import static drupal.enums.content.FlexibleContentSpacePosition.CONTENT;
import static drupal.models.TimeStampPattern.DATE_WITH_HYPHEN_PATTERN;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.models.UserModel.loadUserModel;

public class TC250631_Create_Sidebar_Add_List_Of_Links_Landing_Page_Test extends AdminUITestBase {
    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionLandingPageWithComponents() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "testData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "250631")})
    public void landingPageWithDirectory(List<ISectionDataModel> flexibleSpaceData, List<ISectionDataModel> landingPageData, List<ISectionDataModel> editLandingPageData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover over Components");
        logStep("Hover over Flexible Space(Landing page sidebars)");
        FlexibleSpacesPage flexibleSpacesPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.COMPONENTS, ComponentsMenu.FLEXIBLE_SPACE), FlexibleSpacesPage.class);

        logStep("Click on +Add Flexible space");
        logStep("In the Flexible Content Space (Content) section, click the Down arrow and select Add List of Links");
        logStep("Enter Details");
        logStep("In the Lists section, type Test Content in the URL & Link Text fields (PARENT LINK Section)");
        logStep("CLick Save");
        logStep("Validate: Drupal Regular Flexible space is created: " + Configuration.baseUrl + "/admin/structure/eck/entity/flexible_space/####");
        AddFlexibleSpaceContentPage addFlexibleSpaceContentPage = flexibleSpacesPage.clickFlexibleSpaceTab();
        FlexibleContentSpaceListOfLinksModel flexibleContentSpaceListOfLinksModel = flexibleSpaceData.stream()
                                                                                                     .filter(d -> d instanceof FlexibleContentSpaceListOfLinksModel)
                                                                                                     .map(c -> (FlexibleContentSpaceListOfLinksModel) c)
                                                                                                     .findFirst()
                                                                                                     .get();

        FlexibleSpaceLandingPage flexibleSpaceLandingPage = addFlexibleSpaceContentPage.addFlexibleSpace(flexibleSpaceData)
                                                                                       .saveFlexibleSpaceLanding()
                                                                                       .verifyDrupalFlexibleSpaceCreated();

        logStep("Hover over Content Tab");
        logStep("Click on Landing page");
        AddLandingPage addLandingPage = flexibleSpaceLandingPage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.LANDING_PAGE), AddLandingPage.class);

        logStep("Enter test name in the Title field");
        logStep("Enter Flexible space title in sidebar field");
        logStep("Click Save!!");
        AddLandingPageModel addLandingPageData = landingPageData.stream()
                                                                .filter(d -> d instanceof AddLandingPageModel)
                                                                .map(c -> (AddLandingPageModel) c)
                                                                .findFirst()
                                                                .get();

        ResultLandingPage resultLandingPage = addLandingPage.fillIn(landingPageData)
                                                            .clickSaveButton()
                                                            .verifyListOfLinkFlexibleSpace(flexibleContentSpaceListOfLinksModel);

        logStep("Edit SideBar data and Save");
        resultLandingPage.clickOnEditSidebarLink()
                         .editFlexibleSpaceData(editLandingPageData)
                         .saveFlexibleSpaceLanding();

        logStep("Verify Edited SidBar in Landing page and Save");
        FlexibleContentSpaceListOfLinksModel flexibleSpaceLinkListModel = editLandingPageData.stream()
                                                                                             .filter(d -> d instanceof FlexibleContentSpaceListOfLinksModel)
                                                                                             .map(c -> (FlexibleContentSpaceListOfLinksModel) c)
                                                                                             .findFirst()
                                                                                             .get();
        ContentPage contentPage = flexibleSpaceLandingPage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.CONTENT), ContentPage.class);
        contentPage.fillSearchCriteriaAndFilter(ContentSearchModel.landingPageSearchData())
                   .clickContentLink(ResultLandingPage.class, addLandingPageData.getLandingPageTitle())
                   .verifySidebarTitle(flexibleSpaceLinkListModel.getTitle());
    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
                {
                        List.of(new FlexibleSpaceBaseModel(),
                                new FlexibleContentSpaceListOfLinksModel(CONTENT, 0, "Test Content " + timeStampFormat(DATE_WITH_HYPHEN_PATTERN), "Memorial Benefits (221)", "Test memorial Benefits", Map.of("/benefits/tricare/other-programs", "Test Tricare's Other Programs"))),
                        List.of(new AddLandingPageModel("Test Landing Page " + timeStampFormat(DATE_WITH_HYPHEN_PATTERN), true, null, null, List.of("Test Title " + timeStampFormat(PATTERN)), null, List.of(Category.BENEFITS), null)),
                        List.of(new FlexibleSpaceBaseModel("Edit Test Title " + timeStampFormat(PATTERN)), new FlexibleContentSpaceListOfLinksModel(CONTENT, 0, "Edit Test Content " + timeStampFormat(DATE_WITH_HYPHEN_PATTERN), null, null, null))

                }
        };
    }
}
