package drupal.ui.pages.media;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddYouTubeVideoPage extends AddMediaBasePage {

    private static final String URL_REGEX = "/media/add/youtube";
    private static final SelenideElement YOUTUBE_VIDEO_H_1_TAG = $("#block-pagetitle h1").as("YouTube Video Page H1 Tag");

    public AddYouTubeVideoPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "YouTube Video Page not loaded.");
        assertTrue(YOUTUBE_VIDEO_H_1_TAG.should(exist, appear)
                                        .has(text("Add YouTube video")), "Correct page is not displayed.");
        log.info("YouTube Video Page loaded properly.");
    }

}