package drupal.ui.pages.structure.taxonomy.categories;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static drupal.ui.pages.structure.taxonomy.categories.CategoriesAddTermPage.LINK_URL_INPUT;
import static drupal.ui.pages.structure.taxonomy.categories.CategoriesAddTermPage.URL_ALIAS_INPUT;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CategoriesUrlModel extends MasterPage implements ISectionDataModel {

    String urlAlias;
    String linkUrl;

    public CategoriesUrlModel() {
        urlAlias = null;
        linkUrl = null;
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeUrl(expectedClass);
        typeLinkUrl(expectedClass);
        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeUrl(Class<P> expectedClass) {
        if (urlAlias != null) {
            URL_ALIAS_INPUT.should(appear, exist, enabled)
                           .scrollTo()
                           .setValue(urlAlias);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeLinkUrl(Class<P> expectedClass) {
        if (linkUrl != null) {
            LINK_URL_INPUT.should(appear, exist, enabled)
                          .scrollTo()
                          .setValue(linkUrl);
        }
        return returnInstanceOf(expectedClass);
    }

}
