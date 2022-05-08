package drupal.ui.pages.add_content.landing_page;

import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import drupal.enums.content.Category;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListOfLinksModel;
import drupal.ui.pages.components.content_list.VideoContentModel;
import drupal.ui.pages.components.flexible_space.EditFlexibleSpacePage;
import drupal.ui.pages.structure.eck_entity_types.call_to_action.CTABaseModel;
import drupal.ui.pages.structure.eck_entity_types.call_to_action.CTAButtonModel;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.driver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.lang.String.format;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Log4j2
public class ResultLandingPage extends AdministrationToolbar {

    // TODO create a static final member: "Left column CTA title is not matching"

    private static final String URL_REGEX = Configuration.baseUrl;
    private static final SelenideElement RESULT_LANDING_PAGE_H_1_TITLE = $(".block--page--title h1 span");
    private static final SelenideElement EDIT_TAB = $("ul.menu.menu--tabs a[href*='edit']");
    private static final SelenideElement LEFT_COLUMN_CTA_TITLE_TEXT = $x("//div[contains(@class,'page__content')]//h3 | //div[contains(@class,'page__content')]//h1 | //div[contains(@class,'page__content')]//h2");
    private static final SelenideElement LEFT_COLUMN_CTA_BLURB_TEXT = $(".page__content .cta-container .blurb");
    private static final SelenideElement LEFT_COLUMN_CTA_BUTTON = $x("//div[contains(@class,'page__content')]//a | //div[contains(@class,'page__content')]//button");
    private static final SelenideElement SIDEBAR_CTA_TITLE_TEXT = $x("//div[contains(@class,'page__sidebar')]//h1 | //div[contains(@class,'page__sidebar')]//h3 | //div[contains(@class,'page__sidebar')]//h2");
    private static final SelenideElement SIDEBAR_CTA_BLURB_TEXT = $(".page__sidebar .cta-container .blurb");
    private static final SelenideElement SIDEBAR_CTA_BUTTON = $x("//div[contains(@class,'page__sidebar')]//a[@class='button'] | //div[contains(@class,'page__sidebar')]//button");
    private static final ElementsCollection UPDATED_BRIGHTCOVE_CURATED_LIST = $$x("*//div[@class='thumbnail--large__content']/ a | *//div[@class='thumbnail--large__content']/ span");
    private static final ElementsCollection ENTITY_TYPE_LIST = $$(".list-view.list-view--one-col ul li a");
    private static final ElementsCollection LIST_VIEW_TITLE_LIST = $$(".col-md-4.page__sidebar div h2");
    private static final SelenideElement SIDEBAR_TEXT = $("div.call-to-action.image-no-variant h3");
    private static final ElementsCollection RESPONSIVE_IMAGES_LIST = $$(".image--responsive");
    private static final SelenideElement SIDEBAR_SLIDESHOW_CAPTION_TEXT = $(".slideshow figcaption");
    private static final SelenideElement SIDEBAR_SLIDESHOW_PREVIOUS_BUTTON = $(".lSAction a.lSPrev");
    private static final SelenideElement SIDEBAR_SLIDESHOW_NEXT_BUTTON = $(".lSAction a.lSNext");
    private static final SelenideElement LIST_OF_LINK_TITLE = $(".paragraph--list-of-links h2");
    private static final ElementsCollection LIST_OF_LINK_PARENT_LINK_TEXT_LIST = $$(".field--link a");
    private static final SelenideElement SOCIAL_LINK_TEXT = $("div.paragraph--social-link span");
    private static final SelenideElement EDIT_SIDEBAR_LINK = $x("//a[text()='Edit sidebar']");
    private final String currentDiscountURL = getDriver().getCurrentUrl();

    public ResultLandingPage() {

        assertTrue(verifyURLLoaded(URL_REGEX), "Result Landing Page not loaded..");
        log.info("Result Landing Page loaded");

    }

    public ResultLandingPage validateLandingPage(AddLandingPageModel landingPageModel) {

        assertEquals(RESULT_LANDING_PAGE_H_1_TITLE.getText()
                                                  .trim(), landingPageModel.getLandingPageTitle(), "landing page not displayed..");
        log.info(format("Landing Page : %s created..", landingPageModel.getLandingPageTitle()
                                                                       .trim()));
        return this;
    }

    public EditLandingPage clickEditTab() {

        EDIT_TAB.should(appear, exist, enabled)
                .click();
        return new EditLandingPage();
    }

    public ResultLandingPage verifyCTA(CTABaseModel ctaBaseData, CTAButtonModel ctaButtonData) {

        assertTrue(LEFT_COLUMN_CTA_TITLE_TEXT.should(exist, visible)
                                             .getText()
                                             .equalsIgnoreCase(ctaBaseData.getTitle()), "Left column CTA title is not matching: " + LEFT_COLUMN_CTA_TITLE_TEXT.should(exist, visible)
                                                                                                                                                              .getText() + "vs." + ctaBaseData.getTitle());
        assertEquals(ctaBaseData.getBlurb(), LEFT_COLUMN_CTA_BLURB_TEXT.should(exist, visible)
                                                                       .getText(), "Left column CTA blurb is not matching");
        if (ctaButtonData != null) {
            assertTrue(LEFT_COLUMN_CTA_BUTTON.should(exist, visible)
                                             .getText()
                                             .equalsIgnoreCase(ctaButtonData.getButtonLabel()), "Left column CTA button is not matching");
        }
        assertTrue(SIDEBAR_CTA_TITLE_TEXT.should(exist, visible)
                                         .getText()
                                         .equalsIgnoreCase(ctaBaseData.getTitle()), "Left column CTA title is not matching");
        assertEquals(ctaBaseData.getBlurb(), SIDEBAR_CTA_BLURB_TEXT.should(exist, visible)
                                                                   .getText(), "Sidebar CTA blurb is not matching");
        if (ctaButtonData != null) {
            assertTrue(SIDEBAR_CTA_BUTTON.should(exist, visible)
                                         .getText()
                                         .equalsIgnoreCase(ctaButtonData.getButtonLabel()), "Sidebar CTA button is not matching");
        }

        return this;
    }

    public ResultLandingPage verifyMobileViewCTA(CTABaseModel ctaBaseData, CTAButtonModel ctaButtonData) {

        System.setProperty("chromeoptions.mobileEmulation", "deviceName=iPhone X");
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone X");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        Config configExtra = driver().config();
        configExtra.browserCapabilities()
                   .merge(chromeOptions);
        SelenideDriver selenideDriverTemplate = new SelenideDriver(configExtra);
        try {
            selenideDriverTemplate.open(currentDiscountURL);
            assertTrue(LEFT_COLUMN_CTA_TITLE_TEXT.should(exist, visible)
                                                 .getText()
                                                 .equalsIgnoreCase(ctaBaseData.getTitle()), "Left column CTA title is not matching");
            assertEquals(ctaBaseData.getBlurb(), LEFT_COLUMN_CTA_BLURB_TEXT.should(exist, visible)
                                                                           .getText(), "Left column CTA blurb is not matching");
            if (ctaButtonData != null) {
                assertTrue(LEFT_COLUMN_CTA_BUTTON.should(exist, visible)
                                                 .getText()
                                                 .equalsIgnoreCase(ctaButtonData.getButtonLabel()), "Left column CTA button is not matching");
            }
            assertTrue(SIDEBAR_CTA_TITLE_TEXT.should(exist, visible)
                                             .getText()
                                             .equalsIgnoreCase(ctaBaseData.getTitle()), "Left column CTA title is not matching");
            assertEquals(ctaBaseData.getBlurb(), SIDEBAR_CTA_BLURB_TEXT.should(exist, visible)
                                                                       .getText(), "Sidebar CTA blurb is not matching");
            if (ctaButtonData != null) {
                assertTrue(SIDEBAR_CTA_BUTTON.should(exist, visible)
                                             .getText()
                                             .equalsIgnoreCase(ctaButtonData.getButtonLabel()), "Sidebar CTA button is not matching");
            }
        } finally {
            selenideDriverTemplate.close();
            System.clearProperty("chromeoptions.mobileEmulation");
            chromeOptions = new ChromeOptions();
            configExtra = driver().config();
            configExtra.browserCapabilities()
                       .merge(chromeOptions);

        }

        return this;
    }

    public ResultLandingPage verifyBrightcoveCuratedListView(List<String> brightcoveCuratedListItems) {
        List<String> updatedEntityType = UPDATED_BRIGHTCOVE_CURATED_LIST.stream()
                                                                        .map(SelenideElement::getText)
                                                                        .collect(Collectors.toList());
        assertTrue(updatedEntityType.size() == brightcoveCuratedListItems.size() && updatedEntityType.containsAll(brightcoveCuratedListItems),
                   "Check if the lists size are equivalent and/or if there are no typo or none valid data in brightcoveCuratedListItems collections");

        return this;
    }

    public ResultLandingPage resultLandingPageValidation(VideoContentModel videoContent, List<String> listViewTitleList) {

        if (!ENTITY_TYPE_LIST.isEmpty()) {
            List<String> updatedEntityType = ENTITY_TYPE_LIST.stream()
                                                             .map(SelenideElement::getText)
                                                             .collect(Collectors.toList());
            List<String> entityLinkList = new ArrayList<>(videoContent.getVideoContent()
                                                                      .keySet());
            assertTrue(updatedEntityType.size() == entityLinkList.size() && updatedEntityType.containsAll(entityLinkList),
                       "Check if the lists size are equivalent and/or if there are no typo or none valid data in entityLinkList collections");
        }

        if (!LIST_VIEW_TITLE_LIST.isEmpty()) {
            List<String> updatedListViewType = LIST_VIEW_TITLE_LIST.stream()
                                                                   .map(SelenideElement::getText)
                                                                   .collect(Collectors.toList());
            assertTrue(updatedListViewType.size() == listViewTitleList.size() && updatedListViewType.containsAll(listViewTitleList),
                       "Check if the lists size are equivalent and/or if there are no typo or none valid data in listViewTitleList collections");
        }

        return this;
    }

    public ResultLandingPage verifyLandingPageUrl(AddLandingPageModel addLandingPageModel) {
        String landingPage = (Configuration.baseUrl + "/" + Category.BENEFITS.getCategoryName() + "/" + addLandingPageModel.getLandingPageTitle()
                                                                                                                           .replace(" ", "-")).toLowerCase();
        assertTrue(getWebDriver().getCurrentUrl()
                                 .contains(landingPage), "Generated landing page url doesn't match. Url: " + landingPage);
        return this;
    }

    public ResultLandingPage verifyCreatedSidebarBlock(String landingPageTitle, String sidebarTitle) {

        assertTrue(RESULT_LANDING_PAGE_H_1_TITLE.should(exist, visible)
                                                .getText()
                                                .contains(landingPageTitle), "Incorrect Landing Page");
        log.info(format("Landing Page : %s created..", landingPageTitle.trim()));
        waitAjaxJQueryMet(100);
        assertTrue(SIDEBAR_TEXT.should(exist, visible)
                               .getText()
                               .contains(sidebarTitle.substring(0, sidebarTitle.length() - 5)), "Incorrect Sidebar value displaying");
        return this;
    }

    public ResultLandingPage verifySidebarTitle(String sidebarTitle) {
        if (!LIST_VIEW_TITLE_LIST.isEmpty()) {
            List<String> sidebarTitleList = LIST_VIEW_TITLE_LIST.stream()
                                                                .map(SelenideElement::getText)
                                                                .collect(Collectors.toList());
            assertTrue(sidebarTitleList.contains(sidebarTitle), "Sidebar Title not matching");
        }
        return this;
    }

    public ResultLandingPage verifyListOfLinkFlexibleSpace(FlexibleContentSpaceListOfLinksModel flexibleContentSpaceListOfLinksModel) {
        assertTrue(LIST_OF_LINK_TITLE.should(appear, visible)
                                     .has(text(flexibleContentSpaceListOfLinksModel.getTitle())), "Correct List of links title is not displayed in landing result page");
        if (!LIST_OF_LINK_PARENT_LINK_TEXT_LIST.isEmpty()) {
            List<String> listOfLinks = LIST_OF_LINK_PARENT_LINK_TEXT_LIST.stream()
                                                                         .map(SelenideElement::getText)
                                                                         .collect(Collectors.toList());
            assertTrue(listOfLinks.contains(flexibleContentSpaceListOfLinksModel.getParentLinkLinkText()), "Parent link url list is not matching!!");
        }
        return this;
    }

    public ResultLandingPage verifyResponsiveSidebarImage() {

        assertFalse(RESPONSIVE_IMAGES_LIST.isEmpty(), "Added responsive image not displayed");
        log.info("Added responsive image displayed");
        return this;
    }

    public EditFlexibleSpacePage clickOnEditSidebarLink() {

        EDIT_SIDEBAR_LINK.should(appear, enabled)
                         .click();
        log.info("Clicked on edit Sidebar link");
        return new EditFlexibleSpacePage();
    }

    public ResultLandingPage verifySidebarSlideshow(String caption) {

        assertEquals(SIDEBAR_SLIDESHOW_CAPTION_TEXT.should(exist, visible)
                                                   .getText(), caption, "Slideshow caption not matching " + SIDEBAR_SLIDESHOW_CAPTION_TEXT);
        assertTrue(SIDEBAR_SLIDESHOW_PREVIOUS_BUTTON.isDisplayed(), "Slideshow previous arrow button not displayed");
        assertTrue(SIDEBAR_SLIDESHOW_NEXT_BUTTON.isDisplayed(), "Slideshow next arrow button not displayed");
        log.info("Sidebar - Slideshow is verified");

        return this;
    }

    public ResultLandingPage verifySidebarSocialLink(String socialLinkTitle) {

        assertEquals(SOCIAL_LINK_TEXT.should(exist, visible)
                                     .getText(), socialLinkTitle, "Social Link title not matching " + SOCIAL_LINK_TEXT);
        log.info("Sidebar - Slideshow is verified");

        return this;
    }
}
