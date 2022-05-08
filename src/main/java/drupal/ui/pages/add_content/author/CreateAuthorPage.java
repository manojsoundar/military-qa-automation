package drupal.ui.pages.add_content.author;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.components.AttachAnImage;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class CreateAuthorPage extends AdministrationToolbar implements AttachAnImage {

    public static final SelenideElement AUTHORS_NAME_INPUT = $("#edit-title-0-value").as("Authors input");
    public static final SelenideElement FIRST_NAME_INPUT = $("#edit-field-firstname-0-value").as("First Name input");
    public static final SelenideElement LAST_NAME_INPUT = $("#edit-field-lastname-0-value").as("Last Name input");
    public static final SelenideElement BIO_INPUT = $("body[class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders'] p").as("Bio Input");
    public static final SelenideElement BIO_IFRAME = $("iframe[title='Rich Text Editor, Bio field']").as("Bio Iframe");
    public static final SelenideElement BLURB_TEXTAREA = $("#edit-field-summary-0-value");
    public static final SelenideElement EMAIL_INPUT = $("edit-field-email-0-value").as("Email input");
    public static final SelenideElement CONTENT_RELEASE = $("#edit-field-release-target-id");
    public static final SelenideElement BREADCRUMB_CATEGORY = $("#edit-field-breadcrumb-category-0-target-id");
    public static final SelenideElement INACTIVE_SELECTION = $("#edit-field-inactive-selection");
    protected static final SelenideElement ATTACH_AN_IMAGE_BUTTON = $("input#edit-field-image-entity-browser-entity-browser-open-modal").as("Attach an Image Button");
    protected static final SelenideElement UPLOADED_IMAGE = $("img.media__element.b-lazy.b-loaded").as("Attach an image frame");
    protected static final SelenideElement WEB_SITE_URL = $("#edit-field-link-0-uri");
    protected static final SelenideElement WEB_SITE_LINK_TEXT = $("#edit-field-link-0-title");
    protected static final SelenideElement SOCIAL_LINK_TWITTER = $("#edit-field-link-0-uri");
    protected static final SelenideElement SOCIAL_LINK_FACEBOOK = $("#edit-field-link-0-title");
    protected static final SelenideElement SOCIAL_LINK_GOOGLE = $("#edit-field-link-0-title");
    private static final String URL_REGEX = "/add/author";
    private static final SelenideElement CREATE_AUTHOR_PAGE_H_1_TAG = $("#block-pagetitle h1").as("Create Author Page H1 Tag");
    private static final SelenideElement SAVE_BUTTON = $("#edit-submit--2").as("Save Button");


    public CreateAuthorPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Create Author Page not loaded..");
        assertEquals(CREATE_AUTHOR_PAGE_H_1_TAG.getText()
                                               .trim(), "Create Author", "Create Author Page title not displayed..");
        log.info("Create Author Page loaded");
    }

    public CreateAuthorPage fillIn(List<ISectionDataModel> authorInputData) {
        for (ISectionDataModel authorSectionDataModel : authorInputData) {
            authorSectionDataModel.setData(CreateAuthorPage.class);
        }
        return this;
    }

    public AuthorPage clickSaveButton() {
        SAVE_BUTTON.should(appear, enabled, visible)
                   .click();
        return new AuthorPage();
    }

}
