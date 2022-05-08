package drupal.ui.pages.components.content_list;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddCuratedNodeListPage extends ContentListBasePage {

    private static final String URL_REGEX = "/content_list/add/curated_node_list";
    private static final SelenideElement ADD_CURATED_NODE_CONTENT_H_1_TAG = $("#block-pagetitle h1.page-title").as("Add Curated node list Page H1 Tag");

    public AddCuratedNodeListPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Add curated node page not loaded.");
        assertEquals(ADD_CURATED_NODE_CONTENT_H_1_TAG.should(exist, appear)
                                                     .getText(), "Add Curated node list content", "Correct page is not displayed.");
        log.info("Add content Page loaded properly.");
    }

    public AddCuratedNodeListPage fillIn(List<ISectionDataModel> curatedNodeSectionData) {

        for (ISectionDataModel sectionData : curatedNodeSectionData) {
            sectionData.setData(AddCuratedNodeListPage.class);
        }
        return this;
    }

    public ContentListLandingPage clickSaveButton() {
        CONTENT_LIST_SAVE_BUTTON.should(exist, appear)
                                .click();
        return new ContentListLandingPage();
    }

}
