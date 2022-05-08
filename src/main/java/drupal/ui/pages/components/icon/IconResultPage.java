package drupal.ui.pages.components.icon;

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
public class IconResultPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/icon/";
    private static final SelenideElement ICON_H_1_TAG = $(".block--page--title h1").as("Icon Result Page H1 Tag");
    private static final SelenideElement EDIT_TAB = $(".menu--tabs li a[href*='edit']");

    public IconResultPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Icon result Page not loaded.");
        assertTrue(ICON_H_1_TAG.should(exist, appear)
                               .has(text("Icon")), "Correct page is not displayed.");
        log.info("Icon result Page loaded properly.");
    }

    public IconResultPage verifyIconCreated() {

        log.info("Drupal Image link is: " + getDriver().getCurrentUrl());
        assertTrue(verifyURLLoaded(URL_REGEX + "\\d{3}"), "Icon result page isn't created");
        return this;
    }

    public EditIconPage clickEditTab() {
        EDIT_TAB.should(exist, appear, enabled)
                .click();

        return new EditIconPage();
    }
}

