package military.drupal.ui.content_menu;

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
import drupal.ui.pages.add_content.author.AttachAuthorImageModel;
import drupal.ui.pages.add_content.author.AuthorModel;
import drupal.ui.pages.add_content.author.AuthorPage;
import drupal.ui.pages.add_content.author.CreateAuthorPage;
import drupal.ui.pages.add_content.author.EditAuthorPage;
import lombok.extern.log4j.Log4j2;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static drupal.models.UserModel.loadUserModel;

@Log4j2
public class TC249482_Create_Author_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateAuthor() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "addAuthor")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249482")})
    @TestCaseId("249482")
    public void createAuthor(List<ISectionDataModel> authorData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over Add content");
        logStep("Click on Author");
        CreateAuthorPage createAuthorPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.AUTHOR), CreateAuthorPage.class);

        logStep("Create Author with Author's Name,First Name,Last Name,Bio and Existing Image(Hope Seck)");
        logStep("Validation : Drupal Author link is created " + Configuration.baseUrl + "/author/test-author");
        AuthorModel authorInputData = authorData.stream()
                                                .filter(d -> d instanceof AuthorModel)
                                                .map(c -> (AuthorModel) c)
                                                .findFirst()
                                                .get();
        AuthorPage authorPage = createAuthorPage.fillIn(authorData)
                                                .clickSaveButton()
                                                .verifyAuthor(authorInputData);
        EditAuthorPage editAuthorPage = authorPage.clickEditTab();

        logStep("Fill Edit Author Page details");
        logStep("verify -> edited author page is loaded");
        editAuthorPage.editAuthorPage(List.of(authorInputData))
                      .clickSaveButton();
        authorPage.verifyAuthor(authorInputData)
                  .validateEditedAuthor(authorInputData.getName());

    }

    @DataProvider
    public Object[][] addAuthor() {
        return new Object[][]{
                {
                        List.of(
                                new AuthorModel(),
                                new AttachAuthorImageModel("Hope Seck")
                        )
                }
        };
    }

}
