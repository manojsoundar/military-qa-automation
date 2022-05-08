package drupal.ui.pages.add_content.article;

import drupal.models.ISectionDataModel;
import drupal.models.ImageUploadModel;
import drupal.ui.components.AttachAnImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static drupal.ui.pages.add_content.article.CreateArticlePage.ATTACH_AN_IMAGE_BUTTON;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ArticleImageModel extends MasterPage implements ISectionDataModel, AttachAnImage {

    ImageUploadModel imageData;

    public static ArticleImageModel setImageArticleData() {
        return new ArticleImageModel(new ImageUploadModel());
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

        if (imageData != null) {

            ATTACH_AN_IMAGE_BUTTON.scrollIntoView("{behavior: 'instant', block: 'center', inline: 'center'}")
                                  .should(enabled, appear)
                                  .click();
            uploadOrAttachExistingImage(imageData);
        }
        return returnInstanceOf(expectedClass);
    }

}
