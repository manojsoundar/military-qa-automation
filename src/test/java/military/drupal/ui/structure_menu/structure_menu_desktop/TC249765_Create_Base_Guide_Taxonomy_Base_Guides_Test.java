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
import drupal.ui.pages.structure.taxonomy.base_guide.AddBaseGuidePage;
import drupal.ui.pages.structure.taxonomy.base_guide.BaseGuidesOverviewPage;
import drupal.ui.pages.structure.taxonomy.base_guide.TaxonomyBaseGuideDataModel;
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

public class TC249765_Create_Base_Guide_Taxonomy_Base_Guides_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateBaseGuide() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "baseGuideTaxonomyData")
    @Priority(level = PriorityLevel.HIGH)
    @TestCaseId("249765")
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249765")})
    public void createBaseGuideTaxonomy(List<ISectionDataModel> taxonomyBaseModel) {
        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Structure Tab");
        logStep("Select TAXONOMY");
        logStep("Find Base Guides and click List Terms under the Operations column");
        TaxonomyPage taxonomyPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.STRUCTURE, StructureMenu.TAXONOMY), TaxonomyPage.class);
        logStep("Click the Add Term radio button");
        logStep("Type Test Content in the Name field");
        logStep("Click Save");
        BaseGuidesOverviewPage baseGuidesOverviewPage = taxonomyPage.clickTaxonomyType(TaxonomyPage.TaxonomyVocabularyType.BASE_GUIDES, TaxonomyPage.TaxonomyOperationType.LIST_TERMS_LINK, BaseGuidesOverviewPage.class);
        AddBaseGuidePage addBaseGuidePage = baseGuidesOverviewPage.clickAddTermButton();
        TaxonomyBaseGuideDataModel baseGuideDataModel = taxonomyBaseModel.stream()
                                                                         .filter(d -> d instanceof TaxonomyBaseGuideDataModel)
                                                                         .map(c -> (TaxonomyBaseGuideDataModel) c)
                                                                         .findFirst()
                                                                         .get();
        logStep("Verify Drupal Taxonomy URL created:" + Configuration.baseUrl + "/taxonomy/term/#####");
        TaxonomyResultPage taxonomyResultPage = addBaseGuidePage.createBaseGuideTaxonomy(taxonomyBaseModel)
                                                                .clickSaveButton()
                                                                .clickStatusMessage()
                                                                .verifyTaxonomyTermCreated(baseGuideDataModel.getName());
        logStep("Verify edit functionality");
        logStep("CLick on edit tab");
        logStep("Verify name is not empty");
        logStep("Click Save");
        String updateTitle = "Edit Test Individual " + timeStampFormat(DATE_WITH_HYPHEN_PATTERN);
        baseGuideDataModel.setName(updateTitle);
        taxonomyResultPage.clickEditTab()
                          .editTaxonomy(taxonomyBaseModel)
                          .verifyTaxonomyUpdated(updateTitle);
    }

    @DataProvider
    public Object[][] baseGuideTaxonomyData() {
        return new Object[][]{
                {
                        List.of(
                                new TaxonomyBaseGuideDataModel()
                        )
                }
        };
    }

}
