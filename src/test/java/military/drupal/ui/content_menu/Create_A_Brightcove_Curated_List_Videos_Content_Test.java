package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.attribute.Attributes;
import com.epam.reportportal.annotations.attribute.MultiValueAttribute;
import drupal.enums.content.ComponentsMenu;
import drupal.enums.content.ContentMenu;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.components.content_list.AddBrightcoveCuratedListPage;
import drupal.ui.pages.components.content_list.AddContentListContentPage;
import drupal.ui.pages.components.content_list.ContentListLandingPage;
import drupal.ui.pages.components.content_list.ContentListTitleModel;
import drupal.ui.pages.components.content_list.ContentListsPage;
import drupal.ui.pages.components.content_list.MoreLinkModel;
import drupal.ui.pages.components.content_list.VideoContentModel;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static common.CommonMethods.timeStampFormat;
import static drupal.enums.content.EntityType.BRIGHTCOVE_VIDEO;
import static drupal.enums.content.EntityType.CONTENT;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.models.UserModel.loadUserModel;
import static java.lang.String.format;

public class Create_A_Brightcove_Curated_List_Videos_Content_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateABrightcoveCuratedListVideos() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "contentListData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(multiValueAttributes = {@MultiValueAttribute(key = "TestCaseIds", values = {"249655", "249654"})})
    public void createBrightcoveCuratedListVideos(String testCaseId, List<ISectionDataModel> addContentListModelsData) {

        logStep(format("Running Test Case : %s", testCaseId));
        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over Components");
        logStep("Click on 'Content List'");
        ContentListsPage contentListsPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.COMPONENTS, ComponentsMenu.CONTENT_LISTS), ContentListsPage.class);

        logStep("Click 'Brightcove Curated List' button");
        logStep("Enter test name in the Title Field");
        logStep("Leave Entity Type as Brightcove Video and begin typing the article or snippet name. Select the video from the dropdown");
        logStep("Click save");
        logStep("Drupal Brightcove Curated List (Video) is: " + Configuration.baseUrl + "/admin/structure/eck/entity/content_list/#####");
        ContentListTitleModel contentListTitleModel = addContentListModelsData.stream()
                                                                              .filter(d -> d instanceof ContentListTitleModel)
                                                                              .map(c -> (ContentListTitleModel) c)
                                                                              .findFirst()
                                                                              .get();

        ContentListLandingPage contentListLandingPage = contentListsPage.clickAddContentListButton()
                                                                        .clickContentListItem(AddContentListContentPage.ContentItem.BRIGHTCOVE_LIST, AddBrightcoveCuratedListPage.class)
                                                                        .fillIn(addContentListModelsData)
                                                                        .clickSaveButton()
                                                                        .verifyBrightCoveVideoContentCreated(contentListTitleModel.getTitle());

        logStep("Edit the title and Click Save -> verify the title saved");
        contentListTitleModel.setTitle("Edit " + contentListTitleModel.getTitle());
        contentListLandingPage.clickEditTab()
                              .fillIn(List.of(contentListTitleModel))
                              .clickSaveButton()
                              .verifyBrightCoveVideoContentCreated(contentListTitleModel.getTitle());
    }


    @DataProvider
    public Object[][] contentListData() {
        return new Object[][]{
                {
                        "TC249655",
                        List.of(
                                new ContentListTitleModel("Test Brightcove Video Curated List " + timeStampFormat(PATTERN)),
                                new VideoContentModel(Map.of("Emotional Reunion for Army Family", BRIGHTCOVE_VIDEO)),
                                new MoreLinkModel("Military Videos (4261)", "View More Videos")
                        )
                },
                {
                        "TC249654",
                        List.of(
                                new ContentListTitleModel("Test Content Snippet Curated List " + timeStampFormat(PATTERN)),
                                new VideoContentModel(Map.of("Ask Stew: Swimming for Special Ops", CONTENT)),
                                new MoreLinkModel("OAS Homepage (4026)", "View More Articles")
                        )
                },
        };
    }

}