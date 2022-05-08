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
import drupal.ui.pages.add_content.article.ArticleModel;
import drupal.ui.pages.add_content.article.ArticlePage;
import drupal.ui.pages.add_content.article.BylineModel;
import drupal.ui.pages.add_content.article.CreateArticlePage;
import drupal.ui.pages.add_content.article.DetailsModel;
import drupal.ui.pages.add_content.article.GateContentModel;
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

public class TC250556_Create_Edit_Articles_Add_Article_Gate_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateEditArticleAddArticleGate() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "testData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "250023")})
    public void createEditArticleAddArticleGate(String testcase, List<ISectionDataModel> data) {

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
    public Object[][] testData() {
        return new Object[][]{
                {
                        "TC250556-Create/Edit Articles-Add Article gate",
                        List.of(
                                ArticleModel.setPressReleaseArticleData(),
                                GateContentModel.setGateContentData(),
                                BylineModel.setPressReleaseArticleData(),
                                DetailsModel.setPressReleaseArticleData()
                        )
                }
        };
    }

}
