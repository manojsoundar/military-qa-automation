package drupal.ui.pages.add_content.landing_page;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.models.ImageUploadModel;
import drupal.ui.components.AttachAnImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@Getter
@AllArgsConstructor
public class HeroImageModel extends MasterPage implements ISectionDataModel, AttachAnImage {

    private static final SelenideElement HERO_IMAGE_BUTTON = $("#edit-field-header-image-entity-browser-entity-browser-open-modal").as("Hero image button");

    private final String mediaName;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        attachHeroModelImage();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private HeroImageModel attachHeroModelImage() {
        if (mediaName != null) {
            HERO_IMAGE_BUTTON.should(enabled, visible)
                             .scrollIntoView("{behavior: 'instant', block: 'center', inline: 'center'}")
                             .click();
            waitAjaxJQueryMet(300);
            uploadOrAttachExistingImage(new ImageUploadModel(mediaName, null, null, true, false, null));
            waitAjaxJQueryMet(300);
        }
        return this;
    }

}
