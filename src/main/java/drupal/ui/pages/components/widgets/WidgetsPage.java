package drupal.ui.pages.components.widgets;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class WidgetsPage extends AdministrationToolbar {

    private static final String URL_REGEX = "widget";
    private static final SelenideElement WIDGETS_PAGE_H_1_TITLE = $("#block-pagetitle h1").as("Widgets page title");
    private static final SelenideElement ADD_WIDGET_BUTTON = $(".button.button-action.button--primary.button--small").as("Add widget button");
    private static final SelenideElement WIDGET_TITLE_INPUT = $("#edit-title").as("Widget title text field");
    private static final SelenideElement FILTER_BUTTON = $("#edit-submit-eck-widgets").as("Filter button");

    public WidgetsPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Widgets Page not loaded.");
        assertTrue(WIDGETS_PAGE_H_1_TITLE.should(exist, appear)
                                         .has(text("Widgets")), "Page title not loaded..");
        log.info("Widgets Page loaded.");
    }

    public AddWidgetContentPage clickAddWidgetButton() {

        ADD_WIDGET_BUTTON.should(visible, enabled)
                         .click();
        return new AddWidgetContentPage();
    }

}
