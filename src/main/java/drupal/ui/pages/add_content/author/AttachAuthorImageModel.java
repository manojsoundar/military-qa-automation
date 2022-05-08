package drupal.ui.pages.add_content.author;

import drupal.models.ISectionDataModel;
import drupal.models.ImageUploadModel;
import drupal.ui.components.AttachAnImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static drupal.ui.pages.add_content.author.CreateAuthorPage.ATTACH_AN_IMAGE_BUTTON;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@Getter
@AllArgsConstructor
public class AttachAuthorImageModel extends MasterPage implements ISectionDataModel, AttachAnImage {

    private final String imageMediaName;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        uploadThumbNailImage();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private AttachAuthorImageModel uploadThumbNailImage() {

        ATTACH_AN_IMAGE_BUTTON.should(appear, visible, enabled)
                              .click();
        waitAjaxJQueryMet(120);
        uploadOrAttachExistingImage(new ImageUploadModel(imageMediaName, "", "", true, false, null));

        return this;
    }


}
