package drupal.ui.pages.structure.eck_entity_types.call_to_action;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.components.AttachAnImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static drupal.ui.pages.structure.eck_entity_types.call_to_action.AddCallToActionBasePage.SAVE_BUTTON;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddCallToActionNormalPage extends AdministrationToolbar implements AttachAnImage {

    protected static final SelenideElement TITLE_SIZE_DROPDOWN = $("#edit-field-title-size");
    protected static final SelenideElement LINK_INPUT = $("#edit-field-link-0-uri");
    protected static final SelenideElement TRACKING_PIXEL_INPUT = $("#edit-field-tracking-pixel-0-uri");
    protected static final SelenideElement BUTTON_COLOR_OVERRIDE_INPUT = $("#edit-field-button-color-override-0-color");
    protected static final SelenideElement BUTTON_TEXT_COLOR_OVERRIDE_INPUT = $("#edit-field-button-text-color-override-0-color");
    private static final String URL_REGEX = "/add/call_to_action_normal";
    private static final SelenideElement ADD_CALL_TO_ACTION_NORMAL_H_1_TAG = $("#block-pagetitle h1 em.placeholder");

    public AddCallToActionNormalPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Add Call To Action - Normal Page not loaded.");
        assertTrue(ADD_CALL_TO_ACTION_NORMAL_H_1_TAG.should(exist, appear)
                                                    .has(text("Normal")), "Normal title:" + ADD_CALL_TO_ACTION_NORMAL_H_1_TAG.getText() + "not displayed,should contain('Normal')");
        log.info("Add Call To Action-Normal  Page loaded properly.");
    }

    public CallToActionResultPage fillIn(List<ISectionDataModel> sectionModel) {

        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(AddCallToActionNormalPage.class);
        }
        SAVE_BUTTON.should(appear, enabled, visible)
                   .click();

        return new CallToActionResultPage();
    }

    @Getter
    @AllArgsConstructor
    public enum TitleSize {
        NONE("- None -"),
        LARGE("Large");

        private final String titleSizeItem;
    }

}
