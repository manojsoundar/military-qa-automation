package military.drupal.ui.ap_news_dashboard;

import com.epam.reportportal.annotations.attribute.Attributes;
import com.epam.reportportal.annotations.attribute.MultiValueAttribute;
import drupal.enums.content.ContentMenu;
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

import static drupal.models.UserModel.loadUserModel;

public class AP_News_Dashboard_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionAPNewsDashboard() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "apDashboardData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(multiValueAttributes = {@MultiValueAttribute(key = "TestCaseIds", values = {"249616", "249617"})})
    public void apNewsDashboard(String testCaseId, APNewsDashBoardModel apDashBoardModel) {

        logStep("Running TestCase Id: " + testCaseId);

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the 'CONTENT' Tab");
        logStep("Select ‘AP DASHBOARD’");
        APDashboardPage apDashboardPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.AP_DASHBOARD), APDashboardPage.class);

        logStep("The dashboard should display both articles and images based on chronological order, with the most " +
                        "recent (including the date of the test) displaying at the top of the page. The date can be confirmed by " +
                        "hovering over the title links. All of the urls include a date, and this date is your confirmation.");
        apDashboardPage.verifyTableDisplayInChronologicalOrder(apDashBoardModel.getSearchInput());

        logStep("Click Search");
        logStep("The dashboard should display both articles and images based on chronological" +
                        " order, with the most recent (including the date of the test) displaying at the top of " +
                        "the page. The date and time (in real time) can be confirmed on the 'VERSION CREATED' column.");
        logStep("verify -> If the date/time matches today's date and time, you have confirmed the date," +
                        " the time, and the expected result.");

        logStep("In the AP FEEDS SEARCH FORM, type" + apDashBoardModel.getSearchInput() + " and click Search");
        logStep("NOTE: Do not add a date to the search request");
        logStep("verify -> The Search results should display content related to AP Top Political News based on chronological" +
                        " order (title will display time based on EST. The Version Created time is based on PST).");
        apDashboardPage.searchForApFeedsSearch(apDashBoardModel)
                       .verifyTableDisplayInChronologicalOrder(apDashBoardModel.getSearchInput());
    }


    @DataProvider
    public Object[][] apDashboardData() {
        return new Object[][]{
                {
                        "TC_249616",
                        new APNewsDashBoardModel(null, null, null, null, null, null, false)
                },
                {
                        "TC_249617",
                        new APNewsDashBoardModel("AP Top Political News", null, null, null, null, null, false)
                }
        };
    }

}
