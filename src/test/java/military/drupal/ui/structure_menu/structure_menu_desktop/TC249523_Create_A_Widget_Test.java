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
import drupal.ui.pages.components.widgets.AddWidgetContentPage;
import drupal.ui.pages.components.widgets.CssFilesDataModel;
import drupal.ui.pages.components.widgets.EditWidgetPage;
import drupal.ui.pages.components.widgets.JsFilesDataModel;
import drupal.ui.pages.components.widgets.WidgetComponentDataModel;
import drupal.ui.pages.components.widgets.WidgetsResultsPage;
import drupal.ui.pages.structure.eck_entity_types.ECKEntityTypesPage;
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

public class TC249523_Create_A_Widget_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateAWidget() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "widgetData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249523")})
    @TestCaseId("249523")
    public void createAWidget(List<ISectionDataModel> widgetComponentData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Structure Tab");
        logStep("Click the ECK Entity Types");
        ECKEntityTypesPage eckEntityTypes = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.STRUCTURE, StructureMenu.ECK_ENTITY_TYPES), ECKEntityTypesPage.class);

        logStep("Go to WIDGET and select 'Add Content' Button under the OPERATIONS column");
        AddWidgetContentPage addWidgetContentPage = eckEntityTypes.clickEntityType(ECKEntityTypesPage.ECKEntityType.WIDGET, ECKEntityTypesPage.ECKEntityOperationType.ADD_CONTENT, AddWidgetContentPage.class);

        logStep("Enter test name in the Title field");
        logStep("Enter HTML Code snippet in the HTML snippet field");
        logStep("Click Save Button");
        WidgetsResultsPage widgetResultsPage = addWidgetContentPage.addWidgetContent(widgetComponentData)
                                                                   .clickSaveButton();
        WidgetComponentDataModel widgetData = widgetComponentData.stream()
                                                                 .filter(d -> d instanceof WidgetComponentDataModel)
                                                                 .map(c -> (WidgetComponentDataModel) c)
                                                                 .findFirst()
                                                                 .get();

        logStep("Validate: Drupal Widget is created: " + Configuration.baseUrl + "/admin/structure/eck/entity/widget/###");
        logStep("Validate Edit functionality");
        EditWidgetPage editWidgetPage = widgetResultsPage.verifyDrupalWidgetCreated()
                                                         .clickEditTab();
        widgetData.setTitle("Test Widget - Edit " + timeStampFormat(PATTERN));
        widgetData.setHtmlSnippet(null);
        editWidgetPage.editWidgetContent(List.of(widgetData))
                      .clickEditTab()
                      .verifyDrupalWidgetCreated();


    }

    @DataProvider
    public Object[][] widgetData() {
        return new Object[][]{
                {
                        List.of(
                                new WidgetComponentDataModel()
                        )
                },
                {
                        List.of(
                                new WidgetComponentDataModel(),
                                new JsFilesDataModel()
                        )
                },
                {
                        List.of(
                                new WidgetComponentDataModel(),
                                new CssFilesDataModel()
                        )
                },
                {
                        List.of(
                                new WidgetComponentDataModel(),
                                new CssFilesDataModel(),
                                new JsFilesDataModel()
                        )
                }
        };
    }

}
