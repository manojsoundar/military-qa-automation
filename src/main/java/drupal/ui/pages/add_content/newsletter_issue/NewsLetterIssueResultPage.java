package drupal.ui.pages.add_content.newsletter_issue;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

@Log4j2
public class NewsLetterIssueResultPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/node";
    private static final SelenideElement STATUS_MESSAGE = $("div.messages.messages--status").as("NewsLetter Issue Status Message");
    private static final SelenideElement EDIT_TAB = $("ul.menu.menu--tabs a[href*='edit']");

    public NewsLetterIssueResultPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "NewsLetter Issue Result Page not loaded.");
        log.info("NewsLetter Issue Result Page loaded properly.");
    }

    public NewsLetterIssueResultPage verifyNewsLetterIssue(NewsletterIssueBaseModel newsLetterIssueData) {

        assertTrue(STATUS_MESSAGE.should(exist, appear)
                                 .has(text(newsLetterIssueData.getSubject())), "Correct status message not displayed.");
        log.info(format("Newsletter Issue %s verified", newsLetterIssueData.getSubject()));

        return this;
    }

    public EditNewsLetterIssuePage clickEditTab() {

        EDIT_TAB.should(exist, enabled)
                .click();

        return new EditNewsLetterIssuePage();

    }
}