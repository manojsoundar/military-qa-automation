package drupal.ui.pages.add_content.discount;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.MILITARY_ID_DROPDOWN;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.MilitaryIdItem;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class MilitaryIDModel extends MasterPage implements ISectionDataModel {

    private CreateDiscountPage.MilitaryIdItem militaryId;

    public MilitaryIDModel() {
        militaryId = MilitaryIdItem.REQUIRED;
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        selectMilitaryId(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectMilitaryId(Class<P> expectedClass) {

        MILITARY_ID_DROPDOWN.should(exist, appear, enabled)
                            .selectOptionContainingText(militaryId.getMilitaryId());
        return returnInstanceOf(expectedClass);
    }

}
