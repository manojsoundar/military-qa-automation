package drupal.ui.pages.components.widgets;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddWidgetContentPage extends AdministrationToolbar {

    protected static final SelenideElement TITLE_INPUT = $("#edit-title-0-value").as("Add widget content title text field");
    protected static final SelenideElement HTML_SNIPPET_INPUT = $("#edit-field-html-snippet-0-value").as("Add widget content page HTML snippet field");
    protected static final SelenideElement CSS_FILES_BUTTON = $("input#edit-field-css-files-0-upload").as("Add widget content page CSS files upload field");
    protected static final ElementsCollection CSS_FILES_INPUT = $$("input.form-url[id^=edit-field-external-css-files-]").as("Add widget content page external CSS files url field");
    protected static final SelenideElement CSS_FILES_ADD_ANOTHER_ITEM_BUTTON = $("input[id^='edit-field-external-css-files-add-more']").as("Add widget content page external CSS files add another item button");
    protected static final SelenideElement JS_FILES_BUTTON = $("input#edit-field-js-files-0-upload").as("Add widget content page JS files upload field");
    protected static final ElementsCollection JS_FILES_INPUT = $$("input.form-url[id^=edit-field-external-js-files-]").as("Add widget content page external JS files url field");
    protected static final SelenideElement JS_FILES_ADD_ANOTHER_ITEM_BUTTON = $("input[id^='edit-field-external-js-files-add-more']").as("Add widget content page external JS files add another item button");
    protected static final SelenideElement SAVE_BUTTON = $("#edit-submit").as("Add widget content page save button");
    protected static final String CSS_FILE_FORMAT_LIST = "css";
    protected static final String JS_FILE_FORMAT_LIST = "js";
    private static final String URL_REGEX = "/widget/add/widget";
    private static final SelenideElement ADD_WIDGET_CONTENT_H_1_TAG = $("#block-pagetitle h1").as("Add widget content header title");
    private static final SelenideElement USED_IN_LINK = $("#edit-entity-backreferences span").as("Add widget content page used in link");


    public AddWidgetContentPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Add Widget content Page not loaded.");
        assertEquals(ADD_WIDGET_CONTENT_H_1_TAG.should(exist, appear)
                                               .getText(), "Add Widget content", "Correct page is not displayed.");
        log.info("Add Widget content Page loaded properly.");
    }

    public AddWidgetContentPage addWidgetContent(List<ISectionDataModel> sectionModel) {

        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(AddWidgetContentPage.class);
        }

        return this;
    }

    public WidgetsResultsPage clickSaveButton() {
        SAVE_BUTTON.should(appear, enabled, visible)
                   .click();

        return new WidgetsResultsPage();
    }

}
