package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.ContentMenu;
import drupal.models.ContentSearchModel;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.registration_gate.EditRegistrationGatePage;
import drupal.ui.pages.add_content.registration_gate.RegistrationFormTypeModel;
import drupal.ui.pages.content.ContentPage;
import drupal.ui.pages.content.CreateLoginRegFormPage;
import drupal.ui.pages.content.LoginRegFormDataModel;
import drupal.ui.pages.content.LoginRegFormFieldListModel;
import drupal.ui.pages.content.LoginRegFormsPage;
import drupal.ui.pages.content.NewMembersBuddyFinderPage;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static drupal.models.UserModel.loadUserModel;
import static drupal.ui.pages.content.ContentPage.PrimaryTabItem;
import static java.lang.String.format;

public class TC249800_Create_A_Login_Reg_Form_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateALoginRegForm() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "testData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249800")})
    public void createALoginRegForm(String testCase, List<ISectionDataModel> loginRegFormData, ContentSearchModel loginRegFormSearch) {

        logStep(format("Running test case : %s", testCase));
        logStep("1. Login to Drupal");
        logStep("2. Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("3. Click on Content Tab");
        ContentPage contentPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.CONTENT), ContentPage.class);

        logStep("Login/Reg Forms");
        LoginRegFormsPage loginRegFormsPage = contentPage.clickOnPrimaryTab(PrimaryTabItem.LOGIN_REG_FORMS, LoginRegFormsPage.class);

        logStep("Click the Add new form radio button");

        logStep("4. Click the Add new form radio button");
        CreateLoginRegFormPage createLoginRegFormPage = loginRegFormsPage.clickOnAddNewFormButton();
        LoginRegFormDataModel loginRegFormDataModel = loginRegFormData.stream()
                                                                      .filter(d -> d instanceof LoginRegFormDataModel)
                                                                      .map(c -> (LoginRegFormDataModel) c)
                                                                      .findFirst()
                                                                      .get();
        LoginRegFormFieldListModel loginRegFormModel = loginRegFormData.stream()
                                                                       .filter(d -> d instanceof LoginRegFormFieldListModel)
                                                                       .map(c -> (LoginRegFormFieldListModel) c)
                                                                       .findFirst()
                                                                       .get();
        logStep("5. Type Test content in the Administrative title field");
        logStep("Test Content Test Login Reg Form YYYYMMDD Test Login Reg form 20210712");
        logStep("6. Enter Test content in the ISRC field");
        logStep("Test Content TEST_REG_FORM_YYYYMMDD");
        logStep(format("7. Enter Test URL in Redirect URL field Test URL %s/oas-homepage", Configuration.baseUrl));
        logStep("8. Enter Test content in the Button Text field -> Test Content Test Login Now!");
        logStep("9. Select the following options under the Visible column Service, Status, Zip Code, First Name, Last Name, Email");
        logStep("10. Select the following options under the Visible column Email, First Name, Last Name, Service, Status, Zip Code");
        logStep("11. Click the Create New Form radio button to save the form.");
        loginRegFormsPage = createLoginRegFormPage.fillNewLoginRegForm(loginRegFormData)
                                                  .dragDropComponent(loginRegFormModel)
                                                  .createNewFormButton();

        logStep(format("Drupal does NOT generate a url for the form. However, the reg form should display at the top of the Reg Form list " +
                               "(the most recently created is at the top of the list) %s/admin/content/forms", Configuration.baseUrl));
        loginRegFormsPage.verifyCreatedLoginRegForm(loginRegFormDataModel);

        logStep("12. Click on the Content Tab");
        logStep("13. In the Search fields, please add/select the following and click Filter: Title - Buddy Finder, " +
                        "Content Type - Registration Gate, Published Status - Published");
        logStep("14. Click EDIT in the Operations Column for the Buddy Finder gate");
        EditRegistrationGatePage editRegistrationGatePage = loginRegFormsPage.clickOnContentTab()
                                                                             .fillSearchCriteriaAndFilter(loginRegFormSearch)
                                                                             .clickEditButton(EditRegistrationGatePage.class, 0, null);


        logStep("15. Update the Registration Form field with form just created. Test Form Test Login Reg Form 20210708");
        logStep("16. Click SAVE");
        editRegistrationGatePage.editRegistrationForm(List.of(new RegistrationFormTypeModel(loginRegFormDataModel.getAdministrativeTitle())))
                                .clickSaveButton(ContentPage.class)
                                .verifyUpdatedMessage();
        logStep(format("Go to the Buddy Finder gate %s/newmembers/buddy-finder and confirm the fields in Step 10 display on the gate.", Configuration.baseUrl));
        open(Configuration.baseUrl + "/newmembers/buddy-finder", NewMembersBuddyFinderPage.class).verifyNewMemberBuddyFinder(loginRegFormModel);
    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
                {
                        "249800",
                        List.of(
                                new LoginRegFormDataModel(),
                                new LoginRegFormFieldListModel()
                        ),
                        ContentSearchModel.loginRegFormSearchData()
                }
        };
    }

}
