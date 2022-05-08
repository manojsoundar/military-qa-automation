package drupal.ui.pages.add_content.discount;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.DISCLAIMER_BLURB_TEXTAREA;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.DISCLAIMER_BUTTON;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.DISCLAIMER_HEADING_INPUT;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.HIDE_DISCLAIMER_CHECKBOX;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class DisclaimerModel extends MasterPage implements ISectionDataModel {

    private boolean hideDisclaimer;
    private String disclaimerHeading;
    private String disclaimerBlurb;

    public DisclaimerModel() {
        hideDisclaimer = false;
        disclaimerHeading = "Test Disclaimer";
        disclaimerBlurb = "This Test Disclaimer supersedes the standard disclaimer notice found on Discounts pages";
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        clickCustomizeDisclaimer(expectedClass);
        if (!hideDisclaimer) {
            typeDisclaimerHeading(expectedClass);
            typeDisclaimerBlurb(expectedClass);
        } else {
            clickHideDisclaimer(expectedClass);
        }

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P clickCustomizeDisclaimer(Class<P> expectedClass) {

        DISCLAIMER_BUTTON.should(appear, enabled)
                         .click();
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeDisclaimerHeading(Class<P> expectedClass) {

        if (disclaimerHeading != null) {
            DISCLAIMER_HEADING_INPUT.should(exist, appear, enabled)
                                    .setValue(disclaimerHeading);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeDisclaimerBlurb(Class<P> expectedClass) {

        if (disclaimerBlurb != null) {
            DISCLAIMER_BLURB_TEXTAREA.should(exist, appear, enabled)
                                     .setValue(disclaimerBlurb);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P clickHideDisclaimer(Class<P> expectedClass) {

        HIDE_DISCLAIMER_CHECKBOX.should(exist, appear, enabled)
                                .click();
        return returnInstanceOf(expectedClass);
    }

}
