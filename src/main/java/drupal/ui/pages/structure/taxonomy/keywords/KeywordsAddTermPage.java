package drupal.ui.pages.structure.taxonomy.keywords;

import com.codeborne.selenide.ElementsCollection;
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
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class KeywordsAddTermPage extends AdministrationToolbar implements AttachAnImage {

    protected static final SelenideElement NAME_INPUT = $("input#edit-name-0-value");
    protected static final SelenideElement DESCRIPTION_FRAME = $("div#cke_1_contents iframe.cke_reset");
    protected static final SelenideElement DESCRIPTION_TEXT_AREA = $("body.cke_editable p");
    protected static final SelenideElement GENERATE_AUTOMATIC_URL_ALIAS_CHECKBOX = $("#edit-path-0-pathauto");
    protected static final SelenideElement URL_ALIAS_INPUT = $("#edit-path-0-alias");
    protected static final SelenideElement CATEGORY_DROPDOWN = $("#edit-field-keyword-category");
    protected static final SelenideElement ADD_ANOTHER_ITEM_BUTTON = $("input.field-add-more-submit");
    protected static final ElementsCollection TRACKING_PIXEL_URL_LIST = $$("input.form-url");
    protected static final SelenideElement LINK_URL_INPUT = $("input#edit-field-link-0-uri");
    protected static final SelenideElement RELATIONS_LINK = $("#edit-relations span");
    protected static final SelenideElement PARENT_TERMS_SELECT_MENU = $("select#edit-parent");
    private static final String URL_REGEX = "/keywords/add";
    private static final SelenideElement ADD_TERM_H_1_TAG = $("#block-pagetitle h1");
    private static final SelenideElement SAVE_BUTTON = $("input#edit-submit");
    private static final SelenideElement STATUS_MESSAGE = $("div.messages--status");
    private static final SelenideElement STATUS_MESSAGE_TERM_LINK = STATUS_MESSAGE.$(".placeholder");

    public KeywordsAddTermPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Keywords-Add term Page not loaded.");
        assertTrue(ADD_TERM_H_1_TAG.should(exist, appear)
                                   .has(text("Add term")), "Keywords Add Term title:" + ADD_TERM_H_1_TAG.getText() + "not displayed,should contain('Add term')");
        log.info("Keywords-Add term Page loaded properly.");
    }

    public KeywordsAddTermPage fillIn(List<ISectionDataModel> sectionModel) {
        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(KeywordsAddTermPage.class);
        }
        SAVE_BUTTON.should(appear, enabled, visible)
                   .click();
        return this;
    }

    public KeywordsAddTermPage verifyStatusMessage(KeywordsDataModel taxonomyKeywordsData) {
        assertTrue(STATUS_MESSAGE.isDisplayed(), "Add Term Status message not displayed");
        assertTrue(STATUS_MESSAGE_TERM_LINK.getText()
                                           .contains(taxonomyKeywordsData.getName()), "Keyword term Status message not displayed with correct term name");
        return this;
    }

    public KeywordsLandingPage navigateToNewTermCreated(KeywordsDataModel taxonomyKeywordsData) {

        STATUS_MESSAGE_TERM_LINK.should(exist, appear, enabled)
                                .shouldHave(text(taxonomyKeywordsData.getName()))
                                .click();
        return new KeywordsLandingPage();
    }

    @Getter
    @AllArgsConstructor
    public enum Category {
        NONE("- None -"),
        AUTOS("Autos"),
        AWARDS("Awards"),
        BENEFITS("Benefits"),
        COMMANDS("Commands"),
        COMPONENTS("Components"),
        CONFLICTS("Conflicts"),
        DEALS_AND_DISCOUNT("Deals and Discount"),
        DEPLOYMENT("Deployment"),
        EDUCATION("Education"),
        ENTERTAINMENT("Entertainment"),
        FAMILY_AND_SPOUSE("Family and Spouse"),
        FORCES("Forces"),
        GEAR_AND_EQUIPMENT("Gear and Equipment"),
        GOVERNMENT("Government"),
        HOLIDAY_AND_EVENTS("Holidays and Events"),
        JOIN_THE_MILITARY("Join the Military"),
        LAW_ENFORCEMENT("Law Enforcement"),
        LEGISLATION("Legislation"),
        MILITARY_BASES("Military Bases"),
        MILITARY_FITNESS("Military Fitness"),
        MILITARY_TECHNOLOGY("Military Technology"),
        NEWS("News"),
        OPERATIONS("Operations"),
        PEOPLE("People"),
        PERSONAL_FINANCE("Personal Finance"),
        PERSONAS("Personas"),
        PERSONNEL("Personnel"),
        PLACES("Places"),
        RANK("Rank"),
        TERRORISM("Terrorism"),
        TRAINING("Training"),
        VETERAN_JOBS("Veteran Jobs");

        private final String categoryItem;
    }
}
