package drupal.ui.pages.media;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.MediaModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Log4j2
public class MediaPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/admin/content/media";
    private static final SelenideElement MEDIA_H_1_TAG = $("#block-pagetitle h1").as("Media Page H1 Tag");
    private static final ElementsCollection MEDIA_TABLE_MEDIA_FILES_NAME_LIST = $$("table.views-table tbody td.views-field-name").as("Media Files Name List");
    private static final ElementsCollection MEDIA_TABLE_MEDIA_FILES_PROVIDER_LIST = $$("table.views-table tbody td.views-field-bundle").as("Media Files Provider List");
    private static final SelenideElement PUBLISHING_STATUS_DROPDOWN = $("#edit-status");
    private static final SelenideElement PROVIDER_DROPDOWN = $("#edit-provider");
    private static final SelenideElement MEDIA_NAME_INPUT = $("#edit-name");
    private static final SelenideElement APPLY_BUTTON = $("#edit-submit-media");
    private static final ElementsCollection MEDIA_LINK_LIST = $$("td.views-field-name a");
    private static final SelenideElement MEDIA_STATUS_MESSAGE = $(".messages--status");

    public MediaPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Media Page not loaded.");
        assertTrue(MEDIA_H_1_TAG.should(exist, appear)
                                .has(text("Media")), "Correct page is not displayed.");
        log.info("Media Page loaded properly.");
    }

    public MediaPage verifyAddedMedia(String mediaName) {

        log.info("Verify if uploaded file is displayed in Media table");
        assertFalse(MEDIA_TABLE_MEDIA_FILES_NAME_LIST.isEmpty(), "No files displayed");
        assertTrue(MEDIA_LINK_LIST.find(exactText(mediaName))
                                  .isDisplayed(), "Filename displayed in the list is not same as Uploaded file---" + mediaName);
        int index = MEDIA_TABLE_MEDIA_FILES_NAME_LIST.stream()
                                                     .map(SelenideElement::getText)
                                                     .collect(Collectors.toList())
                                                     .indexOf(mediaName);
        log.info("Uploaded media listed at the top is: " + MEDIA_TABLE_MEDIA_FILES_NAME_LIST.find(exactText(mediaName))
                                                                                            .getText()
                         + " and media type is: " + MEDIA_TABLE_MEDIA_FILES_PROVIDER_LIST.get(index)
                                                                                         .getText()
                                                                                         .trim());
        return this;
    }

    public MediaPage fillSearchCriteriaAndFilterMedia(MediaModel mediaData, String mediaName) {

        if (mediaData.getPublishingStatus() != null) {
            PUBLISHING_STATUS_DROPDOWN.should(exist, appear, enabled)
                                      .selectOptionContainingText(mediaData.getPublishingStatus()
                                                                           .getPublishingStatusType());
        }
        if (mediaData.getProvider() != null) {
            PROVIDER_DROPDOWN.should(exist, appear, enabled)
                             .selectOptionContainingText(mediaData.getProvider()
                                                                  .getProviderType());
        }
        if (mediaName != null) {
            MEDIA_NAME_INPUT.should(exist, appear, enabled)
                            .setValue(mediaName);
        }
        APPLY_BUTTON.should(exist, appear, enabled)
                    .click();
        return this;
    }

    public MediaLandingPage clickMediaLink(String mediaTitle) {

        if (!MEDIA_LINK_LIST.isEmpty()) {
            MEDIA_LINK_LIST.find(text(mediaTitle))
                           .click();
        }

        return new MediaLandingPage();
    }

    public MediaPage verifyMediaUpdatedMessage(String mediaTitle) {

        log.info("verify Media update message");
        assertTrue(MEDIA_STATUS_MESSAGE.has(text("updated")), "Updated message not displayed");
        assertTrue(MEDIA_STATUS_MESSAGE.$("em a")
                                       .has(text(mediaTitle)), "Media updated message doest not display correct Media Name");
        return this;
    }

    @Getter
    @AllArgsConstructor
    public enum PublishingStatus {

        ANY("- Any -"),
        PUBLISHED("Published"),
        UNPUBLISHED("Unpublished");

        private final String publishingStatusType;
    }

    @Getter
    @AllArgsConstructor
    public enum Provider {

        ANY("- Any -"),
        AUDIO("Audio"),
        BRIGHTCOVE_VIDEO("Brightcove video"),
        DOCUMENT("Document"),
        FACEBOOK("Facebook"),
        IMAGE("Image"),
        INSTAGRAM("Instagram"),
        PINTEREST("Pinterest"),
        TWITTER("Twitter"),
        YOUTUBE_VIDEO("YouTube video");

        private final String providerType;
    }

}
