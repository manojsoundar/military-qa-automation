package drupal.ui.pages.components.snippet;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class SnippetsPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/admin/content/snippet";
    private static final SelenideElement SNIPPETS_H_1_TAG = $("#block-pagetitle h1.page-title").as("Snippets Page H1 Tag");
    private static final SelenideElement ADD_SNIPPET_BUTTON = $("a[href='/admin/content/snippet/add']").as("Add Snippet button");

    public SnippetsPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Snippets Page isn't loaded.");
        assertEquals(SNIPPETS_H_1_TAG.should(exist, appear)
                                     .getText(), "Snippets", "Correct page is not displayed.");
        log.info("Snippets Page loaded properly.");
    }

    public AddSnippetContentPage clickAddSnippetButton() {
        ADD_SNIPPET_BUTTON.should(exist, appear, enabled)
                          .click();

        return new AddSnippetContentPage();
    }

}
