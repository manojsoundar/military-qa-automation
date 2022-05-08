package drupal.ui.pages.components.snippet;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class SnippetRegularPage extends SnippetBasePage {

    private static final String URL_REGEX = "/admin/content/snippet/add/snippet";
    private static final SelenideElement ADD_SNIPPET_CONTENT_H_1_TAG = $("#block-pagetitle h1.page-title").as("Add Snippet Page H1 Tag");
    private static final SelenideElement Link_INPUT = $("input#edit-field-link-0-uri").as("Link text box");

    public SnippetRegularPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Add Snippet content Page not loaded.");
        assertEquals(ADD_SNIPPET_CONTENT_H_1_TAG.should(exist, appear)
                                                .getText(), "Add Snippet content", "Correct page is not displayed.");
        log.info("Add Snippet content Page loaded properly.");
    }

    public SnippetRegularPage fillRegularSnippet(List<ISectionDataModel> dataModel) {
        for (ISectionDataModel data : dataModel) {
            data.setData(SnippetRegularPage.class);
        }
        return this;
    }

    public SnippetPage clickSaveButton() {
        SAVE_BUTTON.should(exist, appear)
                   .scrollTo()
                   .click();
        return new SnippetPage();
    }

}
