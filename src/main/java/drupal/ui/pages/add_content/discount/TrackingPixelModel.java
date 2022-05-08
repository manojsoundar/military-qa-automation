package drupal.ui.pages.add_content.discount;

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
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.ADD_ANOTHER_ITEM_BUTTON;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.URL_INPUT;
import static java.time.Duration.ofSeconds;
import static mgs.qa.utils.LoopUtils.doWhileConditionNotMet;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@Log4j2
public class TrackingPixelModel extends MasterPage implements ISectionDataModel {

    private List<String> trackingPixelUrl;


    public TrackingPixelModel() {
        trackingPixelUrl = List.of("https://www.military.com/?pixel=click", "https://www.military.com/?pixel=impression");
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        addURL(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P addURL(Class<P> expectedClass) {

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
                URL_INPUT.get(i)
                         .should(appear, ofSeconds(30))
                         .setValue(trackingPixelUrl.get(i));
                log.info("URL" + trackingPixelUrl.get(i));

            }
        }

        return returnInstanceOf(expectedClass);
    }

}
