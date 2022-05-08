package drupal.ui.pages.add_content.base_installation;

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
public class BaseInstallationPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/base-guide/";
    private static final SelenideElement BASE_INSTALLATION_H_1_TAG = $("div.block--page--title span").as("BaseInstallation Page H1 Tag");
    private static final SelenideElement EDIT_TAB = $("ul.menu.menu--tabs a[href$='/edit']").as("Edit tab");
    private static final SelenideElement UPDATED_STATUS_MESSAGE_WEB_ELEMENT = $("div.messages.messages--status a").as("Status Message");

    public BaseInstallationPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "BaseInstallation Page not loaded.");
        log.info("BaseInstallation Page loaded properly.");
    }

    public BaseInstallationPage verifyCreatedBaseInstallation(BaseInstallationModel baseInstallationData) {
        assertTrue(BASE_INSTALLATION_H_1_TAG.should(exist, appear)
                                            .has(text(baseInstallationData.getTitle())), "Correct H1 Title message not displayed.");

        return this;
    }

    public EditBaseInstallationPage clickEditTab() {
        EDIT_TAB.should(exist, appear, enabled)
                .click();
        return new EditBaseInstallationPage();
    }

    public BaseInstallationPage verifyUpdatedStatusConfirmation(String updatedTitle) {
        assertTrue(UPDATED_STATUS_MESSAGE_WEB_ELEMENT.should(appear, visible)
                                                     .has(text(updatedTitle)), "BaseInstallation title has been updated");
        return this;
    }
}
