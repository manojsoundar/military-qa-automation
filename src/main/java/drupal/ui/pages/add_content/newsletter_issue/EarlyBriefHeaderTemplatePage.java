package drupal.ui.pages.add_content.newsletter_issue;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import common.CommonMethods;
import drupal.models.TimeStampPattern;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.WebDriverRunner.driver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EarlyBriefHeaderTemplatePage {

    private static final String URL_REGEX = "/newsletter-templates/header/early-brief";

    public EarlyBriefHeaderTemplatePage() {
        log.info("Loading Newsletter Issue Header Template");
    }

    public NewsLetterIssueResultPage verifyHeaderText(NewsletterIssueBaseModel newsLetterIssueData) {

        SelenideDriver selenideDriverTemplate = new SelenideDriver(driver().config());

        try {
            selenideDriverTemplate.open(Configuration.baseUrl + URL_REGEX);
            SelenideElement headerDisplayText = selenideDriverTemplate.$("body p");
            SelenideElement defaultHeaderDisplayText = selenideDriverTemplate.$x("//tbody/tr[3]/td");
            SelenideElement defaultHeaderAdImage = selenideDriverTemplate.$x("//tr[1]//img[2]");
            SelenideElement militaryLogo = selenideDriverTemplate.$x("//tbody/tr[2]//img");
            SelenideElement displayDate = selenideDriverTemplate.$x("//tbody/tr[4]/td");

            if (newsLetterIssueData.getNewsLetterHeader() != null) {
                log.info("Header validation");
                assertEquals(headerDisplayText.getText(), newsLetterIssueData.getNewsLetterHeader(), "Header Text Not matching");
            } else {
                log.info("Default Header Validation");
                assertTrue(defaultHeaderAdImage.isDisplayed());
                assertTrue(militaryLogo.isDisplayed());
                assertTrue(defaultHeaderDisplayText.should(exist, appear)
                                                   .has(text("Daily Brief")), "Default Header text Not Matching");
                assertEquals(CommonMethods.convertDateFormat("dd MMMM yyyy", TimeStampPattern.DATE_PATTERN, displayDate.getText()),
                             newsLetterIssueData.getDisplayDate(), "Display date Not matching");


            }
        } finally {
            selenideDriverTemplate.close();
        }

        return new NewsLetterIssueResultPage();
    }

}


