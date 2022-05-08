package drupal.ui.pages.components.flexible_space;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.components.AttachAnImage;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


@Log4j2
public class AddFlexibleSpaceContentPage extends AdministrationToolbar implements AttachAnImage {

    public static final SelenideElement TITLE_INPUT = $("#edit-title-0-value").as("Title input");
    protected static final SelenideElement ATTACH_AN_IMAGE_BUTTON = $(".form-wrapper input[name*='field_image_']").as("Attach an Image Button");
    protected static final SelenideElement CONTENT_ADD_IMAGE_LINK_INPUT = $(".form-wrapper input[name*='field_link']").as("Content title input box");
    protected static final SelenideElement GENERATED_KEYWORD_LIST_INPUT = $("div.field--type-entity-reference.field--name-field-generated-list-term div.chosen-container input.chosen-search-input");
    protected static final ElementsCollection GENERATED_KEYWORD_LIST = $$("div.field--type-entity-reference.field--name-field-generated-list-term ul.chosen-results li");
    protected static final ElementsCollection CURATED_LIST_INPUT = $$("div.js-form-item.form-item.js-form-type-entity-autocomplete [type='text']");
    protected static final ElementsCollection GENERATED_LIST_CATEGORY_LIST = $$("div.field--type-entity-reference.field--name-field-generated-list-cat-term ul.chosen-results li");
    protected static final ElementsCollection CONTENT_TITLE_INPUT_LIST = $$("input.js-text-full[id^='edit-field-content']");
    protected static final ElementsCollection DISPLAY_TITLE_CHECKBOX_LIST = $$(".form-checkbox[id*='label-display']");
    protected static final SelenideElement BODY_FRAME = $("div#cke_1_contents iframe.cke_reset");
    protected static final ElementsCollection CTA_FIELD_CONTENT_LIST = $$(".form-autocomplete[id*='subform-field-call-to-action']");
    private static final String URL_REGEX = "/add/flexible_space";
    private static final SelenideElement ADD_FLEXIBLE_SPACE_CONTENT_H_1_TAG = $("#block-pagetitle h1.page-title").as("Add Flexible Space Content Page H1 Tag");
    private static final SelenideElement SAVE_BUTTON = $("#edit-submit").as("Save button");

    public AddFlexibleSpaceContentPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "AddFlexible Space Content Page not loaded..");
        assertEquals(ADD_FLEXIBLE_SPACE_CONTENT_H_1_TAG.getText()
                                                       .trim(), "Add Flexible space content", "Add Flexible Space Content Page title not loaded..");
        log.info("Add Flexible Space Content Page loaded");
    }

    public AddFlexibleSpaceContentPage addFlexibleSpace(List<ISectionDataModel> sectionModel) {

        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(AddFlexibleSpaceContentPage.class);
        }

        return this;
    }

    public FlexibleSpaceLandingPage saveFlexibleSpaceLanding() {

        SAVE_BUTTON.should(visible, enabled)
                   .click();
        waitAjaxJQueryMet(300);
        return new FlexibleSpaceLandingPage();
    }

}
