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
import drupal.ui.pages.components.content_list.MoreLinkModel;
import drupal.ui.pages.components.content_list.VideoContentModel;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static common.CommonMethods.timeStampFormat;
import static drupal.models.AddContentListModel.videoContents;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.models.UserModel.loadUserModel;
import static drupal.ui.pages.components.content_list.AddContentListContentPage.ContentItem.BRIGHTCOVE_LIST;
import static java.lang.String.format;

public class TC249672_Create_an_Important_Contacts_List_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateAnImportantContactsList() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "brightcoveCuratedListData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249672")})
    @TestCaseId("249672")
    public void createAnImportantContentsList(List<ISectionDataModel> addBrightcoveSectionData) {
        logStep("1. Login to Drupal");
        logStep("2. Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("3. Hover the Content Tab");
        logStep("4. Hover over Components and Select 'CONTENT LISTS'");
        ContentListsPage contentListsPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.COMPONENTS, ComponentsMenu.CONTENT_LISTS), ContentListsPage.class);

        logStep("5. Click the 'Add Content List' radio button");
        logStep(format("Link -  %s/admin/structure/eck/content_list/content/add", Configuration.baseUrl));
        AddContentListContentPage addContentListContentPage = contentListsPage.clickAddContentListButton();

        logStep("6. Select Brightcove Curated List");
        logStep("7. Select Entity Type (Content or Snippet) and begin typing the article or snippet name. Select the article from the dropdown.");
        logStep("Test QA Curated List Articles  1) Fort Eustis Welcome & Visitors Center 2) Hampton Roads Military Bases Navy Federal Credit Union 3) Naval Station" +
                        " Norfolk Willoughby Child Development Center 4) Naval Air Station Oceana EFMP - Family Support 5) Coast Guard Sector Hampton Roads ID/CAC Card Processing");
        logStep("8. Enter test name in the Title Field");
        logStep("Example: Test Important Contacts YYYYMMDD  Ex. Test Important Contacts 20210617");
        logStep("9. In the MORE LINK section: 1. Type OAS Homepage in the URL field 2. Type 'View More Contacts' in the Link Text field");
        logStep(format("OAS Homepage Link %s/oas-homepage", Configuration.baseUrl));
        AddBrightcoveCuratedListPage brightcoveListPage = addContentListContentPage.clickContentListItem(BRIGHTCOVE_LIST, AddBrightcoveCuratedListPage.class);

        logStep("10. Click Save");
        logStep(format("Drupal Brightcove Curated List (Content) is created: %s/admin/structure/eck/entity/content_list/###", Configuration.baseUrl));
        ContentListLandingPage contentListLandingPage = brightcoveListPage.fillIn(addBrightcoveSectionData)
                                                                          .clickSaveButton()
                                                                          .verifyDrupalContentCreated();

        logStep("11. Edit the title and Click Save -> verify the title saved");
        ContentListTitleModel contentListTitleModel = addBrightcoveSectionData.stream()
                                                                              .filter(d -> d instanceof ContentListTitleModel)
                                                                              .map(c -> (ContentListTitleModel) c)
                                                                              .findFirst()
                                                                              .get();
        contentListTitleModel.setTitle("Edit " + contentListTitleModel.getTitle());
        contentListLandingPage.clickEditTab()
                              .fillIn(List.of(contentListTitleModel))
                              .clickSaveButton()
                              .verifyBrightCoveVideoContentCreated(contentListTitleModel.getTitle());
    }

    @DataProvider
    public Object[][] brightcoveCuratedListData() {
        return new Object[][]{
                {
                        List.of(
                                new ContentListTitleModel("Test " + timeStampFormat(PATTERN)),
                                new VideoContentModel(videoContents()),
                                new MoreLinkModel("Exchanges Soon Opening to Millions of Veterans (181)", "test")
                        )
                }
        };
    }

}
