package military.drupal.ui.content_menu;


import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.ContentMenu;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.contact.AddContactPage;
import drupal.ui.pages.add_content.contact.ContactModel;
import drupal.ui.pages.add_content.contact.ContactPage;
import drupal.ui.pages.add_content.contact.LocationModel;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.models.UserModel.loadUserModel;

public class TC249671_Create_Base_Guide_Contact_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateBaseGuideContact() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "contactData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249671")})
    public void createBaseGuideContact(List<ISectionDataModel> data) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over 'ADD CONTENT'");
        logStep("Click on 'CONTACT'");
        AddContactPage addContactPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.CONTACT), AddContactPage.class);

        logStep("Test Base Category Example: Test Base Contact YYYYMMDD");
        logStep("Test Content:\n" +
                        "M-F 9am-5:30pm; Sa 9am-2pm; Su Closed");
        logStep("Test Website link\n" +
                        "https://www.navyfederal.org");
        logStep("In the Location section, type 111 Gainsborough Square in the Street Address 1 field");
        logStep("In the Location section, type Suite 200 in the Street Address 2 field");
        logStep("In the Location section, type 10A in the Building Number field");
        logStep("In the Location section, type Corner of Gainsborough Square and Johnson Avenue in the Cross Street field");
        logStep("In the Location section, select the following from the Location Dropdown fields:\n" +
                        "United States\n" +
                        "VA\n" +
                        "Chesapeake");
        logStep("In the Location section, type 23322 in the Zip Code field");
        logStep("In the Location section, type test content in the Latitude & Longitude fields");
        logStep("Type (888) 842-6328\n" +
                        "(757) 511-1230 in the Phone Number field.");
        logStep("Select 'Base Guide | Default Sidebar' in the Sidebar field");
        logStep("Select Hampton Roads Military Bases in the BASE REFERENCE dropdown field");
        logStep("Click Save");

        ContactPage contactPage = addContactPage.fillCreateContact(data)
                                                .clickSaveButton();
        ContactModel contactModel = data.stream()
                                        .filter(d -> d instanceof ContactModel)
                                        .map(c -> (ContactModel) c)
                                        .findFirst()
                                        .get();
        contactPage.verifyContactPage(contactModel.getContactTitle());
        contactModel.setContactTitle("Edit Test Base Contact" + timeStampFormat(PATTERN));
        contactPage.clickEditTab()
                   .editContactInfo(data)
                   .clickSaveButton()
                   .validateEditedContact(contactModel);
    }

    @DataProvider
    public Object[][] contactData() {
        return new Object[][]{
                {
                        List.of(
                                ContactModel.getBaseGuideContactData(),
                                LocationModel.getBaseGuideLocationData()
                        )
                }
        };
    }

}
