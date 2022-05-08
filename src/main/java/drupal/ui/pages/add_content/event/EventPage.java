package drupal.ui.pages.add_content.event;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EventPage extends AdministrationToolbar {

    protected static final SelenideElement EVENT_PAGE_H_1_TAG = $("#block-pagetitle  h1").as("Event Page H1 Tag");
    protected static final SelenideElement TITLE_INPUT = $("#edit-title-0-value").as("Title input");
    protected static final SelenideElement HEADLINE_INPUT = $("#edit-field-headline-0-value").as("Headline input");
    protected static final SelenideElement START_DATE_INPUT = $("#edit-field-start-date-0-value-date").as("Start date input");
    protected static final SelenideElement START_DATE_TIME_INPUT = $("#edit-field-start-date-0-value-time").as("Start date time input");
    protected static final SelenideElement END_DATE_INPUT = $("#edit-field-end-date-0-value-date").as("End date input");
    protected static final SelenideElement END_DATE_TIME_INPUT = $("#edit-field-end-date-0-value-time").as("End date time input");
    protected static final SelenideElement JOB_FAIR_TYPE_SELECTOR = $("#edit-field-event-job-fair-type").as("Job fair type");
    protected static final SelenideElement EMAIL_INPUT = $("#edit-field-email-0-value").as("Email input");
    protected static final SelenideElement LINK_INPUT = $("#edit-field-link-0-uri").as("Link input");
    protected static final SelenideElement SAVE_BUTTON = $("#edit-submit").as("Save button");
    protected static final SelenideElement COUNTRY_SELECTOR = $("#edit-field-location-0-address-country-code--2").as("Country");
    protected static final SelenideElement STREET_ADDRESS_INPUT = $("#edit-field-location-0-address-address-line1").as("Street address input");
    protected static final SelenideElement CITY_INPUT = $("#edit-field-location-0-address-locality").as("City input");
    protected static final SelenideElement STATE_SELECTOR = $("#edit-field-location-0-address-administrative-area").as("State");
    protected static final SelenideElement ZIPCODE_INPUT = $("#edit-field-location-0-address-postal-code").as("Zipcode input");
    protected static final SelenideElement DISPLAY_TIME_CHECKBOX = $("#edit-field-show-time-value").as("Display time checkbox");
    protected static final SelenideElement PHONE_INPUT = $("#edit-field-phone-0-value").as("Phone input");
    protected static final SelenideElement EVENT_ID_INPUT = $("#edit-field-event-id-0-value").as("event id input");
    protected static final SelenideElement CATEGORIES_INPUT = $("input.chosen-search-input").as("Categories Dropdown");
    protected static final ElementsCollection CATEGORIES_AVAILABLE_LIST = $$("ul.chosen-results li.active-result");
    protected static final SelenideElement CATEGORIES_CONTAINER_WEB_ELEMENT = $(".chosen-container-active div.chosen-drop");
    protected static final SelenideElement CONTENT_RELEASE_INPUT = $("#edit-field-release-target-id").as("Content Release");
    protected static final SelenideElement BODY_FRAME = $("div#cke_1_contents iframe.cke_reset");
    protected static final SelenideElement BODY_TEXTAREA = $("body.cke_editable p").as("Body Input");
    protected static final SelenideElement BLURB_TEXTAREA = $("textarea.form-textarea[name*='field_summary']").as("Blurb Textarea");
    private static final String URL_REGEX = "/add/event";


    public EventPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Event Page not loaded..");
        assertTrue(EVENT_PAGE_H_1_TAG.getText()
                                     .trim()
                                     .contains("Create Event"),
                   "Event title: " + EVENT_PAGE_H_1_TAG.getText() + " not displayed, should contains('Create Event')");
        log.info("Event Page loaded..");
    }

    public UpcomingJobFairsPage createEvent(List<ISectionDataModel> sectionModel) {
        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(EventPage.class);
        }
        SAVE_BUTTON.scrollIntoView("{behavior: 'instant', block: 'center', inline: 'center'}")
                   .should(appear, enabled, visible)
                   .click();
        // TOOD fix : click event wrong ? ajax to sync ?
        return new UpcomingJobFairsPage();

    }

}
