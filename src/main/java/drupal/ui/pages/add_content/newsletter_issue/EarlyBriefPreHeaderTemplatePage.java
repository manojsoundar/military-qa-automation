package drupal.ui.pages.add_content.newsletter_issue;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.WebDriverRunner.driver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EarlyBriefPreHeaderTemplatePage {

    private static final String URL_REGEX = "/newsletter-templates/preheader/early-brief";

    public EarlyBriefPreHeaderTemplatePage() {
        log.info("Loading Newsletter Issue Pre Header Template");
    }

    public NewsLetterIssueResultPage verifyPreHeaderText(NewsletterIssueBaseModel newsLetterIssueData) {

        SelenideDriver selenideDriverTemplate = new SelenideDriver(driver().config());
        try {
            selenideDriverTemplate.open(Configuration.baseUrl + URL_REGEX);
            SelenideElement preHeaderDisplayText = selenideDriverTemplate.$("body td p");
            SelenideElement defaultPreHeaderDisplayText = selenideDriverTemplate.$x("//body");

            if (newsLetterIssueData.getNewsLetterPreHeader() != null) {
                log.info("Pre Header validation");
                assertEquals(preHeaderDisplayText.getText(), newsLetterIssueData.getNewsLetterPreHeader(), "Pre Header Text Not matching");
            } else {
                log.info("Default Pre Header validation");
                assertTrue(defaultPreHeaderDisplayText.getText()
                                                      .trim()
                                                      .isEmpty(), "Default Pre Header Not Matching");
            }
        } finally {
            selenideDriverTemplate.close();
        }

        return new NewsLetterIssueResultPage();
    }

}
