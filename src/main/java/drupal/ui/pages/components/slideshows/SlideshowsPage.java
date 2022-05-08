package drupal.ui.pages.components.slideshows;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class SlideshowsPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/content/slideshow";
    private static final SelenideElement SLIDESHOWS_H_1_TAG = $("#block-pagetitle h1.page-title").as("Add Slideshow Page H1 Tag");
    private static final SelenideElement ADD_SLIDESHOW_BUTTON = $("a[href$='/add/slideshow']").as("Slideshow Link");
    private static final SelenideElement TITLE_INPUT = $("input#edit-title").as("Title Text box");
    private static final SelenideElement FILTER_BUTTON = $("input#edit-submit-eck-slideshow").as("Title Text box");
    private static final ElementsCollection WEB_TABLE_TITLE_LINKS_LIST = $$(".views-field-title a[href^='/admin/structure/eck/entity/slideshow/']")
            .as("WebTable Title column links list");

    public SlideshowsPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Slideshows Page isn't loaded.");
        assertEquals(SLIDESHOWS_H_1_TAG.should(exist, appear)
                                       .getText(), "Slideshows", "Correct page is not displayed.");
        log.info("Slideshows Page loaded properly.");
    }

    public AddSlideshowContentPage clickOnAddSlideshowButton() {
        assertTrue(ADD_SLIDESHOW_BUTTON.should(appear, enabled)
                                       .exists(), "Add Slideshow button doesn't exist.");
        log.info("Add Slideshow button is displayed");
        ADD_SLIDESHOW_BUTTON.click();

        return new AddSlideshowContentPage();
    }

    public SlideshowsPage performFilterByTitle(String title) {

        TITLE_INPUT.should(appear, enabled)
                   .setValue(title);
        log.info("Title Text box is displayed and value entered");
        FILTER_BUTTON.should(appear, enabled)
                     .click();
        log.info("Clicked on Filter Button");

        return this;
    }

}
