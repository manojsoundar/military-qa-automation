package drupal.ui.pages.structure.eck_entity_types.call_to_action;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static drupal.ui.pages.structure.eck_entity_types.call_to_action.AddCallToActionBasePage.BUTTON_LABEL_INPUT;
import static drupal.ui.pages.structure.eck_entity_types.call_to_action.AddCallToActionNormalPage.BUTTON_COLOR_OVERRIDE_INPUT;
import static drupal.ui.pages.structure.eck_entity_types.call_to_action.AddCallToActionNormalPage.BUTTON_TEXT_COLOR_OVERRIDE_INPUT;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CTAButtonModel extends MasterPage implements ISectionDataModel {

    String buttonLabel;
    String buttonColorOverride;
    String buttonTextColorOverride;

    public CTAButtonModel() {
        buttonLabel = "Learn More Today!";
        buttonColorOverride = null;
        buttonTextColorOverride = null;
    }

    public static CTAButtonModel getLeadFormButtonData() {
        return new CTAButtonModel("Get Free Quotes", null, null);
    }

    public static CTAButtonModel getNLSignupButtonData() {
        return new CTAButtonModel("Subscribe Now!", null, null);
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeButtonLabel(expectedClass);
        typeButtonColorOverride(expectedClass);
        typeTextColorOverride(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeButtonLabel(Class<P> expectedClass) {

        if (buttonLabel != null) {
            BUTTON_LABEL_INPUT.should(exist, appear, enabled)
                              .setValue(buttonLabel);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeButtonColorOverride(Class<P> expectedClass) {

        if (buttonColorOverride != null) {
            BUTTON_COLOR_OVERRIDE_INPUT.should(exist, appear, enabled)
                                       .setValue(buttonColorOverride);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeTextColorOverride(Class<P> expectedClass) {

        if (buttonTextColorOverride != null) {
            BUTTON_TEXT_COLOR_OVERRIDE_INPUT.should(exist, appear, enabled)
                                            .setValue(buttonTextColorOverride);
        }
        return returnInstanceOf(expectedClass);
    }

}