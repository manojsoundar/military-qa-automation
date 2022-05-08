package drupal.ui.pages.components.widgets;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class WidgetsResultsPage extends AdministrationToolbar {

    private static final String URL_REGEX = "widget";
    private static final SelenideElement WIDGETS_PAGE_H_1_TITLE = $(".block--page--title h1").as("Widgets page title");
    private static final SelenideElement EDIT_TAB = $(".menu--tabs li a[href*='edit']");

    public WidgetsResultsPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Widget result Page not loaded.");
        assertTrue(WIDGETS_PAGE_H_1_TITLE.should(exist, appear)
                                         .has(text("Widget")), "Page title not loaded..");
        log.info("Widgets result Page loaded.");
    }

    public WidgetsResultsPage verifyDrupalWidgetCreated() {
        assertTrue(verifyURLLoaded(URL_REGEX + "/\\d{3}"), "Drupal widget isn't created");

        return this;
    }

    public EditWidgetPage clickEditTab() {
        EDIT_TAB.should(exist, appear, enabled)
                .click();

        return new EditWidgetPage();
    }

}
