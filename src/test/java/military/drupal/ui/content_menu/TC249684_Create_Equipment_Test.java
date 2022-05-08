package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.ContentMenu;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.equipment.AddEquipmentPage;
import drupal.ui.pages.add_content.equipment.DetailsModel;
import drupal.ui.pages.add_content.equipment.EditEquipmentPage;
import drupal.ui.pages.add_content.equipment.EquipmentDataModel;
import drupal.ui.pages.add_content.equipment.SpecificationsModel;
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
import static java.lang.String.format;

public class TC249684_Create_Equipment_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateEquipment() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "addEquipmentData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249684")})
    public void createEquipment(List<ISectionDataModel> equipmentData) {

        logStep("1. Login to Drupal");
        logStep("2. Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("3. Hover the Content Tab");
        logStep("4. Hover over 'ADD CONTENT'");
        logStep("5. Click on 'EQUIPMENT'");
        AddEquipmentPage addEquipmentPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.EQUIPMENT), AddEquipmentPage.class);

        logStep("6. Enter test name in the Title field");
        logStep("Example: Test Equipment YYYYMMDD, Test Equipment 20210622");
        logStep("7. Type test content to the Blurb (or Summary) field");
        logStep("Test Content: Mission: Ship-to-Shore Troop Transport");

        logStep("8. Type test content to the Body field");
        logStep("Test Content: Manufacturer: United Defense, Service: USMC Armament: Mk 19 40 mm grenade launcher or M242 Bushmaster 25mm gun; M2HB .50-caliber machine gun" +
                        "Engine: Detroit Diesel 8V-53T (P-7), Cummins VT 400 903 (P-7A1)   Range: 300 miles; 20 nm in water\n" +
                        "Speed: 20 mph off-road, 45 mph surfaced road, 8 mph water" +
                        "Crew: 3 crew, 25 Marines From ship to shore to objective, no equipment better defines the distinction and purpose of Marine Corps expeditionary capabilities than the AAV-7 Amphibious Assault Vehicle. " +
                        "Designed to assault any shoreline from the well decks of Navy assault ships, AAVs are highly mobile, tracked armored amphibious vehicles that transport Marines and cargo to and through hostile territory." +
                        "The AAVP7A1 is an armored assault amphibious full-tracked landing vehicle. The vehicle carries troops in water operations from ship to shore, through rough water and surf zone. It also carries troops to inland objectives after ashore.");

        logStep("9. Type test content in the Specification section");
        logStep("Test Content: Label Manufacturer: Description BAE Systems");

        logStep("10. Click the Add Specification button");
        logStep("A new set of Specification fields will display under the first Specification field.");

        logStep("11. Click the 'Attach an Image' button in the Thumbnail Image field");
        logStep("Click the Existing Image link");

        logStep("12. Type 'Adobe Stock' in the Media Name field and click the Search button");
        logStep("13. Click any image found in the Search Results");
        logStep("14. Once saved, the image popup will disappear and the selected image will display in the Thumbnail Image field");

        logStep("15. In the Slideshow section, click 'Add Existing Slideshow' A new field (Slideshow) will display in the 'Add Existing Slideshow' section.");
        logStep("Type test content when requested in the Slideshow field Test Content: Type Carrier and select Ford Class Aircraft Carrier (718)");

        logStep("16. Select Test Categories in the CATEGORIES field");
        logStep("Test Categories: 1) Equipment > Electronics > Radars 2) Equipment > Military Aircraft > Bombers 3) Equipment > Ordnance");
        logStep("17. Click Save");
        logStep(format("Drupal Equipment is created : %s/equipment/test-equipment-YYYYMMDD", Configuration.baseUrl));
        EquipmentDataModel equipmentDataModel = equipmentData.stream()
                                                             .filter(d -> d instanceof EquipmentDataModel)
                                                             .map(c -> (EquipmentDataModel) c)
                                                             .findFirst()
                                                             .get();
        EditEquipmentPage editEquipmentPage = addEquipmentPage.fillIn(equipmentData)
                                                              .clickSaveButton()
                                                              .verifyEquipmentPage(equipmentDataModel.getTitle())
                                                              .clickEditTab();

        logStep("Verify Edit functionality");
        equipmentDataModel.setTitle("Edit Equipment " + timeStampFormat(PATTERN));
        editEquipmentPage.fillIn(List.of(equipmentDataModel))
                         .clickSaveButton()
                         .verifyEquipmentPage(equipmentDataModel.getTitle());
    }

    @DataProvider
    public Object[][] addEquipmentData() {
        return new Object[][]{
                {
                        List.of(
                                new EquipmentDataModel(),
                                new SpecificationsModel(),
                                new DetailsModel()
                        )
                }
        };
    }

}
