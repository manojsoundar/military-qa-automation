package military.drupal.ui.content_menu;

import com.epam.reportportal.annotations.TestCaseId;
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
import drupal.ui.pages.add_content.contact.LocationModel;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static drupal.models.UserModel.loadUserModel;

public class TC0_Create_Contact_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateContact() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(enabled = false, dataProvider = "contactData")
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "0")})
    @Priority(level = PriorityLevel.HIGH)
    @TestCaseId("0")
    public void createContact(List<ISectionDataModel> data) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over 'ADD CONTENT'");
        logStep("Click on 'CONTACT'");
        AddContactPage addContactPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.CONTACT), AddContactPage.class);

        logStep("Fill Create Contact Page details");
        logStep("verify -> Newly created Contact page is loaded and title displayed");
        ContactModel contactModel = data.stream()
                                        .filter(d -> d instanceof ContactModel)
                                        .map(c -> (ContactModel) c)
                                        .findFirst()
                                        .get();
        addContactPage.fillCreateContact(data)
                      .clickSaveButton()
                      .verifyContactPage(contactModel.getContactTitle());
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
