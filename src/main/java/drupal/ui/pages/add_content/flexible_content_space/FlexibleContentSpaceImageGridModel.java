package drupal.ui.pages.add_content.flexible_content_space;

import drupal.enums.content.FlexibleContentSpacePosition;
import drupal.models.ISectionDataModel;
import drupal.models.ImageUploadModel;
import drupal.ui.components.AttachAnImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static drupal.enums.content.FlexibleContentSpaceType.ADD_IMAGE_GRID;
import static java.lang.String.format;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@Getter
@AllArgsConstructor
public class FlexibleContentSpaceImageGridModel extends MasterPage implements ISectionDataModel, IFlexibleContentSpace, AttachAnImage {

    private static final String FLEXIBLE_CONTENT_SPACE_IMAGE_GRID_TITLE_INPUT = ".form-text[id*=-%s-%s-subform-field-title-0]";
    private static final String FLEXIBLE_CONTENT_SPACE_IMAGE_GRID_IMAGES_TITLE_INPUT = ".form-text[id*=-%s-%s-subform-field-linked-images][id*=title]";
    private static final String FLEXIBLE_CONTENT_SPACE_IMAGE_GRID_IMAGES_LINK_INPUT = ".form-text[id*=-%s-%s-subform-field-linked-images][id*=uri]";
    private static final String FLEXIBLE_CONTENT_SPACE_IMAGE_ATTACH_AN_IMAGE_BUTTON = ".button[id*=-%s-%s-subform-field-linked-images][id*=browser-open]";

    private final FlexibleContentSpacePosition position;
    private final String imagesTitle;
    private final String imagesLink;
    private final String imagesMediaName;
    private int index;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        index = selectFlexibleContentSpace(ADD_IMAGE_GRID, position) - 1;
        imageGridTitle().imagesTitle()
                        .imagesLink();
        imagesAttachAnImage();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private FlexibleContentSpaceImageGridModel imageGridTitle() {
        $(format(FLEXIBLE_CONTENT_SPACE_IMAGE_GRID_TITLE_INPUT, position.getFlexibleContentPosition(), index)).should(appear, enabled)
                                                                                                              .setValue(imagesTitle);
        return this;
    }

    private FlexibleContentSpaceImageGridModel imagesTitle() {
        $(format(FLEXIBLE_CONTENT_SPACE_IMAGE_GRID_IMAGES_TITLE_INPUT, position.getFlexibleContentPosition(), index)).should(appear, enabled)
                                                                                                                     .setValue(imagesTitle);
        return this;
    }

    private FlexibleContentSpaceImageGridModel imagesLink() {
        if (imagesLink != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_IMAGE_GRID_IMAGES_LINK_INPUT, position.getFlexibleContentPosition(), index)).should(visible, appear)
                                                                                                                        .setValue(imagesLink);
        }
        return this;
    }

    private FlexibleContentSpaceImageGridModel imagesAttachAnImage() {
        if (imagesMediaName != null) {
            waitAjaxJQueryMet(90);
            $(format(FLEXIBLE_CONTENT_SPACE_IMAGE_ATTACH_AN_IMAGE_BUTTON, position.getFlexibleContentPosition(), index)).should(appear, enabled)
                                                                                                                        .click();
            uploadOrAttachExistingImage(new ImageUploadModel(imagesMediaName, null, null, true, false, null));
            waitAjaxJQueryMet(60);
        }
        return this;
    }

}


