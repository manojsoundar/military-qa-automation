package drupal.ui.pages.structure.eck_entity_types.source;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EditSourcePage extends AdministrationToolbar {

    private static final String URL_REGEX = "/edit";
    private static final SelenideElement EDIT_SOURCES_H_1_TAG = $("#block-pagetitle h1");
    private static final SelenideElement SAVE_BUTTON = $("#edit-submit").as("Save Button");

    public EditSourcePage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Edit Source Page not loaded.");
        assertTrue(EDIT_SOURCES_H_1_TAG.should(exist, appear)
                                       .has(text("Edit Source")), "Edit source title:" + EDIT_SOURCES_H_1_TAG.getText() + "not displayed,should contain('Edit Source')");
        log.info("Edit Source Page loaded properly.");
    }

    public SourcePage editSource(List<ISectionDataModel> sectionModel) {
        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(EditSourcePage.class);
        }
        SAVE_BUTTON.should(appear, enabled)
                   .click();

        return new SourcePage();
    }
}
