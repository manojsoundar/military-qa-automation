package drupal.ui.pages.add_content.ap_dashboard;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.components.generic.Tables;
import drupal.models.TimeStampPattern;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;
import mgs.qa.utils.asserts.SoftAssertMt;

import java.time.format.DateTimeFormatter;
import java.util.Map;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static common.CommonMethods.verifyGridDisplayInChronologicalOrder;
import static java.time.LocalDateTime.now;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Log4j2
public class APDashboardPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/ap-dashboard";
    private static final SelenideElement AP_FEEDS_SEARCH_FORM_LABEL = $(".fieldset-legend").as("AP FEEDS SEARCH FORM Label");
    private static final SelenideElement SEARCH_INPUT = $("#edit-search").as("Search input");
    private static final SelenideElement SEARCH_BUTTON = $("#edit-submit").as("Search button");
    private static final ElementsCollection ARTICLE_HEAD_LINE_LIST = $$x(".//*[@class='responsive-enabled']/tbody/tr/td[1]").as("head line");
    private static final ElementsCollection ARTICLE_VERSION_CREATED_LIST = $$x(".//*[@class='responsive-enabled']/tbody/tr/td[3]").as("version created list");
    private static final ElementsCollection CLONE_ARTICLE_BUTTON_LIST = $$x(".//*[@class='responsive-enabled']/tbody/tr/td//a").as("clone article button");
    private static final SelenideElement RECORDS_MESSAGE_WEB_ELEMENT = $("div.messages--status h2.visually-hidden");

    public APDashboardPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "AP Dashboard Page not loaded..");
        assertEquals(AP_FEEDS_SEARCH_FORM_LABEL.getText()
                                               .trim(), "AP FEEDS SEARCH FORM", "AP FEEDS SEARCH FORM Label not loaded");
        log.info("AP Dashboard Page loaded");
    }

    public APDashboardPage searchForApFeedsSearch(APNewsDashBoardModel apNewsDashBoardModel) {

        if (apNewsDashBoardModel.getSearchInput() != null) {
            SEARCH_INPUT.should(enabled, visible)
                        .setValue("\"" + apNewsDashBoardModel.getSearchInput() + "\"");
            log.info("AP FEEDS SEARCH FORM Search text: " + apNewsDashBoardModel.getSearchInput());
        }

        SEARCH_BUTTON.should(visible, enabled)
                     .click();
        log.info("Search button clicked");
        SEARCH_BUTTON.should(enabled);
        RECORDS_MESSAGE_WEB_ELEMENT.should(visible, exist);
        assertFalse(CLONE_ARTICLE_BUTTON_LIST.isEmpty(), "No Records found");

        if (apNewsDashBoardModel.getSearchInput() != null) {
            var softAssert = SoftAssertMt.getSoftAssert();
            for (SelenideElement element : ARTICLE_HEAD_LINE_LIST) {
                softAssert.assertTrue(element.getText()
                                             .contains(apNewsDashBoardModel.getSearchInput()), "Grid is not filtered based on Search String");
            }
            softAssert.assertAll();
            log.info("Search String " + apNewsDashBoardModel.getSearchInput() + " matched with the results");
        }

        return this;
    }

    public APDashboardPage verifyTableDisplayInChronologicalOrder(String searchString) {
        verifyGridDisplayInChronologicalOrder(ARTICLE_VERSION_CREATED_LIST, TimeStampPattern.DATE_TIME_PATTERN);

        if (searchString == null) {
            assertTrue(ARTICLE_VERSION_CREATED_LIST.first()
                                                   .getText()
                                                   .contains(now().format(DateTimeFormatter.ofPattern(TimeStampPattern.DATE_WITH_DELIMITER_PATTERN))), "Today's date doesn't exist");
        }
        return this;
    }

    public Map<String, String> getArticleDataFromTable() {

        Tables table = new Tables();
        ElementsCollection rowData = table.getRowCells(0);

        return Map.of("HEADLINE", rowData.get(0)
                                         .should(enabled, visible)
                                         .getText()
                                         .trim(),
                      "ITEMID", rowData.get(1)
                                       .should(enabled, visible)
                                       .getText()
                                       .trim(),
                      "DATE", rowData.get(2)
                                     .should(enabled, visible)
                                     .getText()
                                     .split(" ")[0].trim());

    }

    public APDashboardArticleClonePage validateCloneArticle(Map<String, String> article, APNewsDashBoardModel apNewsDashBoardModel) {

        if (!CLONE_ARTICLE_BUTTON_LIST.isEmpty()) {
            CLONE_ARTICLE_BUTTON_LIST.first()
                                     .should(enabled, visible)
                                     .click();
            log.info("Clone article button clicked");
        }

        switchTo().window(1);
        new APDashboardArticlePage().validateArticle(article, apNewsDashBoardModel);
        return new APDashboardArticleClonePage();
    }

    public APDashboardArticlePage clickOnCloneArticle() {
        if (!CLONE_ARTICLE_BUTTON_LIST.isEmpty()) {
            CLONE_ARTICLE_BUTTON_LIST.first()
                                     .should(enabled, visible)
                                     .click();
            log.info("Clone article button clicked");
        }
        switchTo().window(1);
        return new APDashboardArticlePage();
    }

}
