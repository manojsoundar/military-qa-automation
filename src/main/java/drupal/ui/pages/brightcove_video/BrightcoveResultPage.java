package drupal.ui.pages.brightcove_video;

import com.codeborne.selenide.SelenideElement;
import drupal.enums.content.Category;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;
import mgs.qa.utils.asserts.SoftAssertMt;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@SuppressWarnings("CanBeFinal")
@Log4j2
public class BrightcoveResultPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/video/";
    private static final SelenideElement VIDEO_RESULT_PAGE_BREADCRUMB_CATEGORY_LINK = $(".block--breadcrumb li:nth-child(3) a").as("Category Link");
    private static final SelenideElement VIDEO_RESULT_PAGE_CATEGORY_LINK = $(".field--label-inline a").as("Category link items");
    private static final SelenideElement VIDEO_TITLE = $("div.block.block--page--title h1").as("Title of the video");
    private static final SelenideElement SAVED_MESSAGE_TEXT = $("div.messages.messages--status").as("Save Message");
    private static final SelenideElement EDIT_TAB = $("ul.menu.menu--tabs a[href*='edit']");
    SoftAssert softAssert = SoftAssertMt.getSoftAssert();

    public BrightcoveResultPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Edit Brightcove Video page not loaded.");
        log.info("Edit Brightcove video admin page loaded properly.");
    }

    public BrightcoveResultPage verifyBrightcoveVideoUpdated() {
        String videoTitle = VIDEO_TITLE.getText();
        assertTrue(SAVED_MESSAGE_TEXT.should(exist, appear)
                                     .has(text(videoTitle)), "Saved message is not displayed.");
        log.info(SAVED_MESSAGE_TEXT.getText() + " is displayed");
        return this;
    }

    public BrightcoveResultPage verifyVideoCategoriesAndBreadcrumbFieldsUpdated(DetailsCategoriesModel detailsCategoriesModel) {
        log.info(format(" Saved message is displayed: %s", SAVED_MESSAGE_TEXT.getText()));
        String videoTitle = VIDEO_TITLE.getText();
        assertTrue(SAVED_MESSAGE_TEXT.should(exist, appear)
                                     .has(text(videoTitle)), "Saved message is not displayed.");
        log.info("Verifying Category List");
        for (Category url : detailsCategoriesModel.getCategoriesList()) {
            softAssert.assertTrue(url.getCategoryName()
                                     .contains(VIDEO_RESULT_PAGE_CATEGORY_LINK.getText()), "Failed to find category list in video result page");
        }
        softAssert.assertAll();
        log.info("Verifying Breadcrumb category List");
        assertTrue(detailsCategoriesModel.getBreadcrumbCategory()
                                         .getBreadcrumbCategoryItem()
                                         .contains(VIDEO_RESULT_PAGE_BREADCRUMB_CATEGORY_LINK
                                                 .getText()), "Failed to breadcrumb category link");
        return this;
    }

    public EditBrightcoveVideoPage clickEditTab() {

        EDIT_TAB.should(exist, appear, enabled)
                .click();
        log.info("EDIT link clicked");
        return new EditBrightcoveVideoPage();
    }

    public BrightcoveResultPage validateUpdatedPage(String videoTitle) {

        assertEquals(VIDEO_TITLE.getText()
                                .trim(), videoTitle, "Updated Brightcove video page not displayed..");
        log.info(format("Landing Page : %s created..", videoTitle));
        return this;
    }


}
