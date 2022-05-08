package drupal.ui.pages.structure.taxonomy.base_guide;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.DATE_WITH_HYPHEN_PATTERN;
import static drupal.ui.pages.structure.taxonomy.base_guide.AddBaseGuidePage.DESCRIPTION_IFRAME;
import static drupal.ui.pages.structure.taxonomy.base_guide.AddBaseGuidePage.DESCRIPTION_TEXT_AREA_INPUT;
import static drupal.ui.pages.structure.taxonomy.base_guide.AddBaseGuidePage.NAME_INPUT;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Log4j2
@Data
@AllArgsConstructor
public class TaxonomyBaseGuideDataModel extends MasterPage implements ISectionDataModel {

    String name;
    String description;

    public TaxonomyBaseGuideDataModel() {
        name = "Test Individual " + timeStampFormat(DATE_WITH_HYPHEN_PATTERN);
        description = "Test description for taxonomy base guide model";
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeName(expectedClass);
        typeDescription(expectedClass);
        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeDescription(Class<P> expectedClass) {
        if (description != null) {
            switchTo().frame(DESCRIPTION_IFRAME);
            DESCRIPTION_TEXT_AREA_INPUT.should(appear, exist, visible)
                                       .click();
            DESCRIPTION_TEXT_AREA_INPUT.sendKeys(description);
            switchTo().parentFrame();
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeName(Class<P> expectedClass) {

        if (name != null) {
            NAME_INPUT.should(appear, exist, enabled)
                      .setValue(name);
        }
        return returnInstanceOf(expectedClass);
    }

}
