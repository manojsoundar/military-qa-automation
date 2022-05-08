package drupal.ui.pages.structure.taxonomy.categories;

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
import static drupal.ui.pages.structure.taxonomy.categories.CategoriesAddTermPage.ADD_ANOTHER_ITEM_BUTTON;
import static drupal.ui.pages.structure.taxonomy.categories.CategoriesAddTermPage.TRACKING_PIXEL_URL_LIST;
import static java.time.Duration.ofSeconds;
import static mgs.qa.utils.LoopUtils.doWhileConditionNotMet;

@SuppressWarnings("ALL")
@Log4j2
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CategoriesTrackingPixelModel extends MasterPage implements ISectionDataModel {

    private List<String> trackingPixelUrl;

    public CategoriesTrackingPixelModel() {
        trackingPixelUrl = List.of("https://www.miitary.com?test-equipment-category-pixel", "https://www.miitary.com?test-equipment-category-in-qa-pixel");
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeTrackingPixel(expectedClass);
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

}
