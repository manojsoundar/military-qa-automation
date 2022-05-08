package drupal.ui.pages.structure.taxonomy.keywords;

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
import static drupal.ui.pages.structure.taxonomy.keywords.KeywordsAddTermPage.LINK_URL_INPUT;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class LinkURLModel extends MasterPage implements ISectionDataModel {

    String linkURL;

    public LinkURLModel() {

        linkURL = "Test Topic Page Keyword " + timeStampFormat(PATTERN);
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeLinkUrl(expectedClass);
        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeLinkUrl(Class<P> expectedClass) {

        if (!linkURL.isEmpty()) {
            LINK_URL_INPUT.should(appear, exist, enabled)
                          .setValue(linkURL);
        }
        return returnInstanceOf(expectedClass);
    }

}