package drupal.ui.pages.add_content.newsletter_article;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.AddNewsLetterArticleModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddNewsletterArticlePage extends AdministrationToolbar {

    private static final String URL_REGEX = "/node/add/newsletter_article";
    private static final SelenideElement NEWS_LETTER_ARTICLE_PAGE_H_1_TITLE = $("#block-pagetitle .page-title");
    private static final SelenideElement TITLE_INPUT = $("input#edit-title-0-value").as("Title Input Text box");
    private static final SelenideElement SOURCE_INPUT = $("input#edit-field-source-0-target-id").as("Source Input Text box");
    private static final SelenideElement BLURB_TEXT_AREA = $("textarea[id='edit-field-summary-0-value']").as("Blurb or Summary Text box");
    private static final SelenideElement BODY_TEXT_FRAME = $("iframe.cke_wysiwyg_frame.cke_reset").as("Body Input Text frame");
    private static final SelenideElement BODY_FRAME_TEXTAREA = $("body[class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders'] p").as("Body Input Text field");
    private static final SelenideElement CATEGORY_INPUT = $("#edit_field_category_chosen .search-field").as("Category Input Text box");
    private static final SelenideElement RELATED_TOPICS_INPUT = $("input[id='edit-field-keywords-target-id']").as("Details Keywords Text box");
    private static final SelenideElement CONTENT_RELEASE_INPUT = $("input[id='edit-field-release-target-id']").as("Details Current Release Text box");
    private static final SelenideElement BODY_TOP_WEB_ELEMENT = $("span.cke_top.cke_reset_all").as("Body top section");
    private static final SelenideElement CATEGORIES_CONTAINER_DROP_WEB_ELEMENT = $(".chosen-container-active div.chosen-drop").as("Container displayed after entering the Category");
    private static final ElementsCollection CATEGORIES_AVAILABLE_LIST = $$("div#edit_field_category_chosen ul li.active-result").as("Categories displayed in the container after entering the Category");
    private static final SelenideElement SAVE_BUTTON = $("input#edit-submit[value='Save']");

    public AddNewsletterArticlePage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Newsletter Article page not loaded.");
        assertEquals(NEWS_LETTER_ARTICLE_PAGE_H_1_TITLE.should(exist, appear)
                                                       .getText()
                                                       .trim(), "Create Newsletter article",
                     "Correct page title not displayed.");
        log.info("Newsletter Article page loaded properly.");
    }

    public NewsLetterArticlePage createNewsLetterArticleAndSave(AddNewsLetterArticleModel addNewsLetterArticleModel) {

        if (addNewsLetterArticleModel.getTitle() != null) {
            TITLE_INPUT.should(appear, enabled)
                       .setValue(addNewsLetterArticleModel.getTitle());
        }

        if (addNewsLetterArticleModel.getSource() != null) {
            SOURCE_INPUT.should(appear, enabled)
                        .setValue(addNewsLetterArticleModel.getSource());
        }

        if (addNewsLetterArticleModel.getBlurb() != null) {
            BODY_TOP_WEB_ELEMENT.should(appear, visible, enabled);
            BLURB_TEXT_AREA.should(appear, enabled)
                           .setValue(addNewsLetterArticleModel.getBlurb());
        }

        if (addNewsLetterArticleModel.getBody() != null) {
            switchTo().frame(BODY_TEXT_FRAME);
            BODY_FRAME_TEXTAREA.should(appear, exist, visible)
                               .click();
            BODY_FRAME_TEXTAREA.should(appear, visible, enabled)
                               .sendKeys(addNewsLetterArticleModel.getBody());
            switchTo().parentFrame();
        }

        for (String key : addNewsLetterArticleModel.getCategory()
                                                   .keySet()) {
            selectCategories(addNewsLetterArticleModel.getCategory()
                                                      .get(key));
        }

        if (addNewsLetterArticleModel.getRelatedTopics() != null) {
            RELATED_TOPICS_INPUT.should(appear, enabled)
                                .setValue(addNewsLetterArticleModel.getRelatedTopics());
        }
        if (addNewsLetterArticleModel.getContentRelease() != null) {
            CONTENT_RELEASE_INPUT.should(appear, enabled)
                                 .setValue(addNewsLetterArticleModel.getContentRelease());
        }

        return clickSaveButton();
    }

    public NewsLetterArticlePage clickSaveButton() {
        SAVE_BUTTON.scrollTo()
                   .should(appear, enabled, visible)
                   .click();

        return new NewsLetterArticlePage();
    }

    public AddNewsletterArticlePage selectCategories(String category) {
        BLURB_TEXT_AREA.scrollTo();
        CATEGORY_INPUT.should(exist, appear)
                      .click();

        if (category != null) {
            CATEGORY_INPUT.should(appear, enabled)
                          .setValue(category);
        }

        if (CATEGORIES_CONTAINER_DROP_WEB_ELEMENT.should(appear, enabled, visible)
                                                 .exists()) {
            CATEGORIES_AVAILABLE_LIST.first()
                                     .should(appear, exist, visible)
                                     .click();
        }

        return this;
    }

}
