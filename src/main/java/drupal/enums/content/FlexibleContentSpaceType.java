package drupal.enums.content;

import com.codeborne.selenide.ElementsCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.enums.ComparableEnum;

import static com.codeborne.selenide.Selenide.$$;

@AllArgsConstructor
@Getter
public enum FlexibleContentSpaceType implements ComparableEnum {

    ADD_LIST_VIEW("Add List view", $$("input[id*='add-more-button-list-view']")),
    ADD_FULL_TEXT_AREA("Add Full text area", $$("input[id*='add-more-button-full-text-area']")),
    ADD_IMAGE("Add Image", $$("input[id*='add-more-button-linked-image']")),
    ADD_RESPONSIVE_IMAGE("Add Responsive Image", $$("input[id*='add-more-button-responsive-image']")),
    ADD_CTA("Add CTA", $$("input[id*='add-more-button-cta']")),
    ADD_VIDEO("Add Video", $$("input[id*='add-more-button-video']")),
    ADD_SNIPPET_NO_LINK("Add Snippet - No Link", $$("input[id*='add-more-button-snippet-no-link']")),
    ADD_FACEBOOK_FEED_EMBED("Add Facebook Feed Embed", $$("input[id*='add-more-button-facebook-feed-embed']")),
    ADD_SLIDESHOW("Add Slideshow", $$("input[id*='add-more-button-slideshow']")),
    ADD_TRIVIA("Add Trivia", $$("input[value*='Trivia']")),
    ADD_IMAGE_GRID("Add Image grid", $$("input[id*='add-more-button-image-grid']")),
    ADD_FEED("Add Feed", $$("input[id*='add-more-button-feed']")),
    ADD_LIST_OF_LINKS("Add List of links", $$("input[id*='add-more-button-list-of-links']")),
    ADD_CALL_TO_ACTION_OLD("Add Call To Action OLD", $$("input[id*='add-more-button-call-to-action']")),
    ADD_FEATURED_ARTICLE("Add Featured article", $$("input[id*='add-more-button-featured-article']")),
    ADD_SOCIAL_LINK("Add Social link", $$("input[id*='add-more-button-social-link']")),
    ADD_BIO_BOX("Add Bio box", $$("input[id*='add-more-button-bio-box']")),
    ADD_SUCCESS_STORY("Add Success story", $$("input[id*='add-more-button-success-story']")),
    ADD_SPECIFICATION("Add Specification", $$("input[id*='add-more-button-specification']")),
    ADD_WIDGET("Add Widget", $$("input[id*='add-more-button-widget']")),
    ADD_BLOCK("Add Block", $$("input[id*='add-more-button-block']")),
    ADD_EMBEDDED_VIEW("Add Embedded view", $$("input[id*='add-more-button-embedded-view']")),
    ADD_THYMELEAF_TEMPLATE("Add Thymeleaf Template", $$("input[id*='add-more-button-thymeleaf-template']")),
    ADD_COLUMNS("Add Column", $$("input[id*='add-more-button-columns-two']")),
    ADD_AUDIO("Add Audio", $$("input[id*='add-more-button-audio']"));

    private final String contentTypeLinkName;
    private final ElementsCollection contentTypeLinkElement;
}

