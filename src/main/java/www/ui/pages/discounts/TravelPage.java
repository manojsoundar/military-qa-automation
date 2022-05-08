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
public class TravelPage extends GlobalHeaderComponent {

    private static final String URL = "/discounts/travel";
    private static final SelenideElement TRAVEL_PAGE_TITLE = $("div.block--page--title span").as("Page title");

    public TravelPage() {
        assertTrue(verifyURLLoaded(URL), "Travel page not loaded.");
        assertEquals(TRAVEL_PAGE_TITLE.should(exist, appear)
                                      .getText()
                                      .trim(), "Military Travel Discounts",
                     "Correct page title not displayed.");
        log.info("Travel page loaded properly.");
    }

}
