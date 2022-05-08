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
public class MostPopularPage extends GlobalHeaderComponent {

    private static final String URL = "/video/most-popular";
    private static final SelenideElement MOST_POPULAR_PAGE_TITLE = $(".block.block--page--title span");

    public MostPopularPage() {
        assertTrue(verifyURLLoaded(URL), "Most Popular Military Videos page not loaded.");
        assertEquals(MOST_POPULAR_PAGE_TITLE.should(exist, appear)
                                            .getText()
                                            .trim(), "Most Popular Military Videos",
                     "Correct page title not displayed.");
        log.info("Most Popular Military Videos page loaded properly.");
    }

}
