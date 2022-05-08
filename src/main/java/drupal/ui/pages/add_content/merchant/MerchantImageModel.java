package drupal.ui.pages.add_content.merchant;

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
import static drupal.ui.pages.add_content.merchant.MerchantPage.ATTACH_AN_IMAGE_BUTTON;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Log4j2
@Data
@AllArgsConstructor
public class MerchantImageModel extends MasterPage implements ISectionDataModel, AttachAnImage {

    ImageUploadModel merchantImage;

    public MerchantImageModel() {
        merchantImage = new ImageUploadModel("Test Image", null, null, true, false, null);
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
        if (merchantImage != null) {
            ATTACH_AN_IMAGE_BUTTON.should(exist, appear, enabled)
                                  .scrollIntoView("{behavior: 'instant', block: 'center', inline: 'center'}")
                                  .click();
            waitAjaxJQueryMet(120);
            uploadOrAttachExistingImage(merchantImage);
        }
        return returnInstanceOf(expectedClass);
    }

}
