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
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceFeaturedArticleModel;
import drupal.ui.pages.components.flexible_space.AddFlexibleSpaceContentPage;
import drupal.ui.pages.components.flexible_space.FlexibleSpaceBaseModel;
import drupal.ui.pages.components.flexible_space.FlexibleSpaceLandingPage;
import drupal.ui.pages.components.flexible_space.FlexibleSpacesPage;
import drupal.ui.pages.structure.eck_entity_types.ECKEntityTypesPage;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static common.CommonMethods.timeStampFormat;
import static drupal.enums.content.FlexibleContentSpacePosition.CONTENT;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.models.UserModel.loadUserModel;

public class TC250652_Create_Sidebar_Add_Featured_Article_Test extends AdminUITestBase {
    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateSidebarFeaturedArticle() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "flexibleSpaceData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "250652")})
    @TestCaseId("250652")
    public void createSidebarWithFeaturedArticle(List<ISectionDataModel> listViewItemData, List<ISectionDataModel> editFlexibleData) {
        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Structure Tab");
        logStep("Select ECK Entity Types");
        ECKEntityTypesPage eckEntityTypesPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.STRUCTURE, StructureMenu.ECK_ENTITY_TYPES), ECKEntityTypesPage.class);

        logStep("Go to FLEXIBLE SPACE and select 'Content List' Button under the OPERATIONS column");
        FlexibleSpacesPage flexibleSpacesPage = eckEntityTypesPage.clickEntityType(ECKEntityTypesPage.ECKEntityType.FLEXIBLE_SPACE, ECKEntityTypesPage.ECKEntityOperationType.CONTENT_LIST, FlexibleSpacesPage.class);

        logStep("Click Add Flexible space button and navigate to 'Add Flexible space content' page");
        AddFlexibleSpaceContentPage addFlexibleSpaceContentPage = flexibleSpacesPage.clickFlexibleSpaceTab();

        logStep("Enter title input for 'Add Flexible space content' page");
        logStep("click 'Add Featured Article'");
        logStep("Fill in fields of 'Add featured article' component fields");
        logStep("click save");
        logStep("Validate: Drupal Regular Flexible space is created: " + Configuration.baseUrl + "/admin/structure/eck/entity/flexible_space/####");
        FlexibleContentSpaceFeaturedArticleModel flexibleContentSpaceFeaturedArticleModel = listViewItemData.stream()
                                                                                                            .filter(d -> d instanceof FlexibleContentSpaceFeaturedArticleModel)
                                                                                                            .map(c -> (FlexibleContentSpaceFeaturedArticleModel) c)
                                                                                                            .findFirst()
                                                                                                            .get();
        FlexibleSpaceLandingPage flexibleSpaceLandingPage = addFlexibleSpaceContentPage.addFlexibleSpace(listViewItemData)
                                                                                       .saveFlexibleSpaceLanding()
                                                                                       .verifyFlexibleSpaceFeaturedArticle(flexibleContentSpaceFeaturedArticleModel);
        flexibleContentSpaceFeaturedArticleModel = editFlexibleData.stream()
                                                                   .filter(d -> d instanceof FlexibleContentSpaceFeaturedArticleModel)
                                                                   .map(c -> (FlexibleContentSpaceFeaturedArticleModel) c)
                                                                   .findFirst()
                                                                   .get();
        flexibleSpaceLandingPage.clickEditTab()
                                .editFlexibleSpaceData(editFlexibleData)
                                .saveFlexibleSpaceLanding()
                                .verifyFlexibleSpaceFeaturedArticle(flexibleContentSpaceFeaturedArticleModel);

    }

    @DataProvider
    public Object[][] flexibleSpaceData() {
        return new Object[][]{
                {
                        List.of(new FlexibleContentSpaceFeaturedArticleModel(CONTENT, 0, "Test featured article", "Fitness Motivation TEST (2696)"),
                                new FlexibleSpaceBaseModel("Test Flexible Space for Featured Article " + timeStampFormat(PATTERN))),
                        List.of(new FlexibleSpaceBaseModel("Edit Test Flexible Space for Featured Article " + timeStampFormat(PATTERN)),
                                new FlexibleContentSpaceFeaturedArticleModel(CONTENT, 0, "Edit Test featured article", "Edit Fitness Motivation TEST (2696)"))

                }
        };
    }
}
