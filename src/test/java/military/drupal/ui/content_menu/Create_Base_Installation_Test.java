package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.attribute.Attributes;
import com.epam.reportportal.annotations.attribute.MultiValueAttribute;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.ContentMenu;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.base_installation.BaseInstallationModel;
import drupal.ui.pages.add_content.base_installation.BaseInstallationPage;
import drupal.ui.pages.add_content.base_installation.BaseReferenceShortNameModel;
import drupal.ui.pages.add_content.base_installation.BaseServiceCategoryModel;
import drupal.ui.pages.add_content.base_installation.BaseServiceLocationModel;
import drupal.ui.pages.add_content.base_installation.BaseServiceSideBarModel;
import drupal.ui.pages.add_content.base_installation.CreateBaseInstallationPage;
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
public class Create_Base_Installation_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateBaseInstallation() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "baseInstallationTest")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(multiValueAttributes = {@MultiValueAttribute(key = "TestCaseIds", values = {"249670", "249667"})})

    public void createBaseInstallation(String testCaseId, List<ISectionDataModel> baseInstallationModel) {

        logStep("Running TestCase Id: " + testCaseId);

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over Add content");
        logStep("Click on Base Installation");
        CreateBaseInstallationPage createBaseInstallationPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.BASE_INSTALLATION), CreateBaseInstallationPage.class);

        logStep("Enter title name in the Title field");
        logStep("Enter test text in the Blurb (for summary) field");
        logStep("Select 'Base service check box' and enter value or select 'Navy' and 'Marine' checkboxes ");
        logStep("Select Hampton Roads Military Bases in the BASE REFERENCE dropdown field");
        logStep("Enter 'Test Base Discounts' or 'Test Base LP' in the SHORT NAME field");
        logStep("Enter '22 Lincoln Street' in the Street Address 1 field");
        logStep("Enter 'Suite 200' in the Street Address 2 field");
        logStep("select the following from the Location Dropdown fields: United States,VA,Hampton");
        logStep("Enter '23669' in the ZIP CODE field");
        logStep("Enter Latitude='36.850505', Longitude = '-76.285629' inGeolocation fields");
        logStep("select 'Base Guide | Default Sidebar' in the Sidebar field");
        BaseInstallationModel baseInstallationData = baseInstallationModel.stream()
                                                                          .filter(d -> d instanceof BaseInstallationModel)
                                                                          .map(c -> (BaseInstallationModel) c)
                                                                          .findFirst()
                                                                          .get();
        BaseInstallationPage baseInstallationPage = createBaseInstallationPage.createBaseInstallation(baseInstallationModel);

        logStep("Validation : Base Installation Page is created: " + Configuration.baseUrl + "/base-guide/#########");
        baseInstallationPage.verifyCreatedBaseInstallation(baseInstallationData);

        logStep("Edit functionality");
        String updateTitle = "Edited Test Base " + timeStampFormat(PATTERN);
        baseInstallationData.setTitle(updateTitle);
        baseInstallationPage.clickEditTab()
                            .editBaseInstallationPage(List.of(baseInstallationData))
                            .verifyUpdatedStatusConfirmation(updateTitle);
    }

    @DataProvider
    public Object[][] baseInstallationTest() {
        return new Object[][]{
                {
                        "TC249670",
                        List.of(
                                new BaseInstallationModel(),
                                new BaseServiceCategoryModel().getDiscountsData(),
                                new BaseReferenceShortNameModel().getDiscountsData(),
                                new BaseServiceLocationModel(),
                                new BaseServiceSideBarModel()
                        )
                },
                {
                        "TC249667",
                        List.of(
                                new BaseInstallationModel(),
                                new BaseServiceCategoryModel(),
                                new BaseReferenceShortNameModel(),
                                new BaseServiceLocationModel(),
                                new BaseServiceSideBarModel()
                        )
                }
        };
    }

}
