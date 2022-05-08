package www.ui.components;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;
import org.openqa.selenium.interactions.Actions;
import www.models.NavigationDataModel;
import www.ui.pages.MainLoginPage;
import www.ui.pages.SearchResultsPage;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertTrue;

/**
 * The main www.military.com navigation fragment.
 *
 * @author fkrivsky
 */
@Log4j2
public class GlobalHeaderComponent extends MasterPage {

    private static final SelenideElement LOGO = $("img.logo").as("Top Left Military.com logo");

    private static final SelenideElement GLOBAL_NAVIGATION_BLOCK = $("nav.block.block--system-menu-block-main").as("Global Navigation Menu");

    private static final SelenideElement SEARCH_TEXT_INPUT = $("input#edit-keyword--2").as("Search text input");
    private static final SelenideElement SHOW_SEARCH_BUTTON = $("div.block.block--global-search-toggle").as("Show Search button");
    private static final SelenideElement EXECUTE_SEARCH_BUTTON = $("button.button.button--icon.button--icon--search").as("Execute Search button");

    private static final SelenideElement LOGIN_BUTTON = $("#user-menu-login-link").as("Login button");

    public GlobalHeaderComponent() {
        LOGO.should(exist);
    }

    public MainLoginPage clickLoginButton() {
        LOGIN_BUTTON.click();
        LOGIN_BUTTON.should(disappear.because("Going to Login page"));
        return new MainLoginPage();
    }

    /**
     * Execute search for specified search term.
     *
     * @param searchTerm search term
     * @return new {@link SearchResultsPage()};
     */
    public SearchResultsPage searchFor(String searchTerm) {
        SHOW_SEARCH_BUTTON.click();
        SEARCH_TEXT_INPUT.should(appear)
                         .setValue(searchTerm);
        EXECUTE_SEARCH_BUTTON.click();
        log.info("Executing search for '{}'...", searchTerm);
        EXECUTE_SEARCH_BUTTON.should(disappear);
        return new SearchResultsPage();
    }

    public <P extends MasterPage> P navigateTo(NavigationDataModel<P> navigation) {

        log.info("Main Menu is: " + navigation.getMainMenuItem()
                                              .getMainMenuName());
        if (null == navigation.getSubMenuItem()) {
            log.info("Clicking Main menu link as Submenu is null");
            navigation.getMainMenuItem()
                      .getMainMenuLink()
                      .should(visible, enabled)
                      .click();
            return returnInstanceOf(navigation.getExpectedClass());
        }

        log.info("Clicking on the Submenu: " + navigation.getSubMenuItem()
                                                         .getSubMenuLink());
        List<?> subMenuListValues = navigation.getMainMenuItem()
                                              .getSubEnumValues();

        if (!subMenuListValues.contains(navigation.getSubMenuItem())) {
            log.info(navigation.getSubMenuItem()
                               .getSubMenuLink() + " is not a Sub Menu of " + navigation.getMainMenuItem()
                                                                                        .getMainMenuName());
            log.error("Navigation can't be applied");
            log.info("Navigation can't be applied");
            return null;
        }

        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(navigation.getMainMenuItem()
                                        .getMainMenuLink()
                                        .should(visible, enabled))
               .build()
               .perform();
        assertTrue(navigation.getMainMenuItem()
                             .getMainMenuLink()
                             .isDisplayed(), navigation.getMainMenuItem()
                                                       .name() + " Menu options NOT displayed");
        actions.moveToElement(navigation.getSubMenuItem()
                                        .getSubMenuLink()
                                        .should(visible, enabled))
               .click()
               .build()
               .perform();
        return returnInstanceOf(navigation.getExpectedClass());

    }

}
