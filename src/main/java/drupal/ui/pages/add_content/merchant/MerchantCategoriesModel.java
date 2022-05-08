package drupal.ui.pages.add_content.merchant;

import drupal.enums.content.Category;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static drupal.enums.content.Category.DINING_DISCOUNTS;
import static drupal.enums.content.Category.MILITARY_TRAVEL_DISCOUNTS;
import static drupal.enums.content.Category.WASHINGTON_DC;
import static drupal.ui.pages.add_content.merchant.MerchantPage.CATEGORIES_AVAILABLE_LIST;
import static drupal.ui.pages.add_content.merchant.MerchantPage.CATEGORIES_CONTAINER_DROP_WEB_ELEMENT;
import static drupal.ui.pages.add_content.merchant.MerchantPage.CATEGORIES_INPUT;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Log4j2
@Data
@AllArgsConstructor
public class MerchantCategoriesModel extends MasterPage implements ISectionDataModel {

    private List<Category> categoriesList;

    public MerchantCategoriesModel() {
        categoriesList = List.of(MILITARY_TRAVEL_DISCOUNTS, DINING_DISCOUNTS, WASHINGTON_DC);
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        selectCategories(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectCategories(Class<P> expectedClass) {
        if (!categoriesList.isEmpty()) {
            for (Category category : categoriesList) {
                CATEGORIES_INPUT.should(exist, appear, enabled)
                                .click();
                CATEGORIES_INPUT.setValue(category.getCategoryName());
                if (CATEGORIES_CONTAINER_DROP_WEB_ELEMENT.should(appear, visible)
                                                         .isDisplayed()) {
                    CATEGORIES_AVAILABLE_LIST.first()
                                             .should(appear, exist)
                                             .click();
                }
            }
        }
        return returnInstanceOf(expectedClass);
    }

}
