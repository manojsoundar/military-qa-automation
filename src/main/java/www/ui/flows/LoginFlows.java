package www.ui.flows;

import lombok.extern.log4j.Log4j2;
import www.models.UserRegistrationDataModel;
import www.ui.components.GlobalHeaderComponent;
import www.ui.pages.LandingPage;
import www.ui.pages.LoginPage;

import static org.testng.Assert.assertTrue;
import static www.models.UserRegistrationDataModel.random;

/**
 * Flows related to login and user registration functionality.
 */
@Log4j2
public class LoginFlows {

    /**
     * @param user UserRegistrationDataModel instance to provide
     * @return {@link LandingPage} instance
     */
    public static LandingPage registerUser(UserRegistrationDataModel user) {
        new GlobalHeaderComponent().clickLoginButton()
                                   .clickRegisterLink()
                                   .fillInData(user)
                                   .clickSubmit();
        return new LandingPage();
    }

    public static LandingPage registerRandomUser() {
        return registerUser(random());
    }

    /**
     * @param username the user login
     * @param password the user's password
     * @return {@link LandingPage} instance
     */
    public static LandingPage militaryLogin(String username, String password) {
        assertTrue(username.isEmpty(), "Username is empty");
        assertTrue(password.isEmpty(), "Password is empty");
        new LoginPage().militaryLoginDoNotVerify(username, password);
        log.info("Username: " + username + " / Password: " + password);
        return new LandingPage();
    }

}