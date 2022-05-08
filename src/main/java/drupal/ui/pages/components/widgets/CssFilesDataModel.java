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
import static drupal.ui.pages.components.widgets.AddWidgetContentPage.CSS_FILES_ADD_ANOTHER_ITEM_BUTTON;
import static drupal.ui.pages.components.widgets.AddWidgetContentPage.CSS_FILES_BUTTON;
import static drupal.ui.pages.components.widgets.AddWidgetContentPage.CSS_FILES_INPUT;
import static drupal.ui.pages.components.widgets.AddWidgetContentPage.CSS_FILE_FORMAT_LIST;

@Log4j2
@Getter
public class CssFilesDataModel extends MasterPage implements ISectionDataModel {

    private final List<String> cssFileUrl;
    private final List<String> cssFilePath;

    public CssFilesDataModel() {

        cssFilePath = List.of("DrupalTestFiles/Test Document.css");
        cssFileUrl = List.of("https://www.military1.com", "https://www.military2.com", "https://www.military3.com", "https://www.military4.com");

    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        uploadCssFile(expectedClass);
        enterCssFileUrl(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P uploadCssFile(Class<P> expectedClass) {

        for (String currentCssFilePath : cssFilePath) {
            File cssTestFile = FileUtils.getFileFromClassPath(currentCssFilePath);
            CSS_FILES_BUTTON.scrollIntoView("{behavior: 'instant', block: 'center', inline: 'center'}")
                            .uploadFile(cssTestFile);

            if (!CSS_FILE_FORMAT_LIST.contains(getFileExtension(cssTestFile.getName()))) {
                log.error("Selected file type cannot be uploaded");
            }
        }

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P enterCssFileUrl(Class<P> expectedClass) {

        if (cssFileUrl != null) {
            for (int i = 0; i < cssFileUrl.size() - 1; i++) {
                CSS_FILES_ADD_ANOTHER_ITEM_BUTTON.should(exist, appear, enabled)
                                                 .click();
            }

            ElementsCollection cssTextBoxes = CSS_FILES_INPUT;

            for (int i = 0; i < cssTextBoxes.size(); i++) {
                cssTextBoxes.get(i)
                            .should(exist, appear, enabled)
                            .setValue(cssFileUrl.get(i));
            }
        }

        return returnInstanceOf(expectedClass);
    }

}
