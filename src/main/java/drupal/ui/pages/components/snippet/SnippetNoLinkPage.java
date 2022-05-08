package drupal.ui.pages.components.snippet;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AttachAnImage;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class SnippetNoLinkPage extends SnippetBasePage implements AttachAnImage {

    private static final String URL_REGEX = "/admin/content/snippet/add/snippet_no_link";
    private static final SelenideElement ADD_SNIPPET_CONTENT_H_1_TAG = $("#block-pagetitle h1.page-title").as("Add Snippet Page H1 Tag");

    public SnippetNoLinkPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Add Snippet content Page not loaded.");
        assertEquals(ADD_SNIPPET_CONTENT_H_1_TAG
                             .should(exist, appear)
                             .getText(), "Add Snippet - No Link content", "Correct page is not displayed.");
        log.info("Add Snippet content Page loaded properly.");
    }

    public SnippetNoLinkPage fillSnippet(List<ISectionDataModel> dataModel) {
        for (ISectionDataModel data : dataModel) {
            data.setData(SnippetNoLinkPage.class);
        }
        return this;
    }

    public SnippetPage clickSaveButton() {
        SAVE_BUTTON.should(exist, appear)
                   .click();
        return new SnippetPage();
    }

}
