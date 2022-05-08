package drupal.ui.pages.components.slideshows;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class SlideShowResultPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/slideshow/";
    private static final SelenideElement SLIDE_SHOW_SUCCESS_TEXT = $(".block--page--title h1 .title").as("Slideshow Page H1 Tag");
    private static final SelenideElement EDIT_TAB = $(".menu--tabs a[href*='edit']");
    private static final SelenideElement TITLE_STATUS_MESSAGE = $(".block--page--title h1 .title");

    public SlideShowResultPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Slideshow Page isn't loaded.");
        log.info("Slideshow Page loaded properly.");
    }

    public SlideShowResultPage verifyDrupalSlideShowCreated(SlideshowsModel slideshowsModel) {
        assertTrue(verifyURLLoaded(URL_REGEX + "\\d{3}"), "Drupal Slideshow page isn't created");
        assertTrue(SLIDE_SHOW_SUCCESS_TEXT.should(exist, appear)
                                          .has(text(slideshowsModel.getTitle())), "Correct status message not displayed.");
        log.info(format("Newsletter Issue %s created", slideshowsModel.getTitle()));
        return this;
    }

    public EditSlideshowPage clickEditTab() {

        EDIT_TAB.should(enabled, visible)
                .click();
        return new EditSlideshowPage();
    }

    public SlideShowResultPage verifyUpdatedSlideshow(SlideshowsModel slideshowsModel) {

        assertEquals(TITLE_STATUS_MESSAGE.should(visible, appear)
                                         .getText()
                                         .trim(), slideshowsModel.getTitle(), "SlideShow updated message isn't displayed");

        return this;
    }

}
