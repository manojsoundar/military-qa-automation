package www.ui.pages.videos;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class SpecialOperationsPage extends GlobalHeaderComponent {

    private static final String URL = "video/shock-and-awe/special-operations";
    private static final SelenideElement SPECIAL_OPERATIONS_PAGE_TITLE = $(".block.block--page--title div");

    public SpecialOperationsPage() {
        assertTrue(verifyURLLoaded(URL), "Special Operations page not loaded.");
        assertEquals(SPECIAL_OPERATIONS_PAGE_TITLE.should(exist, appear)
                                                  .getText()
                                                  .trim(), "Special Operations",
                     "Correct page title not displayed.");
        log.info("Special Operations page loaded properly.");

    }

}
