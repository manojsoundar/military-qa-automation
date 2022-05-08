package drupal.ui.pages.add_content.webinar;

import drupal.models.ISectionDataModel;
import drupal.models.ImageUploadModel;
import drupal.ui.components.AttachAnImage;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static drupal.ui.pages.add_content.webinar.WebinarPage.ATTACH_AN_IMAGE_BUTTON;
import static java.time.Duration.ofSeconds;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

public class WebinarImageModel extends MasterPage implements ISectionDataModel, AttachAnImage {

    ImageUploadModel webinarImage;

    public WebinarImageModel() {
        webinarImage = new ImageUploadModel("Adobe stock", null, null, true, false, null);
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

        if (webinarImage != null) {
            ATTACH_AN_IMAGE_BUTTON.should(appear, ofSeconds(30))
                                  .scrollIntoView(false)
                                  .should(enabled)
                                  .click();
            waitAjaxJQueryMet(120);
            uploadOrAttachExistingImage(webinarImage);
        }
        return returnInstanceOf(expectedClass);
    }
}
