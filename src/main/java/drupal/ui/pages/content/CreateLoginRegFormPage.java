package drupal.ui.pages.content;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;
import static org.testng.Assert.assertTrue;

@Log4j2
public class CreateLoginRegFormPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/admin/content/forms/new";
    protected static final SelenideElement LOGIN_REG_FORMS_TITLE = $("#block-pagetitle h1");
    protected static final SelenideElement ADMINISTRATIVE_TITLE_INPUT = $("input#edit-name");
    protected static final SelenideElement IRSC_INPUT = $("input#edit-isrc");
    protected static final SelenideElement REDIRECT_URL_INPUT = $("input#edit-redirect-url");
    protected static final SelenideElement BUTTON_TEXT_INPUT = $("input#edit-button");
    protected static final SelenideElement CREATE_NEW_FORM_BUTTON = $("input#edit-submit");
    protected static final ElementsCollection DRAGGABLE_WEB_ELEMENTS_LIST = $$x("//*[@id='edit-fields']/tbody/tr//a");
    protected static final ElementsCollection FIELD_NAMES_LIST = $$x("//*[@id='edit-fields']/tbody/tr/td[1]");

    public CreateLoginRegFormPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Content page not loaded.");
        assertTrue(LOGIN_REG_FORMS_TITLE.should(exist, appear)
                                        .has(text("New Login/Reg form")), "Login/Reg Forms page title not displayed.");
        log.info("Login/Reg Forms admin page loaded properly.");
    }

    public CreateLoginRegFormPage fillNewLoginRegForm(List<ISectionDataModel> sectionModel) {
        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(CreateLoginRegFormPage.class);
        }
        return this;
    }

    public CreateLoginRegFormPage dragDropComponent(LoginRegFormFieldListModel loginRegFormModel) {
        List<LoginRegFormFieldListModel.LoginRegFormField> loginRegFormFields = new ArrayList<>(loginRegFormModel.getLoginFieldsList());
        Collections.reverse(loginRegFormFields);
        for (LoginRegFormFieldListModel.LoginRegFormField field : loginRegFormFields) {
            int fieldCurrentIndex = FIELD_NAMES_LIST.stream()
                                                    .map(e -> e.getText()
                                                               .trim())
                                                    .collect(Collectors.toList())
                                                    .indexOf(field.getFieldName());
            new Actions(getDriver()).moveToElement(DRAGGABLE_WEB_ELEMENTS_LIST.get(fieldCurrentIndex))
                                    .clickAndHold()
                                    .moveToElement(DRAGGABLE_WEB_ELEMENTS_LIST.first())
                                    .release()
                                    .build()
                                    .perform();
        }
        return this;
    }

    public LoginRegFormsPage createNewFormButton() {
        CREATE_NEW_FORM_BUTTON.should(exist, appear, enabled)
                              .click();
        return new LoginRegFormsPage();
    }
}