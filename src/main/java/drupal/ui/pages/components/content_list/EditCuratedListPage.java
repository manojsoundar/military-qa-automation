package drupal.ui.pages.components.content_list;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.AddContentListModel;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.interactions.Actions;

import javax.annotation.Nonnull;
import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EditCuratedListPage extends AdministrationToolbar {

    protected static final SelenideElement TITLE_INPUT = $("input#edit-title-0-value");
    protected static final SelenideElement CONTENT_LIST_SAVE_BUTTON = $("input#edit-submit[value=Save]");
    private static final String URL_REGEX = "/edit";
    private static final SelenideElement CONTENT_INPUT = $("input#edit-field-nodes-0-target-id").as("Text box");
    private static final ElementsCollection ENTITY_TYPE_INPUT_LIST = $$("input.form-text[id^='edit-field-video-content']").as("Text box list");
    private static final SelenideElement ADD_ANOTHER_ITEM_BUTTON = $("input.button[id^='edit-field-video-content']");
    private static final ElementsCollection MOVE_ENTITY_LIST = $$x("//tr/td[1]/a");
    private static final SelenideElement EDIT_TAB = $(".tabs__tab a[href*='edit']");

    public EditCuratedListPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Content List Page isn't loaded.");
    }

    public EditCuratedListPage fillIn(List<ISectionDataModel> editData) {

        for (ISectionDataModel sectionData : editData) {
            sectionData.setData(EditCuratedListPage.class);
        }
        return this;
    }

    public ContentListLandingPage clickSaveButton() {
        CONTENT_LIST_SAVE_BUTTON.should(exist, appear)
                                .click();
        return new ContentListLandingPage();
    }

    public ContentListLandingPage editBrightcoveCuratedListData(@Nonnull AddContentListModel addContentListModel, List<String> contentList, String updateTitle) {

        if (addContentListModel.getTitle() != null) {
            TITLE_INPUT.should(exist, appear)
                       .setValue(updateTitle);
        }
        for (int j = 0; j < contentList.size(); j++) {
            if (j != 0) {
                ADD_ANOTHER_ITEM_BUTTON.should(exist, appear, enabled)
                                       .scrollTo()
                                       .click();
                waitAjaxJQueryMet(120);
            }

            ENTITY_TYPE_INPUT_LIST.last()
                                  .should(exist, appear, enabled)
                                  .sendKeys(contentList.get(j));
            dragNDropToTopEntityType();
            waitAjaxJQueryMet(120);
        }
        CONTENT_LIST_SAVE_BUTTON.should(exist, appear)
                                .click();

        return new ContentListLandingPage();
    }

    public EditCuratedListPage dragNDropToTopEntityType() {

        new Actions(getDriver()).moveToElement(MOVE_ENTITY_LIST.last())
                                .clickAndHold()
                                .moveToElement(MOVE_ENTITY_LIST.first())
                                .release()
                                .build()
                                .perform();

        return this;
    }

}
