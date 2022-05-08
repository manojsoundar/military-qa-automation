package military.www.ui.search;

import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.www.ui.MilitaryUITestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import www.ui.components.GlobalHeaderComponent;
import www.ui.pages.SearchResultsPage;

public class SearchTest extends MilitaryUITestBase {

    @Test(dataProvider = "variants")
    @Priority(level = PriorityLevel.CRITICAL)
    @Attributes(attributes = @Attribute(key = "TestCaseId", value = "0"))
    @TestCaseId("0")
    public void searchTest(String searchTerm, boolean resultsExpected) {
        new GlobalHeaderComponent().searchFor(searchTerm)
                                   .assertSearchResultsDisplayed(resultsExpected);
    }

    @Test(dataProvider = "variants")
    @Priority(level = PriorityLevel.CRITICAL)
    @Attributes(attributes = @Attribute(key = "TestCaseId", value = "0"))
    @TestCaseId("0")
    public void searchTestDirect(String searchTerm, boolean resultsExpected) {

        new SearchResultsPage(searchTerm).assertSearchResultsDisplayed(resultsExpected);
    }

    @DataProvider
    public Object[][] variants() {
        return new Object[][]{
                {"Valkyrie", true},
                {"searchKeywordThatBringsNoResults", false}
        };
    }

}
