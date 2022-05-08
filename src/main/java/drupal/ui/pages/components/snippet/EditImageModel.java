package drupal.ui.pages.components.snippet;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;
import org.openqa.selenium.JavascriptExecutor;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.ui.pages.components.snippet.EditSnippetPage.EDIT_IMAGE_BUTTON;
import static drupal.ui.pages.components.snippet.EditSnippetPage.IMAGE_ALTERNATIVE_NAME;
import static drupal.ui.pages.components.snippet.EditSnippetPage.IMAGE_CAPTION;
import static drupal.ui.pages.components.snippet.EditSnippetPage.IMAGE_NAME;
import static drupal.ui.pages.components.snippet.EditSnippetPage.IMAGE_SAVE_BUTTON;
import static mgs.qa.utils.LoopUtils.waitForConditionToBeMet;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Data
public class EditImageModel extends MasterPage implements ISectionDataModel {

    private String imageName;
    private String imageAlternativeName;
    private String imageCaption;
    private boolean editImage;

    public EditImageModel() {
        imageName = null;
        imageCaption = null;
        imageAlternativeName = null;
        editImage = false;
    }

    public static EditImageModel getEditImageData() {
        return new EditImageModel("Test Image - Edit " + timeStampFormat(PATTERN), "Edit alt image", "Edit caption", true);
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();

        if (isEditImage()) {
            editImage(); // TODO code standard : we have to handle the DialogModal ; to create a separate class for the DialogModal
            fillImageName();
            fillImageCaption();
            fillImageAlternativeName();
            saveImage();
        }

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private EditSnippetPage editImage() {
        if (EDIT_IMAGE_BUTTON.exists()) {
            EDIT_IMAGE_BUTTON.should(exist, enabled, visible)
                             .click();
            // TODO to replace  by LoopUtils.waitAjaxJQueryMet(300); then import static ;)
            waitForConditionToBeMet(
                    () -> {
                        return (Boolean) ((JavascriptExecutor) getWebDriver()).executeScript("return window.jQuery != undefined && jQuery.active == 0");
                    },
                    300
            );
        }
        return new EditSnippetPage();
    }

    private EditSnippetPage fillImageName() {
        if (imageName != null) {
            IMAGE_NAME.should(enabled, visible)
                      .setValue(imageName);
        }
        return new EditSnippetPage();
    }

    private EditSnippetPage fillImageAlternativeName() {
        if (imageAlternativeName != null) {
            IMAGE_ALTERNATIVE_NAME.should(enabled, visible)
                                  .setValue(imageAlternativeName);
        }
        return new EditSnippetPage();
    }

    private EditSnippetPage fillImageCaption() {
        if (imageCaption != null) {
            IMAGE_CAPTION.should(enabled, visible)
                         .setValue(imageCaption);
        }
        return new EditSnippetPage();
    }

    private EditSnippetPage saveImage() {
        IMAGE_SAVE_BUTTON.should(enabled, visible, appear)
                         .click();
        return new EditSnippetPage();
    }

}
