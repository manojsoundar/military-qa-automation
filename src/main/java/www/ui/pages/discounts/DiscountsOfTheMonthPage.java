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
public class DiscountsOfTheMonthPage extends GlobalHeaderComponent {

    private static final String URL = "/discounts/hot-deals";
    private static final SelenideElement DISCOUNTS_OF_THE_MONTH_PAGE_TITLE = $("div.block--page--title span").as("Page title");

    public DiscountsOfTheMonthPage() {
        assertTrue(verifyURLLoaded(URL), "Discounts of The Month page not loaded.");
        assertEquals(DISCOUNTS_OF_THE_MONTH_PAGE_TITLE.should(exist, appear)
                                                      .getText()
                                                      .trim(), "Hot Deals",
                     "Correct page title not displayed.");
        log.info("Discounts of The Month page loaded properly.");
    }

}
