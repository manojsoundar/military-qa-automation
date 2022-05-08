package www.ui.pages.discounts;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class LodgingPage extends GlobalHeaderComponent {

    private static final String URL = "/discounts/lodging";
    private static final SelenideElement LODGING_PAGE_TITLE = $("div.block--page--title span").as("Page title");

    public LodgingPage() {
        assertTrue(verifyURLLoaded(URL), "Lodging page not loaded.");
        assertEquals(LODGING_PAGE_TITLE.should(exist, appear)
                                       .getText()
                                       .trim(), "Lodging Discounts for Military",
                     "Correct page title not displayed.");
        log.info("Lodging page loaded properly.");
    }

}
