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
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListViewModel;
import drupal.ui.pages.add_content.landing_page.AddLandingPage;
import drupal.ui.pages.add_content.landing_page.AddLandingPageModel;
import drupal.ui.pages.add_content.landing_page.EditLandingPage;
import drupal.ui.pages.add_content.landing_page.ResultLandingPage;
import drupal.ui.pages.add_content.landing_page.ThumbnailImageModel;
import drupal.ui.pages.components.content_list.AddContentListContentPage;
import drupal.ui.pages.components.content_list.AddCuratedListPage;
import drupal.ui.pages.components.content_list.ContentListLandingPage;
import drupal.ui.pages.components.content_list.ContentListTitleModel;
import drupal.ui.pages.components.content_list.VideoContentModel;
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
import java.util.stream.Collectors;

import static common.CommonMethods.timeStampFormat;
import static drupal.enums.content.FlexibleContentSpacePosition.CONTENT;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.models.UserModel.loadUserModel;
import static drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListViewModel.DisplayMode.ARTICLE_LIST_WITH_LEFT_FLOATED_THUMBNAILS;
import static drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListViewModel.DisplayMode.ONE_COLUMN_LINKS;
import static drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListViewModel.DisplayMode.TWO_COLUMN_BULLETS_LINKS;
import static drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListViewModel.ListOrder.NONE;
import static drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListViewModel.ListType.AUTO_GENERATED_LIST;
import static drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListViewModel.ListType.CURATED_LIST;

public class TC249876_Create_A_Sidebar_Video_Listviews_And_Video_Taxonomy_Curated_List_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateASidebarVideoListviewsAndTaxonomyCuratedList() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "videoListTaxonomyCuratedData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249876")})
    @TestCaseId("249876")
    public void createASidebarVideoListviewsAndTaxonomyCuratedList(List<ISectionDataModel> videoListViewData, List<ISectionDataModel> contentListData, ContentSearchModel contentSearchData, List<ISectionDataModel> landingPageData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Structure Tab");
        logStep("Click the ECK Entity Types");
        ECKEntityTypesPage eckEntityTypes = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.STRUCTURE, StructureMenu.ECK_ENTITY_TYPES), ECKEntityTypesPage.class);

        logStep("Go to CONTENT LIST and select 'Add Content' Button under the OPERATIONS column");
        logStep("Select Curated List");
        logStep("Enter test name in the Title Field");
        logStep("Select Entity Type (Content or Snippet) and begin typing the article or snippet name. Select the article from the dropdown.");
        logStep("In the MORE LINK section:\n" +
                        "1. Type OAS Homepage in the URL field\n" +
                        "2. Type 'View More Articles' in the Link Text field");
        logStep("Click save");
        ContentListLandingPage contentListLandingPage = eckEntityTypes.clickEntityType(ECKEntityTypesPage.ECKEntityType.CONTENT_LIST, ECKEntityTypesPage.ECKEntityOperationType.ADD_CONTENT, AddContentListContentPage.class)
                                                                      .clickContentListItem(AddContentListContentPage.ContentItem.CURATED_LIST, AddCuratedListPage.class)
                                                                      .fillIn(contentListData)
                                                                      .clickSaveButton();
        VideoContentModel videoContent = contentListData.stream()
                                                        .filter(d -> d instanceof VideoContentModel)
                                                        .map(c -> (VideoContentModel) c)
                                                        .findFirst()
                                                        .get();

        logStep("Hover the Structure Tab");
        logStep("Click the ECK Entity Types");
        eckEntityTypes = contentListLandingPage.navigateTo(List.of(AdministrationToolbar.TrayItem.STRUCTURE, StructureMenu.ECK_ENTITY_TYPES), ECKEntityTypesPage.class);

        logStep("Go to FLEXIBLE SPACE and select 'Content List' Button under the OPERATIONS column");
        FlexibleSpacesPage flexibleSpacesPage = eckEntityTypes.clickEntityType(ECKEntityTypesPage.ECKEntityType.FLEXIBLE_SPACE, ECKEntityTypesPage.ECKEntityOperationType.CONTENT_LIST, FlexibleSpacesPage.class);

        logStep("Click Add Flexible space button and navigate to 'Add Flexible space content' page");
        AddFlexibleSpaceContentPage addFlexibleSpaceContentPage = flexibleSpacesPage.clickFlexibleSpaceTab();

        logStep("Enter title input for 'Add Flexible space content' page");
        logStep("click 'Add list view'");
        logStep("Fill in all fields in Add list view and 'Add Flexible space content' page");
        logStep("click save");
        logStep("Validate: Drupal Regular Flexible space is created: " + Configuration.baseUrl + "/admin/structure/eck/entity/flexible_space/####");
        FlexibleSpaceBaseModel flexibleSpaceBaseData = videoListViewData.stream()
                                                                        .filter(d -> d instanceof FlexibleSpaceBaseModel)
                                                                        .map(c -> (FlexibleSpaceBaseModel) c)
                                                                        .findFirst()
                                                                        .get();
        List<String> listViewData = videoListViewData.stream()
                                                     .filter(d -> d instanceof FlexibleContentSpaceListViewModel)
                                                     .map(c -> (FlexibleContentSpaceListViewModel) c)
                                                     // TODO syntax lambda can be replaced with method reference
                                                     .map(FlexibleContentSpaceListViewModel -> (FlexibleContentSpaceListViewModel.getTitle()))
                                                     .collect(Collectors.toList());

        FlexibleSpaceLandingPage flexibleSpaceLandingPage = addFlexibleSpaceContentPage.addFlexibleSpace(videoListViewData)
                                                                                       .saveFlexibleSpaceLanding()
                                                                                       .verifyDrupalFlexibleSpaceCreated();

        logStep("Hover the Content Tab");
        logStep("Hover over Add Content");
        logStep("Click on Landing Page");
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

        logStep("In the Search fields, Enter Article Title and select the Article type, Published status field");
        logStep("Click EDIT in the Operations Column for the Crated Test Article");
        logStep("In the Sidebar field, type and select the newly created sidebar");
        logStep("click save");
        logStep("Verify Article updated in the edit landing page");
        contentPage.fillSearchCriteriaAndFilter(contentSearchData)
                   .clickEditButton(EditLandingPage.class, -1, addLandingPageData.getLandingPageTitle())
                   .addSidebar(flexibleSpaceBaseData.getTitle())
                   .clickSaveButton(ContentPage.class)
                   .verifyUpdatedMessage()
                   .clickOnUpdatedArticleLink(ResultLandingPage.class)
                   .resultLandingPageValidation(videoContent, listViewData);

    }

    @DataProvider
    public Object[][] videoListTaxonomyCuratedData() {
        return new Object[][]{
                {
                        List.of(
                                new FlexibleContentSpaceListViewModel(CONTENT, 0, "Test 1 column links", CURATED_LIST, ONE_COLUMN_LINKS, List.of("Test " + timeStampFormat(PATTERN)), null, null, "5", false, NONE),
                                new FlexibleContentSpaceListViewModel(CONTENT, 0, "Test 2 column bullets (links)", CURATED_LIST, TWO_COLUMN_BULLETS_LINKS, List.of("Video Channels (1686)"), null, null, "0", false, NONE),
                                new FlexibleContentSpaceListViewModel(CONTENT, 0, "Test Video List - Floated Thumbnails", AUTO_GENERATED_LIST, ARTICLE_LIST_WITH_LEFT_FLOATED_THUMBNAILS, null, null, "Editors' Picks", "0", true, NONE),
                                new FlexibleSpaceBaseModel()
                        ),
                        List.of(
                                new ContentListTitleModel("Test " + timeStampFormat(PATTERN)), VideoContentModel.getVideoTaxonomyCuratedList()
                        ),
                        ContentSearchModel.videoListViewData(),
                        List.of(
                                new AddLandingPageModel(),
                                new ThumbnailImageModel("Test Image")
                        )
                }
        };
    }

}