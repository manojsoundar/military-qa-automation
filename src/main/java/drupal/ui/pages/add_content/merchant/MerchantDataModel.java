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
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;
import static drupal.ui.pages.add_content.merchant.MerchantPage.BLURB_TEXTAREA;
import static drupal.ui.pages.add_content.merchant.MerchantPage.BODY_FRAME;
import static drupal.ui.pages.add_content.merchant.MerchantPage.BODY_TEXTAREA;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Log4j2
@Data
@AllArgsConstructor
public class MerchantDataModel extends MasterPage implements ISectionDataModel {

    String blurbText;
    String bodyText;

    public MerchantDataModel() {
        blurbText = "Test Blurb Text";
        bodyText = "Text Body Text";
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeBlurbText(expectedClass);
        typeBodyText(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeBlurbText(Class<P> expectedClass) {
        if (blurbText != null) {
            BLURB_TEXTAREA.should(appear, exist, enabled)
                          .scrollTo()
                          .setValue(blurbText);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeBodyText(Class<P> expectedClass) {
        if (bodyText != null) {
            switchTo().frame(BODY_FRAME);
            BODY_TEXTAREA.should(appear, exist, visible)
                         .click();
            BODY_TEXTAREA.should(appear, visible, enabled)
                         .sendKeys(bodyText);
            switchTo().parentFrame();
        }
        return returnInstanceOf(expectedClass);
    }

}
