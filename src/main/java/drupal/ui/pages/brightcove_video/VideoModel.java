package drupal.ui.pages.brightcove_video;

import drupal.models.ISectionDataModel;
import drupal.ui.components.AttachAVideo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static drupal.ui.pages.brightcove_video.EditBrightcoveVideoPage.ALTERNATIVE_IMAGE_INPUT;
import static drupal.ui.pages.brightcove_video.EditBrightcoveVideoPage.RELATED_TOPICS_INPUT;
import static java.lang.String.format;

@Getter
@AllArgsConstructor
@Log4j2
public class VideoModel extends MasterPage implements ISectionDataModel, AttachAVideo {

    private final String videoName;
    private final String relatedTopics;
    private final String file;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        editVideo();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    public VideoModel editVideo() {
        log.info(format("Video title for alternative image text is -  %s", videoName));
        ALTERNATIVE_IMAGE_INPUT.should(exist, appear, enabled)
                               .setValue(videoName);
        return this;
    }

    public VideoModel enterRelatedTopics() {
        if (relatedTopics != null) {
            RELATED_TOPICS_INPUT.should(exist, appear, enabled)
                                .selectOptionContainingText(relatedTopics);
        }
        return this;
    }

}
