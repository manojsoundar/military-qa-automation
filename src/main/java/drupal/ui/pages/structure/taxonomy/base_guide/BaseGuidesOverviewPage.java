package drupal.ui.pages.structure.taxonomy.base_guide;

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
public class BaseGuidesOverviewPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/base_guides/overview";
    private static final SelenideElement SERVICES_H_1_TAG = $("#block-pagetitle h1").as("Base guides overview Page H1 Tag");
    private static final SelenideElement ADD_TERM_BUTTON = $("#block-primaryadminactions a").as("Add term button");

    public BaseGuidesOverviewPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Base guides overview page isn't loaded.");
        assertTrue(SERVICES_H_1_TAG.should(exist, appear)
                                   .has(text("Base Guides")), "Base guides title: " + SERVICES_H_1_TAG.getText() + "not displayed,should contain('Base guides')");
    }

    public AddBaseGuidePage clickAddTermButton() {
        assertTrue(ADD_TERM_BUTTON.should(exist, appear)
                                  .exists(), "Add term button is not displayed.");
        log.info("Add term button is displayed");
        ADD_TERM_BUTTON.should(visible, enabled)
                       .click();
        return new AddBaseGuidePage();
    }
}
