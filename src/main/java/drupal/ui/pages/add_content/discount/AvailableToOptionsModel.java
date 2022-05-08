package drupal.ui.pages.add_content.discount;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.AVAILABLE_TO_CHECKBOX_LIST;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.AvailableToItem;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AvailableToOptionsModel extends MasterPage implements ISectionDataModel {

    private List<AvailableToItem> availableTo;

    public AvailableToOptionsModel() {
        availableTo = List.of(AvailableToItem.ACTIVE_DUTY, AvailableToItem.RETIREE, AvailableToItem.VETERAN, AvailableToItem.RESERVE_NATIONAL_GUARD, AvailableToItem.DEPENDENT, AvailableToItem.MILITARY_EXCLUSIVE);
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        selectAvailableToOptions(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectAvailableToOptions(Class<P> expectedClass) {

        for (AvailableToItem availableToItem : availableTo) {
            AVAILABLE_TO_CHECKBOX_LIST.find(text(availableToItem.getAvailableTo()))
                                      .click();
        }
        return returnInstanceOf(expectedClass);
    }

}
