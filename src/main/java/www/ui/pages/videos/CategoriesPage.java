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
public class CategoriesPage extends GlobalHeaderComponent {

    private static final String URL = "/video/category";
    private static final SelenideElement VIDEOS_CATEGORIES_TITLE = $(".block.block--page--title span");

    public CategoriesPage() {
        assertTrue(verifyURLLoaded(URL), "Video Categories page not loaded.");
        assertEquals(VIDEOS_CATEGORIES_TITLE.should(exist, appear)
                                            .getText()
                                            .trim(), "Video Categories",
                     "Correct page title not displayed.");
        log.info("Video Categories page loaded properly.");
    }

}
