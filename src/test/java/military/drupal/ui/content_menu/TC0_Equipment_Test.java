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
import drupal.ui.pages.add_content.equipment.AddEquipmentPage;
import drupal.ui.pages.add_content.equipment.DetailsModel;
import drupal.ui.pages.add_content.equipment.EditEquipmentPage;
import drupal.ui.pages.add_content.equipment.EquipmentDataModel;
import drupal.ui.pages.add_content.equipment.EquipmentPage;
import drupal.ui.pages.add_content.equipment.SpecificationsModel;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static common.CommonMethods.timeStampFormat;
import static drupal.enums.content.EquipmentCategory.ELECTRONICS_RADARS;
import static drupal.models.TimeStampPattern.DATE_PATTERN;
import static drupal.models.UserModel.loadUserModel;

public class TC0_Equipment_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateEquipment() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "equipmentData", enabled = false)
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "0")})
    public void createEquipment(List<ISectionDataModel> equipSectionData) {
        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over 'ADD CONTENT'");
        logStep("Click on 'EQUIPMENT'");
        AddEquipmentPage addEquipmentPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.EQUIPMENT), AddEquipmentPage.class);

        logStep("Fill Create Equipment Page details");
        logStep("verify -> Newly created equipment page is loaded");


        EquipmentPage equipmentPage = addEquipmentPage.fillIn(equipSectionData)
                                                      .clickSaveButton();
        EquipmentDataModel equipmentDataModel = equipSectionData.stream()
                                                                .filter(d -> d instanceof EquipmentDataModel)
                                                                .map(c -> (EquipmentDataModel) c)
                                                                .findFirst()
                                                                .get();
        equipmentPage.verifyEquipmentPage(equipmentDataModel.getTitle());
        EditEquipmentPage editEquipmentPage = equipmentPage.clickEditTab();

        logStep("Fill Edit Equipment Page details");
        logStep("verify -> edited equipment page is loaded");
        equipmentDataModel = new EquipmentDataModel("Edit " + timeStampFormat(DATE_PATTERN), "Test Content: Mission: Ship-to-Shore Troop Transport", null, "test");
        editEquipmentPage.fillIn(List.of(equipmentDataModel))
                         .clickSaveButton();
        equipmentPage.verifyEquipmentPage(equipmentDataModel.getTitle());
    }


    @DataProvider
    public Object[][] equipmentData() {
        return new Object[][]{
                {
                        List.of(
                                new EquipmentDataModel("Equipment " + timeStampFormat(DATE_PATTERN), "Test Content: Mission: Ship-to-Shore Troop Transport", null, null),
                                new SpecificationsModel(Map.of("Manufacturer:", "BAE Systems")),
                                new DetailsModel("Test Image", false, null, null, false, null, null, List.of(ELECTRONICS_RADARS), null)
                        )
                }
        };
    }

}

