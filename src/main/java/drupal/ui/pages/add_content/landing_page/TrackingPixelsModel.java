package drupal.ui.pages.add_content.landing_page;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static drupal.ui.pages.add_content.landing_page.AddLandingPage.ADD_ANOTHER_ITEM_BUTTON;
import static drupal.ui.pages.add_content.landing_page.AddLandingPage.MESSAGE_WEB_ELEMENT;
import static drupal.ui.pages.add_content.landing_page.AddLandingPage.TRACKING_PIXELS_INPUT;

@Getter
@AllArgsConstructor
public class TrackingPixelsModel extends MasterPage implements ISectionDataModel {

    private final List<String> url;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        enterTrackingPixels();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private TrackingPixelsModel enterTrackingPixels() {
        if (url != null) {
            for (String currentUrl : url) {
                TRACKING_PIXELS_INPUT.should(appear, exist)
                                     .setValue(currentUrl);
                if ((url.get(url.size() - 1)
                        .equals(currentUrl))) {
                    ADD_ANOTHER_ITEM_BUTTON.should(appear, enabled)
                                           .click();
                    MESSAGE_WEB_ELEMENT.should(exist, appear)
                                       .should(disappear);
                }
            }
        }
        return this;
    }


}
