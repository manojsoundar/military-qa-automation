package drupal.ui.pages.content;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.CommonMethods;
import drupal.models.ContentSearchModel;
import drupal.models.TimeStampPattern;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.add_content.article.SchedulingOptionsModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static mgs.qa.utils.LoopUtils.waitForConditionToBeMet;
import static org.testng.Assert.assertTrue;

@Log4j2
public class ContentPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/admin/content";
    private static final SelenideElement CONTENT_PAGE_TITLE = $("#block-pagetitle h1");
    private static final SelenideElement TITLE_INPUT = $("input#edit-title");
    private static final SelenideElement CONTENT_TYPE_DROPDOWN = $("select#edit-type");
    private static final SelenideElement PUBLISHED_DROPDOWN = $("select#edit-status");
    private static final SelenideElement ARTICLE_TYPE_DROPDOWN = $("select#edit-field-article-type-value");
    private static final SelenideElement FILTER_BUTTON = $("input#edit-submit-content");
    private static final ElementsCollection OPERATIONS_EDIT_BUTTONS_LIST = $$(".views-field ul li a[href*='edit']").as("Operations first edit button");
    private static final SelenideElement UPDATED_STATUS_MESSAGE = $(".region-highlighted .messages--status").as("Updated Status Message");
    private static final ElementsCollection CONTENT_LIST = $$("td.views-field.views-field-title a").as("Content List");
    private static final SelenideElement UPDATED_ARTICLE_LINK = $(".messages.messages--status em a").as("Updated Article Link");
    private static final ElementsCollection STATUS_MESSAGE_LIST = $$(".messages__item");

    public ContentPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Content page not loaded.");
        assertTrue(CONTENT_PAGE_TITLE.should(exist, appear)
                                     .has(text("Content")), "Correct page title not displayed: " + CONTENT_PAGE_TITLE.getText() + "vs. Content");
        log.info("Content admin page loaded properly.");
    }

    public ContentPage fillSearchCriteriaAndFilter(ContentSearchModel contentSearchModel) {
        log.info("Fill Search Criteria and click on Apply button");
        if (contentSearchModel.getTitle() != null) {
            TITLE_INPUT.should(visible, enabled)
                       .setValue(contentSearchModel.getTitle());
        }

        if (contentSearchModel.getContentType() != null) {
            CONTENT_TYPE_DROPDOWN.should(visible, enabled)
                                 .selectOption(contentSearchModel.getContentType()
                                                                 .getContentTypeItem());
        }

        if (contentSearchModel.getPublishedStatus() != null) {
            PUBLISHED_DROPDOWN.should(visible, enabled)
                              .selectOption(contentSearchModel.getPublishedStatus()
                                                              .getPublishingStatusType());
        }

        if (contentSearchModel.getArticleType() != null) {
            ARTICLE_TYPE_DROPDOWN.should(visible, enabled)
                                 .selectOption(contentSearchModel.getArticleType()
                                                                 .getArticleTypeItem());
        }

        FILTER_BUTTON.should(visible, enabled)
                     .click();

        log.info("Filter button clicked");

        return this;
    }

    public <P extends MasterPage> P clickEditButton(Class<P> expectedPage, int index, String titleText) {

        if (index < 0 && titleText != null) {
            index = CONTENT_LIST.stream()
                                .map(SelenideElement::getText)
                                .collect(Collectors.toList())
                                .indexOf(titleText);
            if (index == -1) {
                log.error(titleText + " does not match in any column");
                throw new AssertionError(titleText + " does not match in any column");
            }
        }
        OPERATIONS_EDIT_BUTTONS_LIST.get(index)
                                    .should(visible, enabled)
                                    .click();
        log.info("Clicked on edit button, index: " + index);
        return returnInstanceOf(expectedPage);
    }

    public <P extends MasterPage> P clickContentLink(Class<P> expectedPage, String titleName) {
        CONTENT_LIST.find(text(titleName))
                    .should(appear, exist)
                    .click();
        log.info("Clicked on Content for the given title");
        return returnInstanceOf(expectedPage);
    }

    public ContentPage verifyUpdatedMessage() {
        waitForConditionToBeMet(
                () -> UPDATED_STATUS_MESSAGE.is(visible),
                30
        );
        assertTrue(UPDATED_STATUS_MESSAGE.should(visible, appear)
                                         .getText()
                                         .contains("updated"), "Article updated message isn't displayed");
        return this;
    }

    public ContentPage verifyScheduledMessage(String articleName, SchedulingOptionsModel schedulingOptionsData) {
        if (schedulingOptionsData.getUnPublishOnDate() != null) {
            assertTrue(STATUS_MESSAGE_LIST.first()
                                          .text()
                                          .contains(articleName + " is scheduled to be unpublished " + CommonMethods.convertDateFormat(TimeStampPattern.DATE_PATTERN, "EEE, MM/dd/yyyy", schedulingOptionsData.getUnPublishOnDate())),
                       "Status message not matching: " + STATUS_MESSAGE_LIST.first()
                                                                            .text());
        } else {
            assertTrue(STATUS_MESSAGE_LIST.first()
                                          .text()
                                          .contains(articleName + " is scheduled to be published " + CommonMethods.convertDateFormat(TimeStampPattern.DATE_PATTERN, "EEE, MM/dd/yyyy", schedulingOptionsData.getPublishOnDate())),
                       "Status message not matching: " + STATUS_MESSAGE_LIST.first()
                                                                            .text());
        }
        return this;
    }

    public <P extends MasterPage> P clickOnPrimaryTab(PrimaryTabItem primaryTabLink, Class<P> expectedClass) {

        assertTrue(primaryTabLink.getTabLink()
                                 .should(exist, appear)
                                 .exists(), primaryTabLink.name() + " is not displayed.");
        log.info(primaryTabLink.name() + " is displayed");
        primaryTabLink.getTabLink()
                      .should(exist, visible, enabled)
                      .click();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P clickOnUpdatedArticleLink(Class<P> expectedClass) {
        UPDATED_ARTICLE_LINK.should(visible, enabled)
                            .click();
        return returnInstanceOf(expectedClass);
    }

    @Getter
    @AllArgsConstructor
    public enum PrimaryTabItem {
        CONTENT($(".tabs__tab a[href='/admin/content']")),
        SCHEDULED($(".tabs__tab a[href*='scheduled']")),
        LOGIN_REG_FORMS($(".tabs__tab a[href*='forms']")),
        BRIGHTCOVE_VIDEOS($(".tabs__tab a[href*='video']")),
        BRIGHTCOVE_PLAYLISTS($(".tabs__tab a[href*='playlist']")),
        FILES($(".tabs__tab a[href*='files']")),
        AP_DASHBOARD($(".tabs__tab a[href*='dashboard']")),
        MEDIA($(".tabs__tab a[href*='media']"));

        private final SelenideElement tabLink;
    }

}