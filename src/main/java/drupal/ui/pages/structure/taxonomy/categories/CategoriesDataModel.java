package drupal.ui.pages.structure.taxonomy.categories;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;
import static drupal.ui.pages.structure.taxonomy.categories.CategoriesAddTermPage.DESCRIPTION_FRAME;
import static drupal.ui.pages.structure.taxonomy.categories.CategoriesAddTermPage.DESCRIPTION_TEXT_AREA;
import static drupal.ui.pages.structure.taxonomy.categories.CategoriesAddTermPage.DISPLAY_LABEL_INPUT;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CategoriesDataModel extends MasterPage implements ISectionDataModel {

    String displayLabel;
    String descriptionText;

    public CategoriesDataModel() {
        displayLabel = "Test Label";
        descriptionText = "Test Description";
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeDisplayLabel(expectedClass);
        typeDescriptionText(expectedClass);
        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeDisplayLabel(Class<P> expectedClass) {
        if (displayLabel != null) {
            DISPLAY_LABEL_INPUT.should(appear, exist, enabled)
                               .scrollTo()
                               .setValue(displayLabel);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeDescriptionText(Class<P> expectedClass) {
        if (descriptionText != null) {
            switchTo().frame(DESCRIPTION_FRAME);
            DESCRIPTION_TEXT_AREA.should(appear, exist, visible)
                                 .click();
            DESCRIPTION_TEXT_AREA.should(appear, visible, enabled)
                                 .sendKeys(descriptionText);
            switchTo().parentFrame();
        }
        return returnInstanceOf(expectedClass);
    }

}
