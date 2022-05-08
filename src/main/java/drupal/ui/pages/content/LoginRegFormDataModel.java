package drupal.ui.pages.content;

import com.codeborne.selenide.Configuration;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.ui.pages.content.CreateLoginRegFormPage.ADMINISTRATIVE_TITLE_INPUT;
import static drupal.ui.pages.content.CreateLoginRegFormPage.BUTTON_TEXT_INPUT;
import static drupal.ui.pages.content.CreateLoginRegFormPage.IRSC_INPUT;
import static drupal.ui.pages.content.CreateLoginRegFormPage.REDIRECT_URL_INPUT;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class LoginRegFormDataModel extends MasterPage implements ISectionDataModel {

    String administrativeTitle;
    String irsc;
    String redirectURL;
    String configURL;
    String buttonText;
    List<String> newsLetters;

    public LoginRegFormDataModel() {
        administrativeTitle = "Test Login Reg form " + timeStampFormat(PATTERN);
        irsc = "TEST_REG_FORM_" + timeStampFormat(PATTERN);
        redirectURL = Configuration.baseUrl + "/oas-homepage";
        configURL = null;
        buttonText = "Test Login Now!";
        newsLetters = null;
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        enterAdministrativeTitle(expectedClass);
        enterIrsc(expectedClass);
        enterRedirectUrl(expectedClass);
        enterButtonText(expectedClass);
        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P enterAdministrativeTitle(Class<P> expectedClass) {

        if (administrativeTitle != null) {
            ADMINISTRATIVE_TITLE_INPUT.should(appear, exist, enabled)
                                      .setValue(administrativeTitle);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P enterIrsc(Class<P> expectedClass) {

        if (irsc != null) {
            IRSC_INPUT.should(appear, exist, enabled)
                      .setValue(irsc);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P enterRedirectUrl(Class<P> expectedClass) {

        if (redirectURL != null) {
            REDIRECT_URL_INPUT.should(appear, exist, enabled)
                              .setValue(redirectURL);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P enterButtonText(Class<P> expectedClass) {

        if (buttonText != null) {
            BUTTON_TEXT_INPUT.should(appear, exist, enabled)
                             .setValue(buttonText);
        }
        return returnInstanceOf(expectedClass);
    }

}
