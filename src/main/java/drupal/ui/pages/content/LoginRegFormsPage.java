package drupal.ui.pages.content;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.assertTrue;

@Log4j2
public class LoginRegFormsPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/admin/content/forms";
    private static final SelenideElement LOGIN_REG_FORMS_TITLE = $("#block-pagetitle h1");
    private static final SelenideElement ADD_NEW_FORM_BUTTON = $(".action-links a[href='/admin/content/forms/new']");
    private static final ElementsCollection TABLE_FIRST_ROW_LIST = $$x("//*[@id='block-mainpagecontent']/div//tbody/tr[1]/td");
    private static final SelenideElement CONTENT_TAB = $x("//li[@class='tabs__tab']/a[@href='/admin/content']/..");

    public LoginRegFormsPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Content page not loaded.");
        assertTrue(LOGIN_REG_FORMS_TITLE.should(exist, appear)
                                        .has(text("Login/Reg Forms")), "Login/Reg Forms page title not displayed.");
        log.info("Login/Reg Forms admin page loaded properly.");
    }

    public CreateLoginRegFormPage clickOnAddNewFormButton() {
        ADD_NEW_FORM_BUTTON.should(exist, appear, enabled)
                           .doubleClick();
        return new CreateLoginRegFormPage();
    }

    public LoginRegFormsPage verifyCreatedLoginRegForm(LoginRegFormDataModel loginRegModel) {
        assertTrue(TABLE_FIRST_ROW_LIST.stream()
                                       .anyMatch(ele -> ele
                                               .getText()
                                               .contains(loginRegModel.getAdministrativeTitle())));
        assertTrue(TABLE_FIRST_ROW_LIST.stream()
                                       .anyMatch(ele -> ele
                                               .getText()
                                               .contains(loginRegModel.getIrsc())));
        assertTrue(TABLE_FIRST_ROW_LIST.stream()
                                       .anyMatch(ele -> ele
                                               .getText()
                                               .contains(loginRegModel.getRedirectURL())));
        return this;
    }

    public ContentPage clickOnContentTab() {
        CONTENT_TAB.should(visible, appear)
                   .click();
        return new ContentPage();
    }

}