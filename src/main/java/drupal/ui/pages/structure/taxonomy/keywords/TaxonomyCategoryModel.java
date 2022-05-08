package drupal.ui.pages.structure.taxonomy.keywords;

import drupal.models.ISectionDataModel;
import drupal.ui.pages.structure.taxonomy.keywords.KeywordsAddTermPage.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static drupal.ui.pages.structure.taxonomy.keywords.KeywordsAddTermPage.CATEGORY_DROPDOWN;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class TaxonomyCategoryModel extends MasterPage implements ISectionDataModel {

    Category category;

    public TaxonomyCategoryModel() {
        category = Category.AUTOS;
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        setCategory(expectedClass);
        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P setCategory(Class<P> expectedClass) {

        CATEGORY_DROPDOWN.should(exist, appear, enabled)
                         .selectOptionContainingText(category.getCategoryItem());
        return returnInstanceOf(expectedClass);
    }

}