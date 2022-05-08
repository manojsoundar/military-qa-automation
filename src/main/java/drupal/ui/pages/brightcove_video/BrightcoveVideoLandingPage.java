package drupal.ui.pages.brightcove_video;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.TimeStampPattern;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;
import mgs.qa.utils.CollectionUtils;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static common.CommonMethods.verifyGridDisplayInChronologicalOrder;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Log4j2
public class BrightcoveVideoLandingPage extends AdministrationToolbar {
    private static final String URL_REGEX = "/brightcove_video";
    private static final SelenideElement BRIGHTCOVE_VIDEO_ADMIN_PAGE_TITLE = $("#block-pagetitle h1");
    private static final SelenideElement BRIGHTCOVE_TAB_APPLY_BUTTON = $("#edit-submit-brightcove-video-admin");
    private static final ElementsCollection VIDEO_UPDATED_DATE = $$("td.views-field.views-field-changed");
    private static final ElementsCollection VIDEO_LINK = $$("td.views-field.views-field-name a").as("First video in the grid");

    public BrightcoveVideoLandingPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Video page not loaded.");
        assertTrue(BRIGHTCOVE_VIDEO_ADMIN_PAGE_TITLE.should(exist, appear)
                                                    .has(text("Brightcove Video Admin")), "Correct page title not displayed.");
        log.info("Brightcove video admin page loaded properly.");
    }


    public BrightcoveVideoLandingPage searchForBrightcoveVideo() {
        log.info("Brightcove video admin page-Click on Apply button");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(BRIGHTCOVE_TAB_APPLY_BUTTON.should(visible, enabled))
               .click()
               .build()
               .perform();
        log.info("Apply button clicked");
        verifyGridDisplayInChronologicalOrder(VIDEO_UPDATED_DATE, TimeStampPattern.DAY_DATE_TIME_PATTERN);

        return this;
    }

    public BrightcoveResultPage clickVideo() {
        log.info("Random video link selected and click");
        assertFalse(VIDEO_LINK.isEmpty(), "Video link is empty");
        CollectionUtils.getRandomItem(VIDEO_LINK)
                       .click();
        return new BrightcoveResultPage();
    }

}