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
import drupal.ui.pages.add_content.equipment.EquipmentPage;
import drupal.ui.pages.structure.taxonomy.TaxonomyPage;
import drupal.ui.pages.structure.taxonomy.categories.CategoriesAddTermPage;
import drupal.ui.pages.structure.taxonomy.categories.CategoriesDataModel;
import drupal.ui.pages.structure.taxonomy.categories.CategoriesPage;
import drupal.ui.pages.structure.taxonomy.categories.CategoriesRelationModel;
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
import static java.lang.String.format;

public class TC249795_Create_Equipment_Category_Page_With_Pixel_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateEquipmentCategoryPageWithPixel() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "addTermCategoryData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249795")})
    @TestCaseId("249795")
    public void createEquipmentCategoryPageWithPixel(List<ISectionDataModel> taxonomyCategoriesModel) {

        logStep("1. Login to Drupal");
        logStep("2. Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("3. Hover the Structure Tab");
        logStep("4. Select TAXONOMY");
        TaxonomyPage taxonomyPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.STRUCTURE, StructureMenu.TAXONOMY), TaxonomyPage.class);

        logStep("5. Find Categories and click List Terms under the Operations column");
        CategoriesPage categoriesPage = taxonomyPage.clickTaxonomyType(TaxonomyPage.TaxonomyVocabularyType.CATEGORIES, TaxonomyPage.TaxonomyOperationType.LIST_TERMS_LINK, CategoriesPage.class);

        logStep("6. Click the Add Term radio button");
        CategoriesAddTermPage addTermPage = categoriesPage.clickAddTerm();

        logStep("Type Test Content in the Name field. Test Content Test Equipment Category YYYYMMDD");
        logStep("8. Type Test URL in the URL Alias field Test URL /equipment/test-equipment-category-yyyymmdd");
        logStep("9. Type Test URL in the Link URL field Test URL /equipment/test-equipment-category-yyyymmdd");
        logStep("10. In the Tracking-Pixels section, add test urls in the URL field");
        logStep(format("Test URLs %s?test-equipment-category-pixel, %s?test-equipment-category-in-qa-pixel", Configuration.baseUrl, Configuration.baseUrl));
        logStep("11. Click Relations, type Equipment, and press the down arrow to select Ordnance");
        logStep("12. Click Save. Drupal Taxonomy URL " + Configuration.baseUrl + "/taxonomy/term/#####");
        CategoriesTitleModel categoriesTitleModel = taxonomyCategoriesModel.stream()
                                                                           .filter(d -> d instanceof CategoriesTitleModel)
                                                                           .map(c -> (CategoriesTitleModel) c)
                                                                           .findFirst()
                                                                           .get();
        String linkUrl = "/equipment/test-equipment-category" + timeStampFormat(PATTERN);
        taxonomyCategoriesModel.add(new CategoriesUrlModel(linkUrl, linkUrl));
        addTermPage.addNewTerm(taxonomyCategoriesModel)
                   .clickSaveButton()
                   .verifyStatusMessage(categoriesTitleModel);

        logStep("13. Go to the Ordnance category page and confirm the new category is displaying.at the top of the page (links above Equipment thumbnails)");
        logStep(format("Ordnance Equipment Category Page %s/equipment/ordnance", Configuration.baseUrl));
        EquipmentPage equipmentPage = open(Configuration.baseUrl + "/equipment/ordnance", EquipmentPage.class);

        logStep("14. Click the Test Category page and confirm the pixels display on the page. Right click on the page, and click Page Source");
        logStep("15. In the Page Source page, click Find (CTRL+F) and type pixel.");
        logStep("Verification: Category Page, the pixel url should display in Page Source.");
        equipmentPage.verifyEquipmentCategoryPageAndClick(categoriesTitleModel.getName());
    }

    @DataProvider
    public Object[][] addTermCategoryData() {
        return new Object[][]{
                {
                        new ArrayList<>(
                                Arrays.asList(new CategoriesTitleModel("Test Equipment Category" + timeStampFormat(PATTERN)),
                                              new CategoriesDataModel(),
                                              new CategoriesTrackingPixelModel(List.of("https://www.miitary.com?test-equipment-category-pixel", "https://www.miitary.com?test-equipment-category-in-qa-pixel")),
                                              new CategoriesRelationModel("-Ordnance")
                                )
                        )
                }
        };
    }

}
