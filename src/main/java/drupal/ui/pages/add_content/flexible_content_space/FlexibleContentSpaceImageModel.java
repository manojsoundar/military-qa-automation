package drupal.ui.pages.add_content.flexible_content_space;

import drupal.enums.content.FlexibleContentSpacePosition;
import drupal.models.ISectionDataModel;
import drupal.models.ImageUploadModel;
import drupal.ui.components.AttachAnImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static drupal.enums.content.FlexibleContentSpaceType.ADD_IMAGE;
import static java.lang.String.format;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@Log4j2
public class FlexibleContentSpaceImageModel extends MasterPage implements ISectionDataModel, IFlexibleContentSpace, AttachAnImage {

    private static final String FLEXIBLE_CONTENT_SPACE_IMAGE_TITLE_INPUT = "input[id*='-%s-%s-subform-field-title-0-value']";
    private static final String FLEXIBLE_CONTENT_SPACE_IMAGE_LINK_INPUT = "input[id*='-%s-%s-subform-field-link-0-uri']";
    private static final String FLEXIBLE_CONTENT_SPACE_IMAGE_ATTACH_AN_IMAGE_BUTTON = "input[id*='-%s-%s-subform-field-image-entity-browser-entity-browser-open']";

    private final FlexibleContentSpacePosition position;
    private int index;
    private final String imagesTitle;
    private final String imagesLink;
    private final String imagesMediaName;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        index = selectFlexibleContentSpace(ADD_IMAGE, position) - 1;
        imagesTitle();
        imagesLink();
        imagesAttachAnImage();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();
        clickEditFlexibleContentSpace(position, index);
        imagesTitle();
        return returnInstanceOf(expectedClass);
    }

    private FlexibleContentSpaceImageModel imagesTitle() {
        if (position != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_IMAGE_TITLE_INPUT, position.getFlexibleContentPosition(), index)).should(exist, appear, enabled)
                                                                                                             .setValue(imagesTitle);
            log.info("Image title inserted ");
        }
        return this;
    }

    private FlexibleContentSpaceImageModel imagesLink() {
        if (imagesLink != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_IMAGE_LINK_INPUT, position.getFlexibleContentPosition(), index)).should(visible, appear)
                                                                                                            .setValue(imagesLink);
            log.info("Image link inserted ");

        }
        return this;
    }

    private FlexibleContentSpaceImageModel imagesAttachAnImage() {
        if (imagesMediaName != null) {
            waitAjaxJQueryMet(90);
            $(format(FLEXIBLE_CONTENT_SPACE_IMAGE_ATTACH_AN_IMAGE_BUTTON, position.getFlexibleContentPosition(), index)).should(appear, enabled)
                                                                                                                        .click();
            uploadOrAttachExistingImage(new ImageUploadModel(imagesMediaName, null, null, true, false, null));
            waitAjaxJQueryMet(60);
            log.info("Image inserted ");

        }
        return this;
    }

}


