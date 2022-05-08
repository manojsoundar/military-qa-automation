package drupal.ui.pages.structure.eck_entity_types.call_to_action;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static drupal.ui.pages.structure.eck_entity_types.call_to_action.AddCallToActionLeadFormPage.C_VAR_INPUT;
import static drupal.ui.pages.structure.eck_entity_types.call_to_action.AddCallToActionLeadFormPage.LEADS_PRODUCT_DROPDOWN;
import static drupal.ui.pages.structure.eck_entity_types.call_to_action.AddCallToActionLeadFormPage.LeadsProduct;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class CTALeadsFormModel extends MasterPage implements ISectionDataModel {

    LeadsProduct leadsProduct;
    String cVar;

    public CTALeadsFormModel() {
        leadsProduct = LeadsProduct.AUTO;
        cVar = "X20180";
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        selectLeadsProduct(expectedClass);
        typeCVar(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectLeadsProduct(Class<P> expectedClass) {

        LEADS_PRODUCT_DROPDOWN.selectOptionContainingText(leadsProduct.getLeadsProductItem());
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeCVar(Class<P> expectedClass) {

        if (cVar != null) {
            C_VAR_INPUT.should(exist, appear, enabled)
                       .setValue(cVar);
        }
        return returnInstanceOf(expectedClass);
    }

}
