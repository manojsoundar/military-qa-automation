package drupal.ui.pages.structure.eck_entity_types.source;

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
public class SourcePage extends AdministrationToolbar {

    private static final String URL_REGEX = "/source";
    private static final SelenideElement SOURCE_H_1_TAG = $(".block--page--title h1 div.field").as("Source Page H1 Tag");
    private static final SelenideElement EDIT_TAB = $(".menu--tabs a[href*='edit']");


    public SourcePage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Source Page not loaded.");
        log.info("Source Page loaded properly.");
    }

    public SourcePage verifyAddedSource(String title) {
        assertTrue(SOURCE_H_1_TAG.should(exist, appear)
                                 .has(text(title)), "Source title: " + SOURCE_H_1_TAG.getText() + "not displayed,should contain" + title);
        log.info("Source created successfully: " + title);
        return this;
    }

    public EditSourcePage clickEditTab() {
        EDIT_TAB.should(exist, appear, enabled)
                .click();
        log.info("EDIT link clicked");
        return new EditSourcePage();
    }
}
