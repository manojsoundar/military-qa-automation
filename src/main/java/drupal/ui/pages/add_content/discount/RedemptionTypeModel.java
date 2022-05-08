package drupal.ui.pages.add_content.discount;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.REDEMPTION_TYPE_DROPDOWN;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.RedemptionTypeItem;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class RedemptionTypeModel extends MasterPage implements ISectionDataModel {

    private RedemptionTypeItem redemptionType;

    public RedemptionTypeModel() {
        redemptionType = RedemptionTypeItem.ONLINE;
    }

    public static RedemptionTypeModel addPixelRedemptionType() {
        return new RedemptionTypeModel(RedemptionTypeItem.IN_STORE);
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        selectRedemptionType(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectRedemptionType(Class<P> expectedClass) {

        REDEMPTION_TYPE_DROPDOWN.should(exist, appear, enabled)
                                .selectOptionContainingText(redemptionType.getRedemptionType());
        return returnInstanceOf(expectedClass);
    }

}
