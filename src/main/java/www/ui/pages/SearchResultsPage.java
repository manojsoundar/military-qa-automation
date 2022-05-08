package www.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import mgs.qa.enums.TestEnvironment;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Page object for www.military.com/search(?keyword=...)
 */
@Log4j2
public class SearchResultsPage extends mgs.qa.base.page.MasterPage {

    private static final String URL = "search";
    private static final String URL_PATTERN = "/search?keyword=";

    private static final SelenideElement PAGE_H_1_TITLE = $("div.block.block--page--title").as("Page H1 Title");
    private static final SelenideElement SEARCH_BOX_CONTAINER = $("#views-exposed-form-acquia-global-search-page-1").as("Search Box");
    private static final SelenideElement SEARCH_TERM_TEXT_FIELD = SEARCH_BOX_CONTAINER.$("input#edit-keyword--4")
                                                                                      .as("Search Term Text Field");
    private static final SelenideElement FILTER_DROPDOWN = SEARCH_BOX_CONTAINER.$("select#edit-keyword--3")
                                                                               .as("Filter Dropdown");
    private static final SelenideElement SEARCH_BUTTON = SEARCH_BOX_CONTAINER.$("input#edit-submit-acquia-global-search--3")
                                                                             .as("Search button");

    private static final ElementsCollection SEARCH_RESULTS = $$("div.view__content>div.item-list ul .has-thumbnail");
    private static final ElementsCollection SEARCH_RESULT_LINKS = $$(".views-field.views-field-url>span>a");


    public SearchResultsPage() {
        verifyURLLoaded(URL);
        assertTrue(PAGE_H_1_TITLE.exists() && PAGE_H_1_TITLE.getText()
                                                            .equals("Search Results"), "Correct page title not displayed.");
        log.info("Search Results page loaded.");
    }

    /**
     * Open search results page for specified keyword.
     *
     * @param searchTerm search keyword
     */
    public SearchResultsPage(String searchTerm) {
        log.debug("Opening search results for keyword '{}'...", searchTerm);
        open(URL_PATTERN + searchTerm);
        new SearchResultsPage();
    }

    /**
     * Assert if search results are displayed.
     * Set true if results are expected, false if not.
     *
     * @param resultsExpected are results expected?
     * @return this page
     */
    public SearchResultsPage assertSearchResultsDisplayed(boolean resultsExpected) {
        var resultsDisplayed = !SEARCH_RESULTS.isEmpty();
        var resultsMessage = format("Results for '%s' displayed: %s, expected: %s",
                                    SEARCH_TERM_TEXT_FIELD.getValue(), resultsDisplayed, resultsExpected);
        log.debug(resultsMessage);
        assertEquals(resultsDisplayed, resultsExpected, resultsMessage);
        return this;
    }

    public TestEnvironment getEnvironmentFromResultLinks() throws Exception {

        var envString = SEARCH_RESULT_LINKS.get(0)
                                           .getText()
                                           .substring(8)
                                           .split("\\.", 2)[0];

        log.debug(format("Environment string is [%s]", envString));

        switch (envString) {
            case "dev":
                return TestEnvironment.MILDEV;
            case "dev2":
                return TestEnvironment.MILDEV2;
            case "qa":
                return TestEnvironment.MILQA;
            case "www":
                return TestEnvironment.PROD;
            default:
                throw new Exception(format("String '%s' does not match any expected environment.", envString));
        }
    }

}
