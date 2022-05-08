package drupal.ui.pages.components.icon;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;
import mgs.qa.utils.fileutils.FileUtils;

import java.io.File;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.ui.pages.components.icon.AddIconPage.CHOOSE_FILE_BUTTON;
import static drupal.ui.pages.components.icon.AddIconPage.REMOVE_BUTTON;
import static drupal.ui.pages.components.icon.AddIconPage.TITLE_INPUT;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;
import static org.testng.Assert.assertTrue;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@Log4j2
public class IconDataModel extends MasterPage implements ISectionDataModel {

    private String title;
    private String imageFile;

    public IconDataModel() {

        title = "Test Icon Image " + timeStampFormat(PATTERN);
        imageFile = "DrupalTestFiles/TestImage2.jpg";

    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();

        enterTitle(expectedClass);
        uploadIcon(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P enterTitle(Class<P> expectedClass) {

        if (title != null) {
            TITLE_INPUT.should(appear, enabled)
                       .setValue(title);
        }

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P uploadIcon(Class<P> expectedClass) {

        if (imageFile != null) {
            File testFile = FileUtils.getFileFromClassPath(imageFile);
            CHOOSE_FILE_BUTTON.scrollTo()
                              .uploadFile(testFile);
            waitAjaxJQueryMet(300);
            if (!testFile.getName()
                         .contains(".jpg")) {
                log.error("Selected file type cannot be uploaded");
            }
            assertTrue(REMOVE_BUTTON.should(visible, enabled)
                                    .exists(), "Remove Button not Displayed");
        }
        return returnInstanceOf(expectedClass);
    }

}
