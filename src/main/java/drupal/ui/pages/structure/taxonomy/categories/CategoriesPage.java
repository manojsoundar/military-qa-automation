package drupal.ui.pages.structure.taxonomy.categories;

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
public class CategoriesPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/categories/overview";
    private static final SelenideElement CATEGORIES_H_1_TAG = $("#block-pagetitle h1");
    private static final SelenideElement ADD_TERM_BUTTON = $("a[href*='/categories/add']");

    public CategoriesPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Taxonomy-Categories Page not loaded.");
        assertTrue(CATEGORIES_H_1_TAG.should(exist, appear)
                                     .has(text("Categories")), "Taxonomy Categories title: " + CATEGORIES_H_1_TAG.getText() + "not displayed,should contain('Categories')");
        log.info("Taxonomy-Categories Page loaded properly.");
    }

    public CategoriesAddTermPage clickAddTerm() {
        assertTrue(ADD_TERM_BUTTON.should(exist, appear)
                                  .exists(), " Add term button is not displayed.");
        ADD_TERM_BUTTON.should(appear, exist, enabled)
                       .click();
        return new CategoriesAddTermPage();
    }
}
