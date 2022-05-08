package drupal.ui.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.VideoUploadModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static java.time.Duration.ofMinutes;
import static java.time.Duration.ofSeconds;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

public interface AttachAVideo {

    Logger LOG = LogManager.getLogger(AttachAVideo.class);

    SelenideElement MEDIA_NAME_INPUT = $("input[id*='edit-name']");
    SelenideElement SELECT_VIDEO_BUTTON = $("input#edit-submit[value='Select video']");
    SelenideElement SEARCH_BUTTON = $("input[id*=edit-submit-media-browser]");
    ElementsCollection EXISTING_VIDEO_LINK_LIST = $$(".eb-tabs a");
    SelenideElement ATTACH_A_VIDEO_POPUP_FRAME = $("iframe[name*='entity_browser_iframe_video_browser']").as("Attach a video frame");
    ElementsCollection THUMBNAIL_VIDEOS_LIST = $$("img.image-style-media-entity-browser-thumbnail").as("Videos for selection");
    SelenideElement BODY_VIDEO_NEXT_BUTTON = $("button.js-button-next");
    SelenideElement BODY_VIDEO_EMBED_BUTTON = $x(".//*[text()='Embed']");
    SelenideElement SELECTED_VIDEO_WEB_ELEMENT = $x("//div[contains(@class,'view-display-id-entity_browser_videos')]//div[contains(@class,'view-content')]//div[contains(@class,'selected')]");

    default void uploadOrAttachExistingVideo(VideoUploadModel videoUploadModel) {

        if (videoUploadModel.isExistingVideo()) {
            waitAjaxJQueryMet(150);
            ATTACH_A_VIDEO_POPUP_FRAME.should(appear, ofSeconds(20));
            switchTo().frame(ATTACH_A_VIDEO_POPUP_FRAME);
            selectExistingVideo(videoUploadModel);
            switchTo().parentFrame();
        }

    }

    default AttachAVideo selectExistingVideo(VideoUploadModel videoUploadModel) {

        EXISTING_VIDEO_LINK_LIST.last()
                                .should(exist, appear)
                                .click();
        if (videoUploadModel.getMediaName() != null) {

            MEDIA_NAME_INPUT.should(exist, appear, enabled)
                            .setValue(videoUploadModel.getMediaName());
            waitAjaxJQueryMet(150);
            SEARCH_BUTTON.should(appear, enabled)
                         .click();
        }
        waitAjaxJQueryMet(300);
        THUMBNAIL_VIDEOS_LIST.first()
                             .should(appear, ofSeconds(30))
                             .click();
        while (!(SELECT_VIDEO_BUTTON.isEnabled() && SELECTED_VIDEO_WEB_ELEMENT.isDisplayed())) {
            THUMBNAIL_VIDEOS_LIST.first()
                                 .should(appear, ofSeconds(30))
                                 .click();
        }
        SELECT_VIDEO_BUTTON.should(appear, exist, enabled)
                           .click();
        waitAjaxJQueryMet(300);

        if (videoUploadModel.isBodyVideo()) {
            waitAjaxJQueryMet(120);
            // Should be : Code Standard DialogModal
            //BODY_VIDEO_NEXT_BUTTON.should(and("sync", enabled, visible), ofMinutes(5)).click();
            BODY_VIDEO_NEXT_BUTTON.should( enabled, visible)
                                  .click();
            waitAjaxJQueryMet(120);
            BODY_VIDEO_EMBED_BUTTON.should(enabled, visible)
                                   .click();
            waitAjaxJQueryMet(150);
        }
        return this;
    }

}
