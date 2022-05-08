package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.ComponentsMenu;
import drupal.enums.content.ContentMenu;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.components.widgets.AddWidgetContentPage;
import drupal.ui.pages.components.widgets.CssFilesDataModel;
import drupal.ui.pages.components.widgets.JsFilesDataModel;
import drupal.ui.pages.components.widgets.WidgetComponentDataModel;
import drupal.ui.pages.components.widgets.WidgetsPage;
import drupal.ui.pages.components.widgets.WidgetsResultsPage;
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

public class TC0_Create_Widget_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateWidget() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(enabled = false)
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "0")})
    @TestCaseId("0")
    public void createWidget(List<ISectionDataModel> widgetComponentModel) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Click on 'Components'");
        logStep("Click on 'Widgets'");
        WidgetsPage widgetsPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.COMPONENTS, ComponentsMenu.WIDGETS), WidgetsPage.class);

        logStep("Click on 'Add widget'");
        AddWidgetContentPage widgetContentPage = widgetsPage.clickAddWidgetButton();

        logStep("Enter the widget title");
        logStep("Click save");
        WidgetsResultsPage widgetResultsPage = widgetContentPage.addWidgetContent(widgetComponentModel)
                                                                .clickSaveButton();

        logStep("Validate: Drupal Widget is created: " + Configuration.baseUrl + "/admin/structure/eck/entity/widget/###");
        widgetResultsPage.verifyDrupalWidgetCreated();

        logStep("Verify Edit functionality");
        WidgetComponentDataModel widgetComponentDataModel = widgetComponentModel.stream()
                                                                                .filter(d -> d instanceof WidgetComponentDataModel)
                                                                                .map(c -> (WidgetComponentDataModel) c)
                                                                                .findFirst()
                                                                                .get();
        widgetComponentDataModel.setTitle("Edit Widget" + timeStampFormat(PATTERN));
        widgetResultsPage.clickEditTab()
                         .editWidgetContent(List.of(widgetComponentDataModel));
    }

    @DataProvider
    public Object[][] widgetData() {
        return new Object[][]{
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
