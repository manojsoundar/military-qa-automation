package military.drupal.ui.content_menu;

import com.epam.reportportal.annotations.attribute.Attributes;
import com.epam.reportportal.annotations.attribute.MultiValueAttribute;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.ContentMenu;
import drupal.models.ContentSearchModel;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.registration_gate.CreateRegistrationGatePage;
import drupal.ui.pages.add_content.registration_gate.EditRegistrationGatePage;
import drupal.ui.pages.add_content.registration_gate.LandingRegistrationGatePage;
import drupal.ui.pages.add_content.registration_gate.RegistrationDataModel;
import drupal.ui.pages.add_content.registration_gate.RegistrationFormTypeModel;
import drupal.ui.pages.add_content.registration_gate.RegistrationImageModel;
import drupal.ui.pages.add_content.registration_gate.RegistrationRightColumnModel;
import drupal.ui.pages.add_content.registration_gate.RegistrationSizeModel;
import drupal.ui.pages.content.ContentPage;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static drupal.models.UserModel.loadUserModel;
import static java.lang.String.format;

public class Create_A_Registration_Gate_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateARegistrationGate() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "setRegistrationFormData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(multiValueAttributes = {@MultiValueAttribute(key = "TestCaseIds", values = {"249683", "249687"})})
    public void createRegistrationGate(String testCaseId, List<ISectionDataModel> registrationModel) {

        logStep(format("Running Test Case : %s", testCaseId));
        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over Components");
        logStep("Click on 'Content'");
        logStep("Click on 'Add Content'");
        logStep("Select and click on 'Registration gate'");
        CreateRegistrationGatePage createRegistrationGate = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.REGISTRATION_GATE), CreateRegistrationGatePage.class);

        logStep("Enter test name in the Title field");
        logStep("Confirm 'Small' in the SIZE field");
        logStep("Click 'Attach an Image' in the Background Image field");
        logStep("Type Login Gate BG Image in the MEDIA IMAGE field and click on the Search button");
        logStep("Click on the Image and click on the Select Image button.Once the image is selected, the Image popup closes and returns the user to the original Reg Gate page with the image displayed in the Background Image field");
        logStep("Include HTML in the CTA Text field");
        logStep("Select Discounts Page Level Reg Form in the REGISTRATION FORM field");
        logStep("In the Right Column Section, click on the dropdown and select ADD FULL TEXT AREA");
        logStep("In the Full Text Area, type test content into the TITLE and TEXT fields");
        logStep("Click Save");
        RegistrationDataModel registrationDataModel = registrationModel.stream()
                                                                       .filter(d -> d instanceof RegistrationDataModel)
                                                                       .map(c -> (RegistrationDataModel) c)
                                                                       .findFirst()
                                                                       .get();
        LandingRegistrationGatePage landingRegistrationGatePage = createRegistrationGate.createRegistrationGate(registrationModel);
        logStep("Registration gate created successfully");
        logStep("Verifying Logo displayed");
        logStep("Verifying URL loaded with title given in title input");

        landingRegistrationGatePage.verifyDrupalRegistrationGateCreated(registrationDataModel);

        logStep("Edit the registration gate");
        ContentPage contentPage = landingRegistrationGatePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.CONTENT), ContentPage.class);

        EditRegistrationGatePage editRegistrationGatePage = contentPage.fillSearchCriteriaAndFilter(ContentSearchModel.editRegistrationGate())
                                                                       .clickContentLink(LandingRegistrationGatePage.class, registrationDataModel.getName())
                                                                       .clickEditTab();
        String leftColumnData = "<p>Join the Largest Online</p>\n" +
                "<h1>Military Community</h1>\n" +
                "<h3>Updated</h3>";
        logStep("Edit registration form fields");
        logStep("Verifying registration gate updated!!");
        registrationDataModel.setLeftColumnData(leftColumnData);
        editRegistrationGatePage.editRegistrationForm((List.of(registrationDataModel)))
                                .clickSaveButton(LandingRegistrationGatePage.class)
                                .verifyDrupalRegistrationGateUpdated();
    }

    @DataProvider
    public Object[][] setRegistrationFormData() {
        return new Object[][]{
                {
                        "TC249683",
                        List.of(
                                new RegistrationDataModel(),
                                new RegistrationImageModel(),
                                new RegistrationRightColumnModel(),
                                new RegistrationFormTypeModel(),
                                new RegistrationSizeModel()
                        )
                },
                {
                        "TC249687",
                        List.of(
                                new RegistrationDataModel(),
                                new RegistrationImageModel(),
                                new RegistrationRightColumnModel(),
                                new RegistrationFormTypeModel().setDiscountFormType(),
                                new RegistrationSizeModel().setSmallFontSize()
                        )
                }
        };
    }

}
