package drupal.ui.pages.structure.eck_entity_types.call_to_action;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddCallToActionNLSignupPage extends AdministrationToolbar {

    protected static final SelenideElement INTERNAL_TITLE_INPUT = $("#edit-field-internal-title-0-value");
    protected static final SelenideElement TITLE_INPUT = $("#edit-title-0-value");
    protected static final SelenideElement BLURB_INPUT = $("#edit-field-blurb-0-value");
    protected static final SelenideElement DISPLAY_FIELDS_DROPDOWN = $("#edit-field-displayed-fields");
    protected static final SelenideElement BUTTON_LEVEL_INPUT = $("#edit-field-button-label-0-value");
    protected static final SelenideElement ISRC_INPUT = $("#edit-field-isrc-0-value");
    private static final String URL_REGEX = "/add/call_to_action_nl_signup";
    private static final SelenideElement ADD_CALL_TO_ACTION_NL_SIGNUP_H_1_TAG = $("#block-pagetitle h1 em.placeholder");
    private static final SelenideElement SAVE_BUTTON = $("#edit-submit");


    public AddCallToActionNLSignupPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Add Call To Action NL Signup Page not loaded");
        assertTrue(ADD_CALL_TO_ACTION_NL_SIGNUP_H_1_TAG.getText()
                                                       .trim()
                                                       .contains("NL Signup"), "NL Signup title: " + ADD_CALL_TO_ACTION_NL_SIGNUP_H_1_TAG.getText() + "not displayed,should contain('NL sign up')");

    }

    public AddCallToActionNLSignupPage fillSignupCTA(List<ISectionDataModel> dataModel) {
        for (ISectionDataModel data : dataModel) {
            data.setData(AddCallToActionNLSignupPage.class);
        }
        return this;
    }

    public CallToActionResultPage clickSaveButton() {

        SAVE_BUTTON.should(enabled, visible, exist)
                   .click();
        return new CallToActionResultPage();
    }

    @AllArgsConstructor
    @Getter
    public enum TargetNewsletter {

        AIR_FORCE_INSIDER($x(".//*[text()='Air Force Insider']/preceding-sibling::input")),
        ARMY_INSIDER($x(".//*[text()='Army Insider']/preceding-sibling::input")),
        COAST_GUARD_INSIDER($x(".//*[text()='Coast Guard Insider']/preceding-sibling::input")),
        EARLY_BRIEF($x(".//*[text()='Early Brief']/preceding-sibling::input")),
        DO_D_INSIDER($x(".//*[text()='DoD Insider']/preceding-sibling::input")),
        MARINES_INSIDER($x(".//*[text()='Marines Insider']/preceding-sibling::input")),
        NAVY_INSIDER($x(".//*[text()='Navy Insider']/preceding-sibling::input")),
        FINANCE_INSIDER($x(".//*[text()='Finance Insider']/preceding-sibling::input")),
        MILITARY_REPORT($x(".//*[text()='Military Report']/preceding-sibling::input")),
        DISCOUNTS_NL($x(".//*[text()='Discounts NL']/preceding-sibling::input")),
        VETERAN_JOBS_NL($x(".//*[text()='Veteran Jobs NL']/preceding-sibling::input")),
        AT_EASE($x(".//*[text()='AtEase']/preceding-sibling::input")),
        SPOUSE_FAMILY_NL($x(".//*[text()='Spouse & Family NL']/preceding-sibling::input")),
        EARLY_BRIEF_AND_MR($x(".//*[text()='Early Brief and MR']/preceding-sibling::input")),
        FINANCE_MR($x(".//*[text()='Finance & MR']/preceding-sibling::input"));

        private final SelenideElement targetNewsLetterItem;

    }

    @Getter
    @AllArgsConstructor
    public enum DisplayFieldType {
        EMAIL_FIELD_ONLY("Email field only"),
        MULTIPLE_FIELDS("Multiple fields");

        private final String fieldsTypeItem;
    }

}
