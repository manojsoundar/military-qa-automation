package drupal.ui.pages.structure.taxonomy.keywords;

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
import static drupal.ui.pages.structure.taxonomy.keywords.KeywordsAddTermPage.ADD_ANOTHER_ITEM_BUTTON;
import static drupal.ui.pages.structure.taxonomy.keywords.KeywordsAddTermPage.TRACKING_PIXEL_URL_LIST;
import static java.time.Duration.ofSeconds;
import static mgs.qa.utils.LoopUtils.doWhileConditionNotMet;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Log4j2
@Data
@AllArgsConstructor
public class TrackingPixelModel extends MasterPage implements ISectionDataModel {

    private List<String> trackingPixelUrl;

    public TrackingPixelModel() {
        trackingPixelUrl = List.of("https://www.military.com/?pixel=keyword");
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeTrackingPixel(trackingPixelUrl, expectedClass);
        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeTrackingPixel(List<String> url, Class<P> expectedClass) {

        if (!url.isEmpty()) {
            for (int i = 0; i < url.size(); i++) {
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
                                       .setValue(url.get(i));
            }
        }
        return returnInstanceOf(expectedClass);
    }

}