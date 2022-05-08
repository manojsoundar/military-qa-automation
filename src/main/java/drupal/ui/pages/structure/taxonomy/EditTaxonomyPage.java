package drupal.ui.pages.structure.taxonomy;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EditTaxonomyPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/taxonomy/term/";
    private static final SelenideElement SAVE_BUTTON = $("#edit-submit").as("save button");
    private static final SelenideElement UPDATED_STATUS_MESSAGE = $(".placeholder a").as("updated status message");

    public EditTaxonomyPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Edit Services Page not loaded.");
        log.info("Edit Taxonomy Page loaded properly.");
    }

    public EditTaxonomyPage editTaxonomy(List<ISectionDataModel> taxonomyBaseModel) {
        for (ISectionDataModel currentSectionModel : taxonomyBaseModel) {
            currentSectionModel.setData(EditTaxonomyPage.class);
        }
        SAVE_BUTTON.should(appear, enabled, visible)
                   .click();
        return this;
    }

    public EditTaxonomyPage verifyTaxonomyUpdated(String updatedTitle) {
        assertTrue(verifyURLLoaded(URL_REGEX + "\\d{5}"), "Data edit is failed");
        assertTrue(UPDATED_STATUS_MESSAGE.should(exist, appear)
                                         .has(text(updatedTitle)), "Updated title message not displayed.");
        return this;
    }

}
