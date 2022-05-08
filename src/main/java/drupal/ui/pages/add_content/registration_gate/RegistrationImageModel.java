package drupal.ui.pages.add_content.registration_gate;

import drupal.models.ISectionDataModel;
import drupal.models.ImageUploadModel;
import drupal.ui.components.AttachAnImage;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static drupal.ui.pages.add_content.registration_gate.CreateRegistrationGatePage.ATTACH_AN_IMAGE_BUTTON;
import static java.time.Duration.ofSeconds;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@SuppressWarnings("CanBeFinal")
public class RegistrationImageModel extends MasterPage implements ISectionDataModel, AttachAnImage {

    ImageUploadModel registrationImage;

    public RegistrationImageModel() {
        registrationImage = new ImageUploadModel("Login Gate BG Image", null, null, true, false, "Login Gate BG Image");

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
        if (registrationImage != null) {
            ATTACH_AN_IMAGE_BUTTON.should(appear, ofSeconds(30))
                                  .scrollIntoView(false)
                                  .should(enabled)
                                  .click();
            waitAjaxJQueryMet(120);
            uploadOrAttachExistingImage(registrationImage);
        }
        return returnInstanceOf(expectedClass);
    }

}
