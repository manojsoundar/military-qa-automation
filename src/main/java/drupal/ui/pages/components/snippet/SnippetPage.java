package drupal.ui.pages.components.snippet;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class SnippetPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/snippet/";
    private static final SelenideElement SNIPPET_H_1_TAG = $(".block.block--page--title h1").as("Snippet Page H1 Tag");
    private static final SelenideElement EDIT_TAB = $(".menu--tabs a[href*='edit']");
    private static final SelenideElement BLURB_TEXT = $(".field.field--summary.field");

    public SnippetPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Snippet Page isn't loaded.");
        assertEquals(SNIPPET_H_1_TAG.should(exist, appear)
                                    .getText(), "Snippet", "Correct page is not displayed.");
        log.info("Snippet Page loaded properly.");
    }

    public SnippetPage verifyDrupalRegularSnippetCreated() {
        assertTrue(verifyURLLoaded(URL_REGEX + "\\d{5}"), "Drupal Regular Snippet isn't created");

        return this;
    }

    public EditSnippetPage clickEditTab() {
        EDIT_TAB.should(enabled, visible)
                .click();
        return new EditSnippetPage();
    }

    public SnippetPage verifyEditSnippet(SnippetModel snippetModel) {

        assertEquals(BLURB_TEXT.should(visible, appear)
                               .getText()
                               .trim()
                , snippetModel.getBlurb(), "Snippet updated message isn't displayed");
        return this;
    }

}
