package drupal.ui.pages.components.content_list;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddCuratedListPage extends ContentListBasePage {

    protected static final ElementsCollection CURATED_LIST_ARTICLES_SELECT = $$(".ui-menu.ui-widget .ui-menu-item a");
    private static final String URL_REGEX = "/content_list/add/curated_list";
    private static final SelenideElement ADD_BRIGHTCOVE_VIDEO_CONTENT_H_1_TAG = $("#block-pagetitle h1.page-title").as("Add curated list page H1 Tag");

    public AddCuratedListPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Add Curated List content Page not loaded.");
        assertEquals(ADD_BRIGHTCOVE_VIDEO_CONTENT_H_1_TAG.should(exist, appear)
                                                         .getText(), "Add Curated List content", "Correct page is not displayed.");
        log.info("Add content Page loaded properly.");
    }

    public AddCuratedListPage fillIn(List<ISectionDataModel> curatedPageSectionData) {

        for (ISectionDataModel curatedPageSection : curatedPageSectionData) {
            curatedPageSection.setData(AddCuratedListPage.class);
        }
        return new AddCuratedListPage();
    }

    public ContentListLandingPage clickSaveButton() {
        CONTENT_LIST_SAVE_BUTTON.should(exist, appear)
                                .click();
        return new ContentListLandingPage();
    }

}
