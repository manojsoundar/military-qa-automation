package drupal.ui.pages.structure.taxonomy.base_guide;

import drupal.models.ISectionDataModel;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static drupal.ui.pages.structure.taxonomy.base_guide.AddBaseGuidePage.LINK_URL_INPUT;
import static drupal.ui.pages.structure.taxonomy.base_guide.AddBaseGuidePage.URL_ALIAS_INPUT;

@SuppressWarnings({"ALL", "CanBeFinal"})
public class BaseGuideUrlDataModel extends MasterPage implements ISectionDataModel {

    String urlAlias;
    String linkUrl;

    BaseGuideUrlDataModel() {
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
