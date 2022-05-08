package drupal.ui.pages.add_content.ap_dashboard;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.testng.Assert.assertTrue;

@Log4j2
public class DeleteArticlePage extends AdministrationToolbar {

    private static final String URL_REGEX = "/delete";

    private static final SelenideElement DELETE_CONFORMATION_BUTTON = $("#edit-submit").as("Delete conformation button");

    public DeleteArticlePage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "DeleteArticlePage not loaded");
        log.info("Delete Article Page loaded");
    }

    public APDashboardPage clickDeleteButton() {

        DELETE_CONFORMATION_BUTTON.should(enabled, visible, appear)
                                  .click();
        log.info("Clicked on delete conformation button");
        closeWindow();
        switchTo().window(0);
        return new APDashboardPage();
    }

}
