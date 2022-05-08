package drupal.ui.pages.add_content.flexible_content_space;

import com.codeborne.selenide.SelenideElement;
import drupal.enums.content.FlexibleContentSpacePosition;
import drupal.models.ISectionDataModel;
import drupal.models.ImageUploadModel;
import drupal.ui.components.AttachAnImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static drupal.enums.content.FlexibleContentSpaceType.ADD_SUCCESS_STORY;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@Getter
@AllArgsConstructor
public class FlexibleContentSpaceSuccessStoryModel extends MasterPage implements ISectionDataModel, IFlexibleContentSpace, AttachAnImage {

    private static final SelenideElement SUCCESS_STORY_TEXTAREA = $("body.cke_editable p");
    private static final String SUCCESS_STORY_FULL_NAME_INPUT = "input[id*=-%s-%s-subform-field-name]";
    private static final String SUCCESS_STORY_JOB_TITLE_INPUT = "input[id*=-%s-%s-subform-field-job-title]";
    private static final String SUCCESS_STORY_TEXT_FRAME = "[id*=-%s-%s-subform-field-full-text] iframe";
    private static final String SUCCESS_STORY_LINK_INPUT = "input[id*=-%s-%s-subform-field-link]";
    private static final String SUCCESS_STORY_ATTACH_AN_IMAGE_BUTTON = "input.button[id*=-%s-%s-subform-field-image-entity-browser]";

    private final FlexibleContentSpacePosition position;
    private final String fullName;
    private final String jobTitle;
    private final String successStoryText;
    private final String imageMediaName;
    private int index;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        index = selectFlexibleContentSpace(ADD_SUCCESS_STORY, position) - 1;
        enterSuccessStory().enterSuccessStoryText()
                           .addNewSlideshow();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private FlexibleContentSpaceSuccessStoryModel enterSuccessStory() {
        $(format(SUCCESS_STORY_FULL_NAME_INPUT, position.getFlexibleContentPosition(), index)).should(appear, enabled)
                                                                                              .setValue(fullName);
        $(format(SUCCESS_STORY_JOB_TITLE_INPUT, position.getFlexibleContentPosition(), index)).should(appear, enabled)
                                                                                              .setValue(jobTitle);
        $(format(SUCCESS_STORY_LINK_INPUT, position.getFlexibleContentPosition(), index)).should(appear, enabled)
                                                                                         .setValue(successStoryText);
        return this;
    }

    private FlexibleContentSpaceSuccessStoryModel enterSuccessStoryText() {
        if (successStoryText != null) {
            $(format(SUCCESS_STORY_TEXT_FRAME, position.getFlexibleContentPosition(), index))
                    .should(appear, visible);
            switchTo().frame($(format(SUCCESS_STORY_TEXT_FRAME, position.getFlexibleContentPosition(), index)));
            SUCCESS_STORY_TEXTAREA.should(appear, exist, visible)
                                  .click();
            SUCCESS_STORY_TEXTAREA.should(appear, visible, enabled)
                                  .sendKeys(successStoryText);
            switchTo().parentFrame();
        }
        return this;
    }

    private FlexibleContentSpaceSuccessStoryModel addNewSlideshow() {
        if (imageMediaName != null) {
            AJAX_PROGRESS_WEB_ELEMENT.should(disappear, ofSeconds(30));
            $(format(SUCCESS_STORY_ATTACH_AN_IMAGE_BUTTON, position.getFlexibleContentPosition(), index)).should(appear, enabled)
                                                                                                         .click();
            waitAjaxJQueryMet(90);
            uploadOrAttachExistingImage(new ImageUploadModel(imageMediaName, null, null, true, false, null));
            waitAjaxJQueryMet(60);
        }
        return this;
    }

}


