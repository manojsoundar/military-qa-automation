package drupal.ui.pages.structure.eck_entity_types.call_to_action;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.ui.pages.structure.eck_entity_types.call_to_action.AddCallToActionBasePage.BLURB_TEXTAREA;
import static drupal.ui.pages.structure.eck_entity_types.call_to_action.AddCallToActionBasePage.INTERNAL_TITLE_INPUT;
import static drupal.ui.pages.structure.eck_entity_types.call_to_action.AddCallToActionBasePage.TITLE_INPUT;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CTABaseModel extends MasterPage implements ISectionDataModel {

    String internalTitle;
    String title;
    String blurb;

    public CTABaseModel() {

        internalTitle = "Normal CTA QA Automation " + timeStampFormat(PATTERN);
        title = "QA Automation Normal CTA Callout " + timeStampFormat(PATTERN);
        blurb = "Throughout the month, military families are honored and recognized for their commitment and contributions in support of our military and nation.";

    }

    public static CTABaseModel getLeadFormBaseData() {
        return new CTABaseModel("Leads CTA QA Automation " + timeStampFormat(PATTERN), "Leads CTA Callout " + timeStampFormat(PATTERN), "Compare & Save with Free, Fast Quotes");
    }

    public static CTABaseModel getNLSignupData() {
        return new CTABaseModel("Newsletter CTA QA Automation" + timeStampFormat(PATTERN), "CTA Email Callout " + timeStampFormat(PATTERN), "Get the scoop on military employers and the latest award-winning content. Right in your inbox.");
    }

    public static CTABaseModel getNLSignupMultipleFieldsData() {
        return new CTABaseModel("Newsletter CTA QA Automation " + timeStampFormat(PATTERN), "CTA Multiple Callout " + timeStampFormat(PATTERN), "Get the scoop on military employers and the latest award-winning content. Right in your inbox.");
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeInternalTitle(expectedClass);
        typeTitle(expectedClass);
        typeBlurb(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeInternalTitle(Class<P> expectedClass) {

        if (internalTitle != null) {
            INTERNAL_TITLE_INPUT.should(exist, appear, enabled)
                                .setValue(internalTitle);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeTitle(Class<P> expectedClass) {

        if (title != null) {
            TITLE_INPUT.should(exist, appear, enabled)
                       .setValue(title);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeBlurb(Class<P> expectedClass) {

        if (blurb != null) {
            BLURB_TEXTAREA.should(exist, appear, enabled)
                          .setValue(blurb);
        }
        return returnInstanceOf(expectedClass);
    }

}