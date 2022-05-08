package drupal.ui.pages.structure.taxonomy.categories;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EditCategoryPage extends AdministrationToolbar {

    private static final String URL_REGEX = Configuration.baseUrl;
    private static final SelenideElement NAME_INPUT = $("#edit-title-0-value").as("Title input field");
    private static final SelenideElement SAVE_BUTTON = $("#edit-submit").as("save button");

    public EditCategoryPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Edit Taxonomy Page not loaded.");
        log.info("Edit Taxonomy Page loaded properly.");
    }

    public CategoriesTermLandingPage editCategory() {
        assertFalse(NAME_INPUT.getAttribute("value")
                              .isEmpty(), "Title field is empty");
        SAVE_BUTTON.should(appear, enabled, visible)
                   .click();
        log.info("Category term edited successfully.");

        return new CategoriesTermLandingPage();
    }

}
