package drupal.ui.pages.media;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;
import mgs.qa.utils.fileutils.FileUtils;

import java.io.File;
import java.util.List;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static common.CommonMethods.getFileExtension;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.ui.pages.media.AddMediaBasePage.CHOOSE_FILE_BUTTON;
import static drupal.ui.pages.media.AddMediaBasePage.FILE_UPLOAD_ERROR_MESSAGE;
import static drupal.ui.pages.media.AddMediaBasePage.PUBLISHED_CHECKBOX;
import static drupal.ui.pages.media.AddMediaBasePage.REMOVE_BUTTON;
import static drupal.ui.pages.media.AddMediaBasePage.SAVE_BUTTON;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;
import static org.testng.Assert.assertTrue;

// TODO to implement equals if we are going to compare model as MediaURLModel is extends MediaTitleModel
// TODO if understand MediaUploadModel can be a MediaTitleModel, and need title member ?
@Getter
@Setter
@Log4j2
public class MediaUploadModel extends MediaTitleModel {

    private String filePath;
    private FileType fileType;

    public MediaUploadModel(String name, String filePath, FileType fileType) {

        super(name);
        this.filePath = filePath;
        this.fileType = fileType;

    }

    public static MediaUploadModel getAudioData() {

        return new MediaUploadModel("Test Audio File " + timeStampFormat(PATTERN), "DrupalTestFiles/TestAudio1.mp3", FileType.AUDIO);

    }

    public static MediaUploadModel getDocumentData() {

        return new MediaUploadModel("Test Doc File " + timeStampFormat(PATTERN), "DrupalTestFiles/Test Document.docx", FileType.DOCUMENT);

    }

    public static MediaUploadModel getImageData() {

        return new MediaUploadModel("Test Image " + timeStampFormat(PATTERN), "DrupalTestFiles/TestImage2.jpg", FileType.IMAGE);
    }

    private <P extends MasterPage> P uploadIcon(Class<P> expectedClass) {

        if (filePath != null) {
            File testFile = FileUtils.getFileFromClassPath(filePath);
            CHOOSE_FILE_BUTTON.scrollTo()
                              .uploadFile(FileUtils.getFileFromClassPath(filePath));
            waitAjaxJQueryMet(300);
            if (!fileType.getFileTypeList()
                         .contains(getFileExtension(testFile.getName()))) {
                log.error("Selected file type cannot be uploaded");
                log.info(FILE_UPLOAD_ERROR_MESSAGE.getText());
            }
            assertTrue(REMOVE_BUTTON.should(visible, enabled)
                                    .exists(), "Remove Button not Displayed");
            log.info("File uploaded successfully");
            assertTrue(PUBLISHED_CHECKBOX.should(visible, enabled)
                                         .exists(), "Published Checkbox not Displayed");
            assertTrue(SAVE_BUTTON.should(visible, enabled)
                                  .exists(), "Save Button not Displayed");
        }
        return returnInstanceOf(expectedClass);
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        super.setData(expectedClass);
        uploadIcon(expectedClass);
        return returnInstanceOf(expectedClass);

    }

    @Getter
    @AllArgsConstructor
    public enum FileType {

        AUDIO(List.of("mp3")),
        DOCUMENT(List.of("docx", "pdf", "doc", "epub", "odt", "pages", "numbers", "txt", "xlsx", "xls", "pptx", "pps", "xml", "json")),
        IMAGE(List.of("png", "gif", "jpg", "jpeg"));

        private final List<String> fileTypeList;


    }

}
