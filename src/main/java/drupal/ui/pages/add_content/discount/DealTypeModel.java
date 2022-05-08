package drupal.ui.pages.add_content.discount;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.DEAL_TYPE_DROPDOWN;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.DealTypeItem;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class DealTypeModel extends MasterPage implements ISectionDataModel {

    private DealTypeItem dealType;

    public DealTypeModel() {
        dealType = DealTypeItem.SHOPPING_TIP;
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        selectDealType(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectDealType(Class<P> expectedClass) {
        DEAL_TYPE_DROPDOWN.should(exist, appear, enabled)
                          .selectOptionContainingText(dealType.getDealType());

        return returnInstanceOf(expectedClass);
    }

}
