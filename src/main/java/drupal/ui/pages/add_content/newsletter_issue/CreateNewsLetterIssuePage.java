package drupal.ui.pages.add_content.newsletter_issue;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
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
public class CreateNewsLetterIssuePage extends AdministrationToolbar {

    protected static final SelenideElement SUBJECT_INPUT = $("#edit-title-0-value").as("Subject Input");
    protected static final SelenideElement NEWSLETTER_TYPE_DROPDOWN = $("#edit-field-newsletter-type").as("Newsletter Type Dropdown");
    protected static final SelenideElement LEAD_STORY_ENTITY_TYPE_DROPDOWN = $("#edit-field-lead-story-0-target-type").as("Lead story Entity type Dropdown");
    protected static final SelenideElement LEAD_STORY_LABEL_INPUT = $("#edit-field-lead-story-0-target-id").as("Lead Story Label Input");
    protected static final ElementsCollection ARTICLES_OLD_DEPRECATED_INPUT = $$("input[data-drupal-selector*='edit-field-newsletter-articles'][name*='[target_id]']").as("Articles Old Deprecated Input List");
    protected static final SelenideElement ARTICLES_OLD_ADD_ANOTHER_ITEM_BUTTON = $("input[name*='articles_add_more']").as("Articles Old Add Another Item Button");
    protected static final ElementsCollection ARTICLES_ENTITY_TYPE_DROPDOWN_LIST = $$("select[data-drupal-selector*='edit-field-articles-or-snippets'][name*='target_type']").as("Articles Entity type Dropdown List");
    protected static final ElementsCollection ARTICLES_ENTITY_TYPE_INPUT_LIST = $$("input[data-drupal-selector*='edit-field-articles-or-snippets'][name*='[target_id]']").as("Articles Entity type Input List");
    protected static final SelenideElement ARTICLES_OR_SNIPPETS_ADD_ANOTHER_ITEM_BUTTON = $("input[name*='snippets_add_more']").as("Articles or snippets Add Another Item Button");
    protected static final SelenideElement TRIVIA_INPUT = $("#edit-field-trivia-0-target-id").as("Trivia Input");
    protected static final SelenideElement VIDEO_INPUT = $("#edit-field-videos-0-target-id").as("Video Input");
    protected static final SelenideElement VIDEO_EXISTING_MEDIA_INPUT = $("#edit-field-video-0-target-id").as("Video - Existing Media Input");
    protected static final SelenideElement EMAIL_SUBJECT_INPUT = $("#edit-field-email-subject-0-value").as("Email Subject Input");
    protected static final SelenideElement DISPLAY_DATE_INPUT = $("input#edit-field-date-0-value-date").as("Display Date Input");
    protected static final ElementsCollection URL_INPUT = $$("input.form-url").as("URL Input");
    protected static final SelenideElement URL_ADD_ANOTHER_ITEM_BUTTON = $("input[name*='field_tracking_pixels_add_more']").as("URL Add Another Item Button");
    protected static final SelenideElement NEWSLETTER_PRE_HEADER_TEXTAREA = $("#edit-field-newsletter-pre-header-0-value").as("Newsletter Pre Header Textarea");
    protected static final SelenideElement NEWSLETTER_HEADER_TEXTAREA = $("#edit-field-newsletter-header-0-value").as("Newsletter Header Textarea");
    private static final String URL_REGEX = "/add/newsletter_issue";
    private static final SelenideElement CREATE_NEWSLETTER_ISSUE_H_1_TAG = $("#block-pagetitle h1").as("Create Newsletter issue Page H1 Tag");
    private static final SelenideElement SAVE_BUTTON = $("#edit-submit").as("Save Button");

    public CreateNewsLetterIssuePage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Create Newsletter Issue Page not loaded.");
        assertTrue(CREATE_NEWSLETTER_ISSUE_H_1_TAG.should(exist, appear)
                                                  .has(text("Create Newsletter issue")), "Correct page is not displayed.");
        log.info("Create Newsletter Issue Page loaded properly.");
    }

    public NewsLetterIssueResultPage fillIn(List<ISectionDataModel> sectionModel) {

        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(CreateNewsLetterIssuePage.class);
        }
        SAVE_BUTTON.should(appear, enabled, visible)
                   .click();

        return new NewsLetterIssueResultPage();
    }

    @Getter
    @AllArgsConstructor
    public enum NewsletterType {
        SELECT_A_VALUE("- Select a value -"),
        MILITARY_REPORT("Military Report"),
        EARLY_BRIEF("Early Brief"),
        INSIDER_AIR_FORCE_ACTIVE("Insider Air Force (Active)"),
        INSIDER_AIR_FORCE_VETERAN("Insider Air Force (Veteran)"),
        INSIDER_ARMY_ACTIVE("Insider Army (Active)"),
        INSIDER_ARMY_VETERAN("Insider Army (Veteran)"),
        INSIDER_MARINES_ACTIVE("Insider Marines (Active)"),
        INSIDER_MARINES_VETERAN("Insider Marines (Veteran)"),
        INSIDER_NAVY_ACTIVE("Insider Navy (Active)"),
        INSIDER_NAVY_VETERAN("Insider Navy (Veterans)"),
        INSIDER_COAST_GUARD("Insider Coast Guard"),
        INSIDER_DOD("Insider DOD");

        private final String newsletterTypeItem;
    }

    @Getter
    @AllArgsConstructor
    public enum EntityType {
        CONTENT("Content"),
        SNIPPET("Snippet");

        private final String entityTypeItem;
    }
}
