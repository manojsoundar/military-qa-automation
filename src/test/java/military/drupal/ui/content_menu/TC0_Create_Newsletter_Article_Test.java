package military.drupal.ui.content_menu;

import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.ContentMenu;
import drupal.models.AddNewsLetterArticleModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.newsletter_article.AddNewsletterArticlePage;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static drupal.models.UserModel.loadUserModel;

public class TC0_Create_Newsletter_Article_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateNewsLetterArticle() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(enabled = false, dataProvider = "createNewsLetterArticleData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "0")})
    @TestCaseId("0")
    public void createNewsLetterArticle(AddNewsLetterArticleModel newsLetterArticleModel) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over 'ADD CONTENT'");
        logStep("Click on 'Newsletter Article'");
        AddNewsletterArticlePage addNewsletterArticlePage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.NEWSLETTER_ARTICLE), AddNewsletterArticlePage.class);

        logStep("Enter Create Newsletter Article Page details");
        logStep("verify -> Newly created Newsletter Article page is loaded");
        addNewsletterArticlePage.createNewsLetterArticleAndSave(newsLetterArticleModel)
                                .verifyNewsLetterArticlePage(newsLetterArticleModel.getTitle());
    }

    @DataProvider
    public Object[][] createNewsLetterArticleData() {
        return new Object[][]{
                {
                        new AddNewsLetterArticleModel()
                }
        };
    }

}
