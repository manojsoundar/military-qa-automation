package drupal.ui.pages.add_content.flexible_content_space;

import drupal.enums.content.FlexibleContentSpacePosition;
import drupal.models.ISectionDataModel;
import drupal.models.VideoUploadModel;
import drupal.ui.components.AttachAVideo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static drupal.enums.content.FlexibleContentSpaceType.ADD_VIDEO;
import static java.lang.String.format;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@Getter
@AllArgsConstructor

public class FlexibleContentSpaceVideoModel extends MasterPage implements ISectionDataModel, AttachAVideo, IFlexibleContentSpace {

    private static final String FLEXIBLE_CONTENT_SPACE_VIDEO_TITLE_INPUT = "input.form-text[id^='edit-field-flexible-%s-%s-subform-field-title']";
    private static final String FLEXIBLE_CONTENT_SELECT_VIDEO_BUTTON = "input.button[id^='edit-field-'][id*='-%s-%s-subform']";
    private static final String FLEXIBLE_CONTENT_VIDEO_UPLOADED_WEB_ELEMENT = "div[id*='edit-field'][id*='-%s-%s-subform-field-video-current-items']";

    private final FlexibleContentSpacePosition position;
    private int index;
    private final String addVideoTitle;
    private final String existingVideoName;
    private final boolean multipleVideo;
    private final boolean bodyVideo;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        index = selectFlexibleContentSpace(ADD_VIDEO, position) - 1;
        enterAddVideoTitle().uploadExistingVideo();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private FlexibleContentSpaceVideoModel enterAddVideoTitle() {
        if (addVideoTitle != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_VIDEO_TITLE_INPUT, position.name()
                                                                       .toLowerCase(), index)).should(visible, enabled)
                                                                                              .setValue(addVideoTitle);
        }
        return this;
    }

    private FlexibleContentSpaceVideoModel uploadExistingVideo() {
        $(format(FLEXIBLE_CONTENT_SELECT_VIDEO_BUTTON, position.name()
                                                               .toLowerCase(), index)).should(exist, enabled)
                                                                                      .click();
        waitAjaxJQueryMet(120);
        uploadOrAttachExistingVideo(new VideoUploadModel(existingVideoName, true, false, false));
        waitAjaxJQueryMet(120);
        $(format(FLEXIBLE_CONTENT_VIDEO_UPLOADED_WEB_ELEMENT, position.name()
                                                                      .toLowerCase(), index)).should(appear);
        waitAjaxJQueryMet(300);
        return this;
    }

}