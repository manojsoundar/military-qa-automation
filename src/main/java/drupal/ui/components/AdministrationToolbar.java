package drupal.ui.components;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import drupal.enums.IEnumItemMenuNavigation;
import drupal.ui.pages.DrupalLandingPage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static mgs.qa.utils.LoopUtils.doWhileConditionNotMet;

@Log4j2
public class AdministrationToolbar extends MasterPage {

    private static final SelenideElement ADMIN_TOOLBAR_WRAPPER = $("#toolbar-administration");
    private static final SelenideElement TOOLBAR = ADMIN_TOOLBAR_WRAPPER.$("#toolbar-bar");
    private static final SelenideElement TOOLBAR_TAB = $("#class=\"toolbar-tab\"").as("Toolbar tab");
    private static final SelenideElement TOGGLE_TRAY_BUTTON = TOOLBAR_TAB.$("a#toolbar-item-administration")
                                                                         .as("Toggle Tray Button");
    private static final SelenideElement ADMIN_TRAY = TOOLBAR_TAB.$("#toolbar-item-administration-tray");

    private static final SelenideElement TOGGLE_TRAY_DISPLAY_BUTTON = ADMIN_TRAY.$$("div.toolbar-lining>button")
                                                                                .get(0)
                                                                                .as("Toggle Tray Vertical/Horizontal Button");
    private static final SelenideElement DRUPAL_MENU_CONTENT_LINK = $("a.toolbar-icon-system-admin-content");

    private static final SelenideElement DRUPAL_MENU_NEWS_CRED_LINK = $("a.toolbar-icon-mil-newscred-newscred");
    private static final SelenideElement DRUPAL_MENU_NEWS_CRED_ADMIN_LINK = $("a.toolbar-icon-mil-newscred-newscred-admin");
    private static final SelenideElement TOOLS_MENU = $("div a.toolbar-icon-admin-toolbar-tools-help").as("Tools Menu");
    private static final SelenideElement LOGOUT_LINK = $(".menu-item a[href*='/logout']").as("Logout Link");
    private static final SelenideElement LOGIN_LINK = $("#user-menu-login-link").as("Login Link");

    public AdministrationToolbar() {
        //TOOLBAR.shouldBe(Condition.visible.because("Admin toolbar should be displayed on all drupal.ui.pages."), Duration.ofSeconds(60));
    }

    public void hoverTrayItem(TrayItem item) {
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(item.getSelenideElement())
               .build()
               .perform();
    }

    public <P extends MasterPage> P navigateTo(List<IEnumItemMenuNavigation> navigationItems, Class<P> expectedClass) {

        SelenideElement lastItemMenu = null; //TODO handle if no given navigation
        for (int i = 0; i < navigationItems.size(); i++) {
            final int finalI = i;
            doWhileConditionNotMet(
                    //TODO ideal condition to meet, but duration lasts 20-30sec
//                    () -> navigationItems.get(finalI)
//                                         .getSelenideElement()
//                                         .has(attributeMatching("class", ".*hover-intent.*")),
                    () -> navigationItems.get(finalI)
                                         .getSelenideElement()
                                         .isDisplayed(),
                    () -> {

                        try {
                            log.debug("hover on " + navigationItems.get(finalI)
                                                                   .getSelenideElement());
                            Actions actions = new Actions(getDriver());
                            actions.moveToElement(navigationItems.get(finalI)
                                                                 .getSelenideElement())
                                   .build()
                                   .perform();

                        } catch (ElementNotInteractableException | UIAssertionError e) {
                            log.error("hover generate exception, then calling javascript executor");

                            String strJavaScript = "var element = arguments[0]; var attributeValue = element.getAttribute(\"class\"); element.setAttribute(\"class\", attributeValue + \" hover-indent\"); element.removeAttribute(\"displayed:false\"); element.removeAttribute(\"displayed:false, 1\");";
                            ((JavascriptExecutor) getDriver()).executeScript(strJavaScript, navigationItems.get(finalI)
                                                                                                           .getSelenideElement()
                                                                                                           .getWrappedElement()
                            );
                        }
                    },
                    5,
                    1
            );

            //TODO ideal scenario, but duration lasts 20-30sec
//            if (i + 1 < navigationItems.size()) {
//                navigationItems.get(i + 1)
//                               .getSelenideElement()
//                               .should(exist);
//            } else {
            lastItemMenu = navigationItems.get(i)
                                          .getSelenideElement()
                                          .hover();
//            }

        }
        lastItemMenu.click();

        return returnInstanceOf(expectedClass);
    }

    public DrupalLandingPage drupalLogout() throws UIAssertionError, Exception {

        try {
            log.info("Process Logout from Military Drupal");
            if (!doWhileConditionNotMet(
                    () -> TOOLBAR.is(visible.because("Admin toolbar should be displayed on all drupal.ui.pages.")),
                    Selenide::refresh,
                    5,
                    30
            )
            ) {
                throw new Exception("The administration tool bar is not displayed");
            }
            return navigateTo(List.of(TrayItem.TOOLS, ToolsItem.LOGOUT), DrupalLandingPage.class);

        } catch (UIAssertionError e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }

    }

    @AllArgsConstructor
    public enum TrayItem implements IEnumItemMenuNavigation {

        TOOLS("Tools", $("li a[href='/'][data-drupal-link-system-path='<front>']")),
        CONTENT("Content", $("li a[href='/admin/content']")),
        STRUCTURE("Structure", $("li a[href='/admin/structure']")),
        CONFIGURATION("Configuration", $("li a[href='/admin/config']"));

        @Getter
        private final String text;
        private final SelenideElement element;

        @Override
        public SelenideElement getSelenideElement() {
            return element;
        }
    }

    @AllArgsConstructor
    public enum ToolsItem implements IEnumItemMenuNavigation {
        LOGOUT("logout", $("a[href='/user/logout']"));

        private final String text;
        private final SelenideElement selenideElement;

        @Override
        public SelenideElement getSelenideElement() {
            return selenideElement;
        }
    }

}
