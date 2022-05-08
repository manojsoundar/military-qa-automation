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
public class VideoHomePage extends GlobalHeaderComponent {

    private static final String URL = "/video";
    private static final SelenideElement MILITARY_VIDEOS_PAGE_TITLE = $(".block.block--page--title span");

    public VideoHomePage() {
        assertTrue(verifyURLLoaded(URL), "Video page not loaded.");
        assertEquals(MILITARY_VIDEOS_PAGE_TITLE.should(exist, appear)
                                               .getText()
                                               .trim(), "Military Videos",
                     "Correct page title not displayed.");
        log.info("Video page loaded properly.");
    }

}
