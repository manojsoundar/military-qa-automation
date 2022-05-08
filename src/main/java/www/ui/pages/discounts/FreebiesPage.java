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
public class FreebiesPage extends GlobalHeaderComponent {

    private static final String URL = "/discounts/freebies";
    private static final SelenideElement FREEBIES_PAGE_TITLE = $("div.block--page--title span").as("Page title");

    public FreebiesPage() {
        assertTrue(verifyURLLoaded(URL), "Freebies page not loaded.");
        assertEquals(FREEBIES_PAGE_TITLE.should(exist, appear)
                                        .getText()
                                        .trim(), "Freebies for Military",
                     "Correct page title not displayed.");
        log.info("Freebies Page loaded properly.");
    }

}
