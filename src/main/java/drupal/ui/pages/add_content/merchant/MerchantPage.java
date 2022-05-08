package drupal.ui.pages.add_content.merchant;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.components.AttachAnImage;
import lombok.extern.log4j.Log4j2;
import www.ui.pages.discounts.DiscountsHomePage;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class MerchantPage extends AdministrationToolbar implements AttachAnImage {

    protected static final SelenideElement MERCHANT_TITLE_INPUT = $("input[id=edit-title-0-value]");
    protected static final SelenideElement SAVE_INPUT = $("input[id=edit-submit]").as("Save button");
    protected static final SelenideElement ATTACH_AN_IMAGE_BUTTON = $("input#edit-field-image-entity-browser-entity-browser-open-modal").as("Attach an image button");
    protected static final SelenideElement MERCHANT_WEBSITE_URL_INPUT = $("input[id=edit-field-link-0-uri]");
    protected static final SelenideElement MERCHANT_WEBSITE_LINK_TEXT_INPUT = $("input[id=edit-field-link-0-title]");
    protected static final SelenideElement SPECIAL_STATUS_DROPDOWN = $("select[id=edit-field-special-status]");
    protected static final SelenideElement CATEGORIES_CONTAINER_DROP_WEB_ELEMENT = $(".chosen-container-active div.chosen-drop").as("Container displayed after entering the Category");
    protected static final ElementsCollection CATEGORIES_AVAILABLE_LIST = $$("div#edit_field_discount_categories_chosen ul li.active-result").as("Categories displayed in the container after entering the Category");
    protected static final SelenideElement CATEGORIES_INPUT = $("input.chosen-search-input");
    protected static final SelenideElement BODY_FRAME = $("div#cke_1_contents iframe.cke_reset");
    protected static final SelenideElement BODY_TEXTAREA = $("body.cke_editable p").as("Body Input");
    protected static final SelenideElement BLURB_TEXTAREA = $("textarea.form-textarea[name*='field_summary']").as("Blurb Textarea");
    protected static final SelenideElement ADD_ANOTHER_ITEM_BUTTON = $("input.field-add-more-submit");
    protected static final ElementsCollection TRACKING_PIXEL_URL_LIST = $$("input.form-url");
    protected static final SelenideElement CONTENT_RELEASE_INPUT = $("#edit-field-release-target-id").as("Content release input");
    private static final String URL_REGEX = "/node/add/merchant";
    private static final SelenideElement SNIPPETS_H_1_TAG = $("#block-pagetitle h1.page-title").as("Snippets Page H1 Tag");

    public MerchantPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Create Merchant Page isn't loaded.");
        assertEquals(SNIPPETS_H_1_TAG.should(exist, appear)
                                     .getText(), "Create Merchant", "Create Merchant title:" + SNIPPETS_H_1_TAG.getText() + "not displayed,should contain ('Create Merchant')");
        log.info("Create Merchant Page loaded properly.");
    }

    public DiscountsHomePage addMerchant(List<ISectionDataModel> sectionModel) {
        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(MerchantPage.class);
        }
        return clickSaveButton();
    }

    public DiscountsHomePage clickSaveButton() {
        SAVE_INPUT.should(exist, appear)
                  .scrollTo()
                  .click();
        waitAjaxJQueryMet(120);
        return new DiscountsHomePage();
    }

}
