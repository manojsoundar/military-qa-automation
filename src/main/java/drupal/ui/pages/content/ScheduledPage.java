package drupal.ui.pages.content;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.ContentSearchModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class ScheduledPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/admin/content/scheduled";
    private static final SelenideElement SCHEDULED_PAGE_TITLE = $("#block-pagetitle h1");
    private static final SelenideElement TITLE_INPUT = $("input#edit-title");
    private static final SelenideElement CONTENT_TYPE_DROPDOWN = $("select#edit-type");
    private static final SelenideElement PUBLISHED_DROPDOWN = $("select#edit-status");
    private static final SelenideElement FILTER_BUTTON = $("input#edit-submit-scheduler-scheduled-content");
    private static final ElementsCollection CONTENT_TITLE_LIST = $$("td.views-field.views-field-title a");
    private static final SelenideElement LAST_LINK = $x(".//a[@title='Go to last page']");

    public ScheduledPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Scheduled page not loaded.");
        assertTrue(SCHEDULED_PAGE_TITLE.should(exist, appear)
                                       .has(text("Scheduled Content")), "Correct page title not displayed: " + SCHEDULED_PAGE_TITLE.getText() + "vs. CContent");
        log.info("Scheduled page loaded properly.");
    }

    public ScheduledPage fillSearchCriteriaAndFilter(ContentSearchModel searchData) {

        log.info("Fill Search Criteria and click on Apply button");
        if (searchData.getTitle() != null) {
            TITLE_INPUT.should(visible, enabled)
                       .setValue(searchData.getTitle());
        }

        if (searchData.getContentType() != null) {
            CONTENT_TYPE_DROPDOWN.should(visible, enabled)
                                 .selectOption(searchData.getContentType()
                                                         .getContentTypeItem());
        }

        if (searchData.getPublishedStatus() != null) {
            PUBLISHED_DROPDOWN.should(visible, enabled)
                              .selectOption(searchData.getPublishedStatus()
                                                      .getPublishingStatusType());
        }

        FILTER_BUTTON.should(visible, enabled)
                     .click();

        log.info("Filter button clicked");

        return this;
    }

    public ScheduledPage verifyScheduledArticle(String title) {

        LAST_LINK.should(enabled, visible)
                 .click();
        assertTrue(CONTENT_TITLE_LIST.stream()
                                     .map(SelenideElement::getText)
                                     .collect(Collectors.toList())
                                     .contains(title), "Scheduled Article is not displayed in the list");
        log.info("Scheduled Article is displayed in the list");
        return this;
    }
}
