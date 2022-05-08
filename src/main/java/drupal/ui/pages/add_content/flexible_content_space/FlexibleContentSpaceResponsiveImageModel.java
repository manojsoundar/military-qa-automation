package drupal.ui.pages.add_content.flexible_content_space;

import drupal.enums.content.FlexibleContentSpacePosition;
import drupal.models.ISectionDataModel;
import drupal.models.ImageUploadModel;
import drupal.ui.components.AttachAnImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static drupal.enums.content.FlexibleContentSpaceType.ADD_RESPONSIVE_IMAGE;
import static java.lang.String.format;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

// TODO check usage callSuper=false
@Data
@AllArgsConstructor
public class FlexibleContentSpaceResponsiveImageModel extends MasterPage implements ISectionDataModel, AttachAnImage, IFlexibleContentSpace {

    private static final String FLEXIBLE_CONTENT_SPACE_RESPONSIVE_PRIMARY_ATTACH_AN_IMAGE_BUTTON = ".button[id*=-%s-%s-subform-field-image-entity-browser]";
    private static final String FLEXIBLE_CONTENT_SPACE_MOBILE_IMAGE_ATTACH_AN_IMAGE_BUTTON = ".button[id*=-%s-%s-subform-field-mobile-image-entity]";
    private static final String FLEXIBLE_CONTENT_SPACE_TABLET_IMAGE_ATTACH_AN_IMAGE_BUTTON = ".button[id*=-%s-%s-subform-field-tablet-image-entity]";

    private final FlexibleContentSpacePosition position;
    private int index;
    private String primaryImageMediaName;
    private String mobileImageMediaName;
    private String tabletImageMediaName;


    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        index = selectFlexibleContentSpace(ADD_RESPONSIVE_IMAGE, position) - 1;
        attachPrimaryImage().attachMobileImage()
                            .attachTabletImage();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();
        clickEditFlexibleContentSpace(position, index);
        return returnInstanceOf(expectedClass);
    }

    private FlexibleContentSpaceResponsiveImageModel attachPrimaryImage() {
        if (primaryImageMediaName != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_RESPONSIVE_PRIMARY_ATTACH_AN_IMAGE_BUTTON, position.getFlexibleContentPosition(), index)).should(appear, enabled)
                                                                                                                                     .click();
            waitAjaxJQueryMet(90);
            uploadOrAttachExistingImage(new ImageUploadModel(primaryImageMediaName, null, null, true, false, null));
            waitAjaxJQueryMet(60);
        }
        return this;
    }

    private FlexibleContentSpaceResponsiveImageModel attachMobileImage() {
        if (mobileImageMediaName != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_MOBILE_IMAGE_ATTACH_AN_IMAGE_BUTTON, position.getFlexibleContentPosition(), index)).should(appear, enabled)
                                                                                                                               .click();
            waitAjaxJQueryMet(90);
            uploadOrAttachExistingImage(new ImageUploadModel(mobileImageMediaName, null, null, true, false, null));
            waitAjaxJQueryMet(60);
        }
        return this;
    }

    private FlexibleContentSpaceResponsiveImageModel attachTabletImage() {
        if (tabletImageMediaName != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_TABLET_IMAGE_ATTACH_AN_IMAGE_BUTTON, position.getFlexibleContentPosition(), index)).should(appear, enabled)
                                                                                                                               .click();
            waitAjaxJQueryMet(90);
            uploadOrAttachExistingImage(new ImageUploadModel(tabletImageMediaName, null, null, true, false, null));
            waitAjaxJQueryMet(60);
        }
        return this;
    }

}


