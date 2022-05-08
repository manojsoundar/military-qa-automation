package drupal.ui.pages.add_content.employer;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EmployerPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/veteran-employers/";
    private static final SelenideElement EMPLOYER_H_1_TAG = $("div.block--page--title h1").as("Employer Page H1 Tag");
    private static final SelenideElement EDIT_TAB = $("ul.menu.menu--tabs a[href*='edit']");
    private static final SelenideElement UPDATED_STATUS_MESSAGE = $(".messages--status a");
    private static final SelenideElement SIDEBAR_FEED_TITLE = $("h2.paragraph__title");


    public EmployerPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Employer Page not loaded.");
        log.info("Employer Page loaded properly.");
    }

    public EmployerPage verifyCreatedEmployer(EmployerDataModel employerModel) {
        assertTrue(EMPLOYER_H_1_TAG.should(appear, visible)
                                   .has(text(employerModel.getName())), "Correct H1 Title message not displayed.");

        return this;
    }

    public EditEmployerPage clickEditTab() {

        EDIT_TAB.should(enabled, visible)
                .click();
        return new EditEmployerPage();
    }

    public EmployerPage verifyUpdatedMessage(String updatedTitle) {
        assertTrue(UPDATED_STATUS_MESSAGE.should(appear, visible)
                                         .has(text(updatedTitle)), "Employer title has not been updated");
        return this;
    }

    public EmployerPage verifySidebarForEmployer(String sidebarTitle) {

        assertTrue(SIDEBAR_FEED_TITLE.should(appear, visible)
                                     .has(text(sidebarTitle)), "Correct Sidebar feed title is not displayed in employer result page");
        return this;
    }
}
