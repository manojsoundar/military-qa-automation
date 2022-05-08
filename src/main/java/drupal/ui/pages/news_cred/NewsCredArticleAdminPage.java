package drupal.ui.pages.news_cred;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.NewsCredModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static mgs.qa.utils.DateTimeUtils.convertDate;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Log4j2
public class NewsCredArticleAdminPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/admin/content/newscred";
    private static final SelenideElement NEWS_CRED_ARTICLE_ADMIN_PAGE_TITLE = $("#block-pagetitle h1");
    private static final SelenideElement SEARCH_BY_SUBTITLE_INPUT = $("input#edit-combine").as("Search by Title textbox");
    private static final SelenideElement PUBLISHED_DROPDOWN = $("select#edit-status").as("Published Dropdown");
    private static final SelenideElement REVIEWED_DROPDOWN = $("select#edit-field-reviewed-newscred-value").as("Reviewed Dropdown");
    private static final SelenideElement PUBLISHED_DATE_START_INPUT = $("input#edit-field-date-value-min").as("Published Date (start) textbox");
    private static final SelenideElement PUBLISHED_DATE_END_INPUT = $("input#edit-field-date-value-max").as("Published Date (end) textbox");
    private static final ElementsCollection WEB_TABLE_DATE_LIST = $$("table.views-view-table time").as("WebTable date elements collection");
    private static final ElementsCollection WEB_TABLE_TITLES_LIST = $$("table.views-view-table td.views-field-title a[href^='/daily-news/']").as("Title column of WebTable");

    private static final SelenideElement APPLY_BUTTON = $("input#edit-submit-newscred-article-admin");

    public NewsCredArticleAdminPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Video page not loaded.");
        assertTrue(NEWS_CRED_ARTICLE_ADMIN_PAGE_TITLE.should(exist, appear)
                                                     .has(text("NewsCred Article Admin")),
                   "Correct page title not displayed.");
        log.info("NewsCred Article Admin page loaded properly.");
    }

    public NewsCredArticleAdminPage searchNewsCred(NewsCredModel newCredData) {
        if (newCredData.getSearchValue() != null) {
            SEARCH_BY_SUBTITLE_INPUT.should(exist, appear)
                                    .setValue(newCredData.getSearchValue());
        }

        if (newCredData.getPublished() != null) {
            PUBLISHED_DROPDOWN.should(exist, appear)
                              .selectOption(newCredData.getPublished());
        }

        if (newCredData.getReviewed() != null) {
            REVIEWED_DROPDOWN.should(exist, appear)
                             .selectOption(newCredData.getReviewed());
        }

        if (newCredData.getStartDate() != null) {
            PUBLISHED_DATE_START_INPUT.should(exist, appear)
                                      .setValue(newCredData.getStartDate());
        }

        if (newCredData.getEndDate() != null) {
            PUBLISHED_DATE_END_INPUT.should(exist, appear)
                                    .setValue(newCredData.getEndDate());
        }

        APPLY_BUTTON.should(exist, appear)
                    .click();
        return this;
    }

    public NewsCredArticleAdminPage verifyTitlesAreInChronologicalOrder() {
        String url = WEB_TABLE_TITLES_LIST.first()
                                          .getAttribute("href");
        assertFalse(StringUtils.isEmpty(url), "URL is null or empty string");
        assertTrue(url.contains(convertDate(LocalDate.now()
                                                     .toString(), "yyyy-MM-dd", "yyyy/MM/dd")));

        return this;
    }

}

