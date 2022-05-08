package military.drupal.ui.content_menu;

import com.epam.reportportal.annotations.attribute.Attributes;
import com.epam.reportportal.annotations.attribute.MultiValueAttribute;
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
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceImageModel;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static common.CommonMethods.timeStampFormat;
import static drupal.enums.content.FlexibleContentSpacePosition.ABOVE_FOLD;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.models.UserModel.loadUserModel;
import static java.lang.String.format;

public class Create_An_Article_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateAnArticle() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "test")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(multiValueAttributes = {@MultiValueAttribute(key = "TestCaseIds", values = {"249701", "249700", "249698", "249699", "249764"})})
    public void createAnArticle(String testcase, List<ISectionDataModel> data) {

        logStep(format("Running test case : %s", testcase));
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
        articlePage.clickEditTab()
                   .editArticle(List.of(articleModel))
                   .clickSaveButton(ArticlePage.class)
                   .validateUpdatedArticle(articleModel, bylineModel, detailsModel);

    }

    @DataProvider
    public Object[][] test() {
        return new Object[][]{
                {
                        "TC249701-Evergreen",
                        List.of(
                                ArticleModel.setEverGreenArticleData(),
                                ArticleImageModel.setImageArticleData(),
                                BylineModel.setEvergreenArticleData(),
                                DetailsModel.setEverGreenData()
                        )
                },
                {
                        "TC249700-News",
                        List.of(
                                ArticleModel.setNewsArticleData(),
                                ArticleImageModel.setImageArticleData(),
                                BylineModel.setNewsArticleData(),
                                DetailsModel.setNewsArticleData()
                        )
                },
                {
                        "TC249698-Advertorial",
                        List.of(
                                new FlexibleContentSpaceImageModel(ABOVE_FOLD, 0, "Test", null, "Test Image"),
                                ArticleModel.setAdvertorialArticleData(),
                                ArticleImageModel.setImageArticleData(),
                                BylineModel.setAdvertorialArticleData(),
                                DetailsModel.setAdvertorialArticleData()
                        )
                },
                {
                        "TC249699-Press Release",
                        List.of(
                                ArticleModel.setPressReleaseArticleData(),
                                BylineModel.setPressReleaseArticleData(),
                                DetailsModel.setPressReleaseArticleData()
                        )
                },
                {
                        "TC249764-AdvertorialNoLeadImage",
                        List.of(
                                new FlexibleContentSpaceImageModel(ABOVE_FOLD, 0, "Test", null, "Test Image"),
                                ArticleModel.setAdvertorialNoLeadImageArticleData(),
                                BylineModel.setAdvertorialNoLeadImageArticleData(),
                                DetailsModel.setAdvertorialNoLeadImageArticleData(),
                                ArticleUploadVideoModel.setArticleVideoData()
                        )
                }
        };
    }

}
