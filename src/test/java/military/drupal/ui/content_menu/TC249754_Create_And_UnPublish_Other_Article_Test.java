package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
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
import drupal.ui.pages.add_content.article.ArticleUploadVideoModel;
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
import static drupal.models.TimeStampPattern.DATE_WITH_SLASH_PATTERN;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.models.UserModel.loadUserModel;
import static java.lang.String.format;

public class TC249754_Create_And_UnPublish_Other_Article_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateAnArticle() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "unPublishArticleOtherData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249754")})
    public void createAndUnPublishOtherAnArticle(String testCase, List<ISectionDataModel> data) {

        logStep(format("Running test case : %s", testCase));
        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over Add Content");
        logStep("Click on Article");
        CreateArticlePage createArticlePage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.ARTICLE), CreateArticlePage.class);

        logStep("Enter test name in the Title field");
        logStep("Enter TYPE field");
        logStep("Type Military.com and click on Military.com (1) in the SOURCE field");
        logStep("Type test date in the Display Date field");
        logStep("Type AUTHOR field");
        logStep("Type test content in the Blurb (or summary) field");
        logStep("Type test content in Body field");
        logStep("Click 'Attach an Image' in the Images field or without image");
        logStep("Type test content type SIDEBAR field");
        logStep("Type test content type CATEGORY field");
        logStep("Type test keywords in the RELATED KEYWORDS field");
        logStep("Click Save");
        logStep(format("Drupal News Article is created: " + Configuration.baseUrl + "/off-duty/movies/YYYY/MM/DD/article-name.html", Configuration.baseUrl));
        logStep("Go to the article url, click the Edit link (above the Article headline) and return to the Drupal Admin Page");
        logStep("In the URL Alias section (right column), unClick Generate automatic URL Alias and update the field by adding Daily News information");
        logStep("Remove /money/personal-finance. Replace with daily-news/YYYY/MM/DD   Ex. /daily-news/2021/06/28");
        logStep("UnClick the Publish checkbox (so the article is unpublished) and click Save");
        logStep(format("Go to the article url (www version) and confirm the redirect -> Both the old and new URLs should redirect to the Daily News landing page %s/daily-news", Configuration.baseUrl));

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
        articleModel.setUrlAlias("/daily-news/" + timeStampFormat(DATE_WITH_SLASH_PATTERN));
        articlePage.clickEditTab()
                   .editArticle(List.of(articleModel))
                   .clickSaveButton(ArticlePage.class)
                   .validateUpdatedArticle(articleModel, bylineModel, detailsModel);
    }

    @DataProvider
    public Object[][] unPublishArticleOtherData() {
        return new Object[][]{
                {
                        "TC249754-UnPublish Other Article",
                        List.of(
                                ArticleModel.setUnPublishArticleOtherData(),
                                ArticleImageModel.setImageArticleData(),
                                BylineModel.setUnPublishArticleOtherData(),
                                DetailsModel.setUnPublishArticleOtherData(),
                                ArticleUploadVideoModel.setArticleVideoData()
                        )
                }
        };
    }

}
