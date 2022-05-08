package drupal.ui.pages.media;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddMediaBasePage extends AdministrationToolbar {

    protected static final SelenideElement NAME_INPUT = $("#edit-name-0-value").as("Name text field");
    protected static final SelenideElement URL_INPUT = $x("//input[contains(@id,'url-0-value')] | //input[contains(@id,'edit-field-media')]").as("URL field");
    protected static final SelenideElement SAVE_BUTTON = $("#edit-submit").as("Save button");
    protected static final SelenideElement CHOOSE_FILE_BUTTON = $("input.js-form-file").as("Choose File Button");
    protected static final SelenideElement REMOVE_BUTTON = $("input.button.js-form-submit[value='Remove']").as("Remove Button");
    protected static final SelenideElement PUBLISHED_CHECKBOX = $("#edit-status-wrapper input").as("Published Checkbox");
    protected static final SelenideElement FILE_UPLOAD_ERROR_MESSAGE = $("div.file-upload-js-error").as("File Upload Error Message");
    private static final String URL_REGEX = "/media/add";

    public AddMediaBasePage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Add Media Page not loaded.");
        log.info("Add Media Page loaded properly.");
    }

    public AddMediaBasePage fillIn(List<ISectionDataModel> sectionData) {

        for (ISectionDataModel currentSectionModel : sectionData) {
            currentSectionModel.setData(AddMediaBasePage.class);
        }

        return this;
    }

    public MediaPage clickSaveButton() {
        SAVE_BUTTON.should(visible, enabled)
                   .scrollTo()
                   .click();
        return new MediaPage();
    }
}
