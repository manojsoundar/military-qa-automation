package drupal.ui.pages.structure.taxonomy.keywords;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static drupal.ui.pages.structure.taxonomy.keywords.KeywordsAddTermPage.PARENT_TERMS_SELECT_MENU;
import static drupal.ui.pages.structure.taxonomy.keywords.KeywordsAddTermPage.RELATIONS_LINK;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class RelationsModel extends MasterPage implements ISectionDataModel {

    String relations;

    public RelationsModel() {

        relations = "Veteran Jobs";
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        setRelations(expectedClass);
        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P setRelations(Class<P> expectedClass) {

        if (relations != null) {
            RELATIONS_LINK.should(exist, appear, enabled)
                          .click();
            PARENT_TERMS_SELECT_MENU.should(exist, appear, enabled)
                                    .scrollTo()
                                    .click();
            PARENT_TERMS_SELECT_MENU.sendKeys(relations);
        }

        return returnInstanceOf(expectedClass);
    }

}
