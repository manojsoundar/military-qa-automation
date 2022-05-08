package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
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
import drupal.ui.pages.add_content.article.ArticleUploadVideoModel;
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

import static com.codeborne.selenide.Selenide.open;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.models.UserModel.loadUserModel;
import static java.lang.String.format;

public class TC249796_Move_Article_To_Another_Category_And_Change_URL_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateAnArticle() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "testData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249796")})
    public void moveArticleToAnotherCategoryAndChangeURL(String testCase, List<ISectionDataModel> data, ContentSearchModel contentModel, String redirectUrl, String breadCrumb) {

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
        createArticlePage.fillArticle(data)
                         .clickSaveButton()
                         .validateArticle(articleModel);


        logStep("3. Click on Content Tab");
        ContentPage contentPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.CONTENT), ContentPage.class);

        logStep("4. In the Search section, type Test in the TITLE field, select Article in the CONTENT TYPE field, and press Filter");
        logStep("Search results will display with relevant content below content search");
        logStep("5. Find a Test article and select Edit in the OPERATIONS column (far right column)");
        EditArticlePage editArticlePage = contentPage.fillSearchCriteriaAndFilter(contentModel)
                                                     .clickEditButton(EditArticlePage.class, -1, articleModel.getTitle());

        logStep("6. In the URL Alias section (right column), unClick Generate automatic URL alias and add Test URL in the URL Alias field");
        logStep("Copy the current url (you'll need the full url for testing in a later step)");
        logStep("Replace the listed url to /education/gi-bill/test-news-article-dateFormat");
        String currentAliasUrl = editArticlePage.editAliasUrlArticle(redirectUrl);

        logStep("7. In the Category field, replace the current category and replace it with test content");
        logStep("Test Content Education > GI Bill (type GI Bill to find the category quicker)");
        logStep("Click Save");

        DetailsModel detailsModel = data.stream()
                                        .filter(d -> d instanceof DetailsModel)
                                        .map(c -> (DetailsModel) c)
                                        .findFirst()
                                        .get();
        detailsModel.setCategories(List.of("Education > GI Bill"));
        detailsModel.setDeleteCategories(true);
        editArticlePage.editArticle(List.of(detailsModel))
                       .clickSaveButton(ContentPage.class)
                       .verifyUpdatedMessage();

        logStep("Confirm the article redirects.& the article's new category(ies) are displaying on the article Copy the old Test URL and press Enter");
        logStep(format("The article should redirect to the new URL - %s/education/gi-bill/test-news-article-dateFormat", Configuration.baseUrl));
        logStep("The breadcrumb (the category hyperlinks  are above the article headline) should now say Education  > GI Bill");

        open(Configuration.baseUrl + currentAliasUrl, ArticlePage.class).verifyRedirectedUrl(redirectUrl)
                                                                        .verifyBreadCrumbUrl(breadCrumb);
    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
                {
                        "TC249796",
                        List.of(
                                ArticleModel.setAdvertorialArticleData(),
                                ArticleImageModel.setImageArticleData(),
                                BylineModel.setAdvertorialArticleData(),
                                DetailsModel.setAdvertorialArticleData(),
                                ArticleUploadVideoModel.setArticleVideoData()
                        ),
                        new ContentSearchModel(),
                        "/education/gi-bill/test-article-" + timeStampFormat(PATTERN) + ".html",
                        "Education\n" + "GI Bill"
                }

        };
    }

}
