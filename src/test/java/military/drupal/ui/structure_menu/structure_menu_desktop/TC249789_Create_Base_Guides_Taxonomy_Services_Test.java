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
import drupal.ui.pages.structure.taxonomy.TaxonomyPage;
import drupal.ui.pages.structure.taxonomy.TaxonomyResultPage;
import drupal.ui.pages.structure.taxonomy.services.AddServicePage;
import drupal.ui.pages.structure.taxonomy.services.ServiceDataModel;
import drupal.ui.pages.structure.taxonomy.services.ServicesOverviewPage;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.DATE_WITH_HYPHEN_PATTERN;
import static drupal.models.UserModel.loadUserModel;

public class TC249789_Create_Base_Guides_Taxonomy_Services_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateBaseGuidesTaxonomyServices() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "taxonomyData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249789")})
    @TestCaseId("249789")
    public void createBaseGuidesTaxonomyServices(List<ISectionDataModel> taxonomyServiceModel) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Structure Tab");
        logStep("Click the ECK Entity Types");
        logStep("Select Taxonomy option");
        TaxonomyPage taxonomyPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.STRUCTURE, StructureMenu.TAXONOMY), TaxonomyPage.class);

        logStep("Click 'List terms' under Services option");
        ServicesOverviewPage servicesPage = taxonomyPage.clickTaxonomyType(TaxonomyPage.TaxonomyVocabularyType.SERVICES, TaxonomyPage.TaxonomyOperationType.LIST_TERMS_LINK, ServicesOverviewPage.class);

        logStep("Click 'Add term' button in services page");
        AddServicePage addServiceCategoryPage = servicesPage.clickAddTermButton();
        ServiceDataModel serviceDataModel = taxonomyServiceModel.stream()
                                                                .filter(d -> d instanceof ServiceDataModel)
                                                                .map(c -> (ServiceDataModel) c)
                                                                .findFirst()
                                                                .get();
        logStep("Enter test name in the Name field");
        logStep("Click Save button");
        logStep("Validate: Drupal Taxonomy service is created: " + Configuration.baseUrl + "/taxonomy/term/#####");
        TaxonomyResultPage taxonomyServiceResultPage = addServiceCategoryPage.createServiceTaxonomy(taxonomyServiceModel)
                                                                             .clickSaveButton()
                                                                             .clickStatusMessage()
                                                                             .verifyTaxonomyTermCreated(serviceDataModel.getName());
        logStep("Created Taxonomy service page, click the Edit link and return to the Edit term Page");
        logStep("Validate Name input field is not empty and click Save");
        logStep("Validate: Edited Drupal Taxonomy service page");
        String updateTitle = "Edit Test Individual " + timeStampFormat(DATE_WITH_HYPHEN_PATTERN);
        serviceDataModel.setName(updateTitle);
        taxonomyServiceResultPage.clickEditTab()
                                 .editTaxonomy(taxonomyServiceModel)
                                 .verifyTaxonomyUpdated(updateTitle);
    }

    @DataProvider
    public Object[][] taxonomyData() {
        return new Object[][]{
                {
                        List.of(new ServiceDataModel())
                }
        };
    }

}
