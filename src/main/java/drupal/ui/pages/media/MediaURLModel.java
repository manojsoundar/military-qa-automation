package drupal.ui.pages.media;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.ui.pages.media.AddMediaBasePage.URL_INPUT;

// TODO to implement equals if we are going to compare model as MediaURLModel is extends MediaTitleModel
// TODO if understand MediaURLModel can be a MediaTitleModel, and need title member ?
@Getter
@Setter
@Log4j2
public class MediaURLModel extends MediaTitleModel {

    private String mediaURL;

    public MediaURLModel(String name, String mediaURL) {

        super(name);
        this.mediaURL = mediaURL;

    }

    public static MediaURLModel getFacebookData() {

        return new MediaURLModel("Test Facebook Post " + timeStampFormat(PATTERN), "https://www.facebook.com/watch/?v=863957740812414");
    }

    public static MediaURLModel getTwitterData() {

        return new MediaURLModel("Test Twitter Post " + timeStampFormat(PATTERN), "https://twitter.com/WSJ/status/1352700057032617984");
    }

    public static MediaURLModel getPinterestData() {

        return new MediaURLModel("Test Pinterest Post " + timeStampFormat(PATTERN), "https://www.pinterest.com/pin/637892734718314838/");
    }

    public static MediaURLModel getVimeoData() {

        return new MediaURLModel("Test Vimeo Video " + timeStampFormat(PATTERN), "https://vimeo.com/443437002");
    }

    public static MediaURLModel getYoutubeData() {

        return new MediaURLModel("Test YouTube Video " + timeStampFormat(PATTERN), "https://www.youtube.com/watch?v=Uu09lmhg-M0");
    }

    public static MediaURLModel getInstagramData() {

        return new MediaURLModel("Test Instagram Post " + timeStampFormat(PATTERN), "https://www.instagram.com/p/CGD9Jx0lw8p4EZkV3wuuzDz-fSZtMOMBPPRp7A0/");
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        super.setData(expectedClass);
        typeMediaURL(expectedClass);
        return returnInstanceOf(expectedClass);

    }

    private <P extends MasterPage> P typeMediaURL(Class<P> expectedClass) {

        if (mediaURL != null) {
            URL_INPUT.should(visible, enabled)
                     .setValue(mediaURL);
        }
        return returnInstanceOf(expectedClass);
    }

}
