package drupal.ui.flows;

import drupal.models.UserModel;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import static org.testng.Assert.assertFalse;

@Log4j2
public class LoginFlows extends MasterPage {

    public static DrupalHomePage militaryLogin(UserModel userData) {
        assertFalse(userData.getUsername()
                            .isEmpty(), "Username is empty");
        assertFalse(userData.getPassword()
                            .isEmpty(), "Password is empty");
        new DrupalLoginPage().militaryLoginDoNotVerify(userData);
        log.info("Username: " + userData.getUsername() + " / Password: " + userData.getPassword());
        return new DrupalHomePage();
    }

}
