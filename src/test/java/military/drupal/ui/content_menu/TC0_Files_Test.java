package military.drupal.ui.content_menu;

import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.ContentMenu;
import drupal.models.FilesModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.files.FilesPage;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static drupal.models.UserModel.loadUserModel;

public class TC0_Files_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionFilesSearch() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(enabled = false, dataProvider = "fileData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "0")})
    @TestCaseId("0")
    public void filesSearch(FilesModel filesData, FilesPage.StatusMenu status) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Click on 'Files'");
        FilesPage filesPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.FILES), FilesPage.class);

        logStep("Enter the MIME Type/File name");
        logStep("Set Status to '-Any-'");
        logStep("Click filter and validate files are filtered as per search condition");
        logStep("Validate if the filed are displayed in Chronological order");
        filesPage.searchFiles(filesData, status)
                 .verifyFilesGridDisplayInChronologicalOrder();

    }

    @DataProvider
    public Object[][] fileData() {
        return new Object[][]{
                {
                        new FilesModel("Test", null),
                        FilesPage.StatusMenu.PERMANENT
                },
                {
                        new FilesModel(null, "audio"),
                        FilesPage.StatusMenu.ANY
                },
                {
                        new FilesModel(null, "audio"),
                        FilesPage.StatusMenu.TEMPORARY
                },
                {
                        new FilesModel("Test", "audio"),
                        FilesPage.StatusMenu.PERMANENT
                }
        };
    }

}
