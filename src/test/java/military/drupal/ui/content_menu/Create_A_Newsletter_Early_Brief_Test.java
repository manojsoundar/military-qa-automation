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
import drupal.ui.pages.add_content.newsletter_issue.EarlyBriefContentSnippetsTemplatePage;
import drupal.ui.pages.add_content.newsletter_issue.EarlyBriefHeaderTemplatePage;
import drupal.ui.pages.add_content.newsletter_issue.EarlyBriefPreHeaderTemplatePage;
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

public class Create_A_Newsletter_Early_Brief_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateANewsletterEarlyBrief() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "addNewsletterIssue")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(multiValueAttributes = {@MultiValueAttribute(key = "TestCaseIds", values = {"249678", "249681"})})
    public void createNewsLetterIssueEarlyBrief(String testCase, List<ISectionDataModel> newsLetterIssueData, List<ISectionDataModel> newsLetterIssueEditData) {

        logStep(format("Running Test Case : %s", testCase));
        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over Add content");
        logStep("Click on Newsletter Issue");
        CreateNewsLetterIssuePage createNewsLetterIssuePage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.NEWSLETTER_ISSUE), CreateNewsLetterIssuePage.class);

        logStep("Enter Test name in the Title field");
        logStep("Enter 'Early Brief' in the TYPE field");
        logStep("Type test article in the LABEL field in the Lead Story section");
        logStep("Type the various articles or snippets in the ARTICLES OR SNIPPETS FIELD");
        logStep("Type Trivia in the TRIVIA field and select 'Today's Military Trivia (417)'");
        logStep("Type WWII in the 'Use Existing Media' field and select 'Dogfight from WWII (3021)' under the VIDEOS section.");
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
            logStep("Confirm the Articles & Snippets, Trivia Snippet, The Video -  are displaying correctly (hyperlinks only) - Preview URL - " + Configuration.baseUrl + "/newsletter-templates/body/early-brief");
            new EarlyBriefContentSnippetsTemplatePage().verifyArticlesSnippetsLinks(newsletterContentData);

            logStep("Confirm the Pre-header for the newsletter is displaying correctly - " + Configuration.baseUrl + "/newsletter-templates/preheader/early-brief");
            new EarlyBriefPreHeaderTemplatePage().verifyPreHeaderText(newsletterIssueBaseData);

            logStep("Confirm the Header for the newsletter is displaying correctly - " + Configuration.baseUrl + "/newsletter-templates/header/early-brief");
            new EarlyBriefHeaderTemplatePage().verifyHeaderText(newsletterIssueBaseData);

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
                        "TC249678",
                        List.of(
                                NewsletterContentModel.getEarlyBriefData(),
                                NewsletterIssueBaseModel.getEarlyBriefData()
                        ),
                        List.of(
                                NewsletterIssueBaseModel.getNewsletterIssueEditData()
                        )
                },
                {
                        "TC249681",
                        List.of(
                                NewsletterContentModel.getEarlyBriefData(),
                                NewsletterIssueBaseModel.getEarlyBriefWithOverridesData()
                        ),
                        List.of(
                                NewsletterIssueBaseModel.getNewsletterIssueEditData()
                        )
                }
        };
    }

}
