package drupal.ui.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.ImageUploadModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;
import static mgs.qa.utils.LoopUtils.doWhileConditionNotMet;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

public interface AttachAnImage {

    Logger LOG = LogManager.getLogger(AttachAnImage.class);

    SelenideElement MEDIA_NAME_INPUT = $("input#edit-name");
    SelenideElement SELECT_IMAGES_BUTTON = $("input#edit-submit[value='Select images']");
    SelenideElement SEARCH_BUTTON = $("input[id*=edit-submit-media-browser]");
    SelenideElement AJAX_PROGRESS_WEB_ELEMENT = $(".ajax-progress-throbber");
    SelenideElement EXISTING_IMAGE_LINK = $x("//a[text()='Existing image']");
    SelenideElement ATTACH_AN_IMAGE_POPUP_FRAME = $("iframe#entity_browser_iframe_image_browser").as("Attach an image frame");
    ElementsCollection THUMBNAIL_IMAGES_LIST = $$("img.image-style-media-entity-browser-thumbnail").as("Images for selection");

    default void uploadOrAttachExistingImage(ImageUploadModel imageUploadModel) {

        if (imageUploadModel.isExistingImage()) {
            waitAjaxJQueryMet(120);
            ATTACH_AN_IMAGE_POPUP_FRAME.should(appear, ofSeconds(20));
            switchTo().frame(ATTACH_AN_IMAGE_POPUP_FRAME);
            selectExistingImage(imageUploadModel);
            switchTo().parentFrame();
        }

    }

    default AttachAnImage selectExistingImage(ImageUploadModel imageUploadModel) {

        EXISTING_IMAGE_LINK.should(exist, appear)
                           .click();
        if (imageUploadModel.getMediaName() == null) {
            THUMBNAIL_IMAGES_LIST.first()
                                 .should(appear, ofSeconds(20))
                                 .click();
        } else {
            MEDIA_NAME_INPUT.should(exist, appear, enabled)
                            .setValue(imageUploadModel.getMediaName());
            SEARCH_BUTTON.should(appear, enabled)
                         .click();

            waitAjaxJQueryMet(300);

            if (imageUploadModel.isMultipleImage()) {

                for (SelenideElement image : THUMBNAIL_IMAGES_LIST) {
                    image.should(visible, ofSeconds(15))
                         .should(exist, enabled, appear)
                         .scrollTo()
                         .click();
                }
            } else {
                if (imageUploadModel.getImageTitle() != null) {
                    THUMBNAIL_IMAGES_LIST.find(Condition.attributeMatching("title", format(".*%s.*", imageUploadModel.getImageTitle())))
                                         .should(exist, enabled, visible)
                                         .click();
                } else {
                    THUMBNAIL_IMAGES_LIST.first()
                                         .should(exist, enabled, visible)
                                         .click();
                    doWhileConditionNotMet(
                            () -> {
                                return THUMBNAIL_IMAGES_LIST.first()
                                                            .should(exist, enabled, visible)
                                                            .$x("./ancestor::div[contains(@class,'entity-browser-enhanced-processed')]")
                                                            .is(attributeMatching("class", ".*selected"));
                            },
                            () -> {
                                THUMBNAIL_IMAGES_LIST.first()
                                                     .should(exist, enabled, visible)
                                                     .click();
                            }
                    );
                }
            }
        }
        SELECT_IMAGES_BUTTON.should(exist, appear, enabled)
                            .scrollTo()
                            .click();
        waitAjaxJQueryMet(300);

        return this;
    }

}
