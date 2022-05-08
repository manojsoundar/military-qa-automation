package drupal.ui.pages.add_content.newsletter_issue;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.WebDriverRunner.driver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class MilitaryReportHeaderTemplatePage {

    private static final String URL_REGEX = "/newsletter-templates/header/military-report";

    public MilitaryReportHeaderTemplatePage() {
        log.info("Loading Newsletter Issue Header Template");
    }

    public NewsLetterIssueResultPage verifyHeaderText(NewsletterIssueBaseModel newsLetterIssueData) {

        SelenideDriver selenideDriverTemplate = new SelenideDriver(driver().config());

        try {
            selenideDriverTemplate.open(Configuration.baseUrl + URL_REGEX);
            SelenideElement newsletterHeaderDisplayText = selenideDriverTemplate.$("body p");
            SelenideElement newsletterDefaultHeaderDisplayText = selenideDriverTemplate.$x("//tbody/tr[2]/td");

            if (newsLetterIssueData.getNewsLetterHeader() != null) {
                log.info("Header validation");
                assertEquals(newsletterHeaderDisplayText.getText(), newsLetterIssueData.getNewsLetterHeader(), "Header Text Not matching");
            } else {
                log.info("Default Header Validation");
                assertTrue(newsletterDefaultHeaderDisplayText.should(exist, appear)
                                                             .has(text("Military Report")), "Default Header text Not Matching");
            }
        } finally {
            selenideDriverTemplate.close();
        }

        return new NewsLetterIssueResultPage();
    }

}
