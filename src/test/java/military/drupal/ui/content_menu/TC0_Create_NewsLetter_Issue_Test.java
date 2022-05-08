package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.ContentMenu;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.newsletter_issue.CreateNewsLetterIssuePage;
import drupal.ui.pages.add_content.newsletter_issue.NewsLetterIssueResultPage;
import drupal.ui.pages.add_content.newsletter_issue.NewsletterContentModel;
import drupal.ui.pages.add_content.newsletter_issue.NewsletterIssueBaseModel;
import drupal.ui.pages.add_content.newsletter_issue.TrackingPixelURLModel;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static drupal.models.UserModel.loadUserModel;

public class TC0_Create_NewsLetter_Issue_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateNewsLetterIssue() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(enabled = false, dataProvider = "addNewsletterIssue")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "0")})
    @TestCaseId("0")
    public void createNewsLetterIssue(List<ISectionDataModel> newsLetterIssueData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over Add content");
        logStep("Click on Newsletter Issue");
        CreateNewsLetterIssuePage createNewsLetterIssuePage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.NEWSLETTER_ISSUE), CreateNewsLetterIssuePage.class);

        logStep("Fill in the Create Newsletter Issue page and click save");
        NewsLetterIssueResultPage newsLetterIssueResultPage = createNewsLetterIssuePage.fillIn(newsLetterIssueData);

        NewsletterIssueBaseModel newsletterIssueBaseData = newsLetterIssueData.stream()
                                                                              .filter(d -> d instanceof NewsletterIssueBaseModel)
                                                                              .map(c -> (NewsletterIssueBaseModel) c)
                                                                              .findFirst()
                                                                              .get();

        logStep("Validation : Newsletter Issue is created: " + Configuration.baseUrl + "/node/####");
        newsLetterIssueResultPage.verifyNewsLetterIssue(newsletterIssueBaseData);

        logStep("Un-publish the Newsletter issue");
        newsletterIssueBaseData.setPublish(false);
        newsLetterIssueResultPage.clickEditTab()
                                 .fillIn(List.of(newsletterIssueBaseData))
                                 .verifyNewsLetterIssue(newsletterIssueBaseData);
    }

    @DataProvider
    public Object[][] addNewsletterIssue() {
        return new Object[][]{
                {
                        List.of(
                                new NewsletterContentModel(),
                                new NewsletterIssueBaseModel(),
                                new TrackingPixelURLModel()
                        )
                }
        };
    }

}

