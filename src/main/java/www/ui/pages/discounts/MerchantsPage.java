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
public class MerchantsPage extends GlobalHeaderComponent {

    private static final String URL = "/discounts/merchants";
    private static final SelenideElement MERCHANTS_PAGE_TITLE = $("div.block--page--title span").as("Page title");

    public MerchantsPage() {
        assertTrue(verifyURLLoaded(URL), "Merchants page isn't loaded.");
        assertEquals(MERCHANTS_PAGE_TITLE.should(exist, appear)
                                         .getText()
                                         .trim(), "Military Discount Merchants",
                     "Correct page title not displayed.");
        log.info("Merchants Page loaded properly.");
    }

}
