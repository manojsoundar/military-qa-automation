package drupal.ui.pages.structure.taxonomy.services;

import drupal.models.ISectionDataModel;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static drupal.ui.pages.structure.taxonomy.services.AddServicePage.LINK_URL_INPUT;
import static drupal.ui.pages.structure.taxonomy.services.AddServicePage.URL_ALIAS_INPUT;

@SuppressWarnings("CanBeFinal")
public class ServicesUrlDataModel extends MasterPage implements ISectionDataModel {

    String urlAlias;
    String linkUrl;

    ServicesUrlDataModel() {
        urlAlias = "/test";
        linkUrl = "Playlist TEST (2766)";
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeUrlAlias(expectedClass);
        typeLinkUrl(expectedClass);
        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeUrlAlias(Class<P> expectedClass) {

        if (urlAlias != null) {
            URL_ALIAS_INPUT.should(appear, exist, enabled)
                           .setValue(urlAlias);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeLinkUrl(Class<P> expectedClass) {

        if (linkUrl != null) {
            LINK_URL_INPUT.should(appear, exist, enabled)
                          .setValue(linkUrl);
        }
        return returnInstanceOf(expectedClass);
    }

}
