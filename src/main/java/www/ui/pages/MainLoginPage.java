package www.ui.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class MainLoginPage extends MasterPage {

    private static final String URL = "https://www.military.com/newmembers/member-reg";

    private static final SelenideElement REGISTER_LINK = $(".register-anchor").as("Register link");

    public MainLoginPage() {

        assertTrue(verifyURLLoaded(URL), "Main Login page not loaded.");
        log.info("Main Login page loaded.");
    }

    public RegisterPage clickRegisterLink() {
        REGISTER_LINK.click();
        REGISTER_LINK.should(disappear);
        return new RegisterPage();
    }

}
