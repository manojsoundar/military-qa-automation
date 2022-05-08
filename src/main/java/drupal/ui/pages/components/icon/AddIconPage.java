package drupal.ui.pages.components.icon;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddIconPage extends AdministrationToolbar {

    protected static final SelenideElement TITLE_INPUT = $("#edit-title-0-value").as("Title Input");
    protected static final SelenideElement CHOOSE_FILE_BUTTON = $("input.js-form-file").as("Choose File Button");
    protected static final SelenideElement REMOVE_BUTTON = $("input.button.js-form-submit[value='Remove']").as("Remove Button");
    protected static final SelenideElement SAVE_BUTTON = $("#edit-submit").as("Save button");
    private static final String URL_REGEX = "/icon/add/social_icon";
    private static final SelenideElement ADD_ICON_CONTENT_H_1_TAG = $("#block-pagetitle h1").as("Add widget content header title");

    public AddIconPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Add Icon content Page not loaded.");
        assertEquals(ADD_ICON_CONTENT_H_1_TAG.should(exist, appear)
                                             .getText(), "Add Social icon content", "Correct page is not displayed.");
        log.info("Add Icon content Page loaded properly.");
    }

    public AddIconPage fillIn(List<ISectionDataModel> sectionModel) {

        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(AddIconPage.class);
        }

        return this;
    }

    public IconResultPage clickSaveButton() {
        SAVE_BUTTON.should(appear, enabled, visible)
                   .click();

        return new IconResultPage();
    }

}
