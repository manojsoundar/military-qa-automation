package drupal.ui.pages.add_content.newsletter_issue;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.WebDriverRunner.driver;
import static common.CommonMethods.timeStampFormat;
import static org.testng.Assert.assertTrue;

@Log4j2
public class InsiderArmyActiveHeaderTemplatePage {

    private static final String URL_REGEX = "/newsletter-templates/header/insider-army-active";

    public InsiderArmyActiveHeaderTemplatePage() {
        log.info("Loading Newsletter Issue Header Template");
    }

    public NewsLetterIssueResultPage verifyHeaderText(NewsletterIssueBaseModel newsLetterIssueData) {

        SelenideDriver selenideDriverTemplate = new SelenideDriver(driver().config());

        try {
            selenideDriverTemplate.open(Configuration.baseUrl + URL_REGEX);
            SelenideElement newsletterHeaderDisplayOverriddenImage = selenideDriverTemplate.$("img.fullWidthLogo[alt='Military.com Special']");
            SelenideElement newsletterHeaderDefaultDisplayText = selenideDriverTemplate.$x("//tbody/tr[2]/td");
            SelenideElement newsletterHeaderDefaultDisplayTimeText = selenideDriverTemplate.$x("//tbody/tr[3]/td");
            SelenideElement newsletterHeaderDefaultDisplayImage = selenideDriverTemplate.$("img[alt='Military.com']");

            if (newsLetterIssueData.getNewsLetterHeader() != null) {
                log.info("Header validation");
                assertTrue(newsletterHeaderDisplayOverriddenImage.should(exist, appear)
                                                                 .exists(), "Overridden Image isn.t displayed");
            } else {
                log.info("Default Header Validation");
                assertTrue(newsletterHeaderDefaultDisplayText.should(exist, appear)
                                                             .has(text("ARMY INSIDER")), "Default Header text isn't matching");
                assertTrue(newsletterHeaderDefaultDisplayTimeText.should(exist, appear)
                                                                 .has(text(timeStampFormat("dd MMMM yyyy"))), "Default Header Date isn't matching");
                assertTrue(newsletterHeaderDefaultDisplayImage.should(exist, appear)
                                                              .exists(), "Default Header Image not displayed");
            }
        } finally {
            selenideDriverTemplate.close();
        }

        return new NewsLetterIssueResultPage();
    }

}
