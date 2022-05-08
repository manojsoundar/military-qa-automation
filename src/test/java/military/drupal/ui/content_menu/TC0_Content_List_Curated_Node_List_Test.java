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
import drupal.ui.pages.components.content_list.AddContentListContentPage;
import drupal.ui.pages.components.content_list.AddCuratedNodeListPage;
import drupal.ui.pages.components.content_list.ContentListLandingPage;
import drupal.ui.pages.components.content_list.ContentListTitleModel;
import drupal.ui.pages.components.content_list.ContentListsPage;
import drupal.ui.pages.components.content_list.CuratedNodesListModel;
import drupal.ui.pages.components.content_list.EditCuratedListPage;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.models.UserModel.loadUserModel;

public class TC0_Content_List_Curated_Node_List_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionContentListCuratedNodeList() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(enabled = false, dataProvider = "contentData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "0")})
    @TestCaseId("0")
    public void createContentListCuratedNodeList(List<ISectionDataModel> curatedNodeData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over Components");
        logStep("Click on 'Content List'");
        ContentListsPage contentListsPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.COMPONENTS, ComponentsMenu.CONTENT_LISTS), ContentListsPage.class);

        logStep("Click '+Curated List' button");
        AddContentListContentPage addContentListContentPage = contentListsPage.clickAddContentListButton();
        AddCuratedNodeListPage addCuratedNodeListPage = addContentListContentPage.clickContentListItem(AddContentListContentPage.ContentItem.CURATED_NODE_LIST, AddCuratedNodeListPage.class);

        logStep("Enter data in all fields ");
        logStep("Click save");
        logStep("Validate: Drupal Regular Snippet is created: " + Configuration.baseUrl + "/admin/structure/eck/entity/content_list/####");
        ContentListLandingPage contentListLandingPage = addCuratedNodeListPage.fillIn(curatedNodeData)
                                                                              .clickSaveButton()
                                                                              .verifyDrupalContentCreated();

        logStep("Verify Edit function");
        logStep("Click on Edit Tab");
        EditCuratedListPage editCuratedListPage = contentListLandingPage.clickEditTab();
        logStep("Check if Nodes text is not empty.If empty input node text");
        logStep("Click Save");
        logStep("Verify Curated node list updated");
        CuratedNodesListModel curatedNodesListModel = curatedNodeData.stream()
                                                                     .filter(d -> d instanceof CuratedNodesListModel)
                                                                     .map(c -> (CuratedNodesListModel) c)
                                                                     .findFirst()
                                                                     .get();
        curatedNodesListModel.setNodes(List.of("GOOD NEWS FOR LASER JET"));
        editCuratedListPage.fillIn(List.of(curatedNodesListModel))
                           .clickSaveButton()
                           .verifyDrupalCuratedNodeUpdated(curatedNodesListModel.getNodes());
    }

    @DataProvider
    public Object[][] contentData() {
        return new Object[][]{
                {
                        List.of(
                                new ContentListTitleModel("Test Curated Node List " + timeStampFormat(PATTERN)),
                                new CuratedNodesListModel()
                        )
                }
        };
    }

}
