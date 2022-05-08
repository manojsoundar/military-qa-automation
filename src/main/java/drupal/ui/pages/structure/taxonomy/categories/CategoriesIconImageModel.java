package drupal.ui.pages.structure.taxonomy.categories;

import drupal.models.ISectionDataModel;
import drupal.models.ImageUploadModel;
import drupal.ui.components.AttachAnImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static drupal.ui.pages.structure.taxonomy.categories.CategoriesAddTermPage.ATTACH_AN_IMAGE_BUTTON;
import static java.time.Duration.ofSeconds;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CategoriesIconImageModel extends MasterPage implements ISectionDataModel, AttachAnImage {

    ImageUploadModel iconImage;

    public CategoriesIconImageModel() {
        iconImage = new ImageUploadModel("Test Image", null, null, true, false, null);

    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        attachImage(expectedClass);
        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P attachImage(Class<P> expectedClass) {
        if (iconImage != null) {
            ATTACH_AN_IMAGE_BUTTON.should(appear, ofSeconds(30))
                                  .should(enabled)
                                  .click();
            waitAjaxJQueryMet(120);
            uploadOrAttachExistingImage(iconImage);
        }
        return returnInstanceOf(expectedClass);
    }

}
