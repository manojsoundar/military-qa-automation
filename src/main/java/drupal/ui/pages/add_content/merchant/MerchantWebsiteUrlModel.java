package drupal.ui.pages.add_content.merchant;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static drupal.ui.pages.add_content.merchant.MerchantPage.MERCHANT_WEBSITE_LINK_TEXT_INPUT;
import static drupal.ui.pages.add_content.merchant.MerchantPage.MERCHANT_WEBSITE_URL_INPUT;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Log4j2
@Data
@AllArgsConstructor
public class MerchantWebsiteUrlModel extends MasterPage implements ISectionDataModel {

    String url;
    String linkText;

    public MerchantWebsiteUrlModel() {
        url = "https://www.applebees.com";
        linkText = "Test Merchant Website";
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeUrl(expectedClass);
        typeLinkText(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeUrl(Class<P> expectedClass) {

        if (url != null) {
            MERCHANT_WEBSITE_URL_INPUT.should(appear, exist, enabled)
                                      .setValue(url);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeLinkText(Class<P> expectedClass) {

        if (linkText != null) {
            MERCHANT_WEBSITE_LINK_TEXT_INPUT.should(appear, exist, enabled)
                                            .setValue(linkText);
        }
        return returnInstanceOf(expectedClass);
    }

}
