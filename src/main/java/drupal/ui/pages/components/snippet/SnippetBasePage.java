package drupal.ui.pages.components.snippet;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.components.AttachAnImage;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class SnippetBasePage extends AdministrationToolbar implements AttachAnImage {

    protected static final SelenideElement TITLE_INPUT = $("input#edit-title-0-value");
    protected static final SelenideElement BLURB_TEXTAREA = $("textarea#edit-field-summary-0-value").as("Blurb (or summary) Text area");
    protected static final SelenideElement ATTACH_AN_IMAGE_BUTTON = $("input#edit-field-image-entity-browser-entity-browser-open-modal").as("Attach an Image Button");
    protected static final SelenideElement SAVE_BUTTON = $("input#edit-submit[value=Save]").as("Save Button");
    protected static final SelenideElement DISPLAY_TITLE_INPUT = $("input#edit-field-heading-0-value").as("Display title");
    private static final String URL_REGEX = "/admin/content/snippet/add/snippet";

    public SnippetBasePage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Add Snippet content Page not loaded.");
        log.info("Add Snippet content Page loaded properly.");
    }

}

