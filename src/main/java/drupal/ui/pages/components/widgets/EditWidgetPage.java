package drupal.ui.pages.components.widgets;

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
import static drupal.ui.pages.components.widgets.AddWidgetContentPage.SAVE_BUTTON;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EditWidgetPage extends AdministrationToolbar {

    private static final String URL_REGEX = "edit";
    private static final SelenideElement EDIT_WIDGET_CONTENT_H_1_TAG = $("#block-pagetitle h1");


    public EditWidgetPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Edit Widget Page not loaded.");
        assertTrue(EDIT_WIDGET_CONTENT_H_1_TAG.should(exist, appear)
                                              .has(text("Edit")), "Page title not loaded..");
        log.info("Edit Widgets Page loaded.");
    }

    public EditWidgetPage editWidgetContent(List<ISectionDataModel> sectionModel) {

        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(EditWidgetPage.class);
        }

        return this;
    }

    public WidgetsResultsPage clickEditTab() {
        SAVE_BUTTON.should(appear, enabled, visible)
                   .click();

        return new WidgetsResultsPage();
    }
}
