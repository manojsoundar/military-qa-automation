package drupal.ui.pages.components.content_list;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.AddContentListModel;
import drupal.ui.components.AdministrationToolbar;
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
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class ContentListsPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/content/content_list";
    private static final SelenideElement CONTENT_LIST_H_1_TAG = $("#block-pagetitle h1.page-title").as("Curated Lists Page H1 Tag");
    private static final SelenideElement ADD_CONTENT_LIST_BUTTON = $("a[href*='/admin/content/content_list/add']").as("Add Content list button");
    private static final SelenideElement TITLE_INPUT = $("#edit-title").as("Title input");
    private static final SelenideElement TYPE_DROPDOWN = $("#edit-type").as("Type drop down list");
    private static final SelenideElement FILTER_BUTTON = $("#edit-submit-eck-curated-lists").as("Filter button");
    private static final ElementsCollection OPERATIONS_EDIT_BUTTONS_LIST = $$(".views-field ul li a[href*='edit']").as("Operations first edit button");
    private static final ElementsCollection CONTENT_LIST = $$("td.views-field.views-field-title a").as("Content List");

    public ContentListsPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Content List Page isn't loaded.");
        assertEquals(CONTENT_LIST_H_1_TAG.should(exist, appear)
                                         .getText(), "Curated Lists", "Correct page is not displayed.");
        log.info("Content List Page loaded properly.");
    }

    public ContentListsPage filterContentList(String titleName, AddContentListModel addContentListModel) {
        TITLE_INPUT.should(appear, exist)
                   .setValue(titleName);
        TYPE_DROPDOWN.should(exist, appear, enabled)
                     .selectOptionContainingText(addContentListModel.getTypeFilterItem()
                                                                    .getTypeItem());
        FILTER_BUTTON.should(appear, exist, enabled)
                     .click();

        return this;
    }

    public <P extends MasterPage> P clickContentLink(Class<P> expectedPage, String titleName) {
        CONTENT_LIST.find(text(titleName))
                    .should(appear, exist)
                    .click();
        log.info("Clicked on Content for the given title");
        return returnInstanceOf(expectedPage);
    }

    public <P extends MasterPage> P clickEditContentListButton(Class<P> expectedPage, int index, String titleText) {

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

    public AddContentListContentPage clickAddContentListButton() {

        ADD_CONTENT_LIST_BUTTON.should(exist, appear, enabled)
                               .click();

        return new AddContentListContentPage();
    }

}
