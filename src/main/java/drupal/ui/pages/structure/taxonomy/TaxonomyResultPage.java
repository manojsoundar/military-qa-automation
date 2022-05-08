package drupal.ui.pages.structure.taxonomy;

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
public class TaxonomyResultPage extends AdministrationToolbar {

    public static final SelenideElement EDIT_TAB = $("div.block--tabs li a[href*='/edit']").as("Edit tab button");
    private static final String URL_REGEX = "/taxonomy/term/";
    private static final SelenideElement RESULT_H_1_TAG = $("#pagewrapper h1 div").as("Taxonomy Result Page H1 Tag");

    public TaxonomyResultPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Taxonomy Page not loaded.");
        log.info("Taxonomy result Page loaded properly.");
    }

    public TaxonomyResultPage verifyTaxonomyTermCreated(String title) {

        assertTrue(verifyURLLoaded(URL_REGEX + "\\d{5}"), "Taxonomy result  isn't created");
        assertTrue(RESULT_H_1_TAG.should(exist, appear)
                                 .has(text(title)), "Taxonomy Result title: " + RESULT_H_1_TAG.getText() + "not displayed,should contain" + title);
        log.info("Taxonomy result page created");
        return this;
    }

    public EditTaxonomyPage clickEditTab() {
        EDIT_TAB.should(exist, appear, enabled)
                .click();
        return new EditTaxonomyPage();
    }
}
