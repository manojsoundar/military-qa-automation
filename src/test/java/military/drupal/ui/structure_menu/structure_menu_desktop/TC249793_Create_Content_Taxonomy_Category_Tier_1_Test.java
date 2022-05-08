package military.drupal.ui.structure_menu.structure_menu_desktop;

import com.codeborne.selenide.Configuration;
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
import drupal.ui.pages.add_content.landing_page.AddLandingPage;
import drupal.ui.pages.add_content.landing_page.AddLandingPageModel;
import drupal.ui.pages.add_content.landing_page.EditLandingPage;
import drupal.ui.pages.add_content.landing_page.ResultLandingPage;
import drupal.ui.pages.add_content.landing_page.ThumbnailImageModel;
import drupal.ui.pages.structure.taxonomy.TaxonomyPage;
import drupal.ui.pages.structure.taxonomy.categories.CategoriesAddTermPage;
import drupal.ui.pages.structure.taxonomy.categories.CategoriesDataModel;
import drupal.ui.pages.structure.taxonomy.categories.CategoriesPage;
import drupal.ui.pages.structure.taxonomy.categories.CategoriesRelationModel;
import drupal.ui.pages.structure.taxonomy.categories.CategoriesTermLandingPage;
import drupal.ui.pages.structure.taxonomy.categories.CategoriesTitleModel;
import drupal.ui.pages.structure.taxonomy.categories.CategoriesTrackingPixelModel;
import drupal.ui.pages.structure.taxonomy.categories.CategoriesUrlModel;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.models.UserModel.loadUserModel;

public class TC249793_Create_Content_Taxonomy_Category_Tier_1_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateContentTaxonomyCategoryTier1() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "categoryAddTerm")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249793")})
    @TestCaseId("249793")
    public void createCreateContentTaxonomyCategoryTier1(List<ISectionDataModel> landingPageData, List<ISectionDataModel> taxonomyCategoriesModel) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Pre-condition to create a test Landing page");
        AddLandingPage addLandingPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.LANDING_PAGE), AddLandingPage.class);
        AddLandingPageModel addLandingPageData = landingPageData.stream()
                                                                .filter(d -> d instanceof AddLandingPageModel)
                                                                .map(c -> (AddLandingPageModel) c)
                                                                .findFirst()
                                                                .get();
        ResultLandingPage resultLandingPage = addLandingPage.fillIn(landingPageData)
                                                            .clickSaveButton()
                                                            .validateLandingPage(addLandingPageData);
        EditLandingPage editLandingPage = resultLandingPage.clickEditTab();
        String linkUrl = editLandingPage.getUrlAlias();

        logStep("Hover the Structure Tab");
        logStep("Select Taxonomy");
        TaxonomyPage taxonomyPage = open(Configuration.baseUrl + "/admin/structure/taxonomy", TaxonomyPage.class);

        logStep("Find Categories and click List Terms under the Operations column");
        CategoriesPage categoriesPage = taxonomyPage.clickTaxonomyType(TaxonomyPage.TaxonomyVocabularyType.CATEGORIES, TaxonomyPage.TaxonomyOperationType.LIST_TERMS_LINK, CategoriesPage.class);

        logStep("Click the Add Term radio button");
        CategoriesAddTermPage categoriesAddTermPage = categoriesPage.clickAddTerm();

        logStep("Type Test Content in the Name field");
        logStep("Type Test URL in the Link URL field");
        logStep("Click Save");
        logStep("Validation : Drupal Taxonomy URL " + Configuration.baseUrl + "/taxonomy/term/#####");
        CategoriesTitleModel categoriesTitleModel = taxonomyCategoriesModel.stream()
                                                                           .filter(d -> d instanceof CategoriesTitleModel)
                                                                           .map(c -> (CategoriesTitleModel) c)
                                                                           .findFirst()
                                                                           .get();
        taxonomyCategoriesModel.add(new CategoriesUrlModel(null, linkUrl));
        CategoriesTermLandingPage categoriesTermLandingPage = categoriesAddTermPage.addNewTerm(taxonomyCategoriesModel)
                                                                                   .clickSaveButton()
                                                                                   .verifyStatusMessage(categoriesTitleModel)
                                                                                   .navigateToNewTermCreated(categoriesTitleModel);
        categoriesTermLandingPage.verifyLandingPage(linkUrl, addLandingPageData.getLandingPageTitle());

    }

    @DataProvider
    public Object[][] categoryAddTerm() {
        return new Object[][]{
                {
                        List.of(AddLandingPageModel.taxonomyCategoryTier1PreCondition(), new ThumbnailImageModel("Test Image")),
                        new ArrayList<>(
                                Arrays.asList(
                                        new CategoriesTitleModel("Test Article Category" + timeStampFormat(PATTERN)),
                                        new CategoriesDataModel(),
                                        new CategoriesTrackingPixelModel(List.of("https://www.miitary.com?test-equipment-category-pixel", "https://www.miitary.com?test-equipment-category-in-qa-pixel")),
                                        new CategoriesRelationModel("History")
                                )
                        )
                }
        };
    }

}