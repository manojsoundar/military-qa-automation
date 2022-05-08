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
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceImageModel;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListViewModel;
import drupal.ui.pages.components.flexible_space.AddFlexibleSpaceContentPage;
import drupal.ui.pages.components.flexible_space.EditFlexibleSpacePage;
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

import static drupal.enums.content.FlexibleContentSpacePosition.CONTENT;
import static drupal.models.UserModel.loadUserModel;
import static drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListViewModel.DisplayMode.ARTICLE_LIST_WITH_LEFT_FLOATED_THUMBNAILS;
import static drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListViewModel.DisplayMode.ONE_COLUMN_LINKS;
import static drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListViewModel.DisplayMode.TWO_COLUMN_BULLETS_LINKS;
import static drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListViewModel.ListOrder.NONE;
import static drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListViewModel.ListType.AUTO_GENERATED_LIST;
import static drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListViewModel.ListType.CURATED_LIST;

public class TC249717_Create_A_Sidebar_Content_Listviews_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateASidebarContentListviews() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "flexibleSpaceData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249717")})
    @TestCaseId("249717")
    public void createASidebarContentListviews(List<ISectionDataModel> listViewItemData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Structure Tab");
        logStep("Click the ECK Entity Types");
        ECKEntityTypesPage eckEntityTypes = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.STRUCTURE, StructureMenu.ECK_ENTITY_TYPES), ECKEntityTypesPage.class);

        logStep("Go to FLEXIBLE SPACE and select 'Content List' Button under the OPERATIONS column");
        FlexibleSpacesPage flexibleSpacesPage = eckEntityTypes.clickEntityType(ECKEntityTypesPage.ECKEntityType.FLEXIBLE_SPACE, ECKEntityTypesPage.ECKEntityOperationType.CONTENT_LIST, FlexibleSpacesPage.class);

        logStep("Click Add Flexible space button and navigate to 'Add Flexible space content' page");
        AddFlexibleSpaceContentPage addFlexibleSpaceContentPage = flexibleSpacesPage.clickFlexibleSpaceTab();

        logStep("Enter title input for 'Add Flexible space content' page");
        logStep("click 'Add list view'");
        logStep("Fill in all fields in Add list view and 'Add Flexible space content' page");
        logStep("click save");
        FlexibleSpaceLandingPage flexibleSpaceLandingPage = addFlexibleSpaceContentPage.addFlexibleSpace(listViewItemData)
                                                                                       .saveFlexibleSpaceLanding();

        logStep("Validate: Drupal Regular Flexible space is created: " + Configuration.baseUrl + "/flexible_space/####");
        EditFlexibleSpacePage editFlexibleSpacePage = flexibleSpaceLandingPage.verifyDrupalFlexibleSpaceCreated()
                                                                              .clickEditTab();

        logStep("Verify Edit Functionality");
        List<ISectionDataModel> editData = List.of(new FlexibleContentSpaceImageModel(CONTENT, 0, "Test Image", null, "Test Image"));
        editFlexibleSpacePage.fillIn(editData)
                             .saveFlexibleSpaceLanding()
                             .verifyFlexibleSpaceImage(editData.stream()
                                                               .filter(d -> d instanceof FlexibleContentSpaceImageModel)
                                                               .map(c -> (FlexibleContentSpaceImageModel) c)
                                                               .findFirst()
                                                               .get()
                                                               .getImagesTitle());

    }

    @DataProvider
    public Object[][] flexibleSpaceData() {
        return new Object[][]{
                {
                        List.of(
                                new FlexibleContentSpaceListViewModel(CONTENT, 0, "Test 1 column links", AUTO_GENERATED_LIST, ONE_COLUMN_LINKS, null, "Family and Spouse", null, "0", true, NONE),
                                new FlexibleContentSpaceListViewModel(CONTENT, 0, "Test 2 column bullets (links)", CURATED_LIST, TWO_COLUMN_BULLETS_LINKS, List.of("VA Loan Topics (146)"), null, null, "6", true, NONE),
                                new FlexibleContentSpaceListViewModel(CONTENT, 0, "Test Article List - Floated Thumbnails", AUTO_GENERATED_LIST, ARTICLE_LIST_WITH_LEFT_FLOATED_THUMBNAILS, null, null, "Personal Finance", "4", true, NONE),
                                new FlexibleSpaceBaseModel()
                        )
                }
        };
    }

}