package drupal.ui.pages.structure.taxonomy.keywords;

import drupal.models.ISectionDataModel;
import drupal.ui.components.AttachAnImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.ui.pages.structure.taxonomy.keywords.KeywordsAddTermPage.DESCRIPTION_FRAME;
import static drupal.ui.pages.structure.taxonomy.keywords.KeywordsAddTermPage.DESCRIPTION_TEXT_AREA;
import static drupal.ui.pages.structure.taxonomy.keywords.KeywordsAddTermPage.GENERATE_AUTOMATIC_URL_ALIAS_CHECKBOX;
import static drupal.ui.pages.structure.taxonomy.keywords.KeywordsAddTermPage.NAME_INPUT;
import static drupal.ui.pages.structure.taxonomy.keywords.KeywordsAddTermPage.URL_ALIAS_INPUT;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class KeywordsDataModel extends MasterPage implements ISectionDataModel, AttachAnImage {

    private String name;
    private String description;
    private String urlAlias;

    public KeywordsDataModel() {

        name = "Test Topic Page Keyword " + timeStampFormat(PATTERN);
        description = "This is Test Content for automated testing! It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).";
        urlAlias = "/topics/test-topic-page-keyword-" + timeStampFormat(PATTERN);
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeName(expectedClass);
        typeDescription(expectedClass);
        typeUrlAlias(expectedClass);
        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeName(Class<P> expectedClass) {

        if (name != null) {
            NAME_INPUT.should(appear, exist, enabled)
                      .setValue(name);
        }

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeDescription(Class<P> expectedClass) {

        if (description != null) {
            switchTo().frame(DESCRIPTION_FRAME);
            DESCRIPTION_TEXT_AREA.should(appear, exist, visible)
                                 .click();
            DESCRIPTION_TEXT_AREA.should(appear, visible, enabled)
                                 .sendKeys(description);
            switchTo().parentFrame();
        }

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeUrlAlias(Class<P> expectedClass) {

        if (urlAlias != null) {
            if (GENERATE_AUTOMATIC_URL_ALIAS_CHECKBOX.isSelected()) {
                GENERATE_AUTOMATIC_URL_ALIAS_CHECKBOX.should(appear, exist, enabled)
                                                     .click();
            }
            URL_ALIAS_INPUT.should(appear, exist, enabled)
                           .setValue(urlAlias);
        }
        return returnInstanceOf(expectedClass);
    }

}