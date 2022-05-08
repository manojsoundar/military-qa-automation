package drupal.ui.pages;

import com.codeborne.selenide.SelenideElement;
import drupal.models.UserModel;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Log4j2
public class DrupalLoginPage extends MasterPage {
    private static final String URL_REGEX = "/user/login";
    private static final SelenideElement USERNAME_INPUT = $("input#edit-name").as("Username input");
    private static final SelenideElement PASSWORD_INPUT = $("input#edit-pass").as("Password input");
    private static final SelenideElement LOGIN_BUTTON = $("input#edit-submit").as("Login button");

    public DrupalLoginPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Login page not loaded.");
        USERNAME_INPUT.should(exist, appear);
        log.info("Login Page loaded properly.");
    }

    public void militaryLoginDoNotVerify(UserModel userData) {
        USERNAME_INPUT.should(exist, visible, enabled)
                      .setValue(userData.getUsername());
        PASSWORD_INPUT.should(exist, visible, enabled)
                      .setValue(userData.getPassword());
        LOGIN_BUTTON.should(exist, visible, enabled)
                    .click();
    }

    public DrupalHomePage militaryLogin(UserModel userData) {
        assertFalse(userData.getUsername()
                            .isEmpty(), "Username is empty");
        assertFalse(userData.getPassword()
                            .isEmpty(), "Password is empty");
        militaryLoginDoNotVerify(userData);

        return new DrupalHomePage();
    }

}
