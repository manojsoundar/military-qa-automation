package drupal.ui.pages.content;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static drupal.ui.pages.content.LoginRegFormFieldListModel.LoginRegFormField.EMAIL_CHECKBOX;
import static drupal.ui.pages.content.LoginRegFormFieldListModel.LoginRegFormField.FIRST_NAME_CHECKBOX;
import static drupal.ui.pages.content.LoginRegFormFieldListModel.LoginRegFormField.LAST_NAME_CHECKBOX;
import static drupal.ui.pages.content.LoginRegFormFieldListModel.LoginRegFormField.SERVICE_CHECKBOX;
import static drupal.ui.pages.content.LoginRegFormFieldListModel.LoginRegFormField.STATUS_CHECKBOX;
import static drupal.ui.pages.content.LoginRegFormFieldListModel.LoginRegFormField.ZIP_CODE_CHECKBOX;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class LoginRegFormFieldListModel extends MasterPage implements ISectionDataModel {

    List<LoginRegFormField> loginFieldsList;

    public LoginRegFormFieldListModel() {
        loginFieldsList = List.of(EMAIL_CHECKBOX, FIRST_NAME_CHECKBOX, LAST_NAME_CHECKBOX, SERVICE_CHECKBOX, STATUS_CHECKBOX, ZIP_CODE_CHECKBOX);
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        selectFieldCheckbox(expectedClass);
        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectFieldCheckbox(Class<P> expectedClass) {
        if (loginFieldsList != null) {
            getLoginFieldsList()
                    .forEach(checkbox -> checkbox.getFieldCheckBox()
                                                 .should(exist, appear, enabled)
                                                 .setSelected(true));
        }
        return returnInstanceOf(expectedClass);
    }

    public List<String> getLoginFormFieldByNameList() {
        return loginFieldsList.stream()
                              .map(LoginRegFormField::getFieldName)
                              .collect(Collectors.toList());
    }

    @Getter
    @AllArgsConstructor
    public enum LoginRegFormField {
        EMAIL_CHECKBOX("Email", $("input#edit-fields-email-visible")),
        FIRST_NAME_CHECKBOX("First Name", $("input#edit-fields-firstname-visible")),
        LAST_NAME_CHECKBOX("Last Name", $("input#edit-fields-lastname-visible")),
        SERVICE_CHECKBOX("Service", $("input#edit-fields-service-visible")),
        STATUS_CHECKBOX("Status", $("input#edit-fields-status-visible")),
        ZIP_CODE_CHECKBOX("Zip Code", $("input#edit-fields-zipcode-visible"));

        private final String fieldName;
        private final SelenideElement fieldCheckBox;
    }

}
