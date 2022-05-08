package drupal.ui.pages.components.content_list;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class ContentListLandingPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/content_list/";
    private static final SelenideElement CONTENT_LIST_H_1_TAG = $(".block--page--title .title").as("Content List Page H1 Tag");
    private static final SelenideElement EDIT_TAB = $(".menu--tabs a[href*='/edit']");
    private static final ElementsCollection SAVED_NODE_DATA = $$("#block-mainpagecontent a").as("Result page text");
    private static final ElementsCollection ENTITY_SET = $$(".field--video-content a").as("Entity list data");

    public ContentListLandingPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Content List Page isn't loaded.");
    }

    public ContentListLandingPage verifyDrupalContentCreated() {
        assertTrue(verifyURLLoaded(URL_REGEX + "\\d{5}"),
                   "Drupal Regular Content List Page isn't created");
        return this;
    }

    public ContentListLandingPage verifyBrightCoveVideoContentCreated(String expectedTitle) {
        assertTrue(verifyURLLoaded(URL_REGEX + "\\d{5}"),
                   "Drupal Bright Cove Video Content List Page isn't created");
        assertEquals(CONTENT_LIST_H_1_TAG.getText()
                                         .trim(), expectedTitle, "Page not displayed..");
        return this;
    }

    public ContentListLandingPage verifyDrupalContentUpdated(String updatedTitle) {
        assertTrue(verifyURLLoaded(URL_REGEX + "\\d{5}"),
                   "Drupal Regular Content List Page isn't created");
        assertEquals(CONTENT_LIST_H_1_TAG.getText()
                                         .trim(), updatedTitle, "Title not updated");
        return this;
    }

    public ContentListLandingPage verifyDrupalCuratedNodeUpdated(List<String> nodeText) {
        assertTrue(verifyURLLoaded(URL_REGEX + "\\d{5}"),
                   "Drupal Regular Content List Page isn't created");
        List<String> nodeList = SAVED_NODE_DATA.stream()
                                               .map(SelenideElement::getText)
                                               .collect(Collectors.toList());
        assertTrue(nodeList.containsAll(nodeText), "Nodes text is not updated");

        return this;
    }

    public List<String> getEntityListData() {
        return ENTITY_SET.stream()
                         .map(SelenideElement::getText)
                         .collect(Collectors.toList());
    }

    public EditCuratedListPage clickEditTab() {
        EDIT_TAB.should(exist, appear, enabled)
                .click();
        log.info("EDIT link clicked");
        return new EditCuratedListPage();
    }

}
