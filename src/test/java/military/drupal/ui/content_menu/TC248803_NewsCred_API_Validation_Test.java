package military.drupal.ui.content_menu;

import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.ContentMenu;
import drupal.enums.content.NewsCredMenu;
import drupal.models.NewsCredModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.news_cred.NewsCredArticleAdminPage;
import mgs.qa.base.config.TestProperties;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.List;

import static drupal.models.UserModel.loadUserModel;

@Ignore
public class TC248803_NewsCred_API_Validation_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionNewsCredAPIValidation() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(enabled = false, dataProvider = "newsCredSearchData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "248803")})
    @TestCaseId("248803")
    public void newsCredApiValidation(NewsCredModel newsCredData) {

        logStep("1. Login in to Drupal description -> ", TestProperties.getEnvironmentPropertyValue());
        logStep("Enter user name and password -> user name and PW TBD.");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the 'Content' Tab");
        logStep("Hover the NewsCred Tab");
        logStep("Click on 'NewsCred Admin'");
        NewsCredArticleAdminPage newsCredArticleAdminPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.NEWSCRED, NewsCredMenu.NEWS_CRED_ADMIN), NewsCredArticleAdminPage.class);

        logStep("Select ‘Any’ in the Published field");
        logStep("Click Apply -> The dashboard should display both articles and images based on chronological order, " +
                        "with the most recent (including the date of the test) displaying at the top of the page");
        logStep("When hovering of the the top link, the bottom left corner of the screen displays the url (which includes the date). " +
                        "If it matches today's date, you have confirmed the date and the expected result.");
        newsCredArticleAdminPage.searchNewsCred(newsCredData)
                                .verifyTitlesAreInChronologicalOrder();

    }

    @DataProvider
    public Object[][] newsCredSearchData() {
        return new Object[][]{
                {
                        new NewsCredModel(null, "- Any -", null, null, null)
                }
        };
    }

}
