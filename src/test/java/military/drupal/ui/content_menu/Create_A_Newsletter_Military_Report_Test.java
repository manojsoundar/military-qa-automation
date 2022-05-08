package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.attribute.Attributes;
import com.epam.reportportal.annotations.attribute.MultiValueAttribute;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.ContentMenu;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.newsletter_issue.CreateNewsLetterIssuePage;
import drupal.ui.pages.add_content.newsletter_issue.MilitaryReportContentsSnippetsTemplatePage;
import drupal.ui.pages.add_content.newsletter_issue.MilitaryReportHeaderTemplatePage;
import drupal.ui.pages.add_content.newsletter_issue.MilitaryReportPreHeaderTemplatePage;
import drupal.ui.pages.add_content.newsletter_issue.NewsLetterIssueResultPage;
import drupal.ui.pages.add_content.newsletter_issue.NewsletterContentModel;
import drupal.ui.pages.add_content.newsletter_issue.NewsletterIssueBaseModel;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static drupal.models.UserModel.loadUserModel;
import static java.lang.String.format;

public class Create_A_Newsletter_Military_Report_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateANewsletterMilitaryReport() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "addNewsletterIssue")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(multiValueAttributes = {@MultiValueAttribute(key = "TestCaseIds", values = {"249677", "249676"})})
    public void createNewsLetterIssueMilitaryReport(String testCase, List<ISectionDataModel> newsLetterIssueData, List<ISectionDataModel> newsLetterIssueEditData) {

        logStep(format("Running Test Case : %s", testCase));
        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over Add content");
        logStep("Click on Newsletter Issue");
        CreateNewsLetterIssuePage createNewsLetterIssuePage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.NEWSLETTER_ISSUE), CreateNewsLetterIssuePage.class);

        logStep("Enter Test name in the Title field");
        logStep("Enter Military Report in the TYPE field");
        logStep("Type the various articles or snippets in the ARTICLES OR SNIPPETS FIELD");
        logStep("Type Test content for the NEWSLETTER PRE-HEADER field");
        logStep("Type Test content for the NEWSLETTER HEADER field");
        logStep("Click Save");
        NewsLetterIssueResultPage newsLetterIssueResultPage = createNewsLetterIssuePage.fillIn(newsLetterIssueData);

        logStep("Validation : Newsletter Issue is created: " + Configuration.baseUrl + "/node/####");
        NewsletterIssueBaseModel newsletterIssueBaseData = newsLetterIssueData.stream()
                                                                              .filter(d -> d instanceof NewsletterIssueBaseModel)
                                                                              .map(c -> (NewsletterIssueBaseModel) c)
                                                                              .findFirst()
                                                                              .get();
        NewsletterContentModel newsletterContentData = newsLetterIssueData.stream()
                                                                          .filter(d -> d instanceof NewsletterContentModel)
                                                                          .map(c -> (NewsletterContentModel) c)
                                                                          .findFirst()
                                                                          .get();
        newsLetterIssueResultPage.verifyNewsLetterIssue(newsletterIssueBaseData);

        try {
            logStep("Confirm the Articles & Snippets are displaying correctly (hyperlinks only) - Preview URL - " + Configuration.baseUrl + "/newsletter-templates/body/military-report");
            new MilitaryReportContentsSnippetsTemplatePage().verifyArticlesSnippetsLinks(newsletterContentData);

            logStep("Confirm the Pre-header for the newsletter is displaying correctly - " + Configuration.baseUrl + "/newsletter-templates/preheader/military-report");
            new MilitaryReportPreHeaderTemplatePage().verifyPreHeaderText(newsletterIssueBaseData);

            logStep("Confirm the Header for the newsletter is displaying correctly - " + Configuration.baseUrl + "/newsletter-templates/header/military-report");
            new MilitaryReportHeaderTemplatePage().verifyHeaderText(newsletterIssueBaseData);

        } finally {
            logStep("Un-publish the Newsletter issue");
            newsletterIssueBaseData = newsLetterIssueEditData.stream()
                                                             .filter(d -> d instanceof NewsletterIssueBaseModel)
                                                             .map(c -> (NewsletterIssueBaseModel) c)
                                                             .findFirst()
                                                             .get();
            newsLetterIssueResultPage.clickEditTab()
                                     .fillIn(newsLetterIssueEditData)
                                     .verifyNewsLetterIssue(newsletterIssueBaseData);
        }

    }

    @DataProvider
    public Object[][] addNewsletterIssue() {
        return new Object[][]{
                {
                        "TC249677",
                        List.of(
                                NewsletterContentModel.getMilitaryReportData(),
                                NewsletterIssueBaseModel.getMilitaryReportData()
                        ),
                        List.of(
                                NewsletterIssueBaseModel.getNewsletterIssueEditData()
                        )
                },
                {
                        "TC249676",
                        List.of(
                                NewsletterContentModel.getMilitaryReportData(),
                                NewsletterIssueBaseModel.getMilitaryReportWithOverridesData()
                        ),
                        List.of(
                                NewsletterIssueBaseModel.getNewsletterIssueEditData()
                        )
                }
        };
    }

}
