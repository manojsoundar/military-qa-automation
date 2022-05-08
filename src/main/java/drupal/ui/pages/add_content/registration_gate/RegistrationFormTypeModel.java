package drupal.ui.pages.add_content.registration_gate;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static drupal.ui.pages.add_content.registration_gate.CreateRegistrationGatePage.REGISTRATION_FORM_DROPDOWN;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Log4j2
@Data
@AllArgsConstructor
public class RegistrationFormTypeModel extends MasterPage implements ISectionDataModel {

    String registrationFormItems;

    public RegistrationFormTypeModel() {
        registrationFormItems = RegistrationFormItem.MAIN_LOGIN.getRegistrationTypeItem();
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        selectRegistrationFormType(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectRegistrationFormType(Class<P> expectedClass) {
        if (registrationFormItems != null) {
            REGISTRATION_FORM_DROPDOWN.should(exist, appear, enabled)
                                      .selectOptionContainingText(registrationFormItems);
        }
        return returnInstanceOf(expectedClass);
    }

    public RegistrationFormTypeModel setDiscountFormType() {
        return new RegistrationFormTypeModel(RegistrationFormItem.DISCOUNTS_PAGE_LEVEL_REG_FORM.getRegistrationTypeItem());
    }

    @Getter
    @AllArgsConstructor
    public enum RegistrationFormItem {

        PLEASE_SELECT_LOGIN_REG_FORM("Please select login/reg form"),
        TEST_FORM("Test Form"),
        ARMY_REG_FORM_MY_MEMBERSHIP("Army Reg Form - My Membership"),
        REG_FORM("Reg Form"),
        PAY_CALC_LOGIN_FORM("Pay Calc Login Form"),
        ALL_FIELDS_REQUIRED_LOGIN_FORM("All Fields Required Login Form"),
        CLICK_OPTIONAL_FIELD_LOGIN_FORM("Click Optional Field Login Form"),
        VISIBLE_OPTION_BOTH_SELECTED_LOGIN_FORM("Visible Option Both Selected Login Form"),
        TEST_REGISTRATION_FORM_CREATED_FOR_TRAINING("test registration form created for training"),
        TEST("TEST"),
        MAIN_LOGIN("Main Login"),
        NAVY_REG_LOGIN_FORM_MY_MEMBERSHIP("Navy Reg Login Form - My Membership"),
        AIR_FORCE_REG_LOGIN_FORM_MY_MEMBERSHIP("Air Force Reg Login Form My Membership"),
        MARINE_CORPS_REG_LOGIN_FORM_MY_MEMBERSHIP("Marine Corps Reg Login Form My Membership"),
        COAST_GUARD_REG_LOGIN_FORM_MY_MEMBERSHIP("Coast Guard Reg Login Form My Membership"),
        NATIONAL_GUARD_REG_LOGIN_FORM_MY_MEMBERSHIP("National Guard Reg Login Form My Membership"),
        SPOUSE_REG_LOGIN_FORM_MY_MEMBERSHIP("Spouse Reg Login Form My Membership"),
        DISCOUNTS_PAGE_LEVEL_REG_FORM("Discounts Page Level Reg Form");

        private final String registrationTypeItem;
    }

}
