package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
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
import drupal.ui.pages.components.content_list.EditCuratedListPage;
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
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.models.UserModel.loadUserModel;

public class TC0_Content_List_Brightcove_Curated_List_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionContentListBrightcoveCuratedList() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(enabled = false, dataProvider = "contentData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "0")})
    @TestCaseId("0")
    public void createContentListBrightcoveCuratedList(List<ISectionDataModel> brightcoveSectionData) {
        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over Components");
        logStep("Click on 'Content List'");
        ContentListsPage contentListsPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.COMPONENTS, ComponentsMenu.CONTENT_LISTS), ContentListsPage.class);

        logStep("Click 'Brightcove Curated List' button");
        logStep("Enter data in all fields ");
        logStep("Click save");
        ContentListLandingPage contentListLandingPage = contentListsPage.clickAddContentListButton()
                                                                        .clickContentListItem(AddContentListContentPage.ContentItem.BRIGHTCOVE_LIST, AddBrightcoveCuratedListPage.class)
                                                                        .fillIn(brightcoveSectionData)
                                                                        .clickSaveButton();

        logStep("Validate: Drupal Regular Snippet is created: " + Configuration.baseUrl + "/admin/structure/eck/entity/content_list/####");
        logStep("Verify Edit function");
        logStep("Click on Edit Tab");
        ContentListTitleModel contentListTitleModel = brightcoveSectionData.stream()
                                                                           .filter(d -> d instanceof ContentListTitleModel)
                                                                           .map(c -> (ContentListTitleModel) c)
                                                                           .findFirst()
                                                                           .get();
        EditCuratedListPage editCuratedListPage = contentListLandingPage.verifyBrightCoveVideoContentCreated(contentListTitleModel.getTitle())
                                                                        .clickEditTab();
        logStep("Check if Test tile is not empty.If empty input test title");
        logStep("Click Save");
        logStep("Verify if curated list updated");
        contentListTitleModel.setTitle("Test Brightcove Curated List " + timeStampFormat(PATTERN));
        editCuratedListPage.fillIn(List.of(contentListTitleModel))
                           .clickSaveButton()
                           .verifyDrupalContentUpdated(contentListTitleModel.getTitle());
    }

    @DataProvider
    public Object[][] contentData() {
        return new Object[][]{
                {
                        List.of(
                                new ContentListTitleModel("Test " + timeStampFormat(PATTERN)),
                                new VideoContentModel(Map.of("360° View of Seahawk Helicopter Gunner (158141)", BRIGHTCOVE_VIDEO)),
                                new MoreLinkModel("Exchanges Soon Opening to Millions of Veterans (181)", "test")
                        )
                }
        };
    }

}
