package drupal.ui.pages.brightcove_video;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EditBrightcoveVideoPage extends AdministrationToolbar {

    protected static final SelenideElement ALTERNATIVE_IMAGE_INPUT = $("input#edit-poster-0-alt").as("Alternative image text box");
    protected static final SelenideElement CATEGORIES_INPUT = $("input.chosen-search-input").as("Categories Dropdown");
    protected static final ElementsCollection CATEGORIES_AVAILABLE_LIST = $$("ul.chosen-results li.active-result");
    protected static final SelenideElement CATEGORIES_CONTAINER_WEB_ELEMENT = $(".chosen-container-active div.chosen-drop");
    protected static final SelenideElement BREADCRUMB_CATEGORY_DROPDOWN = $(".form-wrapper [name*='field_breadcrumb_category']").as("Breadcrumb dropdown");
    protected static final ElementsCollection REMOVE_CATEGORY_CLOSE_ICONS_LIST = $$("a.search-choice-close").as("Categories Close icons list");
    protected static final SelenideElement RELATED_TOPICS_INPUT = $("input.form-text#edit-field-keywords-target-id").as("Related topics");
    protected static final SelenideElement VIDEO_NAME = $("input#edit-name-0-value").as("Name of the video");
    protected static final SelenideElement LONG_DESCRIPTION_INPUT = $("textarea[id*='edit-long-description']").as("Long description");
    private static final String URL_REGEX = "/edit";
    private static final SelenideElement SAVE_BUTTON = $("input#edit-submit").as("Save button");
    private static final SelenideElement VIDEO_TITLE = $("#block-pagetitle em").as("Title of the video");


    public EditBrightcoveVideoPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Edit Brightcove Video page not loaded.");
        log.info("Edit Brightcove video admin page loaded properly.");
    }


    public EditBrightcoveVideoPage editBrightcoveVideoPageSectionsData(List<ISectionDataModel> brightcoveVideoPageSectionData) {

        for (ISectionDataModel BrightcoveVideoPageSection : brightcoveVideoPageSectionData) {
            BrightcoveVideoPageSection.setData(EditBrightcoveVideoPage.class);
        }
        return new EditBrightcoveVideoPage();
    }

    public String getVideoTitle() {
        log.info("Video title to be picked");
        String videoTitle = VIDEO_TITLE.getText();
        log.info(format("Video title for alternative image text is -  %s", videoTitle));

        return videoTitle;
    }

    public BrightcoveResultPage clickSaveButton() {

        SAVE_BUTTON.scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"center\"}")
                   .should(exist, appear)
                   .click();

        return new BrightcoveResultPage();
    }

    public EditBrightcoveVideoPage fillInTitle(String videoTitle) {

        if (videoTitle != null) {
            VIDEO_NAME.should(appear, exist, enabled)
                      .setValue(videoTitle);
        }
        return new EditBrightcoveVideoPage();
    }

}