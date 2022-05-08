package military.drupal.ui.content_menu;

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
import drupal.ui.pages.content.ContentPage;
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

public class TC249858_Edit_An_Article_Evergreen_Update_Date_And_Breadcrumb_Category_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionEditAnEverGreenArticle() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "testData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249858")})
    public void editAnEverGreenArticle(String testCase, List<ISectionDataModel> data, ContentSearchModel editArticleData) {

        logStep(format("Running test case : %s", testCase));
        logStep("1. Login to Drupal");
        logStep("2. Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Pre-condition - Create Article");
        CreateArticlePage createArticlePage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.ARTICLE), CreateArticlePage.class);

        ArticleModel articleModel = data.stream()
                                        .filter(d -> d instanceof ArticleModel)
                                        .map(c -> (ArticleModel) c)
                                        .findFirst()
                                        .get();
        ArticlePage articlePage = createArticlePage.fillArticle(data)
                                                   .clickSaveButton()
                                                   .validateArticle(articleModel);

        logStep("Click on Content Tab");
        ContentPage contentPage = articlePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.CONTENT), ContentPage.class);

        logStep("In the Search fields, Enter Article Title and select the Article type, Published status field");
        logStep("Click EDIT in the Operations Column for the Crated Test Article");
        EditArticlePage editArticlePage = contentPage.fillSearchCriteriaAndFilter(editArticleData)
                                                     .clickEditButton(EditArticlePage.class, -1, articleModel.getTitle());

        logStep("In the Category field, replace the current category and replace it with test content");
        logStep("Test Content Education > Money for School");
        logStep("Click Save");
        logStep("Click Updated Article Link");
        logStep("Validate Saved Article Page");

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

        detailsModel.setCategories(List.of("Education > Money for School"));
        detailsModel.setDeleteCategories(true);
        articleModel.setTitle("Edit Test Article " + timeStampFormat(PATTERN));
        editArticlePage.editArticle(data)
                       .clickSaveButton(ContentPage.class)
                       .verifyUpdatedMessage()
                       .clickOnUpdatedArticleLink(ArticlePage.class)
                       .validateUpdatedArticle(articleModel, bylineModel, detailsModel);

    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
                {
                        "TC249858",
                        List.of(
                                ArticleModel.setEverGreenArticleData(),
                                ArticleImageModel.setImageArticleData(),
                                BylineModel.setEvergreenArticleData(),
                                DetailsModel.setEverGreenData()
                        ),
                        ContentSearchModel.editArticleData()
                }
        };
    }

}
