package drupal.ui.pages.structure.eck_entity_types.call_to_action;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.components.AttachAnImage;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class AddCallToActionBasePage extends AdministrationToolbar implements AttachAnImage {

    protected static final SelenideElement INTERNAL_TITLE_INPUT = $("#edit-field-internal-title-0-value");
    protected static final SelenideElement TITLE_INPUT = $("#edit-title-0-value");
    protected static final SelenideElement BLURB_TEXTAREA = $("#edit-field-blurb-0-value");
    protected static final SelenideElement BUTTON_LABEL_INPUT = $("#edit-field-button-label-0-value");
    protected static final SelenideElement ATTACH_BACKGROUND_IMAGE_BUTTON = $("input.entity-browser-processed[name*='background']");
    protected static final SelenideElement ATTACH_IMAGE_VARIANT_BUTTON = $("input.entity-browser-processed[name*='variant']");
    protected static final SelenideElement PLEASE_WAIT_SPINNER_WEB_ELEMENT = $("div.ajax-progress.ajax-progress-throbber");
    protected static final SelenideElement SAVE_BUTTON = $("#edit-submit");

    public AddCallToActionBasePage() {
        log.info("Add Call To Base Page loaded properly.");
    }

}
