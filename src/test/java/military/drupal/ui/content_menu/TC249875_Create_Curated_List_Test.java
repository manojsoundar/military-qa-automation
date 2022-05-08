package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.ComponentsMenu;
import drupal.enums.content.ContentMenu;
import drupal.enums.content.EntityType;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.components.content_list.AddContentListContentPage;
import drupal.ui.pages.components.content_list.AddCuratedListPage;
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
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.models.UserModel.loadUserModel;

public class TC249875_Create_Curated_List_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateCuratedList() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "contentData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249875")})
    @TestCaseId("249875")
    public void createCuratedList(List<ISectionDataModel> curatedListSectionData) {
        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over Components");
        logStep("Hover over Components and Select CONTENT LISTS");
        ContentListsPage contentListsPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.COMPONENTS, ComponentsMenu.CONTENT_LISTS), ContentListsPage.class);

        logStep("Click the 'Add Content List' radio button");
        logStep("Select Curated List");
        logStep("Enter test name in the Title Field");
        logStep("Select Entity Type (Content or Snippet) and begin typing the article or snippet name. Select the article from the dropdown.");
        logStep("In the MORE LINK section: 1. Type OAS Homepage in the URL field\n" +
                        "2. Type 'View More Articles' in the Link Text field");
        logStep("Click save");
        logStep("Validate: Drupal Regular Snippet is created: " + Configuration.baseUrl + "/admin/structure/eck/entity/content_list/####");
        ContentListLandingPage contentListLandingPage = contentListsPage.clickAddContentListButton()
                                                                        .clickContentListItem(AddContentListContentPage.ContentItem.CURATED_LIST, AddCuratedListPage.class)
                                                                        .fillIn(curatedListSectionData)
                                                                        .clickSaveButton()
                                                                        .verifyDrupalContentCreated();

        logStep("Verify Edit function");
        logStep("Click on Edit Tab");
        logStep("Check if Test tile is not empty.If empty input test title");
        logStep("Click Save");
        logStep("Verify if curated list updated");
        ContentListTitleModel contentListTitleModel = curatedListSectionData.stream()
                                                                            .filter(d -> d instanceof ContentListTitleModel)
                                                                            .map(c -> (ContentListTitleModel) c)
                                                                            .findFirst()
                                                                            .get();
        contentListTitleModel.setTitle("Test Curated List New " + timeStampFormat(PATTERN));
        contentListLandingPage.clickEditTab()
                              .fillIn(List.of(contentListTitleModel))
                              .clickSaveButton()
                              .verifyDrupalContentUpdated(contentListTitleModel.getTitle());
    }

    @DataProvider
    public Object[][] contentData() {
        return new Object[][]{
                {
                        List.of(
                                new ContentListTitleModel("Test Curated List " + timeStampFormat(PATTERN)),
                                new VideoContentModel(Map.of("Budget Request for Arlington Cemetery Nearly Triples Amid Plans for Major Expansion", EntityType.CONTENT)),
                                new MoreLinkModel("OAS Homepage (4026)", "View More Articles")
                        )
                }
        };
    }

}