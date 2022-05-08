package drupal.ui.pages.components.snippet;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EditSnippetPage extends AdministrationToolbar {

    protected static final SelenideElement TITLE_INPUT = $("#edit-title-0-value");
    protected static final SelenideElement DISPLAY_TITLE_INPUT = $("#edit-field-heading-0-value");
    protected static final SelenideElement BLURB_INPUT = $("#edit-field-summary-0-value");
    protected static final SelenideElement EDIT_IMAGE_BUTTON = $("#edit-field-image-current-items-0-edit-button");
    protected static final SelenideElement DELETE_IMAGE_BUTTON = $("#edit-field-image-current-items-0-remove-button");
    protected static final SelenideElement LINK_INPUT = $("#edit-field-link-0-uri");
    protected static final SelenideElement IMAGE_NAME = $("input[id*='edit-name']");
    protected static final SelenideElement IMAGE_ALTERNATIVE_NAME = $("input[id*='edit-field-image-0-alt']");
    protected static final SelenideElement IMAGE_CAPTION = $("input[id*='edit-field-image-0-title']");
    protected static final SelenideElement IMAGE_SAVE_BUTTON = $(".ui-dialog-buttonpane button");
    private static final String URL_REGEX = "/edit";
    private static final SelenideElement SAVE_BUTTON = $("input#edit-submit");

    public EditSnippetPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Edit Snippet Page not loaded");
        log.info("Edit Snippet Page loaded");
        assertTrue(SAVE_BUTTON.exists(), "Save button not available");
    }

    public SnippetPage clickSaveButton() {
        SAVE_BUTTON.should(enabled, visible, appear)
                   .scrollTo()
                   .click();
        return new SnippetPage();
    }

    public EditSnippetPage editAllSnippet(List<ISectionDataModel> dataModel) {

        for (ISectionDataModel data : dataModel) {
            data.setData(EditSnippetPage.class);
        }

        return this;
    }

}
