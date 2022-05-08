package drupal.ui.pages.add_content.newsletter_issue;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static drupal.ui.pages.add_content.newsletter_issue.CreateNewsLetterIssuePage.ARTICLES_ENTITY_TYPE_DROPDOWN_LIST;
import static drupal.ui.pages.add_content.newsletter_issue.CreateNewsLetterIssuePage.ARTICLES_ENTITY_TYPE_INPUT_LIST;
import static drupal.ui.pages.add_content.newsletter_issue.CreateNewsLetterIssuePage.ARTICLES_OLD_ADD_ANOTHER_ITEM_BUTTON;
import static drupal.ui.pages.add_content.newsletter_issue.CreateNewsLetterIssuePage.ARTICLES_OLD_DEPRECATED_INPUT;
import static drupal.ui.pages.add_content.newsletter_issue.CreateNewsLetterIssuePage.ARTICLES_OR_SNIPPETS_ADD_ANOTHER_ITEM_BUTTON;
import static drupal.ui.pages.add_content.newsletter_issue.CreateNewsLetterIssuePage.EntityType;
import static drupal.ui.pages.add_content.newsletter_issue.CreateNewsLetterIssuePage.LEAD_STORY_ENTITY_TYPE_DROPDOWN;
import static drupal.ui.pages.add_content.newsletter_issue.CreateNewsLetterIssuePage.LEAD_STORY_LABEL_INPUT;
import static drupal.ui.pages.add_content.newsletter_issue.CreateNewsLetterIssuePage.NEWSLETTER_TYPE_DROPDOWN;
import static drupal.ui.pages.add_content.newsletter_issue.CreateNewsLetterIssuePage.NewsletterType;
import static drupal.ui.pages.add_content.newsletter_issue.CreateNewsLetterIssuePage.TRIVIA_INPUT;
import static drupal.ui.pages.add_content.newsletter_issue.CreateNewsLetterIssuePage.VIDEO_EXISTING_MEDIA_INPUT;
import static drupal.ui.pages.add_content.newsletter_issue.CreateNewsLetterIssuePage.VIDEO_INPUT;
import static java.time.Duration.ofSeconds;
import static mgs.qa.utils.LoopUtils.doWhileConditionNotMet;

@SuppressWarnings("FieldMayBeFinal")
@Getter
@AllArgsConstructor
@Log4j2
public class NewsletterContentModel extends MasterPage implements ISectionDataModel {

    // TODO create static final member: "Military Report"

    protected static final String DEFAULT_LEAD_STORY_LABEL = "Watch Gary Sinise and Mark Wahlberg Bond in the Trailer for ‘Joe Bell’";

    protected static final String DEFAULT_ADD_TRIVIA = "Today's Military Trivia (417)";

    private NewsletterType newsletterType;
    private EntityType leadStoryEntityType;
    private String leadStoryLabel;
    private Map<String, CreateNewsLetterIssuePage.EntityType> articlesOrSnippets;
    private List<String> articleOldDeprecated;
    private String videos;
    private String trivia;

    public NewsletterContentModel() {

        newsletterType = NewsletterType.EARLY_BRIEF;
        leadStoryEntityType = EntityType.CONTENT;
        leadStoryLabel = addLeadStoryLabel();
        articlesOrSnippets = addArticlesOrSnippets();
        articleOldDeprecated = List.of("");
        videos = "";
        trivia = "";
    }

    public static String addLeadStoryLabel() {
        return DEFAULT_LEAD_STORY_LABEL;
    }

    public static String addTrivia() {
        return DEFAULT_ADD_TRIVIA;
    }

    public static Map<String, CreateNewsLetterIssuePage.EntityType> addArticlesOrSnippets() {

        Map<String, CreateNewsLetterIssuePage.EntityType> articlesOrSnippets = new LinkedHashMap<>();
        articlesOrSnippets.put("Watch Gary Sinise and Mark Wahlberg Bond in the Trailer for ‘Joe Bell’", CreateNewsLetterIssuePage.EntityType.CONTENT);
        articlesOrSnippets.put("Considering Entrepreneurship? Talk to These 3 People First", CreateNewsLetterIssuePage.EntityType.CONTENT);
        articlesOrSnippets.put("Iran’s Sole Nuclear Power Plant Undergoes Emergency Shutdown", CreateNewsLetterIssuePage.EntityType.CONTENT);
        articlesOrSnippets.put("Top Jobs for JMOs: Learn More in Our FREE Master Class", CreateNewsLetterIssuePage.EntityType.SNIPPET);
        articlesOrSnippets.put("3 Reasons to Take Your Military Family to a National Park", CreateNewsLetterIssuePage.EntityType.CONTENT);
        articlesOrSnippets.put("US Envoy Hopes North Korea Responds Positively on Offered Talks", CreateNewsLetterIssuePage.EntityType.CONTENT);
        articlesOrSnippets.put("How to Achieve Washboard Abs", CreateNewsLetterIssuePage.EntityType.CONTENT);
        articlesOrSnippets.put("Get $200k Family Coverage from Just $14.00 a Month", CreateNewsLetterIssuePage.EntityType.SNIPPET);
        articlesOrSnippets.put("The My HealtheVet Program", CreateNewsLetterIssuePage.EntityType.CONTENT);
        articlesOrSnippets.put("Compare Auto Insurance Premiums and Start Saving Today", CreateNewsLetterIssuePage.EntityType.SNIPPET);

        return articlesOrSnippets;
    }

    public static NewsletterContentModel getMilitaryReportData() {
        return new NewsletterContentModel(NewsletterType.MILITARY_REPORT, null, null, addArticlesOrSnippets(), null, null, null);
    }

    public static NewsletterContentModel getEarlyBriefData() {
        return new NewsletterContentModel(NewsletterType.EARLY_BRIEF, EntityType.CONTENT, addLeadStoryLabel(), addArticlesOrSnippets(), null, "Dogfight From WWII", addTrivia());
    }

    public static NewsletterContentModel getArmyActiveDutyInsiderData() {
        return new NewsletterContentModel(NewsletterType.INSIDER_ARMY_ACTIVE, EntityType.CONTENT, addLeadStoryLabel(), addArticlesOrSnippets(), null, "Newsletters Insiders Video Curated List - Use This One (438)", null);
    }

    private <P extends MasterPage> P selectNewsletterType(Class<P> expectedClass) {

        NEWSLETTER_TYPE_DROPDOWN.should(exist, appear, enabled)
                                .selectOptionContainingText(newsletterType.getNewsletterTypeItem());

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectLeadStory(Class<P> expectedClass) {
        if (!newsletterType.getNewsletterTypeItem()
                           .contains("Military Report")) {
            if (leadStoryEntityType.getEntityTypeItem() != null) {
                LEAD_STORY_ENTITY_TYPE_DROPDOWN.should(exist, appear, enabled)
                                               .selectOptionContainingText(leadStoryEntityType.getEntityTypeItem());
            }
            if (leadStoryLabel != null) {
                LEAD_STORY_LABEL_INPUT.should(exist, appear, enabled)
                                      .setValue(leadStoryLabel);
            }
        }

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectArticlesOrSnippets(Class<P> expectedClass) {

        @SuppressWarnings("SimplifyStreamApiCallChains")
        List<Map.Entry<String, CreateNewsLetterIssuePage.EntityType>> entityTypeEntryList = articlesOrSnippets.entrySet()
                                                                                                              .stream()
                                                                                                              .collect(Collectors.toList());
        for (int i = 0; i < articlesOrSnippets.size(); i++) {
            if (i != 0) {
                ARTICLES_OR_SNIPPETS_ADD_ANOTHER_ITEM_BUTTON.should(exist, appear, enabled)
                                                            .click();
                doWhileConditionNotMet(
                        ARTICLES_OLD_ADD_ANOTHER_ITEM_BUTTON::isEnabled,
                        () -> log.info("waiting ADD_ANOTHER_ITEM being enabled ")
                );
            }
            ARTICLES_ENTITY_TYPE_DROPDOWN_LIST.get(i)
                                              .should(enabled, ofSeconds(20))
                                              .selectOptionContainingText(entityTypeEntryList.get(i)
                                                                                             .getValue()
                                                                                             .getEntityTypeItem());
            ARTICLES_ENTITY_TYPE_INPUT_LIST.get(i)
                                           .should(enabled, ofSeconds(20))
                                           .setValue(entityTypeEntryList.get(i)
                                                                        .getKey());
        }

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeTrivia(Class<P> expectedClass) {

        if (newsletterType.getNewsletterTypeItem()
                          .contains("Early Brief") && trivia != null) {
            TRIVIA_INPUT.should(exist, appear, enabled)
                        .setValue(trivia);
        }

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectVideos(Class<P> expectedClass) {
        if (!newsletterType.getNewsletterTypeItem()
                           .contains("Military Report")) {
            if (newsletterType.getNewsletterTypeItem()
                              .contains("Early Brief")) {
                if (videos != null) {
                    VIDEO_EXISTING_MEDIA_INPUT.should(exist, appear, enabled)
                                              .setValue(videos);
                }
            } else {
                if (videos != null) {
                    VIDEO_INPUT.should(exist, appear, enabled)
                               .setValue(videos);
                }
            }
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectArticleOldDeprecated(Class<P> expectedClass) {

        if (newsletterType.getNewsletterTypeItem()
                          .contains("Military Report") && articleOldDeprecated != null) {
            for (int i = 0; i < articleOldDeprecated.size(); i++) {
                if (i != 0) {
                    ARTICLES_OLD_ADD_ANOTHER_ITEM_BUTTON.should(exist, appear, enabled)
                                                        .click();
                }
                doWhileConditionNotMet(
                        ARTICLES_OLD_ADD_ANOTHER_ITEM_BUTTON::isEnabled,
                        () -> log.info("waiting ADD_ANOTHER_ITEM being enabled ")
                );
                ARTICLES_OLD_DEPRECATED_INPUT.get(i)
                                             .should(appear, ofSeconds(20))
                                             .setValue(articleOldDeprecated.get(i));
            }
        }

        return returnInstanceOf(expectedClass);
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        selectNewsletterType(expectedClass);
        selectLeadStory(expectedClass);
        selectArticlesOrSnippets(expectedClass);
        typeTrivia(expectedClass);
        selectVideos(expectedClass);
        selectArticleOldDeprecated(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

}
