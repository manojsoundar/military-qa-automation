package military.drupal.ui.structure_menu.structure_menu_desktop;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.ContentMenu;
import drupal.enums.structure.StructureMenu;
import drupal.models.ContentSearchModel;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceBlockModel;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceCTAModel;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceSnippetNoLinkModel;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceWidgetModel;
import drupal.ui.pages.add_content.landing_page.AddLandingPage;
import drupal.ui.pages.add_content.landing_page.AddLandingPageModel;
import drupal.ui.pages.add_content.landing_page.EditLandingPage;
import drupal.ui.pages.add_content.landing_page.ResultLandingPage;
import drupal.ui.pages.add_content.landing_page.ThumbnailImageModel;
import drupal.ui.pages.components.flexible_space.AddFlexibleSpaceContentPage;
import drupal.ui.pages.components.flexible_space.FlexibleSpaceBaseModel;
import drupal.ui.pages.components.flexible_space.FlexibleSpaceLandingPage;
import drupal.ui.pages.components.flexible_space.FlexibleSpacesPage;
import drupal.ui.pages.content.ContentPage;
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

public class TC250024_Create_Sidebar_With_Blocks_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateSidebarBlocks() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "setSidebarBlockData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "250024")})
    @TestCaseId("250024")
    public void createSidebarWithBlocks(List<ISectionDataModel> createSidebarData, List<ISectionDataModel> landingPageData, ContentSearchModel contentSearchData, List<ISectionDataModel> flexibleEditData) {
        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Structure Tab");
        logStep("Select ECK Entity Types");
        ECKEntityTypesPage eckEntityTypesPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.STRUCTURE, StructureMenu.ECK_ENTITY_TYPES), ECKEntityTypesPage.class);

        logStep("Go to FLEXIBLE SPACE and select 'Content List' Button under the OPERATIONS column");
        FlexibleSpacesPage flexibleSpacesPage = eckEntityTypesPage.clickEntityType(ECKEntityTypesPage.ECKEntityType.FLEXIBLE_SPACE, ECKEntityTypesPage.ECKEntityOperationType.CONTENT_LIST, FlexibleSpacesPage.class);

        logStep("Click Add Flexible space button and navigate to 'Add Flexible space content' page");
        AddFlexibleSpaceContentPage addFlexibleSpaceContentPage = flexibleSpacesPage.clickFlexibleSpaceTab();

        logStep("Enter test name in the Title field");
        logStep("In the Content section, click Add Block");
        logStep("Fill in all fields in CONTENT section in the 'Add Flexible space content' page");
        logStep("click save");
        logStep("Validate: Drupal Regular Flexible space is created: " + Configuration.baseUrl + "/admin/structure/eck/entity/flexible_space/####");

        FlexibleSpaceBaseModel flexibleSpaceBaseData = createSidebarData.stream()
                                                                        .filter(d -> d instanceof FlexibleSpaceBaseModel)
                                                                        .map(c -> (FlexibleSpaceBaseModel) c)
                                                                        .findFirst()
                                                                        .get();

        FlexibleContentSpaceCTAModel addFlexiSpaceCtaContentData = createSidebarData.stream()
                                                                                    .filter(d -> d instanceof FlexibleContentSpaceCTAModel)
                                                                                    .map(c -> (FlexibleContentSpaceCTAModel) c)
                                                                                    .findFirst()
                                                                                    .get();

        FlexibleSpaceLandingPage flexibleSpaceLandingPage = addFlexibleSpaceContentPage.addFlexibleSpace(createSidebarData)
                                                                                       .saveFlexibleSpaceLanding()
                                                                                       .verifyDrupalFlexibleSpaceCreated();

        logStep("Pre-condition to create a Landing page");
        AddLandingPage addLandingPage = flexibleSpaceLandingPage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.LANDING_PAGE), AddLandingPage.class);
        AddLandingPageModel addLandingPageData = landingPageData.stream()
                                                                .filter(d -> d instanceof AddLandingPageModel)
                                                                .map(c -> (AddLandingPageModel) c)
                                                                .findFirst()
                                                                .get();
        ResultLandingPage resultLandingPage = addLandingPage.fillIn(landingPageData)
                                                            .clickSaveButton()
                                                            .validateLandingPage(addLandingPageData);

        logStep("Click on Content Tab");
        ContentPage contentPage = resultLandingPage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.CONTENT), ContentPage.class);

        logStep("In the Search fields, Enter Landing Page Title and select the Content type, Published status field");
        logStep("Click EDIT in the Operations Column for the Crated Landing Page");
        logStep("Press Save");
        EditLandingPage editLandingPage = contentPage.fillSearchCriteriaAndFilter(contentSearchData)
                                                     .clickEditButton(EditLandingPage.class, -1, addLandingPageData.getLandingPageTitle());
        editLandingPage.addSidebar(flexibleSpaceBaseData.getTitle())
                       .clickSaveButton(ResultLandingPage.class);
        resultLandingPage = contentPage.verifyUpdatedMessage()
                                       .clickContentLink(ResultLandingPage.class, contentSearchData.getTitle());

        logStep("Validation: Created Sidebar value displaying landing page");
        logStep("Edit SideBar data and Save");
        resultLandingPage.verifyCreatedSidebarBlock(contentSearchData.getTitle(), addFlexiSpaceCtaContentData.getCallToAction())
                         .clickOnEditSidebarLink()
                         .editFlexibleSpaceData(flexibleEditData)
                         .saveFlexibleSpaceLanding();

        logStep("Verify Edited SidBar in Landing page and Save");
        addLandingPageData.setSideBars(List.of(flexibleSpaceBaseData.getTitle()));
        flexibleSpaceLandingPage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.LANDING_PAGE), AddLandingPage.class)
                                .fillIn(landingPageData)
                                .clickSaveButton()
                                .verifyLandingPageUrl(addLandingPageData);
    }

    @DataProvider
    public Object[][] setSidebarBlockData() {
        return new Object[][]{
                {
                        List.of(
                                new FlexibleSpaceBaseModel(),
                                new FlexibleContentSpaceCTAModel(CONTENT, 0, "2019 Veterans Day Restaurant Deals & Discounts (6)"),
                                new FlexibleContentSpaceCTAModel(CONTENT, 0, "Get the Deals & Discounts Newsletter (26)"),
                                new FlexibleContentSpaceBlockModel(CONTENT, 0, null, "AppNexus", "AppNexus", false, "Top - 728x90, 970x90, Brandscape Top"),
                                new FlexibleContentSpaceWidgetModel(CONTENT, 0, "WCA Contact Form (16)"),
                                new FlexibleContentSpaceSnippetNoLinkModel(CONTENT, 0, "Test Snippet No Link 10-7 (17216)"),
                                new FlexibleContentSpaceBlockModel(CONTENT, 0, null, "AppNexus", "AppNexus", false, "Featured Placement 1 - 300x100"),
                                new FlexibleContentSpaceBlockModel(CONTENT, 0, null, "My Membership", "My Membership", false, null),
                                new FlexibleContentSpaceBlockModel(CONTENT, 0, null, "AppNexus", "AppNexus", false, "Veteran Jobs FP1 - 300x100")
                        ),
                        List.of(
                                new AddLandingPageModel(),
                                new ThumbnailImageModel("Test Image")
                        ),
                        ContentSearchModel.landingPageSearchData(),
                        List.of(
                                new FlexibleSpaceBaseModel("Edit Test Title " + timeStampFormat(PATTERN)),
                                new FlexibleContentSpaceWidgetModel(CONTENT, 3, "Edit WCA Contact Form (16)"),
                                new FlexibleContentSpaceSnippetNoLinkModel(CONTENT, 4, "Edit Test Snippet No Link 10-7 (17216)")
                        )
                }
        };
    }

}
