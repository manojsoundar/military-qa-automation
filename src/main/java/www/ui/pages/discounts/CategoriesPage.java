package www.ui.pages.discounts;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class CategoriesPage extends GlobalHeaderComponent {

    private static final String URL = "/discounts/category";
    private static final SelenideElement CATEGORIES_PAGE_CONTAINER = $(".view--categories-terms.view--categories-terms--discounts").as("Page title");

    public CategoriesPage() {
        assertTrue(verifyURLLoaded(URL), "Categories page not loaded.");
        assertTrue(CATEGORIES_PAGE_CONTAINER.should(exist, appear)
                                            .exists(), "Categories page title not displayed.");
        log.info("Discounts -> Categories Page loaded properly.");
    }

}
