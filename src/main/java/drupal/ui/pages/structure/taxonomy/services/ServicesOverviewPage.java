package drupal.ui.pages.structure.taxonomy.services;

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
public class ServicesOverviewPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/services/overview";
    private static final SelenideElement SERVICES_H_1_TAG = $("#block-pagetitle h1").as("Services Page H1 Tag");
    private static final SelenideElement ADD_TERM_BUTTON = $("#block-primaryadminactions ul a").as("Add term button");


    public ServicesOverviewPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Services Page isn't loaded.");
        assertTrue(SERVICES_H_1_TAG.should(exist, appear)
                                   .has(text("Services")), "Service title: " + SERVICES_H_1_TAG.getText() + "not displayed,should contain('Services')");
    }

    public AddServicePage clickAddTermButton() {
        assertTrue(ADD_TERM_BUTTON.should(exist, appear)
                                  .exists(), "Add term button is not displayed.");
        log.info("Add term button is displayed");
        ADD_TERM_BUTTON.should(visible, enabled)
                       .click();
        return new AddServicePage();
    }
}
