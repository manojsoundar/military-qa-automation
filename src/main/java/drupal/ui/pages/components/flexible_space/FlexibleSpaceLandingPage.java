package drupal.ui.pages.components.flexible_space;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceFacebookFeedEmbedModel;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceFeaturedArticleModel;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceSlideshowModel;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceSocialLinkModel;
import drupal.ui.pages.structure.eck_entity_types.call_to_action.CTABaseModel;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Log4j2
public class FlexibleSpaceLandingPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/flexible_space/";
    private static final SelenideElement FLEXIBLE_SPACE_H_1_TAG = $(".block--page--title h1").as("Flexible Space Page H1 Tag");
    private static final SelenideElement BREADCRUMB = $(".breadcrumb").as("Breadcrumb on Flexible Space");
    private static final SelenideElement EDIT_TAB = $(".menu--tabs li a[href*='edit']");
    private static final SelenideElement CTA_TITLE_TEXT = $(".paragraph--type--cta .field--name-title");
    private static final SelenideElement CTA_INTERNAL_TITLE_TEXT = $x("//*[contains(@class, 'field--name-field-internal-title')]/*[@class='field__item'] | //*[contains(@class, 'field--name-field-internal-title')]");
    private static final SelenideElement CTA_BLURB_TEXT = $x("//*[contains(@class,'field--name-field-blurb')]/*[@class='field__item'] | //*[contains(@class,'field--name-field-blurb')]");
    private static final SelenideElement IMAGE_TITLE = $("div.paragraph--linked-image h2");
    private static final SelenideElement DISPLAY_TITLE = $("div.fb-page a");
    //private static final SelenideElement PROFILE_URL = $("div.field--name-field-facebook-profile-url");
    private static final ElementsCollection IMAGE_LIST = $$(".has-mobile");
    private static final SelenideElement BLOCK_TITLE = $(".field--title");
    private static final SelenideElement SLIDESHOW_TITLE_TEXT = $(".paragraph--type--slideshow .field--name-title");
    private static final SelenideElement SLIDESHOW_CATION_TEXT = $(".lSSlideOuter figcaption");
    private static final SelenideElement SOCIAL_LINK_TEXT = $(".paragraph--social-link a");
    private static final SelenideElement ARTICLE_TITLE = $("div h2.field.field--title");
    private static final SelenideElement ARTICLE_NAME = $("span.text--title span");

    public FlexibleSpaceLandingPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Flexible Page isn't loaded.");
    }

    public FlexibleSpaceLandingPage verifyDrupalFlexibleSpaceCreated() {

        //BREADCRUMB.should(visible, enabled);
        assertTrue(verifyURLLoaded(URL_REGEX + "\\d{4}"), "Drupal Flexible Space Page  isn't created");
        assertTrue(FLEXIBLE_SPACE_H_1_TAG.should(exist, appear)
                                         .has(text("Flexible Space")), "Correct page is not displayed.");

        return this;
    }

    public FlexibleSpaceLandingPage verifyAddedNormalCTA(CTABaseModel normalCallToActionData) {

        assertEquals(CTA_TITLE_TEXT.should(exist, visible)
                                   .getText(), normalCallToActionData.getTitle(), "CTA title not matching");
        assertTrue(CTA_INTERNAL_TITLE_TEXT.should(exist, visible)
                                          .getText()
                                          .contains(normalCallToActionData.getInternalTitle()), "CTA internal title not matching");
        assertTrue(CTA_BLURB_TEXT.should(exist, visible)
                                 .getText()
                                 .contains(normalCallToActionData.getBlurb()), "CTA blurb not matching");
        log.info("Added CTA verified on Flexible Content Space");

        return this;
    }

    public FlexibleSpaceLandingPage verifyFlexibleSpaceImage(String imageTitle) {

        assertEquals(IMAGE_TITLE.should(exist, visible)
                                .getText(), imageTitle, "Image title not matching " + IMAGE_TITLE);
        log.info("Added Image verified on Flexible Content Space");

        return this;
    }

    public FlexibleSpaceLandingPage verifyFlexibleSpaceFacebookFeedEmbed(FlexibleContentSpaceFacebookFeedEmbedModel flexibleContentSpaceFacebookFeedEmbedModel) {

        assertEquals(DISPLAY_TITLE.should(exist, visible)
                                .getAttribute("href"), flexibleContentSpaceFacebookFeedEmbedModel.getAddFacebookUrl(), "Profile hyperlink url not matching " + DISPLAY_TITLE);
        assertEquals(DISPLAY_TITLE.should(exist, visible)
                                  .getText(), flexibleContentSpaceFacebookFeedEmbedModel.getAddFacebookDisplayTitle(), "Display title not matching " + DISPLAY_TITLE);
        log.info("Profile Url & Display title verified on Flexible Content Space");

        return this;
    }

    public FlexibleSpaceLandingPage verifyFlexibleSpaceFeaturedArticle(FlexibleContentSpaceFeaturedArticleModel flexibleContentSpaceFeaturedArticleModel) {

        assertEquals(ARTICLE_TITLE.should(exist, visible)
                                  .getText(), flexibleContentSpaceFeaturedArticleModel.getFeaturedArticleTitle(), "Article title not matching " + ARTICLE_TITLE);
        assertTrue(flexibleContentSpaceFeaturedArticleModel.getFeaturedArticleArticle()
                                                           .contains(ARTICLE_NAME.should(exist, visible)
                                                                                 .getText()), "Article name not matching " + ARTICLE_NAME);
        log.info("Article title and Article name is verified on Flexible Content Space");
        return this;
    }

    public FlexibleSpaceLandingPage verifyFlexibleSpaceBlock(String blockTitle) {

        assertEquals(BLOCK_TITLE.should(exist, visible)
                                .getText(), blockTitle, "Block title doesn't match: " + blockTitle);
        log.info("Added Block verified on Flexible Content Space");

        return this;
    }

    public FlexibleSpaceLandingPage verifyFlexibleSpaceSlideshow(FlexibleContentSpaceSlideshowModel flexibleContentSpaceSlideshowData) {

        //assertEquals(SLIDESHOW_TITLE_TEXT.should(exist, visible).getText(), flexibleContentSpaceSlideshowData.getNewSlideshowTitle(), "Slideshow title not matching " + SLIDESHOW_TITLE_TEXT);
        assertEquals(SLIDESHOW_CATION_TEXT.should(exist, visible)
                                          .getText(), flexibleContentSpaceSlideshowData.getNewSlideshowCaption(), "Slideshow caption not matching " + SLIDESHOW_CATION_TEXT);

        log.info("Added Slideshow verified on Flexible Content Space");

        return this;
    }

    public EditFlexibleSpacePage clickEditTab() {

        EDIT_TAB.should(exist, appear, enabled)
                .click();
        return new EditFlexibleSpacePage();
    }

    public FlexibleSpaceLandingPage verifyFlexibleSpaceResponsiveImage() {

        assertFalse(IMAGE_LIST.isEmpty(), "Displayed image count : " + IMAGE_LIST.size());
        return this;
    }

    public FlexibleSpaceLandingPage verifyFlexibleSpaceSocialLink(FlexibleContentSpaceSocialLinkModel socialLinkData) {

        assertEquals(SOCIAL_LINK_TEXT.should(exist, visible)
                                     .getText(), socialLinkData.getSocialLinkLinkText(), "Social Link title not matching " + SOCIAL_LINK_TEXT.getText());
        assertTrue(SOCIAL_LINK_TEXT.should(exist, visible)
                                   .getAttribute("href")
                                   .contains(socialLinkData.getSocialLinkUrl()), "Social Link URL not matching " + SOCIAL_LINK_TEXT.getText());

        return this;
    }
}
