package military.drupal.ui.content_menu;

import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.ContentMenu;
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
import static java.lang.String.format;

public class TC249755_Create_An_Article_Un_Publish_Daily_News_Article_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateAnArticle() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "unPublishArticleData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249755")})
    public void createAnDailyUnPublishArticle(String test, List<ISectionDataModel> data) {

        logStep(format("Running test case : %s", test));
        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over Add Content");
        logStep("Click on Article");
        CreateArticlePage createArticlePage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.ARTICLE), CreateArticlePage.class);

        logStep("Enter test name in the Title field");
        logStep("Enter News in the TYPE field");
        logStep("Type Military.com and click on Military.com (1) in the SOURCE field");
        logStep("Type test date in the Display Date field");
        logStep("Type Sarah Blansett and select Sarah Blansett (39669) in the AUTHOR field");
        logStep("Type Type test name in the Contributor/Freelancer field");
        logStep("Type test content in the Blurb (or summary) field");
        logStep("Type test content in Body field");
        logStep("Click 'Attach an Image' in the Images field");
        logStep("Select Daily News content type in the SIDEBAR field");
        logStep("Select Daily News in the CATEGORY field");
        logStep("Type test keywords in the RELATED KEYWORDS field");
        logStep("Click Save");
        logStep("Validate article url version");
        logStep("Article url, click the Edit link (above the Article headline) and return to the Drupal Admin Page");
        logStep("Uncheck the Publish checkbox and click Save");
        logStep("Un-publish the Article page");

        ArticleModel articleModel = data.stream()
                                        .filter(d -> d instanceof ArticleModel)
                                        .map(c -> (ArticleModel) c)
                                        .findFirst()
                                        .get();
        DetailsModel detailsModel = data.stream()
                                        .filter(d -> d instanceof DetailsModel)
                                        .map(c -> (DetailsModel) c)
                                        .findFirst()
                                        .get();
        BylineModel bylineModel = data.stream()
                                      .filter(d -> d instanceof BylineModel)
                                      .map(c -> (BylineModel) c)
                                      .findFirst()
                                      .get();
        ArticlePage articlePage = createArticlePage.fillArticle(data)
                                                   .clickSaveButton()
                                                   .validateArticle(articleModel);

        articleModel.setTitle("Edit Test Article " + timeStampFormat(PATTERN));
        articleModel.setUnPublishArticle(true);
        articlePage.clickEditTab()
                   .editArticle(List.of(articleModel))
                   .clickSaveButton(ArticlePage.class)
                   .validateUpdatedArticle(articleModel, bylineModel, detailsModel);

    }

    @DataProvider
    public Object[][] unPublishArticleData() {
        return new Object[][]{
                {
                        "TC-249755",
                        List.of(
                                ArticleModel.setUnPublishDailyNewsArticleData(),
                                ArticleImageModel.setImageArticleData(),
                                BylineModel.setUnPublishDailyNewsArticleData(),
                                DetailsModel.setUnPublishDailyNewsArticleData()
                        )
                }
        };
    }


}
