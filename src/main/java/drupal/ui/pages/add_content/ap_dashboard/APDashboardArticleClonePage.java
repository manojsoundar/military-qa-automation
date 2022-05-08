package drupal.ui.pages.add_content.ap_dashboard;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class APDashboardArticleClonePage extends AdministrationToolbar {

    private static final String URL_REGEX = "/ap";
    private static final SelenideElement AP_TITLE = $(".block--page--title h1").as("AP title");
    private static final SelenideElement DELETE_BUTTON = $x(".//*[@class='menu menu--tabs']//a[contains(@href,'delete')]").as("Delete button");
    private static final SelenideElement EDIT_BUTTON = $x(".//*[@class='menu menu--tabs']//a[contains(@href,'edit')]").as("edit button");
    private static final SelenideElement UPDATED_STATUS_MESSAGE = $(".messages--status");
    private static final SelenideElement RELATED_TOPIC_TEXT = $(".field--keywords a");

    public APDashboardArticleClonePage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "APDashboardArticleClonePage not loaded");
        log.info("APDashboardArticleClonePage loaded");
    }

    public EditCloneArticlePage clickEditCloneArticle() {

        EDIT_BUTTON.should(exist, enabled, visible)
                   .click();
        return new EditCloneArticlePage();
    }

    public DeleteArticlePage validateAndDeleteClonedArticle(Map<String, String> articleData) {

        assertEquals(AP_TITLE.should(enabled, visible)
                             .getText()
                             .trim(), articleData.get("HEADLINE"), "Article not cloned");
        log.info("Article title displayed after cloning");
        DELETE_BUTTON.should(enabled, visible, appear)
                     .click();
        log.info("Clicked on Delete button");

        return new DeleteArticlePage();
    }

    public APDashboardArticleClonePage validateEditCloneArticle(APNewsDashBoardModel apNewsDashBoardModel) {

        assertTrue(UPDATED_STATUS_MESSAGE.should(visible, appear)
                                         .getText()
                                         .contains("updated"), "Article updated message isn't displayed");

        assertEquals(RELATED_TOPIC_TEXT.getText()
                                       .trim(), apNewsDashBoardModel.getRelatedTopics()
                                                                    .get(apNewsDashBoardModel.getRelatedTopics()
                                                                                             .size() - 1)
                                                                    .toLowerCase(), "Displayed related topic :" + RELATED_TOPIC_TEXT.getText() + " Expected related topic : " + apNewsDashBoardModel.getRelatedTopics()
                                                                                                                                                                                                    .get(apNewsDashBoardModel.getRelatedTopics()
                                                                                                                                                                                                                             .size() - 1)
                                                                                                                                                                                                    .toLowerCase());
        log.info("Success massage displayed after edit");
        return this;
    }

}
