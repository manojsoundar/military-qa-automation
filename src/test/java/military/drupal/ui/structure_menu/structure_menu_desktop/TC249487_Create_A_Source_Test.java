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
import drupal.ui.pages.structure.eck_entity_types.ECKEntityTypesPage;
import drupal.ui.pages.structure.eck_entity_types.source.AddSourcesContentDataModel;
import drupal.ui.pages.structure.eck_entity_types.source.AddSourcesPage;
import drupal.ui.pages.structure.eck_entity_types.source.EditSourcePage;
import drupal.ui.pages.structure.eck_entity_types.source.SourceContentPage;
import drupal.ui.pages.structure.eck_entity_types.source.SourcePage;
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

public class TC249487_Create_A_Source_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateASource() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "sourceData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249487")})
    @TestCaseId("249487")
    public void createASource(List<ISectionDataModel> sourceData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Structure Tab");
        logStep("Click the ECK Entity Types");
        ECKEntityTypesPage eckEntityTypes = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.STRUCTURE, StructureMenu.ECK_ENTITY_TYPES), ECKEntityTypesPage.class);

        logStep("Go to SOURCE and select 'Add Content' Button under the OPERATIONS column");
        AddSourcesPage addSourcesPage = eckEntityTypes.clickEntityType(ECKEntityTypesPage.ECKEntityType.SOURCE, ECKEntityTypesPage.ECKEntityOperationType.ADD_CONTENT, AddSourcesPage.class);

        logStep("Enter test name in the Title field");
        logStep("Click Save");
        AddSourcesContentDataModel addSourceContentData = sourceData.stream()
                                                                    .filter(d -> d instanceof AddSourcesContentDataModel)
                                                                    .map(c -> (AddSourcesContentDataModel) c)
                                                                    .findFirst()
                                                                    .get();

        SourcePage sourcePage = addSourcesPage.addSource(sourceData);

        logStep("Validate: Drupal Source is created: " + Configuration.baseUrl + "/admin/structure/eck/entity/source/####");
        sourcePage.verifyAddedSource(addSourceContentData.getTitle());

        logStep("Edit functionality for Added Source");
        SourceContentPage sourceContentPage = sourcePage.navigateTo(List.of(AdministrationToolbar.TrayItem.STRUCTURE, StructureMenu.ECK_ENTITY_TYPES), ECKEntityTypesPage.class)
                                                        .clickEntityType(ECKEntityTypesPage.ECKEntityType.SOURCE, ECKEntityTypesPage.ECKEntityOperationType.CONTENT_LIST, SourceContentPage.class);

        EditSourcePage editSourcePage = sourceContentPage.navigateToLastPage()
                                                         .clickContentLink(addSourceContentData.getTitle())
                                                         .clickEditTab();
        addSourceContentData.setTitle("Test Source - Edit " + timeStampFormat(PATTERN));
        editSourcePage.editSource(List.of(addSourceContentData))
                      .verifyAddedSource(addSourceContentData.getTitle());
    }

    @DataProvider
    public Object[][] sourceData() {
        return new Object[][]{
                {
                        List.of(
                                new AddSourcesContentDataModel()
                        )
                }
        };
    }

}
