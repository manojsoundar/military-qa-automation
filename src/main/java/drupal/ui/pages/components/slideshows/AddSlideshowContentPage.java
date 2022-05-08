package drupal.ui.pages.components.slideshows;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddSlideshowContentPage extends AdministrationToolbar {

    protected static final SelenideElement TITLE_INPUT = $("input#edit-title-0-value").as("Title Text box");
    protected static final SelenideElement CAPTION_INPUT = $("input#edit-field-caption-0-value").as("Caption Text box");
    protected static final SelenideElement ATTACH_AN_IMAGE_BUTTON = $("input#edit-field-images-entity-browser-entity-browser-open-modal").as("Attach an image button");
    protected static final SelenideElement SAVE_BUTTON = $("input#edit-submit").as("Save button");
    private static final String URL_REGEX = "/slideshow/add/slideshow";
    private static final SelenideElement ADD_SLIDESHOW_CONTENT_H_1_TAG = $("#block-pagetitle h1.page-title").as("Add Slideshow Page H1 Tag");

    public AddSlideshowContentPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Add Slideshow content Page not loaded.");
        assertEquals(ADD_SLIDESHOW_CONTENT_H_1_TAG.should(appear, enabled)
                                                  .getText(), "Add Slideshow content", "Correct page is not displayed.");
        log.info("Add Slideshow content Page loaded properly.");
    }

    public AddSlideshowContentPage fillSlideshow(List<ISectionDataModel> dataModel) {

        for (ISectionDataModel data : dataModel) {
            data.setData(AddSlideshowContentPage.class);
        }
        return this;
    }

    public SlideShowResultPage clickSaveButton() {
        assertTrue(SAVE_BUTTON.should(exist, appear)
                              .exists(), "Save button is not displayed.");
        log.info("Save button is displayed");
        SAVE_BUTTON.should(visible, enabled)
                   .click();

        return new SlideShowResultPage();
    }

}
