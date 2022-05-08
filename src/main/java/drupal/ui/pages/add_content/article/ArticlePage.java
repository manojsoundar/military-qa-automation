package drupal.ui.pages.add_content.article;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class ArticlePage extends AdministrationToolbar {

    private static final String URL_REGEX = "/test-article";
    private static final SelenideElement ARTICLE_PAGE_H_1_TITLE = $(".block--page--title h1").as("Article Page Title");
    private static final SelenideElement EDIT_TAB = $("ul.menu.menu--tabs a[href*='edit']");
    private static final SelenideElement ARTICLE_STATUS_CONFIRMATION_MESSAGE = $(".content .messages.messages--status");
    private static final SelenideElement BREAD_CRUMB_WEB_ELEMENT = $("nav.breadcrumb");
    private static final SelenideElement UPDATED_STATUS_MESSAGE = $(".messages--status");
    private static final SelenideElement UPDATED_CATEGORY = $(".breadcrumb").as("Updated Category");
    private static final SelenideElement ARTICLE_DATE = $(".date span").as("Article Creation Date");
    private static final SelenideElement ARTICLE_AUTHOR = $(".source span a").as("Article Author");

    public ArticlePage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "ArticlePage not loaded");
        log.info("ArticlePage loaded");
    }

    public ArticlePage validateArticle(ArticleModel articleModel) {

        assertTrue(getDriver().getCurrentUrl()
                              .contains(articleModel.getTitle()
                                                    .toLowerCase()
                                                    .replace(" ", "-")
                                                    .trim()), "URL is not correct");
        log.info(format("URL displayed : %s", getDriver().getCurrentUrl()));
        assertEquals(ARTICLE_PAGE_H_1_TITLE.getText()
                                           .trim(), articleModel.getTitle(), "Displayed Article : " + ARTICLE_PAGE_H_1_TITLE.getText()
                                                                                                                            .trim() + ",Expected Article :" + articleModel.getTitle());
        log.info(format("Article : %s", articleModel.getTitle()));
        return this;
    }

    public ArticlePage validateUpdatedArticle(ArticleModel articleModel, BylineModel bylineModel, DetailsModel detailsModel) {

        if (!detailsModel.isDeleteCategories()) {
            assertTrue(UPDATED_STATUS_MESSAGE.should(visible, appear)
                                             .getText()
                                             .contains("updated"), "Article updated message isn't displayed");
        }

        if (articleModel.getTitle() != null) {
            assertEquals(ARTICLE_PAGE_H_1_TITLE.getText()
                                               .trim(), articleModel.getTitle(), "Displayed Article : " + ARTICLE_PAGE_H_1_TITLE.getText()
                                                                                                                                .trim() + ",Expected Article :" + articleModel.getTitle());
            log.info(String.format("Article displayed : %s", articleModel.getTitle()));
        }
        if (bylineModel.getAuthor() != null) {

            assertTrue(ARTICLE_AUTHOR.should(visible, appear)
                                     .getText()
                                     .contains(bylineModel.getAuthor()), "Article updated author isn't displayed");
            log.info(String.format("Article author displayed : %s", bylineModel.getAuthor()));

        }

        if (detailsModel.isDeleteCategories()) {
            assertTrue(UPDATED_CATEGORY.should(enabled, visible)
                                       .getText()
                                       .trim()
                                       .contains(detailsModel.getCategories()
                                                             .get(0)
                                                             .split(">")[1].trim()), "Display category : " + UPDATED_CATEGORY.getText()
                                                                                                                             .trim() + ", Expected category : " + detailsModel.getCategories()
                                                                                                                                                                              .get(0)
                                                                                                                                                                              .trim());
            log.info(String.format("Category displayed : %s", detailsModel.getCategories()
                                                                          .get(0)));

        }
        if (articleModel.getUrlAlias() != null) {

            String currentUrl = getDriver().getCurrentUrl();
            assertTrue(verifyURLLoaded(URL_REGEX), "ArticlePage not loaded");
            assertTrue(getDriver().getCurrentUrl()
                                  .contains(articleModel.getUrlAlias()), "New URLs isn't redirected to the Daily News landing page");
            getDriver().navigate()
                       .to(currentUrl);
            assertTrue(getDriver().getCurrentUrl()
                                  .contains(articleModel.getUrlAlias()), "Old URL isn't redirected to the Daily News landing page");
        }

        return this;
    }

    public ArticlePage verifyBreadCrumbUrl(String breadCrumb) {
        assertTrue(BREAD_CRUMB_WEB_ELEMENT.should(appear, exist, visible)
                                          .getText()
                                          .contains(breadCrumb), "Bread Crumb text isn't matched");
        return this;
    }

    public ArticlePage verifyRedirectedUrl(String replaceUrl) {
        assertTrue(getWebDriver().getCurrentUrl()
                                 .contains(Configuration.baseUrl + replaceUrl), "Old Url is not navigated");
        return this;
    }

    public EditArticlePage clickEditTab() {

        EDIT_TAB.should(enabled, visible)
                .click();
        return new EditArticlePage();
    }
}
