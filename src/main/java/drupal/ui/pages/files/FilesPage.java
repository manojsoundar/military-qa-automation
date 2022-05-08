package drupal.ui.pages.files;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.FilesModel;
import drupal.models.TimeStampPattern;
import drupal.ui.components.AdministrationToolbar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import mgs.qa.utils.asserts.SoftAssertMt;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static common.CommonMethods.verifyGridDisplayInChronologicalOrder;
import static org.testng.Assert.assertTrue;

@Log4j2
public class FilesPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/content/files";
    private static final SelenideElement FILES_H_1_TAG = $("#block-pagetitle h1").as("Files Page H1 Tag");
    private static final SelenideElement FILES_TAB = $("#block-tabs a[href*='files']").as("Files Tab");
    private static final SelenideElement FILENAME_INPUT = $("input#edit-filename").as("Filename Input");
    private static final SelenideElement MIME_TYPE_INPUT = $("input#edit-filemime").as("MIME Type Input");
    private static final SelenideElement STATUS_DROPDOWN = $("select#edit-status").as("Status Dropdown");
    private static final SelenideElement FILTER_BUTTON = $("input#edit-submit-files").as("Filter Button");
    private static final ElementsCollection NAME_LIST = $$("tbody .views-field-filename a").as("Name List");
    private static final ElementsCollection MIME_TYPE_LIST = $$("tbody .views-field-filemime").as("MIME Type List");
    private static final ElementsCollection UPLOAD_DATE_LIST = $$("tbody .views-field-created").as("Upload Date List");
    private static final ElementsCollection CHANGED_DATE_LIST = $$("tbody .views-field-changed").as("Changed Date List");

    public FilesPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Files Page not loaded.");
        assertTrue(FILES_H_1_TAG.should(exist, appear)
                                .has(text("Files")), "Correct page is not displayed.");
        log.info("Files Page loaded properly.");
    }

    public FilesPage setStatus(StatusMenu statusMenu) {

        STATUS_DROPDOWN.should(exist, appear, enabled)
                       .click();
        statusMenu.getStatusOption()
                  .should(exist, appear)
                  .click();

        return this;
    }

    public FilesPage searchFiles(FilesModel fileData, StatusMenu status) {

        setStatus(status);
        if (fileData.getFileName() != null) {
            log.info("Enter the File name and filter");
            FILENAME_INPUT.should(exist, appear)
                          .setValue(fileData.getFileName());
            FILTER_BUTTON.should(exist, appear)
                         .click();
            log.info("Verify if Grid item displays the files filtered by File Name");
            verifyGridDisplayByFilename(fileData);
        }

        if (fileData.getMimeType() != null) {
            log.info("Enter the MIME Type and filter");
            MIME_TYPE_INPUT.should(exist, appear)
                           .setValue(fileData.getMimeType());
            FILTER_BUTTON.should(exist, appear)
                         .click();
            log.info("Verify if Grid item displays the files filtered by MIME Type");
            verifyGridDisplayByMIMEType(fileData);
        }

        return this;
    }

    public FilesPage verifyGridDisplayByMIMEType(FilesModel fileData) {

        if (MIME_TYPE_LIST.isEmpty()) {
            log.info("No files available");
        } else {
            var softAssert = SoftAssertMt.getSoftAssert();
            for (SelenideElement element : MIME_TYPE_LIST) {
                log.info(element.getText());
                softAssert.assertTrue(element.getText()
                                             .contains(fileData.getMimeType()), "Grid is not filtered based on MIME Type");
            }
            softAssert.assertAll();
            log.info("Grid item filtered based on MIME Type");
        }

        return this;
    }

    public FilesPage verifyGridDisplayByFilename(FilesModel fileData) {

        if (NAME_LIST.isEmpty()) {
            log.info("No files available");
        } else {
            var softAssert = SoftAssertMt.getSoftAssert();
            for (SelenideElement element : NAME_LIST) {
                log.info(element.getText());
                softAssert.assertTrue(element.getText()
                                             .contains(fileData.getFileName()), "Grid is not filtered based on File Name");
            }
            softAssert.assertAll();
            log.info("Grid item filtered based on File Name");
        }

        return this;
    }

    public FilesPage verifyFilesGridDisplayInChronologicalOrder() {

        log.info("Verify if Grid item are displayed in Chronological Order");
        if (CHANGED_DATE_LIST.isEmpty()) {
            log.info("No Dates displayed");
        } else {
            verifyGridDisplayInChronologicalOrder(CHANGED_DATE_LIST, TimeStampPattern.DAY_DATE_TIME_PATTERN);
        }

        return this;
    }

    @Getter
    @AllArgsConstructor
    public enum StatusMenu {

        ANY($("#edit-status option[value='All']")),
        TEMPORARY($("#edit-status option[value='0']")),
        PERMANENT($("#edit-status option[value='1']"));

        private final SelenideElement statusOption;
    }

}
