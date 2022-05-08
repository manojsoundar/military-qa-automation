package www.ui.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;
import mgs.qa.webdriver.WebDriverExtensions;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class LoginPage extends MasterPage {

    private static final String URL = "/user/login";
    private static final SelenideElement USERNAME_INPUT = $("input#edit-name").as("Username input");
    private static final SelenideElement PASSWORD_INPUT = $("input#edit-pass").as("Password input");
    private static final SelenideElement LOGIN_BUTTON = $("input#edit-submit").as("Login button");

    public LoginPage() {
        assertTrue(verifyURLLoaded(URL), "Login page not loaded.");
        USERNAME_INPUT.should(exist);
        log.info("Login Page loaded properly.");
    }

    public void militaryLoginDoNotVerify(String username, String passwd) {
        WebDriverExtensions.fillTextBox(USERNAME_INPUT, username);
        WebDriverExtensions.fillTextBox(PASSWORD_INPUT, passwd);
        WebDriverExtensions.clickElement(LOGIN_BUTTON);
    }

}