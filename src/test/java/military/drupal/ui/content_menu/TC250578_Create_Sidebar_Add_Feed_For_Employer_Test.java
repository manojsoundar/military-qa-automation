package military.drupal.ui.content_menu;

import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.ContentMenu;
import drupal.enums.content.FlexibleContentSpacePosition;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.employer.CreateEmployerPage;
import drupal.ui.pages.add_content.employer.EditEmployerPage;
import drupal.ui.pages.add_content.employer.EmployerDataModel;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceFeedModel;
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

public class TC250578_Create_Sidebar_Add_Feed_For_Employer_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateSidebarForEmployer() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "addFeed")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "250578")})
    @TestCaseId("250578")
    public void createSidebar(List<ISectionDataModel> employerDataModel, List<ISectionDataModel> editEmployerData) {
        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over Add content");
        logStep("Click on Employer");
        CreateEmployerPage createEmployerPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.EMPLOYER), CreateEmployerPage.class);

        logStep("Enter test name in the Title field -> Example: Test Employer " + timeStampFormat("yyyyMMdd"));
        FlexibleContentSpaceFeedModel flexibleContentSpaceFeedModel = employerDataModel.stream()
                                                                                       .filter(d -> d instanceof FlexibleContentSpaceFeedModel)
                                                                                       .map(c -> (FlexibleContentSpaceFeedModel) c)
                                                                                       .findFirst()
                                                                                       .get();

        logStep("Flexible space content data");
        logStep("Validation : Employer is created");
        EditEmployerPage editEmployerPage = createEmployerPage.createEmployer(employerDataModel)
                                                              .verifySidebarForEmployer(flexibleContentSpaceFeedModel.getAddFeedTitle())
                                                              .clickEditTab();
        flexibleContentSpaceFeedModel = editEmployerData.stream()
                                                        .filter(d -> d instanceof FlexibleContentSpaceFeedModel)
                                                        .map(c -> (FlexibleContentSpaceFeedModel) c)
                                                        .findFirst()
                                                        .get();
        EmployerDataModel dataModel = editEmployerData.stream()
                                                      .filter(d -> d instanceof EmployerDataModel)
                                                      .map(c -> (EmployerDataModel) c)
                                                      .findFirst()
                                                      .get();
        editEmployerPage.editEmployerPage(editEmployerData)
                        .verifyUpdatedMessage(dataModel.getName())
                        .verifySidebarForEmployer(flexibleContentSpaceFeedModel.getAddFeedTitle());


    }

    @DataProvider
    public Object[][] addFeed() {
        return new Object[][]{
                {
                        List.of(new FlexibleContentSpaceFeedModel(FlexibleContentSpacePosition.SIDEBAR, 0, "Test Feed " + timeStampFormat(PATTERN), "Fox News Inbound Feed", null, true, "Thumbnail and title"),
                                new EmployerDataModel("Test Employer " + timeStampFormat(PATTERN), "Search Test Employer Jobs", "Applied Materials is the leader in materials engineering solutions to produce virtually every new chip and advanced display in the world. Our expertise in modifying materials at at...", null)),
                        List.of(new EmployerDataModel("Edited Test Employer " + timeStampFormat(PATTERN), null, null, null),
                                new FlexibleContentSpaceFeedModel(FlexibleContentSpacePosition.SIDEBAR, 0, "Edited Test Feed " + timeStampFormat(PATTERN), null, null, true, null))
                }
        };
    }
}