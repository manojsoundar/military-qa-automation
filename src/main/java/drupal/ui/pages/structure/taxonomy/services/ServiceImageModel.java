package drupal.ui.pages.structure.taxonomy.services;

import drupal.models.ISectionDataModel;
import drupal.models.ImageUploadModel;
import drupal.ui.components.AttachAnImage;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static drupal.ui.pages.structure.taxonomy.services.AddServicePage.ATTACH_AN_IMAGE_BUTTON;
import static drupal.ui.pages.structure.taxonomy.services.AddServicePage.SERVICE_IMAGE_LINK;
import static java.time.Duration.ofSeconds;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@SuppressWarnings("CanBeFinal")
public class ServiceImageModel extends MasterPage implements ISectionDataModel, AttachAnImage {

    ImageUploadModel serviceImage;

    ServiceImageModel() {
        serviceImage = new ImageUploadModel("mil defense advisory committee women services 1800", null, null, true, false, "Login Gate BG Image");
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
        if (serviceImage != null) {
            SERVICE_IMAGE_LINK.should(exist, appear, enabled)
                              .click();
            ATTACH_AN_IMAGE_BUTTON.should(appear, ofSeconds(30))
                                  .should(enabled)
                                  .click();
            waitAjaxJQueryMet(300);
            uploadOrAttachExistingImage(serviceImage);
        }
        return returnInstanceOf(expectedClass);
    }

}
