package drupal.ui.pages.add_content.discount;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.REDEEM_ONLINE_INPUT;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.REDEEM_ONLINE_LABEL_INPUT;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class RedeemOnlineModel extends MasterPage implements ISectionDataModel {

    private String redeemOnlineURL;
    private String redeemOnlineLabelText;

    public RedeemOnlineModel() {
        redeemOnlineURL = "https://www.apple.com";
        redeemOnlineLabelText = "REDEEM TODAY!";
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeRedeemOnlineUrl(expectedClass);
        typeRedeemOnlineLabelText(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeRedeemOnlineUrl(Class<P> expectedClass) {

        if (redeemOnlineURL != null) {
            REDEEM_ONLINE_INPUT.should(exist, appear, enabled)
                               .setValue(redeemOnlineURL);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeRedeemOnlineLabelText(Class<P> expectedClass) {

        if (redeemOnlineLabelText != null) {
            REDEEM_ONLINE_LABEL_INPUT.should(exist, appear, enabled)
                                     .setValue(redeemOnlineLabelText);
        }
        return returnInstanceOf(expectedClass);
    }

}
