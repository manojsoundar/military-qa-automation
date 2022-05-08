package military.www.meta;

import lombok.SneakyThrows;
import mgs.qa.base.config.TestProperties;
import mgs.qa.testng.Priority;
import military.www.ui.MilitaryUITestBase;
import org.testng.annotations.Test;
import www.ui.pages.SearchResultsPage;

import static mgs.qa.testng.PriorityLevel.CRITICAL;
import static org.testng.Assert.assertEquals;

public class EnvCheckTest extends MilitaryUITestBase {

    /**
     * Verify that script runs against expected environment.
     */
    @SneakyThrows
    @Test()
    @Priority(level = CRITICAL)
    public void environmentCheck() {

        assertEquals(new SearchResultsPage("Valkyrie")
                             .assertSearchResultsDisplayed(true)
                             .getEnvironmentFromResultLinks(), TestProperties.getTestEnvironment());
    }

}
