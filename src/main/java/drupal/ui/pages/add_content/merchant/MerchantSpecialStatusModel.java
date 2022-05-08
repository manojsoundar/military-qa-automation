package drupal.ui.pages.add_content.merchant;

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
import static drupal.ui.pages.add_content.merchant.MerchantPage.SPECIAL_STATUS_DROPDOWN;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Log4j2
@Data
@AllArgsConstructor
public class MerchantSpecialStatusModel extends MasterPage implements ISectionDataModel {

    SpecialStatus specialStatus;

    public MerchantSpecialStatusModel() {
        specialStatus = null;
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        selectSpecialStatusType(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectSpecialStatusType(Class<P> expectedClass) {
        if (specialStatus != null) {
            SPECIAL_STATUS_DROPDOWN.should(exist, appear, enabled)
                                   .selectOptionContainingText(specialStatus.getSpecialStatusTypeItem());
        }
        return returnInstanceOf(expectedClass);
    }

    @Getter
    @AllArgsConstructor
    public enum SpecialStatus {
        NONE("- None -"),
        US_MILITARY_VETERAN_OWNED_BUSINESS("U.S. Military Veteran Owned Business"),
        MILITARY_SPOUSE_OWNED_BUSINESS("Military Spouse Owned business");

        private final String specialStatusTypeItem;
    }

}
