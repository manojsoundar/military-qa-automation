package drupal.ui.pages.structure.taxonomy.base_guide;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.structure.taxonomy.TaxonomyResultPage;
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
public class AddBaseGuidePage extends AdministrationToolbar {

    protected static final SelenideElement NAME_INPUT = $("#edit-name-0-value").as("Name input field");
    protected static final SelenideElement DESCRIPTION_TEXT_AREA_INPUT = $("html body.cke_editable").as("Left column input");
    protected static final SelenideElement DESCRIPTION_IFRAME = $("iframe.cke_reset").as("left column iframe");
    protected static final SelenideElement URL_ALIAS_INPUT = $("#edit-path-0-alias").as("url input");
    protected static final SelenideElement LINK_URL_INPUT = $("#edit-field-link-0-uri").as("Link url input");
    protected static final SelenideElement SAVE_BUTTON = $("#edit-submit").as("save button");
    protected static final SelenideElement STATUS_MESSAGE = $(".placeholder a").as("Status messages");
    protected static final SelenideElement RELATIONS_LINK = $("#edit-relations span");
    protected static final SelenideElement PARENT_TERMS_SELECT_MENU = $("select#edit-parent");
    private static final String URL_REGEX = "/base_guides/add";
    private static final SelenideElement TAXONOMY_BASE_GUIDE_H_1_TAG = $("#block-pagetitle h1").as("Base guide page H1 Tag");

    public AddBaseGuidePage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Add Term Page not loaded.");
        assertTrue(TAXONOMY_BASE_GUIDE_H_1_TAG.should(exist, appear)
                                              .has(text("Add term")), "Taxonomy Base guide title" + TAXONOMY_BASE_GUIDE_H_1_TAG.getText() + "not displayed,should contain('Add term')");
        log.info("Add Term Page loaded properly.");
    }

    public AddBaseGuidePage createBaseGuideTaxonomy(List<ISectionDataModel> taxonomyBaseModel) {
        for (ISectionDataModel sectionDataModel : taxonomyBaseModel) {
            sectionDataModel.setData(AddBaseGuidePage.class);
        }
        return this;
    }

    public AddBaseGuidePage clickSaveButton() {
        SAVE_BUTTON.should(appear, enabled, visible)
                   .click();
        return this;
    }

    public TaxonomyResultPage clickStatusMessage() {
        log.info("To verify Base guide created status message is displayed!");
        assertTrue(STATUS_MESSAGE.exists(), "Base guide not created,Status message not displayed");
        STATUS_MESSAGE.should(exist, appear, enabled)
                      .click();
        return new TaxonomyResultPage();
    }
}
