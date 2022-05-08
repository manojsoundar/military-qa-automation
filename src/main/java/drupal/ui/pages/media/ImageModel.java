package drupal.ui.pages.media;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static drupal.ui.pages.media.AddImagePage.ALTERNATIVE_TEXT_INPUT;
import static drupal.ui.pages.media.AddImagePage.AP_NEWSROOM_CHECKBOX;
import static drupal.ui.pages.media.AddImagePage.CAPTION_TEXT_INPUT;
import static drupal.ui.pages.media.AddImagePage.NEWSCRED_CHECKBOX;
import static drupal.ui.pages.media.AddMediaBasePage.SAVE_BUTTON;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@Log4j2
public class ImageModel extends MasterPage implements ISectionDataModel {

    private String alternateText;
    private String captionText;

    public ImageModel() {

        alternateText = "Test Image";
        captionText = "Test Image";
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        enterAlternateText(alternateText, expectedClass);
        enterCaptionText(captionText, expectedClass);
        verifyCheckboxStatus(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P enterAlternateText(String alternateText, Class<P> expectedClass) {

        if (alternateText != null) {
            ALTERNATIVE_TEXT_INPUT.should(appear, enabled)
                                  .setValue(alternateText);
        }

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P enterCaptionText(String captionText, Class<P> expectedClass) {

        if (captionText != null) {
            CAPTION_TEXT_INPUT.should(appear, enabled)
                              .setValue(captionText);
        }

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P verifyCheckboxStatus(Class<P> expectedClass) {

        log.info("Confirm the Newscred and AP Newsroom boxes are not checked");
        assertFalse(NEWSCRED_CHECKBOX.isSelected(), "Checkbox is selected");
        assertFalse(AP_NEWSROOM_CHECKBOX.isSelected(), "Checkbox is selected");
        assertTrue(SAVE_BUTTON.should(visible, enabled)
                              .exists(), "Save Button not Displayed");

        return returnInstanceOf(expectedClass);
    }

}
