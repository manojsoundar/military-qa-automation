package military.drupal.ui.content_menu;

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
import drupal.ui.pages.add_content.article.ArticleModel;
import drupal.ui.pages.add_content.article.CreateArticlePage;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static drupal.models.UserModel.loadUserModel;

public class TC250663_Create_An_Article_Advertorial_Test extends AdminUITestBase {
    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateArticleAdvertorial() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "articleData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "250663")})
    @TestCaseId("250663")
    public void createAnArticleAdvertorial(List<ISectionDataModel> articleData) {
        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover over Content Tab");
        logStep("Hover over Add Content");
        logStep("Select ARTICLE");
        logStep("Click 'Save'");
        logStep("Verify a warning message appears attached to the 'Title' input field that states 'Please fill out this field'.");
        CreateArticlePage createArticlePage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.ARTICLE), CreateArticlePage.class);
        createArticlePage.fillArticle(articleData)
                         .clickSaveButtonAndVerifyWarningMessageAppeared();
    }

    @DataProvider
    public Object[][] articleData() {
        return new Object[][]{
                {
                        List.of(new ArticleModel(null, "Evergreen", "Test", "Test", "GI Bill", false, null))
                }
        };
    }
}