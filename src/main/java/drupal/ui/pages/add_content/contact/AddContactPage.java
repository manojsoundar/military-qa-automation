package drupal.ui.pages.add_content.contact;

import com.codeborne.selenide.ElementsCollection;
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
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddContactPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/node/add/contact";
    private static final SelenideElement CONTACT_PAGE_H_1_TITLE = $("#block-pagetitle .page-title");
    protected static final SelenideElement CONTACT_TITLE_INPUT = $("input#edit-title-0-value");
    protected static final SelenideElement DESCRIPTION_FRAME = $("iframe[title*='Description field']");
    protected static final SelenideElement DESCRIPTION_FRAME_INPUT = $("html body.cke_editable");
    protected static final SelenideElement HOURS_INPUT = $("input#edit-field-hours-0-value");
    protected static final SelenideElement WEBSITE_INPUT = $("#edit-field-website-wrapper input[name^='field_website']");
    protected static final SelenideElement STREET_ADDRESS_1_INPUT = $("input#edit-field-street-address-1-0-value");
    protected static final SelenideElement STREET_ADDRESS_2_INPUT = $("input#edit-field-street-address-2-0-value");
    protected static final SelenideElement BUILDING_NUMBER_INPUT = $("input#edit-field-building-number-0-value");
    protected static final SelenideElement CROSS_STREET_INPUT = $("input#edit-field-cross-street-0-value");
    protected static final SelenideElement LOCATION_DROPDOWN_1 = $("#edit-field-base-location-shs-0-0").as("location dropdown1");
    protected static final SelenideElement LOCATION_DROPDOWN_2 = $("#edit-field-base-location-shs-0-1").as("location dropdown2");
    protected static final SelenideElement LOCATION_DROPDOWN_3 = $("#edit-field-base-location-shs-0-2").as("location dropdown3");
    protected static final SelenideElement ZIP_CODE_INPUT = $("input[id^=edit-field-zip-code]");
    protected static final SelenideElement GEO_LOCATION_LATITUDE_INPUT = $("input[id$=geo-location-0-lat]");
    protected static final SelenideElement GEO_LOCATION_LONGITUDE_INPUT = $("input[id$=geo-location-0-lng]");
    protected static final ElementsCollection PHONE_NUMBER_INPUT_LIST = $$("input.form-tel[id^='edit-field-phone-number']");
    protected static final SelenideElement ADD_ANOTHER_ITEM_PHONE_NUMBER_BUTTON = $("input[id*='edit-field-phone-number-add-more']");
    protected static final SelenideElement SIDE_BAR_DEFAULT_SELECTED_WEB_ELEMENT = $("a.chosen-single span");
    protected static final SelenideElement SIDE_BAR_INPUT = $(".chosen-search .chosen-search-input");
    protected static final ElementsCollection SIDE_BAR_CONTAINER_LIST = $$("ul.chosen-results li");
    protected static final SelenideElement BASE_REFERENCE_INPUT = $("input#edit-field-base-term-target-id");
    protected static final ElementsCollection BASE_REFERENCE_INPUT_LIST = $$(".ui-menu-item a");
    protected static final SelenideElement KEYWORD_INPUT = $("input#edit-field-keywords-target-id");
    protected static final SelenideElement BASE_OLD_ID_INPUT = $("input[id*='edit-field-base-old-id']");
    protected static final SelenideElement SAVE_BUTTON = $("input#edit-submit[value='Save']");
    protected static final SelenideElement AJAX_PROGRESS_WEB_ELEMENT = $(".ajax-progress-throbber");

    public AddContactPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Contact page not loaded.");
        assertEquals(CONTACT_PAGE_H_1_TITLE.should(exist, appear)
                                           .getText()
                                           .trim(), "Create Contact",
                "Correct page title not displayed.");
        log.info("Contact page loaded properly.");
    }

    public AddContactPage fillCreateContact(List<ISectionDataModel> setData) {
        for (ISectionDataModel sectionData : setData) {
            sectionData.setData(AddContactPage.class);
        }
        return this;
    }

    public ContactPage clickSaveButton() {
        SAVE_BUTTON.scrollTo()
                   .should(appear, enabled, visible)
                   .click();

        return new ContactPage();
    }
}
