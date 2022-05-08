package www.ui.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;
import org.openqa.selenium.By;
import www.models.UserRegistrationDataModel;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class RegisterPage extends MasterPage {

    private static final String URL = "https://www.military.com/newmembers/member-reg";

    private static final SelenideElement LOGIN_LINK = $(".login-anchor").as("Login link");

    private static final SelenideElement SERVICE_DROPDOWN = $("#service");
    private static final SelenideElement STATUS_DROPDOWN = $("#status");
    private static final SelenideElement SECURITY_CLEARANCE_DROPDOWN = $("#securityclearance");
    private static final SelenideElement MILITARY_JOB_CODE_TYPEAHEAD = $("input#mos").as("MILITARY_JOB_CODE_TYPEAHEAD");

    private static final SelenideElement ZIP_CODE_INPUT = $("#zipcode");
    private static final SelenideElement EMAIL = $("#email");

    private static final SelenideElement SUBMIT_BUTTON = $(By.xpath(".//*[@type='submit']"));

    public RegisterPage() {
        assertTrue(verifyURLLoaded(URL), "Registration page not loaded.");
        LOGIN_LINK.should(exist);
        log.info("Register Page loaded properly.");
    }

    //TODO All options, handle typeahead too, handle null etc
    public RegisterPage fillInData(UserRegistrationDataModel userData) {

        SERVICE_DROPDOWN.selectOption(userData.getService()
                                              .getName());
        STATUS_DROPDOWN.selectOption(userData.getStatus());
        SECURITY_CLEARANCE_DROPDOWN.selectOption(userData.getSecurityClearance());
        ZIP_CODE_INPUT.setValue(String.valueOf(userData.getZipCode()));
        EMAIL.setValue(userData.getEmail());

        return this;
    }

    public void clickSubmit() {
        SUBMIT_BUTTON.click();
        // TODO investigate behavior, including error handling
    }


}
