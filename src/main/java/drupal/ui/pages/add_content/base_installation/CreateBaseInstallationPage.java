package drupal.ui.pages.add_content.base_installation;

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
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class CreateBaseInstallationPage extends AdministrationToolbar {

    protected static final SelenideElement BASE_INSTALLATION_TITLE_INPUT = $("#edit-title-0-value").as("Base installation title input");
    protected static final SelenideElement BLURB_FRAME = $("iframe[title='Rich Text Editor, Blurb (or summary) field']").as("Blurb or summary frame");
    protected static final SelenideElement BLURB_TEXT_AREA = $("body.cke_editable p").as("Blurb or summary input");
    protected static final SelenideElement IS_BASE_SERVICE_CHECK_BOX = $("#edit-field-is-base-service-value").as("Is Base Service Check box");
    protected static final SelenideElement MARINES_SERVICES_CHECK_BOX = $("#edit-field-services-67761").as("Marines Services Check box");
    protected static final SelenideElement NAVY_SERVICES_CHECK_BOX = $("#edit-field-services-67766").as("Navy Services Check box");
    protected static final SelenideElement BASE_INSTALLATION_CONTAINER_WEB_ELEMENT = $("ul#ui-id-1.ui-menu.ui-widget");
    protected static final SelenideElement BASE_SERVICE_CATEGORY_INPUT = $("#edit-field-base-service-category-0-target-id").as("Base service category input");
    protected static final ElementsCollection BASE_INSTALLATION_AVAILABLE_LIST = $$("ul#ui-id-1.ui-menu.ui-widget li");
    protected static final SelenideElement BASE_REFERENCE_DROP_DOWN = $("div#edit_field_base_term_chosen").as("Base reference dropdown");
    protected static final SelenideElement BASE_REFERENCE_INPUT = $("div#edit_field_base_term_chosen div.chosen-search .chosen-search-input");
    protected static final ElementsCollection BASE_REFERENCE_CONTAINER_LIST = $$("ul.chosen-results li");
    protected static final SelenideElement SHORT_NAME_INPUT = $("input#edit-field-short-name-0-value").as("Short Name Input");
    protected static final SelenideElement LOCATION_STREET_ADDRESS1_INPUT = $("input#edit-field-street-address-1-0-value").as("Location Street Address 1 Input");
    protected static final SelenideElement LOCATION_STREET_ADDRESS2_INPUT = $("input#edit-field-street-address-2-0-value").as("Location Street Address 1 Input");
    protected static final SelenideElement LOCATION_DROP_DOWN = $("select#edit-field-base-location-shs-0-0").as("Location dropdown");
    protected static final SelenideElement TERRITORY_DROP_DOWN = $("select#edit-field-base-location-shs-0-1").as("Territory dropdown");
    protected static final SelenideElement STATE_DROP_DOWN = $("select#edit-field-base-location-shs-0-2").as("State dropdown");
    protected static final SelenideElement ZIP_CODE_INPUT = $("input#edit-field-zip-code-0-value").as("Zip code Input");
    protected static final SelenideElement GEO_LATITUDE_INPUT = $("input#edit-field-geo-location-0-lat").as("Latitude Input");
    protected static final SelenideElement GEO_LONGITUDE_INPUT = $("input#edit-field-geo-location-0-lng").as("Longitude Input");
    protected static final SelenideElement SIDEBAR_DROP_DOWN = $("div#edit_field_sidebar_chosen").as("Sidebar dropdown");
    protected static final SelenideElement SIDE_BAR_INPUT = $("div#edit_field_sidebar_chosen .chosen-search .chosen-search-input");
    protected static final ElementsCollection SIDE_BAR_CONTAINER_LIST = $$("div#edit_field_sidebar_chosen ul.chosen-results li");
    private static final String URL_REGEX = "/add/base_installation";
    private static final SelenideElement CREATE_BASE_INSTALLATION_PAGE_H_1_TAG = $("#block-pagetitle h1").as("Create Base Installation Page H1 Tag");
    private static final SelenideElement SIDE_BAR_DEFAULT_SELECTED_WEB_ELEMENT = $("a.chosen-single span");
    private static final SelenideElement SAVE_BUTTON = $("#edit-submit").as("Save Button");


    public CreateBaseInstallationPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Create Base Installation Page not loaded..");
        assertEquals(CREATE_BASE_INSTALLATION_PAGE_H_1_TAG.getText()
                                                          .trim(), "Create Base Installation", "Create Base Installation Page title not displayed..");
        log.info("Create Base Installation Page loaded");
    }

    public BaseInstallationPage createBaseInstallation(List<ISectionDataModel> sectionModel) {

        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(CreateBaseInstallationPage.class);
        }
        return clickSaveButton();
    }

    public BaseInstallationPage clickSaveButton() {
        SAVE_BUTTON.should(enabled, visible, appear)
                   .click();
        waitAjaxJQueryMet(120);
        return new BaseInstallationPage();
    }

}
