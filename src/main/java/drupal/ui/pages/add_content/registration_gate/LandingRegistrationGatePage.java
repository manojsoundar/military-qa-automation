package drupal.ui.pages.add_content.registration_gate;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

@Log4j2
public class LandingRegistrationGatePage extends AdministrationToolbar {
    private static final String URL_REGEX = "/newmembers";
    private static final SelenideElement EDIT_TAB = $("ul.menu.menu--tabs a[href*='edit']");
    private static final SelenideElement LEFT_COLUMN_RESULT_PAGE = $(".gate__cta").as("left column cta");

    public LandingRegistrationGatePage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Landing Registration Page:" + URL_REGEX.toLowerCase() + "not displayed");
        log.info("Landing registration gate page loaded properly.");
    }

    public LandingRegistrationGatePage verifyDrupalRegistrationGateCreated(RegistrationDataModel registrationDataModel) {
        log.info(format("URL for verification loaded: %s", getDriver().getCurrentUrl()));
        assertTrue(getDriver().getCurrentUrl()
                              .contains(registrationDataModel.getName()
                                                             .toLowerCase()), "Registration Gate is not created. Page url" + getDriver().getCurrentUrl() + " should contain " + registrationDataModel.getName());
        return this;
    }

    public LandingRegistrationGatePage verifyDrupalRegistrationGateUpdated() {
        assertTrue(LEFT_COLUMN_RESULT_PAGE.should(appear, exist, visible)
                                          .getText()
                                          .contains("Updated"), "Registration Gate " + LEFT_COLUMN_RESULT_PAGE.getText() + "doesn't contain 'Updated' text");
        return this;
    }

    public EditRegistrationGatePage clickEditTab() {
        EDIT_TAB.should(exist, appear, enabled)
                .click();
        log.info("EDIT link clicked");
        return new EditRegistrationGatePage();
    }
}
