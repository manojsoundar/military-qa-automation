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
public class AddSnippetContentPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/admin/content/snippet/add";
    private static final SelenideElement ADD_SNIPPET_CONTENT_H_1_TAG = $("#block-pagetitle h1.page-title").as("Add Snippet Page H1 Tag");
    private static final SelenideElement SNIPPET_LINK = $("a[href='/admin/content/snippet/add/snippet']").as("Snippet Link");
    private static final SelenideElement SNIPPET_NO_LINK = $("a[href='/admin/content/snippet/add/snippet_no_link']").as("Snippet No Link");

    public AddSnippetContentPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Add Snippet content Page not loaded.");
        assertEquals(ADD_SNIPPET_CONTENT_H_1_TAG.should(exist, appear)
                                                .getText(), "Add Snippet content", "Correct page is not displayed.");
        log.info("Add Snippet content Page loaded properly.");
    }

    public SnippetRegularPage clickOnSnippetLink() {
        assertTrue(SNIPPET_LINK.should(exist, appear)
                               .exists(), "Snippet link is not displayed.");

        log.info("Snippet link is displayed");
        SNIPPET_LINK.should(visible, enabled)
                    .click();

        return new SnippetRegularPage();
    }

    public SnippetNoLinkPage clickOnSnippetNoLink() {
        assertTrue(SNIPPET_NO_LINK.should(exist, appear)
                                  .exists(), "Snippet no link is not displayed.");
        log.info("Snippet no link is displayed");
        SNIPPET_NO_LINK.should(visible, enabled)
                       .click();

        return new SnippetNoLinkPage();
    }

}
