package drupal.ui.pages.add_content.employer;

import drupal.models.ISectionDataModel;
import drupal.models.ImageUploadModel;
import drupal.ui.components.AttachAnImage;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static drupal.ui.pages.add_content.employer.CreateEmployerPage.LOGO_ATTACH_AN_IMAGE_BUTTON;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@SuppressWarnings("CanBeFinal")
public class EmployerLogoImageModel extends MasterPage implements ISectionDataModel, AttachAnImage {

    ImageUploadModel logoImage;

    public EmployerLogoImageModel() {
        logoImage = new ImageUploadModel("VTP logo", "", "", true, false, null);
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        attachLogoImage(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P attachLogoImage(Class<P> expectedClass) {
        if (logoImage != null) {
            LOGO_ATTACH_AN_IMAGE_BUTTON.should(appear, enabled)
                                       .scrollIntoView(false)
                                       .click();
            waitAjaxJQueryMet(120);
            uploadOrAttachExistingImage(logoImage);
        }
        return returnInstanceOf(expectedClass);
    }

}
