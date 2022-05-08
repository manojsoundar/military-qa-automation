package drupal.ui.pages.add_content.equipment;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.components.AttachAnImage;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddEquipmentPage extends AdministrationToolbar implements AttachAnImage {

    protected static final SelenideElement TITLE_INPUT = $("input#edit-title-0-value");
    protected static final SelenideElement BLURB_TEXT_AREA = $("textarea[id='edit-field-summary-0-value']");
    protected static final SelenideElement BODY_FRAME = $("iframe.cke_reset");
    protected static final SelenideElement BODY_FRAME_INPUT = $("html body.cke_editable");
    protected static final SelenideElement ADD_SPECIFICATIONS_BUTTON = $("input[id^='edit-field-specifications-add-more']");
    protected static final SelenideElement ATTACH_AN_IMAGE_BUTTON = $("input.button[id*='entity-browser-open-modal']");
    protected static final SelenideElement ADD_NEW_SLIDESHOW_BUTTON = $("input.button[value='Add new slideshow']");
    protected static final SelenideElement CREATE_NEW_SLIDESHOW_BUTTON = $("input.button[id*='actions-ief-add-save']");
    protected static final SelenideElement ADD_NEW_SLIDESHOW_TITLE_INPUT = $("input.form-text[id*='edit-field-slideshow-form-0-title']");
    protected static final SelenideElement ADD_NEW_SLIDESHOW_CAPTION_INPUT = $("input.form-text[id*='field-caption']");
    protected static final SelenideElement ADD_EXISTING_SLIDESHOW_INPUT = $("input.form-autocomplete[id^='edit-field-slideshow-form']");
    protected static final SelenideElement ADD_EXISTING_SLIDESHOW_BUTTON = $("input.button[id*='slideshow-actions-ief-add-existing']");
    protected static final ElementsCollection ADD_EXISTING_SLIDESHOW_CONTAINER_LIST = $$(".ui-widget-content .ui-menu-item a");
    protected static final SelenideElement ADD_SLIDESHOW_BUTTON = $("input.button[value='Add slideshow']").as("Button to add slideshow");
    protected static final SelenideElement SPECIFICATIONS_LABEL_INPUT = $("input.form-text[id^='edit-field-specifications']");
    protected static final SelenideElement SPECIFICATIONS_DESCRIPTION_INPUT = $("textarea.form-textarea[id^='edit-field-specifications']");
    protected static final SelenideElement RELATED_VIDEOS_INPUT = $("input.ui-autocomplete-input[id^='edit-field-feed']").as("Details Related videos Text box");
    protected static final SelenideElement KEYWORDS_INPUT = $("input[id='edit-field-keywords-target-id']").as("Details Keywords Text box");
    protected static final SelenideElement CURRENT_RELEASE_INPUT = $("input[id='edit-field-release-target-id']").as("Details Current Release Text box");
    protected static final SelenideElement ATTACH_AN_IMAGE_PROGRESS_WEB_ELEMENT = $("div.ajax-progress.ajax-progress-throbber");
    protected static final SelenideElement BODY_TOP_WEB_ELEMENT = $(".form-textarea-wrapper #cke_1_top").as("Body top section");
    protected static final SelenideElement SAVE_BUTTON = $("input#edit-submit[value='Save']");
    protected static final SelenideElement CATEGORIES_CONTAINER_DROP_WEB_ELEMENT = $(".chosen-container-active div.chosen-drop")
            .as("Container displayed after entering the Category");
    protected static final ElementsCollection CATEGORIES_AVAILABLE_LIST = $$("div#edit_field_equipment_categories_chosen ul li.active-result")
            .as("Categories displayed in the container after entering the Category");
    protected static final SelenideElement CATEGORIES_INPUT = $("input.chosen-search-input");
    private static final String URL_REGEX = "/node/add/equipment";
    private static final SelenideElement EQUIPMENT_PAGE_H_1_TITLE = $("#block-pagetitle .page-title");

    public AddEquipmentPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Equipment page not loaded.");
        assertEquals(EQUIPMENT_PAGE_H_1_TITLE.should(exist, appear)
                                             .getText()
                                             .trim(), "Create Equipment",
                     "Correct page title not displayed.");
        log.info("Equipment page loaded properly.");
    }

    public AddEquipmentPage fillIn(List<ISectionDataModel> equipmentSectionData) {
        for (ISectionDataModel equipmentSectionDataModel : equipmentSectionData) {
            equipmentSectionDataModel.setData(AddEquipmentPage.class);
        }
        return this;
    }

    public EquipmentPage clickSaveButton() {
        SAVE_BUTTON.scrollTo()
                   .should(appear, enabled, visible)
                   .click();
        waitAjaxJQueryMet(300);
        return new EquipmentPage();
    }

}
