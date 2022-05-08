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
import drupal.ui.pages.add_content.newsletter_issue.InsiderArmyActiveContentsSnippetsTemplatePage;
import drupal.ui.pages.add_content.newsletter_issue.InsiderArmyActiveHeaderTemplatePage;
import drupal.ui.pages.add_content.newsletter_issue.InsiderArmyActivePreHeaderTemplatePage;
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

public class Create_A_Newsletter_Army_Active_Duty_Insiders_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateANewsletterArmyActiveDutyInsiders() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "addNewsletterIssue")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(multiValueAttributes = {@MultiValueAttribute(key = "TestCaseIds", values = {"249686", "249682"})})
    public void createNewsLetterArmyActiveDutyInsiders(String testCase, List<ISectionDataModel> newsLetterIssueData, List<ISectionDataModel> newsLetterIssueEditData) {

        logStep(format("Running Test Case : %s", testCase));
        logStep("1. Login to Drupal");
        logStep("2. Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("3. Hover the Content Tab");
        logStep("4. Hover over Add content");
        logStep("5. Click on Newsletter Issue");
        CreateNewsLetterIssuePage createNewsLetterIssuePage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.NEWSLETTER_ISSUE), CreateNewsLetterIssuePage.class);

        logStep("6. Enter Test name in the Title field");
        logStep("7. Enter Military Report in the TYPE field");
        logStep("8. Type test article in the LABEL field in the Lead Story section ->ENTITY TYPE for the Lead Story is always CONTENT, the default view");
        logStep("9. Type the various articles or snippets in the ARTICLES OR SNIPPETS FIELD");
        logStep("10. Type Use This in the VIDEOS field and select 'Newsletters Insiders Video Curated List - Use This One (438)'");
        logStep("11. Enter Test's Date for DISPLAY DATE -> Ex. MM/DD/YYY 06/22/2021");
        logStep("12. Leave NEWSLETTER PRE-HEADER field empty");
        logStep("13. Leave NEWSLETTER HEADER field empty");
        logStep("14. Click Save");
        logStep(format("Drupal Army Insider(Active) Newsletter Issue is created: %s/node/######", Configuration.baseUrl));
        NewsLetterIssueResultPage newsLetterIssueResultPage = createNewsLetterIssuePage.fillIn(newsLetterIssueData);
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

        try {
            logStep("15. Confirm the following: 1. Articles & Snippets are displaying correctly (thumbnail & image) 2. 3 videos (thumbnail & image)");
            logStep(format("Validation: Preview URL - %s/newsletter-templates/body/insider-army-active", Configuration.baseUrl));
            logStep("The order of articles displaying in the URL be identical to the list from Step 9.");
            newsLetterIssueResultPage.verifyNewsLetterIssue(newsletterIssueBaseData);
            new InsiderArmyActiveContentsSnippetsTemplatePage().verifyArticlesSnippetsLinks(newsletterContentData);

            if (testCase.equals("TC249686")) {
                logStep("16. Validation: Confirm the Pre-header for the newsletter is NOT displaying");
                logStep("17. Validation: Confirm the Header for the newsletter is displaying correctly 1. Military.com Logo 2. ARMY INSIDER headline 3. Date");
                logStep(format("Validation: %s/newsletter-templates/header/insider-army-active", Configuration.baseUrl));
                logStep("The test content displays Default header view");
            } else {
                logStep("16. Confirm the Pre-header for the newsletter is displaying correctly");
                logStep(format("Validation: %s/newsletter-templates/preheader/insider-army-active. Test content in Step 12 overrides the Default pre-header view", Configuration.baseUrl));
                logStep("17. Confirm the Header for the newsletter is displaying correctly");
                logStep(format("Validation: %s/newsletter-templates/header/insider-army-active", Configuration.baseUrl));
                logStep("The test content in Step 13 overrides the Default header view");
            }
            new InsiderArmyActivePreHeaderTemplatePage().verifyPreHeaderText(newsletterIssueBaseData);
            new InsiderArmyActiveHeaderTemplatePage().verifyHeaderText(newsletterIssueBaseData);

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
                        "TC249686",
                        List.of(
                                NewsletterContentModel.getArmyActiveDutyInsiderData(),
                                NewsletterIssueBaseModel.getArmyActiveDutyInsiderData()
                        ),
                        List.of(
                                NewsletterIssueBaseModel.getNewsletterIssueEditData()
                        )
                },
                {
                        "TC249682",
                        List.of(
                                NewsletterContentModel.getArmyActiveDutyInsiderData(),
                                NewsletterIssueBaseModel.getArmyActiveDutyInsiderOverridesData()
                        ),
                        List.of(
                                NewsletterIssueBaseModel.getNewsletterIssueEditData()
                        )
                }
        };
    }

}
