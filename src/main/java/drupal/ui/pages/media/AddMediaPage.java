package drupal.ui.pages.media;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddMediaPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/media/add";
    private static final SelenideElement ADD_MEDIA_H_1_TAG = $("#block-pagetitle h1").as("Add Media Page H1 Tag");

    public AddMediaPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Add Media Page not loaded.");
        assertTrue(ADD_MEDIA_H_1_TAG.should(exist, appear)
                                    .has(text("Add media item")), "Correct page is not displayed.");
        log.info("Add Media Page loaded properly.");
    }

    public <P extends MasterPage> P clickMediaItem(MediaItem mediaItem, Class<P> expectedClass) {
        assertTrue(mediaItem.getMediaItemLink()
                            .should(exist, appear)
                            .exists(), mediaItem.name() + " is not displayed.");
        log.info(mediaItem.name() + " is displayed");
        mediaItem.getMediaItemLink()
                 .should(visible, enabled)
                 .click();

        return returnInstanceOf(expectedClass);
    }

    @Getter
    @AllArgsConstructor
    public enum MediaItem {
        AUDIO($("#block-mainpagecontent a[href*='/audio'] span")),
        BRIGHTCOVE_VIDEO($("#block-mainpagecontent a[href*='/brightcove_video'] span")),
        DOCUMENT($("#block-mainpagecontent a[href*='/document'] span")),
        FACEBOOK($("#block-mainpagecontent a[href*='/facebook'] span")),
        IMAGE($("#block-mainpagecontent a[href*='/image'] span")),
        INSTAGRAM($("#block-mainpagecontent a[href*='/instagram'] span")),
        PINTEREST($("#block-mainpagecontent a[href*='/pinterest'] span")),
        TWITTER($("#block-mainpagecontent a[href*='/twitter'] span")),
        YOUTUBE_VIDEO($("#block-mainpagecontent a[href*='/youtube'] span"));

        private final SelenideElement mediaItemLink;
    }

}
