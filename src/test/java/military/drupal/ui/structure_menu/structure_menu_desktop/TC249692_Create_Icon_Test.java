package military.drupal.ui.structure_menu.structure_menu_desktop;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.structure.StructureMenu;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.components.icon.AddIconPage;
import drupal.ui.pages.components.icon.IconDataModel;
import drupal.ui.pages.components.icon.IconResultPage;
import drupal.ui.pages.structure.eck_entity_types.ECKEntityTypesPage;
import lombok.extern.log4j.Log4j2;
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

@Log4j2
public class TC249692_Create_Icon_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateIcon() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "iconData")
    @Priority(level = PriorityLevel.HIGH)
    @TestCaseId("249692")
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249692")})
    public void createIcon(List<ISectionDataModel> iconData) {
        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Structure Tab");
        logStep("Click the ECK Entity Types");
        ECKEntityTypesPage eckEntityTypes = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.STRUCTURE, StructureMenu.ECK_ENTITY_TYPES), ECKEntityTypesPage.class);

        logStep("Go to Icon and select 'Add Content' Button under the OPERATIONS column");
        AddIconPage addIconPage = eckEntityTypes.clickEntityType(ECKEntityTypesPage.ECKEntityType.ICON, ECKEntityTypesPage.ECKEntityOperationType.ADD_CONTENT, AddIconPage.class);

        logStep("Enter test name in the Icon field");
        logStep("Click Choose File in the ICON field A popup opening local c: drive will display");
        logStep("Select Image File and Click Open");
        logStep("Click Save");
        logStep("Verify Icon created : Drupal Image link is created: " + Configuration.baseUrl + "/icon/###");
        IconResultPage iconResultPage = addIconPage.fillIn(iconData)
                                                   .clickSaveButton()
                                                   .verifyIconCreated();

        logStep("Verify Edit functionality");
        IconDataModel iconDataModel = iconData.stream()
                                              .filter(d -> d instanceof IconDataModel)
                                              .map(c -> (IconDataModel) c)
                                              .findFirst()
                                              .get();
        iconDataModel.setTitle("Test Image - Edit " + timeStampFormat(PATTERN));
        iconDataModel.setImageFile(null);
        iconResultPage.clickEditTab()
                      .fillIn(List.of(iconDataModel))
                      .clickSaveButton();

    }

    @DataProvider
    public Object[][] iconData() {
        return new Object[][]{
                {
                        List.of(
                                new IconDataModel()
                        )
                }
        };
    }

}
