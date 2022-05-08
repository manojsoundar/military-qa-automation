package www.ui.pages.discounts;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class SkiResortsPage extends GlobalHeaderComponent {

    private static final String URL = "/discounts/ski-resorts";
    private static final SelenideElement SHI_RESORTS_PAGE_TITLE = $("div.block--page--title span").as("Page title");

    public SkiResortsPage() {
        assertTrue(verifyURLLoaded(URL), "Ski Resorts page not loaded.");
        assertEquals(SHI_RESORTS_PAGE_TITLE.should(exist)
                                           .getText()
                                           .trim(), "Ski Resort Discounts for Military",
                     "Correct page title not displayed.");
        log.info("Ski Resorts page loaded properly.");
    }

}
