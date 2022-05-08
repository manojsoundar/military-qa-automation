package drupal.ui.pages.add_content.discount;

import drupal.enums.content.Category;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static drupal.enums.content.Category.AMUSEMENT_PARKS;
import static drupal.enums.content.Category.HOT_DEALS;
import static drupal.enums.content.Category.ORLANDO;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.CATEGORIES_AVAILABLE_LIST;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.CATEGORIES_CONTAINER_WEB_ELEMENT;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.CATEGORIES_INPUT;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CategoriesModel extends MasterPage implements ISectionDataModel {

    private List<Category> categoriesList;

    public CategoriesModel() {
        categoriesList = List.of(HOT_DEALS, AMUSEMENT_PARKS, ORLANDO);
    }

    public static CategoriesModel expireDiscountCategory() {
        return new CategoriesModel(List.of(AMUSEMENT_PARKS));
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
                                .scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"center\"}")
                                .click();
                CATEGORIES_INPUT.setValue(category.getCategoryName());
                if (CATEGORIES_CONTAINER_WEB_ELEMENT.should(appear, visible)
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
