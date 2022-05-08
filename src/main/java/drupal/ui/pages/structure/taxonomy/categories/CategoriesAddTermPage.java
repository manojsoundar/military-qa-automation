package drupal.ui.pages.structure.taxonomy.categories;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.components.AttachAnImage;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class CategoriesAddTermPage extends AdministrationToolbar implements AttachAnImage {

    protected static final SelenideElement NAME_INPUT = $("input#edit-name-0-value");
    protected static final SelenideElement DISPLAY_LABEL_INPUT = $("#edit-field-display-label-0-value");
    protected static final SelenideElement DESCRIPTION_FRAME = $("div#cke_1_contents iframe.cke_reset");
    protected static final SelenideElement DESCRIPTION_TEXT_AREA = $("body.cke_editable p");
    protected static final SelenideElement ATTACH_AN_IMAGE_BUTTON = $("#edit-field-image-entity-browser-entity-browser-open-modal");
    protected static final SelenideElement PLEASE_WAIT_SPINNER_WEB_ELEMENT = $("div.ajax-progress.ajax-progress-throbber");
    protected static final SelenideElement URL_ALIAS_INPUT = $("#edit-path-0-alias");
    protected static final SelenideElement ADD_ANOTHER_ITEM_BUTTON = $("input.field-add-more-submit");
    protected static final ElementsCollection TRACKING_PIXEL_URL_LIST = $$("input.form-url");
    protected static final SelenideElement LINK_URL_INPUT = $("input#edit-field-link-0-uri");
    protected static final SelenideElement SAVE_BUTTON = $("input#edit-submit");
    protected static final SelenideElement STATUS_MESSAGE = $("div.messages--status");
    protected static final SelenideElement STATUS_MESSAGE_TERM_LINK = STATUS_MESSAGE.$(".placeholder");
    protected static final SelenideElement RELATIONS_LINK = $("#edit-relations span");
    protected static final SelenideElement RELATIONS_SECTION = $("#edit-relations summary");
    protected static final SelenideElement PARENT_TERMS_SELECT_MENU = $("select#edit-parent");
    private static final String URL_REGEX = "/categories/add";
    private static final SelenideElement ADD_TERM_H_1_TAG = $("#block-pagetitle h1");

    public CategoriesAddTermPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Categories-Add term Page not loaded.");
        assertTrue(ADD_TERM_H_1_TAG.should(exist, appear)
                                   .has(text("Add term")), "Categories Add Term title: " + ADD_TERM_H_1_TAG.getText() + "not displayed,Should contain('Add term')");
        log.info("Categories-Add term Page loaded properly.");
    }

    public CategoriesAddTermPage addNewTerm(List<ISectionDataModel> sectionModel) {
        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(CategoriesAddTermPage.class);
        }
        return this;
    }

    public CategoriesAddTermPage clickSaveButton() {
        SAVE_BUTTON.should(appear, exist, enabled)
                   .scrollTo()
                   .click();
        return this;
    }

    public CategoriesAddTermPage verifyStatusMessage(CategoriesTitleModel taxonomyCategoriesData) {

        assertTrue(STATUS_MESSAGE.isDisplayed(), "Add Term Status message not displayed");
        assertTrue(STATUS_MESSAGE_TERM_LINK.getText()
                                           .contains(taxonomyCategoriesData.getName()));

        return this;
    }


    public CategoriesTermLandingPage navigateToNewTermCreated(CategoriesTitleModel taxonomyCategoriesData) {

        STATUS_MESSAGE_TERM_LINK.should(exist, appear, enabled)
                                .shouldHave(text(taxonomyCategoriesData.getName()))
                                .click();

        return new CategoriesTermLandingPage();
    }


}
