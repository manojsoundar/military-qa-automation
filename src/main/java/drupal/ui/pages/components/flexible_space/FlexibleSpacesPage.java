package drupal.ui.pages.components.flexible_space;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class FlexibleSpacesPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/admin/content/flexible_space";
    private static final SelenideElement FLEXIBLE_SPACES_H_1_TAG = $("#block-pagetitle h1.page-title").as("Flexible Spaces Page H1 Tag");
    private static final SelenideElement ADD_FLEXIBLE_SPACE_LINK = $("a[href*='/add/flexible_space']").as("Add Flexible Space Link");
    private static final SelenideElement TITLE_INPUT = $("#edit-title");
    private static final SelenideElement FILTER_BUTTON = $("#edit-submit-eck-flexible-space");
    private static final ElementsCollection FLEXIBLE_CONTENT_SPACE_LIST = $$("td.views-field.views-field-title a").as("Content List");


    public FlexibleSpacesPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Flexible Spaces Page not loaded..");
        assertEquals(FLEXIBLE_SPACES_H_1_TAG.getText()
                                            .trim(), "Flexible Spaces", "Flexible Spaces page title not loaded..");
        log.info("Flexible Spaces Page loaded");
    }

    public AddFlexibleSpaceContentPage clickFlexibleSpaceTab() {

        ADD_FLEXIBLE_SPACE_LINK.should(enabled, visible)
                               .click();
        log.info("Flexible Space Tab clicked..");

        return new AddFlexibleSpaceContentPage();
    }

    public FlexibleSpacesPage searchByTitleAndFilter(String title) {

        if (title != null) {
            TITLE_INPUT.should(exist, appear, enabled)
                       .setValue(title);
        }
        FILTER_BUTTON.should(exist, appear, enabled)
                     .click();

        return this;
    }

    public FlexibleSpaceLandingPage clickFlexibleContentSpaceLink(String titleName) {
        FLEXIBLE_CONTENT_SPACE_LIST.find(text(titleName))
                                   .should(appear, exist)
                                   .click();
        log.info("Clicked on Content for the given title");

        return new FlexibleSpaceLandingPage();
    }

    public FlexibleSpacesPage verifyFlexibleContentSpaceLinkAppeared(String blockTitle) {
        assertEquals(FLEXIBLE_CONTENT_SPACE_LIST.first().should(exist, visible)
                                .getText(), blockTitle, "Add Block title not matching " + blockTitle);
        log.info("Added Block verified on Flexible Content Space");

        return this;
    }
}
