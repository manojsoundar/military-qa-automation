package drupal.ui.pages.components.widgets;

import com.codeborne.selenide.ElementsCollection;
import drupal.models.ISectionDataModel;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;
import mgs.qa.utils.fileutils.FileUtils;

import java.io.File;
import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static common.CommonMethods.getFileExtension;
import static drupal.ui.pages.components.widgets.AddWidgetContentPage.JS_FILES_ADD_ANOTHER_ITEM_BUTTON;
import static drupal.ui.pages.components.widgets.AddWidgetContentPage.JS_FILES_BUTTON;
import static drupal.ui.pages.components.widgets.AddWidgetContentPage.JS_FILES_INPUT;
import static drupal.ui.pages.components.widgets.AddWidgetContentPage.JS_FILE_FORMAT_LIST;

@Log4j2
@Getter
public class JsFilesDataModel extends MasterPage implements ISectionDataModel {

    private final List<String> jsFileUrl;
    private final List<String> jsFilePath;

    public JsFilesDataModel() {

        jsFilePath = List.of("DrupalTestFiles/Test Document.js");
        jsFileUrl = List.of("https://www.military1.com", "https://www.military2.com", "https://www.military3.com", "https://www.military4.com");

    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        uploadJsFile(expectedClass);
        enterJsFileUrl(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P uploadJsFile(Class<P> expectedClass) {

        // TODO update algo
        // first testing if jsFilePath non null and non empty then we execute
        // second as first applied, the if l.58 is not necessary
        for (int i = 0; i < jsFilePath.size(); i++) {
            File jsTestFile = FileUtils.getFileFromClassPath(jsFilePath.get(i));

            if (jsFilePath != null) {
                JS_FILES_BUTTON.scrollIntoView("{behavior: 'instant', block: 'center', inline: 'center'}")
                               .uploadFile(jsTestFile);

                if (!JS_FILE_FORMAT_LIST.contains(getFileExtension(jsTestFile.getName()))) {
                    log.error("Selected file type cannot be uploaded");
                }
            }
        }

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P enterJsFileUrl(Class<P> expectedClass) {

        for (int i = 0; i < jsFileUrl.size() - 1; i++) {
            JS_FILES_ADD_ANOTHER_ITEM_BUTTON.should(exist, appear, enabled)
                                            .click();
        }

        ElementsCollection jsTextBoxes = JS_FILES_INPUT;

        for (int i = 0; i < jsTextBoxes.size(); i++) {
            jsTextBoxes.get(i)
                       .should(exist, appear, enabled)
                       .setValue(jsFileUrl.get(i));

        }
        return returnInstanceOf(expectedClass);
    }

}
