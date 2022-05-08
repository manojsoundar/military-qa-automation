package military.drupal.ui.content_menu;

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
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListViewModel;
import drupal.ui.pages.add_content.landing_page.AddLandingPage;
import drupal.ui.pages.add_content.landing_page.AddLandingPageModel;
import drupal.ui.pages.add_content.landing_page.ResultLandingPage;
import drupal.ui.pages.components.content_list.AddBrightcoveCuratedListPage;
import drupal.ui.pages.components.content_list.ContentListLandingPage;
import drupal.ui.pages.components.content_list.ContentListTitleModel;
import drupal.ui.pages.components.content_list.ContentListsPage;
import drupal.ui.pages.components.content_list.EditCuratedListPage;
import drupal.ui.pages.components.content_list.MoreLinkModel;
import drupal.ui.pages.components.content_list.VideoContentModel;
import drupal.ui.pages.content.ContentPage;
import lombok.extern.log4j.Log4j2;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static common.CommonMethods.timeStampFormat;
import static drupal.enums.content.EntityType.BRIGHTCOVE_VIDEO;
import static drupal.enums.content.EntityType.CONTENT;
import static drupal.enums.content.FlexibleContentSpacePosition.BELOW;
import static drupal.models.AddContentListModel.updateBrightcoveCuratedListData;
import static drupal.models.ContentSearchModel.ContentType.LANDING_PAGE;
import static drupal.models.ContentSearchModel.published;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.models.UserModel.loadUserModel;
import static drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListViewModel.DisplayMode.ARTICLE_LIST_WITH_LEFT_FLOATED_THUMBNAILS;
import static drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListViewModel.ListOrder.NONE;
import static drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListViewModel.ListType.CURATED_LIST;
import static drupal.ui.pages.add_content.landing_page.AddLandingPageModel.getCurateListBody;
import static drupal.ui.pages.components.content_list.AddContentListContentPage.ContentItem.BRIGHTCOVE_LIST;

@Log4j2
public class TC249869_Edit_A_Brightcove_Curated_List_Include_Articles_Videos_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionBrightcoveCuratedList() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "contentData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249869")})
    @TestCaseId("249869")
    public void editBrightcoveCuratedList(List<ISectionDataModel> addBrightcoveModelList, List<ISectionDataModel> landingPageModelList) {
        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);
        logStep("Creating Brightcove curated list to perform edit function");
        ContentListsPage contentListsPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.COMPONENTS, ComponentsMenu.CONTENT_LISTS), ContentListsPage.class);
        ContentListLandingPage contentListLandingPage = contentListsPage.clickAddContentListButton()
                                                                        .clickContentListItem(BRIGHTCOVE_LIST, AddBrightcoveCuratedListPage.class)
                                                                        .fillIn(addBrightcoveModelList)
                                                                        .clickSaveButton();

        logStep("Pre-condition to create a Landing page");
        logStep("Hover over Content Tab");
        logStep("Hover over Components and Select CONTENT LISTS");
        logStep("Search for a Test List created above and press Filter");
        ContentListTitleModel contentListTitleModel = addBrightcoveModelList.stream()
                                                                            .filter(d -> d instanceof ContentListTitleModel)
                                                                            .map(c -> (ContentListTitleModel) c)
                                                                            .findFirst()
                                                                            .get();

        landingPageModelList.add(new FlexibleContentSpaceListViewModel(BELOW, 0, "Test Brightcove Curated Lading Page", CURATED_LIST, ARTICLE_LIST_WITH_LEFT_FLOATED_THUMBNAILS, List.of(contentListTitleModel.getTitle()), null, null, null, true, NONE));
        String updateTitle = "Edited Test Brightcove Curated List " + timeStampFormat(PATTERN);
        EditCuratedListPage editCuratedListPage = contentListLandingPage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.LANDING_PAGE), AddLandingPage.class)
                                                                        .fillIn(landingPageModelList)
                                                                        .clickSaveButton()
                                                                        .navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.COMPONENTS, ComponentsMenu.CONTENT_LISTS), ContentListsPage.class)
                                                                        .filterContentList(contentListTitleModel.getTitle(), updateBrightcoveCuratedListData())
                                                                        .clickContentLink(ContentListLandingPage.class, contentListTitleModel.getTitle())
                                                                        .clickEditTab();

        List<String> videoContent = List.of("1950s Desert Rock Nuclear Tests (66761)", "F-35C Lightning II Testing aboard USS Nimitz (103211)");
        List<String> brightcoveUpdatedVideoContentList = editCuratedListPage.editBrightcoveCuratedListData(updateBrightcoveCuratedListData(), videoContent, updateTitle)
                                                                            .getEntityListData();

        logStep("Go to the Test landing page to confirm the updates");
        logStep("--Name of the curated list, The order of the articles,3. The addition of the new article/video are updated");
        AddLandingPageModel landingPageModel = landingPageModelList.stream()
                                                                   .filter(d -> d instanceof AddLandingPageModel)
                                                                   .map(c -> (AddLandingPageModel) c)
                                                                   .findFirst()
                                                                   .get();
        ContentSearchModel contentSearchModel = new ContentSearchModel(landingPageModel.getLandingPageTitle(), LANDING_PAGE, published(), null);
        editCuratedListPage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.CONTENT), ContentPage.class)
                           .fillSearchCriteriaAndFilter(contentSearchModel)
                           .clickContentLink(ResultLandingPage.class, landingPageModel.getLandingPageTitle())
                           .verifyBrightcoveCuratedListView(brightcoveUpdatedVideoContentList);
    }

    @DataProvider
    public Object[][] contentData() {
        return new Object[][]{
                {
                        List.of(
                                new ContentListTitleModel("Test Brightcove Video Curated List " + timeStampFormat(PATTERN)),
                                new VideoContentModel(Map.of("SHOT Show 2020: LWRC International Shows Off its Hot New Pistol-Caliber Carbine (161341)", BRIGHTCOVE_VIDEO, "360° View of Seahawk Helicopter Gunner (158141)", BRIGHTCOVE_VIDEO, "Navy Hopes Landing Tweaks Will Increase Osprey Cargo Capacity", CONTENT)),
                                new MoreLinkModel("OAS Homepage (4026)", "View More Videos")
                        ),
                        new ArrayList<ISectionDataModel>() {{
                            add(new AddLandingPageModel("Test Landing Page for brightcove curated List " + timeStampFormat(PATTERN), false, "Test Summary", getCurateListBody(), null, null, null, null));
                        }}
                }
        };
    }

}
