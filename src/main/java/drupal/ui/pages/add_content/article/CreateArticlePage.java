package drupal.ui.pages.add_content.article;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.components.AttachAVideo;
import drupal.ui.components.AttachAnImage;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class CreateArticlePage extends AdministrationToolbar implements AttachAnImage, AttachAVideo {

    protected static final SelenideElement TITLE_INPUT = $("#edit-title-0-value").as("title input");
    protected static final SelenideElement ARTICLE_TYPE_DROPDOWN = $("#edit-field-article-type").as("article type dropdown");
    protected static final SelenideElement DISPLAY_DATE_INPUT = $("#edit-field-date-0-value-date").as("display date input");
    protected static final SelenideElement AUTHOR_INPUT = $("#edit-field-author-0-target-id").as("author input");
    protected static final SelenideElement SOURCE_INPUT = $("#edit-field-source-0-target-id").as("source input");
    protected static final ElementsCollection AUTHOR_ITEM_LIST = $$("#ui-id-2 li a").as("Author item list");
    protected static final ElementsCollection SOURCE_ITEM_LIST = $$("#ui-id-1 li a").as("source item list");
    protected static final ElementsCollection REGISTRATION_GATE_LIST = $$(".ui-widget-content .ui-menu-item a").as("Registration gate list");
    protected static final SelenideElement CONTRIBUTOR_INPUT = $("#edit-field-other-author-0-value").as("contributor input");
    protected static final SelenideElement SUMMARY_INPUT = $("#edit-field-summary-0-value").as("summary input");
    protected static final SelenideElement BODY_IFRAME = $("iframe.cke_wysiwyg_frame").as("body iframe");
    protected static final SelenideElement BODY_INPUT = $(".cke_editable_themed p").as("body input");
    protected static final SelenideElement SIDEBAR_DROP_DOWN = $("div#edit_field_sidebar_chosen").as("Sidebar dropdown");
    protected static final SelenideElement SIDE_BAR_INPUT = $("div#edit_field_sidebar_chosen .chosen-search .chosen-search-input");
    protected static final ElementsCollection SIDE_BAR_CONTAINER_LIST = $$("div#edit_field_sidebar_chosen ul.chosen-results li");
    protected static final SelenideElement RELATED_TOPICS_INPUT = $("#edit-field-keywords-target-id").as("related topics input");
    protected static final ElementsCollection RELATED_TOPICS_LIST = $$x(".//ul[@id='ui-id-3']//li/a | a[@class='ui-menu-item-wrapper']");
    protected static final SelenideElement SAVE_BUTTON = $("#edit-submit").as("save button");
    protected static final SelenideElement ATTACH_AN_IMAGE_BUTTON = $("input#edit-field-image-entity-browser-entity-browser-open-modal").as("Attach an Image Button");
    protected static final ElementsCollection CATEGORIES_AVAILABLE_LIST = $$("#edit_field_category_chosen ul.chosen-results li.active-result").as("Category list");
    protected static final SelenideElement CATEGORIES_INPUT = $("#edit_field_category_chosen input.chosen-search-input");
    protected static final SelenideElement VIDEO_UPLOAD_BUTTON = $(".cke_button__video").as("video Upload Button");
    protected static final SelenideElement GATE_CONTENT_SECTION = $("div.layout-region.layout-region-node-secondary summary[aria-controls*='edit-gated']");
    protected static final SelenideElement GATE_CONTENT_INPUT = $("[id^='edit-field-registration-gate'] input.form-text[id*='target-id']");
    protected static final SelenideElement SCHEDULING_OPTIONS_LINK = $("#edit-scheduler-settings span");
    protected static final SelenideElement UN_PUBLISH_ON_DATE = $("input#edit-unpublish-on-0-value-date");
    protected static final SelenideElement UN_PUBLISH_ON_TIME = $("input#edit-unpublish-on-0-value-time");
    protected static final SelenideElement PUBLISH_ON_DATE = $("input#edit-publish-on-0-value-date");
    protected static final SelenideElement PUBLISH_ON_TIME = $("input#edit-publish-on-0-value-time");
    private static final String URL_REGEX = "/add/article";
    private static final SelenideElement CREATE_ARTICLE_PAGE_H_1_TITLE = $("#block-pagetitle .page-title").as("Create Article Page Title");

    public CreateArticlePage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Create Article Page not loaded");
        assertEquals(CREATE_ARTICLE_PAGE_H_1_TITLE.getText()
                                                  .trim(), "Create Article", "CreateArticlePage title not displayed");
        log.info("Create Article Page loaded");
    }

    public CreateArticlePage fillArticle(List<ISectionDataModel> setData) {
        for (ISectionDataModel sectionData : setData) {
            sectionData.setData(CreateArticlePage.class);
        }
        return this;
    }

    public ArticlePage clickSaveButton() {
        SAVE_BUTTON.should(enabled, visible, exist)
                   .scrollTo()
                   .click();
        return new ArticlePage();
    }

    public CreateArticlePage clickSaveButtonAndVerifyWarningMessageAppeared() {
        SAVE_BUTTON.should(enabled, visible, exist)
                   .scrollTo()
                   .click();

        String isRequired = TITLE_INPUT.getAttribute("required");
        assertTrue(isRequired.contains("true"));
        assertEquals(TITLE_INPUT.getAttribute("validationMessage"), "Please fill out this field.", "Warning message failed to display!!");
        return this;
    }
}
