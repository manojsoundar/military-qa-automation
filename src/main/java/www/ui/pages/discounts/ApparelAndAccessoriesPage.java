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
public class ApparelAndAccessoriesPage extends GlobalHeaderComponent {

    private static final String URL = "/apparel-and-accessories";
    private static final SelenideElement APPAREL_AND_ACCESSORIES_PAGE_TITLE = $("div.block--page--title span").as("Page title");

    public ApparelAndAccessoriesPage() {
        assertTrue(verifyURLLoaded(URL), "Apparel and Accessories page not loaded.");
        assertEquals(APPAREL_AND_ACCESSORIES_PAGE_TITLE.should(exist, appear)
                                                       .getText()
                                                       .trim(), "Apparel and Accessories",
                     "Correct page title not displayed.");
        log.info("Apparel and Accessories page loaded properly.");
    }

}
