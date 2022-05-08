package drupal.ui.pages.structure.eck_entity_types.call_to_action;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.components.AttachAnImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static drupal.ui.pages.structure.eck_entity_types.call_to_action.AddCallToActionBasePage.SAVE_BUTTON;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddCallToActionLeadFormPage extends AdministrationToolbar implements AttachAnImage {

    protected static final SelenideElement LEADS_PRODUCT_DROPDOWN = $("#edit-field-leads-product");
    protected static final SelenideElement C_VAR_INPUT = $("#edit-field-c-var-0-value");
    private static final String URL_REGEX = "/add/call_to_action_lead_form";
    private static final SelenideElement ADD_CALL_TO_ACTION_LEADS_H_1_TAG = $("#block-pagetitle h1 em.placeholder");

    public AddCallToActionLeadFormPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Add Call To Action - Lead Form Page not loaded.");
        assertTrue(ADD_CALL_TO_ACTION_LEADS_H_1_TAG.should(exist, appear)
                                                   .has(text("Lead Form")), "Lead Form: " + ADD_CALL_TO_ACTION_LEADS_H_1_TAG.getText() + "not displayed,should contains('Lead Form')");
        log.info("Add Call To Action-Lead Form Page loaded properly.");
    }

    public CallToActionResultPage fillIn(List<ISectionDataModel> sectionModel) {

        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(AddCallToActionLeadFormPage.class);
        }
        SAVE_BUTTON.should(appear, enabled, visible)
                   .click();

        return new CallToActionResultPage();
    }

    @Getter
    @AllArgsConstructor
    public enum LeadsProduct {
        AUTO("Auto"),
        LIFE("Life"),
        HOME("Home"),
        HEALTH("Health");

        private final String leadsProductItem;
    }
}