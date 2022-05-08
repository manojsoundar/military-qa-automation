package drupal.ui.pages.add_content.discount;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.components.AttachAnImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;
import static org.testng.Assert.assertTrue;

@Log4j2
public class CreateDiscountPage extends AdministrationToolbar implements AttachAnImage {

    protected static final SelenideElement DISCOUNT_TITLE_INPUT = $("input#edit-title-0-value").as("Discount Title Input");
    protected static final SelenideElement MERCHANT_INPUT = $("input#edit-field-merchant-0-target-id").as("Merchant Input");
    protected static final SelenideElement MERCHANT_CONTAINER_WEB_ELEMENT = $("ul#ui-id-1.ui-menu.ui-widget");
    protected static final ElementsCollection MERCHANT_AVAILABLE_LIST = $$("ul#ui-id-1.ui-menu.ui-widget li");
    protected static final SelenideElement MILITARY_ID_DROPDOWN = $("#edit-field-military-id-required").as("Military ID");
    protected static final SelenideElement REDEMPTION_TYPE_DROPDOWN = $("#edit-field-discount-type").as("Redemption Type");
    protected static final SelenideElement DEAL_TYPE_DROPDOWN = $("#edit-field-deal-type").as("Deal Type");
    protected static final ElementsCollection AVAILABLE_TO_CHECKBOX_LIST = $$("div#edit-field-available-to label");
    protected static final SelenideElement BLURB_TEXTAREA = $("textarea.form-textarea[name*='field_summary']").as("Blurb Textarea");
    protected static final ElementsCollection URL_INPUT = $$("input.form-url").as("URL Input");
    protected static final SelenideElement ADD_ANOTHER_ITEM_BUTTON = $("input.field-add-more-submit").as("Add another item Button");
    protected static final SelenideElement END_DATE_INPUT = $("#edit-field-date-0-value-date").as("End Date Input");
    protected static final SelenideElement CATEGORIES_INPUT = $("input.chosen-search-input").as("Categories Dropdown");
    protected static final ElementsCollection CATEGORIES_AVAILABLE_LIST = $$("ul.chosen-results li.active-result");
    protected static final SelenideElement CATEGORIES_CONTAINER_WEB_ELEMENT = $(".chosen-container-active div.chosen-drop");
    protected static final SelenideElement BODY_FRAME = $("div#cke_1_contents iframe.cke_reset");
    protected static final SelenideElement BODY_TEXTAREA = $("body.cke_editable p").as("Body Input");
    protected static final SelenideElement DISCLAIMER_BUTTON = $("#edit-group-disclaimer span");
    protected static final SelenideElement HIDE_DISCLAIMER_CHECKBOX = $(".js-form-item-field-disclaimer-hidden-value input");
    protected static final SelenideElement DISCLAIMER_HEADING_INPUT = $("#edit-field-disclaimer-heading-0-value");
    protected static final SelenideElement DISCLAIMER_BLURB_TEXTAREA = $("#edit-field-disclaimer-blurb-0-value");
    protected static final SelenideElement REDEEM_ONLINE_INPUT = $("#edit-field-link-0-uri");
    protected static final SelenideElement REDEEM_ONLINE_LABEL_INPUT = $("#edit-field-redeem-online-label-0-value");
    protected static final SelenideElement ATTACH_AN_IMAGE_BUTTON = $("#edit-field-coupon-image-entity-browser-entity-browser-open-modal");
    protected static final SelenideElement COUPON_CODE_INPUT = $("#edit-field-coupon-code-0-value");
    protected static final SelenideElement EXPIRED_MESSAGE_FRAME = $("div#cke_2_contents iframe.cke_reset");
    protected static final SelenideElement EXPIRED_MESSAGE_TEXTAREA = $("body.cke_editable p").as("Body Input");
    private static final String URL_REGEX = "/add/discount";
    private static final SelenideElement CREATE_DISCOUNT_H_1_TAG = $("#block-pagetitle h1").as("Create Discount Page H1 Tag");
    private static final SelenideElement SAVE_BUTTON = $("#edit-submit").as("Save Button");
    protected static final SelenideElement GATE_CONTENT_SECTION = $("div.layout-region.layout-region-node-secondary summary[aria-controls*='edit-gated']");
    protected static final SelenideElement GATE_CONTENT_INPUT = $("[id^='edit-field-registration-gate'] input.form-text[id*='target-id']");
    protected static final ElementsCollection REGISTRATION_GATE_LIST = $$(".ui-widget-content .ui-menu-item a").as("Registration gate list");

    public CreateDiscountPage() {

        assertTrue(verifyURLLoaded(URL_REGEX), "Create Discount Page not loaded.");
        assertTrue(CREATE_DISCOUNT_H_1_TAG.should(exist, appear)
                                          .has(text("Create Discount")), "Correct page is not displayed.");
        log.info("Create Discount Page loaded properly.");
    }

    public CreateDiscountPage fillIn(List<ISectionDataModel> sectionModel) {

        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(CreateDiscountPage.class);
        }

        return this;
    }

    public DiscountsPage clickSaveButton() {
        SAVE_BUTTON.scrollTo()
                   .should(appear, enabled, visible)
                   .click();
        waitAjaxJQueryMet(300);
        return new DiscountsPage();
    }

    @Getter
    @AllArgsConstructor
    public enum MilitaryIdItem {
        NONE("- None -"),
        REQUIRED("Required"),
        NOT_REQUIRED("Not required");

        private final String militaryId;
    }

    @Getter
    @AllArgsConstructor
    public enum RedemptionTypeItem {
        NONE("- None -"),
        ONLINE("Online"),
        IN_STORE("In store"),
        ONLINE_AND_IN_STORE("Online & in store");

        private final String redemptionType;
    }

    @Getter
    @AllArgsConstructor
    public enum DealTypeItem {
        NONE("- None -"),
        SHOPPING_TIP("Shopping tip"),
        COUPON_CODE("Coupon code"),
        PRINTABLE_COUPON("Printable coupon");

        private final String dealType;
    }

    @Getter
    @AllArgsConstructor
    public enum AvailableToItem {

        ACTIVE_DUTY("Active duty"),
        RETIREE("Retiree"),
        VETERAN("Veteran"),
        RESERVE_NATIONAL_GUARD("Reserve/National Guard"),
        DEPENDENT("Dependent"),
        MILITARY_EXCLUSIVE("Military.com exclusive");

        private final String availableTo;

        public static List<AvailableToItem> getList() {
            return Arrays.asList(AvailableToItem.values());
        }
    }

}
