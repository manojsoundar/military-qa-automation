package drupal.models;

import drupal.ui.pages.media.MediaPage.Provider;
import drupal.ui.pages.media.MediaPage.PublishingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MediaModel {

    private final PublishingStatus publishingStatus;
    private final Provider provider;

    public MediaModel() {
        publishingStatus = getPublishingStatusType();
        provider = getProviderType();
    }

    public static PublishingStatus getPublishingStatusType() {
        return PublishingStatus.PUBLISHED;
    }

    public static Provider getProviderType() {
        return Provider.ANY;
    }

    public static MediaModel getAudioData() {
        return new MediaModel(getPublishingStatusType(), Provider.AUDIO);
    }

    public static MediaModel getImageData() {
        return new MediaModel(getPublishingStatusType(), Provider.IMAGE);
    }

    public static MediaModel getDocumentData() {
        return new MediaModel(getPublishingStatusType(), Provider.DOCUMENT);
    }

    public static MediaModel getFacebookData() {
        return new MediaModel(getPublishingStatusType(), Provider.FACEBOOK);
    }

    public static MediaModel getInstagramData() {
        return new MediaModel(getPublishingStatusType(), Provider.INSTAGRAM);
    }

    public static MediaModel getPinterestData() {
        return new MediaModel(getPublishingStatusType(), Provider.PINTEREST);
    }

    public static MediaModel getTwitterData() {
        return new MediaModel(getPublishingStatusType(), Provider.TWITTER);
    }

    public static MediaModel getYouTubeData() {
        return new MediaModel(getPublishingStatusType(), Provider.YOUTUBE_VIDEO);
    }

}
