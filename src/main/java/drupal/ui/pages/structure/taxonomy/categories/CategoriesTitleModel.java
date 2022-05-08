package drupal.ui.pages.structure.taxonomy.categories;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.ui.pages.structure.taxonomy.categories.CategoriesAddTermPage.NAME_INPUT;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CategoriesTitleModel extends MasterPage implements ISectionDataModel {

    String name;


    public CategoriesTitleModel() {
        name = "Test Article Category " + timeStampFormat(PATTERN);
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeName(expectedClass);
        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeName(Class<P> expectedClass) {
        if (name != null) {
            NAME_INPUT.should(appear, exist, enabled)
                      .scrollTo()
                      .setValue(name);
        }
        return returnInstanceOf(expectedClass);
    }

}
