package drupal.ui.pages.add_content.discount;

import drupal.models.ISectionDataModel;
import drupal.models.ImageUploadModel;
import drupal.ui.components.AttachAnImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.ATTACH_AN_IMAGE_BUTTON;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class PrintCouponImageModel extends MasterPage implements ISectionDataModel, AttachAnImage {

    ImageUploadModel printCouponImage;

    public PrintCouponImageModel() {
        printCouponImage = new ImageUploadModel("coupon", "", "", true, false, null);
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        attachPrintCouponImage(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P attachPrintCouponImage(Class<P> expectedClass) {

        if (printCouponImage != null) {
            ATTACH_AN_IMAGE_BUTTON.should(enabled, appear)
                                  .click();
            waitAjaxJQueryMet(120);
            uploadOrAttachExistingImage(printCouponImage);
        }

        return returnInstanceOf(expectedClass);
    }

}
