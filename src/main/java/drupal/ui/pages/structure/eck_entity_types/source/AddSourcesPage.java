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
public class AddSourcesPage extends AdministrationToolbar {

    protected static final SelenideElement TITLE_INPUT = $("input.form-text").as("Title Input");
    protected static final SelenideElement SAVE_BUTTON = $("#edit-submit").as("Save Button");
    private static final String URL_REGEX = "/source/add/sources";
    private static final SelenideElement ADD_SOURCES_H_1_TAG = $("#block-pagetitle h1 em.placeholder").as("Add Sources Page H1 Tag");

    public AddSourcesPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Add Sources Page not loaded.");
        assertTrue(ADD_SOURCES_H_1_TAG.should(exist, appear)
                                      .has(text("Sources")), "Add Source title" + ADD_SOURCES_H_1_TAG.getText() + "not displayed,should contain('Sources')");
        log.info("Add Sources Page loaded properly.");
    }

    public SourcePage addSource(List<ISectionDataModel> sectionModel) {
        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(AddSourcesPage.class);
        }
        SAVE_BUTTON.should(appear, enabled)
                   .click();
        return new SourcePage();
    }
}
