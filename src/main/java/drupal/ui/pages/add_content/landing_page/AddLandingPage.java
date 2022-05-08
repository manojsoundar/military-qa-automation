package drupal.ui.pages.add_content.landing_page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddLandingPage extends AdministrationToolbar {

    public static final String GENERIC = "tbody input[id^='edit-field-flexible-%s-'][type='text']";
    protected static final SelenideElement TITLE_INPUT = $("#edit-title-0-value").as("Title input");
    protected static final SelenideElement HIDE_BREAD_CRUMB_INPUT = $("input#edit-field-hide-breadcrumb-value").as("Hide breadcrumb Checkbox");
    protected static final SelenideElement SUMMARY_INPUT = $("#edit-field-summary-0-value").as("Summary input");
    protected static final SelenideElement BODY_TEXTAREA = $("body.cke_editable p").as("Body Input");
    protected static final SelenideElement BODY_FRAME = $("div#cke_1_contents iframe.cke_reset");
    protected static final SelenideElement SIDEBAR_DROPDOWN = $(".chosen-single").as("Sidebar dropdown");
    protected static final SelenideElement SIDEBAR_SEARCH_INPUT = $(".chosen-search .chosen-search-input").as("Sidebar search input");
    protected static final ElementsCollection SIDEBAR_SEARCH_RESULTS = $$(".chosen-drop .chosen-results li").as("sidebar search results");
    protected static final SelenideElement KEYWORDS_INPUT = $("#edit-field-keywords-target-id").as("Keywords input");
    protected static final ElementsCollection KEYWORDS_LIST = $$(".ui-widget-content .ui-menu-item-wrapper").as("keywords list");
    protected static final SelenideElement CONTENT_RELEASE_INPUT = $("#edit-field-release-target-id").as("Content release input");
    protected static final ElementsCollection CONTENT_RELEASE_LIST = $$(".ui-widget-content .ui-menu-item-wrapper").as("Content release list");
    protected static final ElementsCollection CATEGORIES_AVAILABLE_LIST = $$("#edit_field_category_chosen .chosen-drop li").as("Categories displayed in the container after entering the Category");
    protected static final SelenideElement CATEGORY_INPUT = $("#edit_field_category_chosen input").as("Category input");
    protected static final SelenideElement TRACKING_PIXELS_INPUT = $("input[id*='edit-field-tracking-pixels']").as("Tracking Pixels input");
    protected static final SelenideElement ADD_ANOTHER_ITEM_BUTTON = $("input[id*='edit-field-tracking-pixels-add-more']").as("Add another item button");
    protected static final SelenideElement MESSAGE_WEB_ELEMENT = $(".ajax-progress-throbber div.message").as("Tracking pixels loading message");
    protected static final SelenideElement ATTACH_AN_IMAGE_BUTTON = $("input.button[id*='entity-browser-open-modal']");
    protected static final ElementsCollection FLEXIBLE_CONTENT_SPACE_IMAGE_TITLE_INPUT = $$("input[id*='subform-field-title']");
    private static final String URL_REGEX = "/add/landing_page";
    private static final SelenideElement CREATE_LANDING_PAGE_H_1_TAG = $("#block-pagetitle h1.page-title").as("Create Landing page H1 Tag");
    private static final SelenideElement SAVE_BUTTON = $("#edit-submit").as("Save button");

    public AddLandingPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Create Landing Page not loaded");
        assertEquals(CREATE_LANDING_PAGE_H_1_TAG.should(exist, appear)
                                                .getText()
                                                .trim(), "Create Landing page", "Create Landing page title not loaded");
        log.info("Create Landing Page loaded");
    }


    public AddLandingPage fillIn(List<ISectionDataModel> landingPageSectionData) {

        for (ISectionDataModel landingPageSection : landingPageSectionData) {
            landingPageSection.setData(AddLandingPage.class);
        }
        return this;
    }

    public ResultLandingPage clickSaveButton() {
        SAVE_BUTTON.should(exist, enabled)
                   .scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"center\"}")
                   .click();
        return new ResultLandingPage();
    }
}
