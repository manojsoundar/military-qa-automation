package drupal.ui.pages.structure.eck_entity_types.call_to_action;

import drupal.models.ISectionDataModel;
import drupal.models.ImageUploadModel;
import drupal.ui.components.AttachAnImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static drupal.ui.pages.structure.eck_entity_types.call_to_action.AddCallToActionBasePage.ATTACH_BACKGROUND_IMAGE_BUTTON;
import static drupal.ui.pages.structure.eck_entity_types.call_to_action.AddCallToActionBasePage.ATTACH_IMAGE_VARIANT_BUTTON;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class CTAImageModel extends MasterPage implements ISectionDataModel, AttachAnImage {

    ImageUploadModel backgroundImageData;
    ImageUploadModel imageVariantData;

    public CTAImageModel() {
        backgroundImageData = new ImageUploadModel("Lead Form CTA", null, null, true, false, "Lead Form CTA BG");
        imageVariantData = new ImageUploadModel("Lead Form CTA", null, null, true, false, "Lead Form CTA Variant");
    }

    public static CTAImageModel getNormalCTAImage() {

        return new CTAImageModel(new ImageUploadModel("Desktop CTA", null, null, true, false, null), null);
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        attachBackgroundImage(expectedClass);
        attachImageVariant(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P attachBackgroundImage(Class<P> expectedClass) {

        if (backgroundImageData != null) {
            ATTACH_BACKGROUND_IMAGE_BUTTON.should(enabled, appear)
                                          .click();
            waitAjaxJQueryMet(120);
            uploadOrAttachExistingImage(backgroundImageData);
        }

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P attachImageVariant(Class<P> expectedClass) {

        if (imageVariantData != null) {
            ATTACH_IMAGE_VARIANT_BUTTON.should(enabled, appear)
                                       .click();
            waitAjaxJQueryMet(120);
            uploadOrAttachExistingImage(imageVariantData);
        }

        return returnInstanceOf(expectedClass);
    }

}