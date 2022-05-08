package drupal.ui.pages.components.content_list;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddContentListContentPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/content/content_list/add";
    private static final SelenideElement ADD_CONTENT_H_1_TAG = $("#block-pagetitle h1.page-title").as("Add Content Page H1 Tag");

    public AddContentListContentPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Add content page not loaded.");
        assertEquals(ADD_CONTENT_H_1_TAG.should(exist, appear)
                                        .getText(), "Add Content list content", "Correct page is not displayed.");
        log.info("Add Snippet content Page loaded properly.");
    }

    public <P extends MasterPage> P clickContentListItem(ContentItem contentItem, Class<P> expectedClass) {
        assertTrue(contentItem.getContentItemLink()
                              .should(exist, appear)
                              .exists(), contentItem.name() + " is not displayed.");
        log.info(contentItem.name() + " is displayed");
        contentItem.getContentItemLink()
                   .should(visible, enabled)
                   .click();

        return returnInstanceOf(expectedClass);
    }

    @Getter
    @AllArgsConstructor
    public enum ContentItem {
        BRIGHTCOVE_LIST($("a[href*='/brightcove_curated_list']")),
        CURATED_LIST($("a[href*='/curated_list']")),
        CURATED_NODE_LIST($("a[href*='/curated_node_list']"));

        private final SelenideElement contentItemLink;
    }

}
