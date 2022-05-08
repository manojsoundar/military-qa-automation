package drupal.ui.pages.structure.eck_entity_types.call_to_action;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static drupal.ui.pages.structure.eck_entity_types.call_to_action.AddCallToActionNormalPage.LINK_INPUT;
import static drupal.ui.pages.structure.eck_entity_types.call_to_action.AddCallToActionNormalPage.TITLE_SIZE_DROPDOWN;
import static drupal.ui.pages.structure.eck_entity_types.call_to_action.AddCallToActionNormalPage.TRACKING_PIXEL_INPUT;
import static drupal.ui.pages.structure.eck_entity_types.call_to_action.AddCallToActionNormalPage.TitleSize;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class CTANormalModel extends MasterPage implements ISectionDataModel {

    TitleSize titleSize;
    String link;
    String trackingPixelUrl;

    public CTANormalModel() {

        titleSize = TitleSize.LARGE;
        link = "OAS Homepage (4026)";
        trackingPixelUrl = "https://www.military?pixel=normal-cta";
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        selectTitleSize(expectedClass);
        typeLink(expectedClass);
        typeTrackingPixelUrl(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectTitleSize(Class<P> expectedClass) {

        TITLE_SIZE_DROPDOWN.should(exist, appear, enabled)
                           .selectOptionContainingText(titleSize.getTitleSizeItem());
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeLink(Class<P> expectedClass) {

        if (link != null) {
            LINK_INPUT.should(exist, appear, enabled)
                      .setValue(link);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeTrackingPixelUrl(Class<P> expectedClass) {

        if (trackingPixelUrl != null) {
            TRACKING_PIXEL_INPUT.should(exist, appear, enabled)
                                .setValue(trackingPixelUrl);
        }
        return returnInstanceOf(expectedClass);
    }

}
