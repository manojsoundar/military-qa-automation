package drupal.ui.pages.add_content.article;

import drupal.models.ISectionDataModel;
import drupal.models.VideoUploadModel;
import drupal.ui.components.AttachAVideo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static drupal.ui.pages.add_content.article.CreateArticlePage.VIDEO_UPLOAD_BUTTON;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Data
public class ArticleUploadVideoModel extends MasterPage implements ISectionDataModel, AttachAVideo {

    VideoUploadModel videoUploadModel;

    public static ArticleUploadVideoModel setArticleVideoData() {
        return new ArticleUploadVideoModel(new VideoUploadModel("Youtube", true, false, true));
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        attachVideo(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P attachVideo(Class<P> expectedClass) {
        if (videoUploadModel != null) {
            VIDEO_UPLOAD_BUTTON.scrollIntoView("{behavior: 'instant', block: 'center', inline: 'center'}")
                               .should(enabled, visible, appear)
                               .click();
            waitAjaxJQueryMet(120);
            uploadOrAttachExistingVideo(videoUploadModel);
        }
        return returnInstanceOf(expectedClass);
    }

}
