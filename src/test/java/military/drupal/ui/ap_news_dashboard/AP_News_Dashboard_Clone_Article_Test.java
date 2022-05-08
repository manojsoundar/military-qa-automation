package military.drupal.ui.ap_news_dashboard;

import com.epam.reportportal.annotations.attribute.Attributes;
import com.epam.reportportal.annotations.attribute.MultiValueAttribute;
import drupal.enums.content.ContentMenu;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.ap_dashboard.APDashboardPage;
import drupal.ui.pages.add_content.ap_dashboard.APNewsDashBoardModel;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static drupal.models.UserModel.loadUserModel;
import static java.lang.String.format;

public class AP_News_Dashboard_Clone_Article_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionAPNewsDashboardCloneArticle() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "apDashboardData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(multiValueAttributes = {@MultiValueAttribute(key = "TestCaseIds", values = {"249650", "249618"})})
    public void apNewsDashboardCloneArticle(String testCaseID, List<ISectionDataModel> data) {

        logStep(format("Running test case : %s", testCaseID));
        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Select AP News Dashboard");
        APDashboardPage apDashboardPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.AP_DASHBOARD), APDashboardPage.class);

        logStep("Select the most recent 'AP Top 25 Podcast' article and select ARTICLE");
        logStep("Check to confirm:\n" +
                        "1. SOURCE is 'Associated Press (16)'\n" +
                        "2. Display Date is the DATE OF TEST.");
        logStep("A popup should display with the image");
        logStep("Confirm the author(s) of the article is displayed in the CONTRIBUTOR/FREELANCER fields. The author's name is in CAPS.x\n" +
                        "NOTE: The AUTHOR field should have no information displayed.");
        logStep("Confirm copy is displaying in the BLURB and BODY fields");
        logStep("1. Confirm the NAME field:\n" +
                        "The NAME field begins with APNEWSROOM and followed by the Alt Tag information.\n" +
                        "2. Confirm the Alternative Text displays in the ALTERNATIVE TEXT field.");
        logStep("Confirm the following:\n" +
                        "1. CAPTION field is displaying copy.\n" +
                        "2. AP Newsroom field is checked");
        logStep("Check to confirm the name of the title is the same as the cloned article");
        logStep("The IMAGE field should automatically include an image in the field. Press the 'Edit' button");
        logStep("Confirm a unique ID is displaying in the EXTERNAL AP ID.");
        logStep("Select 'Test QA Automation Sidebar' in the SIDEBAR field");
        logStep("Type Test in the RELATED TOPICS and select 'Test Keyword.'");
        logStep("Type 'News' in the CATEGORY FIELD and select DAILY NEWS");
        logStep("Click Save.");
        APNewsDashBoardModel apNewsDashBoardModel = data.stream()
                                                        .filter(d -> d instanceof APNewsDashBoardModel)
                                                        .map(c -> (APNewsDashBoardModel) c)
                                                        .findFirst()
                                                        .get();
        apDashboardPage.searchForApFeedsSearch(apNewsDashBoardModel);
        Map<String, String> tableData = apDashboardPage.getArticleDataFromTable();

        logStep("Article cloned successfully");
        logStep("Click on edit Article");
        logStep("Edited article successfully");
        logStep("Click on delete Article");
        logStep("Article deleted successfully");

        apDashboardPage.clickOnCloneArticle()
                       .validateArticle(tableData, apNewsDashBoardModel)
                       .fillCloneArticle(data)
                       .clickSaveButton()
                       .clickEditCloneArticle()
                       .editCloneArticle(data)
                       .clickSaveButton()
                       .validateEditCloneArticle(apNewsDashBoardModel)
                       .validateAndDeleteClonedArticle(tableData)
                       .clickDeleteButton();

    }

    @DataProvider
    public Object[][] apDashboardData() {
        return new Object[][]{
                // TODO to explain why it has been commented
                //{"TC_249650_Clone_Article_WITH_Image", List.of(new APNewsDashBoardModel())},
                {
                        "TC_249618_Clone_Article_Without_Image",
                        List.of(
                                APNewsDashBoardModel.articleWithoutImage()
                        )
                }
        };
    }

}
