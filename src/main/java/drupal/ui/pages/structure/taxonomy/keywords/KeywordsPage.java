package drupal.ui.pages.structure.taxonomy.keywords;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class KeywordsPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/keywords/overview";
    private static final SelenideElement KEYWORDS_H_1_TAG = $("#block-pagetitle h1");
    private static final SelenideElement ADD_TERM_BUTTON = $("a[href*='/keywords/add']");

    public KeywordsPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Keywords Page not loaded.");
        assertTrue(KEYWORDS_H_1_TAG.should(exist, appear)
                                   .has(text("Keywords")), "Keywords title: " + KEYWORDS_H_1_TAG.getText() + "not displayed,should contain('Keywords')");
        log.info("Keywords Page loaded properly.");
    }

    public KeywordsAddTermPage clickAddTerm() {
        assertTrue(ADD_TERM_BUTTON.should(exist, appear)
                                  .exists(), " Add term button is not displayed.");
        ADD_TERM_BUTTON.should(appear, exist, enabled)
                       .click();
        return new KeywordsAddTermPage();
    }
}