package drupal.ui.pages.add_content.merchant;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static drupal.ui.pages.add_content.merchant.MerchantPage.ADD_ANOTHER_ITEM_BUTTON;
import static drupal.ui.pages.add_content.merchant.MerchantPage.CONTENT_RELEASE_INPUT;
import static drupal.ui.pages.add_content.merchant.MerchantPage.TRACKING_PIXEL_URL_LIST;
import static java.time.Duration.ofSeconds;
import static mgs.qa.utils.LoopUtils.doWhileConditionNotMet;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Log4j2
@Data
@AllArgsConstructor
public class MerchantTrackingPixelModel extends MasterPage implements ISectionDataModel {

    String contentRelease;
    private List<String> trackingPixelUrl;

    public MerchantTrackingPixelModel() {
        trackingPixelUrl = List.of("https://www.military.com/?pixel=keyword");
        contentRelease = null;
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeTrackingPixel(expectedClass);
        typeContentRelease(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeTrackingPixel(Class<P> expectedClass) {

        if (!trackingPixelUrl.isEmpty()) {
            for (int i = 0; i < trackingPixelUrl.size(); i++) {
                if (i != 0) {
                    ADD_ANOTHER_ITEM_BUTTON.should(exist, appear, enabled)
                                           .scrollTo()
                                           .click();
                }
                doWhileConditionNotMet(
                        ADD_ANOTHER_ITEM_BUTTON::isEnabled,
                        () -> log.info("waiting ADD_ANOTHER_ITEM being enabled ")
                );
                TRACKING_PIXEL_URL_LIST.get(i)
                                       .should(appear, ofSeconds(30))
                                       .setValue(trackingPixelUrl.get(i));
            }
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeContentRelease(Class<P> expectedClass) {
        if (contentRelease != null) {
            CONTENT_RELEASE_INPUT.should(appear, exist, enabled)
                                 .setValue(contentRelease);
        }
        return returnInstanceOf(expectedClass);
    }

}
