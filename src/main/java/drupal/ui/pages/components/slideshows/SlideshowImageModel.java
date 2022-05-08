package drupal.ui.pages.components.slideshows;


import drupal.models.ISectionDataModel;
import drupal.models.ImageUploadModel;
import drupal.ui.components.AttachAnImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static drupal.ui.pages.components.slideshows.AddSlideshowContentPage.ATTACH_AN_IMAGE_BUTTON;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Data
public class SlideshowImageModel extends MasterPage implements ISectionDataModel, AttachAnImage {

    ImageUploadModel imageData;

    public static SlideshowImageModel getSlideshowImageData() {
        return new SlideshowImageModel(new ImageUploadModel("Test Image", "", "", true, true, null));
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        attachImage(expectedClass);
        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P attachImage(Class<P> expectedClass) {

        if (imageData != null) {
            ATTACH_AN_IMAGE_BUTTON.should(enabled, appear)
                                  .click();
            uploadOrAttachExistingImage(imageData);
        }
        return returnInstanceOf(expectedClass);
    }

}
