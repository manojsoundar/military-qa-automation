package drupal.ui.pages.components.snippet;

import drupal.models.ISectionDataModel;
import drupal.models.ImageUploadModel;
import drupal.ui.components.AttachAnImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static drupal.ui.pages.components.snippet.SnippetBasePage.ATTACH_AN_IMAGE_BUTTON;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Data
public class SnippetImageModel extends MasterPage implements ISectionDataModel, AttachAnImage {

    ImageUploadModel imageData;

    public static SnippetImageModel getSnippetImageData() {
        return new SnippetImageModel(new ImageUploadModel("Test Image", null, null, true, false, null));
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
            waitAjaxJQueryMet(300);
        }

        return returnInstanceOf(expectedClass);
    }

}
