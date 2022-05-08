package military.drupal.ui.content_menu;

import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.ContentMenu;
import drupal.models.ContentSearchModel;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.article.ArticleImageModel;
import drupal.ui.pages.add_content.article.ArticleModel;
import drupal.ui.pages.add_content.article.ArticlePage;
import drupal.ui.pages.add_content.article.BylineModel;
import drupal.ui.pages.add_content.article.CreateArticlePage;
import drupal.ui.pages.add_content.article.DetailsModel;
import drupal.ui.pages.add_content.article.EditArticlePage;
import drupal.ui.pages.add_content.article.SchedulingOptionsModel;
import drupal.ui.pages.content.ContentPage;
import drupal.ui.pages.content.ScheduledPage;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static drupal.models.UserModel.loadUserModel;
import static drupal.ui.pages.content.ContentPage.PrimaryTabItem;

public class TC250661_Edit_An_Article_Set_UnPublish_On_Date_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionEditAnArticleSetUnPublishDate() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "articleData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "250661")})
    @TestCaseId("250661")
    public void editAnArticleSetUnPublishOnDate(List<ISectionDataModel> articleData, List<ISectionDataModel> editArticleData, ContentSearchModel searchData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Create an Evergreen article with publish status");
        CreateArticlePage createArticlePage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.ARTICLE), CreateArticlePage.class);

        ArticleModel articleModel = articleData.stream()
                                               .filter(d -> d instanceof ArticleModel)
                                               .map(c -> (ArticleModel) c)
                                               .findFirst()
                                               .get();
        ArticlePage articlePage = createArticlePage.fillArticle(articleData)
                                                   .clickSaveButton()
                                                   .validateArticle(articleModel);

        logStep("Click on the Content Tab");
        ContentPage contentPage = articlePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.CONTENT), ContentPage.class);

        logStep("In the Search field: Type Test Article in the Title field, Select Published in the Published Status field and Select Evergreen in the Article type field");
        logStep("Press Filter");
        logStep("Find any test article and click Edit in the Operations column");
        EditArticlePage editArticlePage = contentPage.fillSearchCriteriaAndFilter(searchData)
                                                     .clickEditButton(EditArticlePage.class, -1, articleModel.getTitle());

        logStep("Click 'Scheduling Options' to expand the scheduling options menu");
        logStep("Enter today's date in the 'UnPublish On' input field in mm/dd/yyyy format.");
        logStep("Enter a time 2 minutes in the future in the 'UnPublish On' time input field in HH:MM:SS AM/PM format. Time should be entered relative to current time in US Pacific timezone");
        logStep("CLick Save");
        logStep("Verify: page displays the messages: 'Article <article title> has been updated'");
        logStep("Verify: message '<article title> is scheduled to be unpublished <date entered in step 8> <time entered in step 9>'");
        logStep("Navigate to URL path /admin/content/scheduled");
        logStep("Verify the article schedule to be unpublished appears in the list of scheduled content.");
        SchedulingOptionsModel schedulingOptionsModel = editArticleData.stream()
                                                                       .filter(d -> d instanceof SchedulingOptionsModel)
                                                                       .map(c -> (SchedulingOptionsModel) c)
                                                                       .findFirst()
                                                                       .get();
        editArticlePage.editArticle(editArticleData)
                       .clickSaveButton(ContentPage.class)
                       .verifyUpdatedMessage()
                       .verifyScheduledMessage(articleModel.getTitle(), schedulingOptionsModel)
                       .clickOnPrimaryTab(PrimaryTabItem.SCHEDULED, ScheduledPage.class)
                       .verifyScheduledArticle(articleModel.getTitle());

    }

    @DataProvider
    public Object[][] articleData() {
        return new Object[][]{
                {
                        List.of(
                                ArticleModel.setEverGreenArticleData(),
                                ArticleImageModel.setImageArticleData(),
                                BylineModel.setEvergreenArticleData(),
                                DetailsModel.setEverGreenData()
                        ),
                        List.of(
                                SchedulingOptionsModel.getUnPublishData()
                        ),
                        ContentSearchModel.getEverGreenArticleData()
                }
        };
    }

}