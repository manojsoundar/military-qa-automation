package drupal.ui.pages.structure.eck_entity_types.call_to_action;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static drupal.ui.pages.structure.eck_entity_types.call_to_action.AddCallToActionNLSignupPage.DISPLAY_FIELDS_DROPDOWN;
import static drupal.ui.pages.structure.eck_entity_types.call_to_action.AddCallToActionNLSignupPage.ISRC_INPUT;

@AllArgsConstructor
@Getter
public class NLSignupFormModel extends MasterPage implements ISectionDataModel {


    AddCallToActionNLSignupPage.DisplayFieldType fieldType;
    String isrc;
    List<AddCallToActionNLSignupPage.TargetNewsletter> targetNewsletters;


    public NLSignupFormModel() {
        fieldType = AddCallToActionNLSignupPage.DisplayFieldType.EMAIL_FIELD_ONLY;
        isrc = "GIBILLCALCULATOR";
        targetNewsletters = List.of(AddCallToActionNLSignupPage.TargetNewsletter.AIR_FORCE_INSIDER, AddCallToActionNLSignupPage.TargetNewsletter.FINANCE_MR);

    }

    public static NLSignupFormModel fillMultipleFieldsData() {
        return new NLSignupFormModel(AddCallToActionNLSignupPage.DisplayFieldType.MULTIPLE_FIELDS, "GIBILLCALCULATOR", List.of(AddCallToActionNLSignupPage.TargetNewsletter.AIR_FORCE_INSIDER, AddCallToActionNLSignupPage.TargetNewsletter.FINANCE_MR));
    }

    private <P extends MasterPage> P selectDisplayFieldType(Class<P> expectedClass) {
        if (fieldType != null) {
            DISPLAY_FIELDS_DROPDOWN.should(exist, appear, enabled)
                                   .selectOptionContainingText(fieldType.getFieldsTypeItem());
        }
        return returnInstanceOf(expectedClass);
    }

    private AddCallToActionNLSignupPage fillISRC() {
        ISRC_INPUT.should(enabled, visible)
                  .setValue(this.isrc);
        return new AddCallToActionNLSignupPage();
    }

    private AddCallToActionNLSignupPage fillTargetNewsletters() {

        for (AddCallToActionNLSignupPage.TargetNewsletter newsLetter : this.targetNewsletters) {
            newsLetter.getTargetNewsLetterItem()
                      .should(enabled, visible)
                      .click();
        }
        return new AddCallToActionNLSignupPage();
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        selectDisplayFieldType(expectedClass);
        fillISRC();
        fillTargetNewsletters();
        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

}

