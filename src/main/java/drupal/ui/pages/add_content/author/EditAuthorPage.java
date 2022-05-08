package drupal.ui.pages.add_content.author;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EditAuthorPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/edit";
    private static final SelenideElement EDIT_AUTHOR_PAGE_H_1_TAG = $("#block-pagetitle h1 em");
    private static final SelenideElement SAVE_BUTTON = $("#edit-submit");

    public EditAuthorPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Edit Author Page not loaded..");
        assertTrue(EDIT_AUTHOR_PAGE_H_1_TAG.getText()
                                           .trim()
                                           .contains("Edit Author"), "Edit Author Page title not displayed..");
        log.info("Edit Author Page loaded");
    }

    public EditAuthorPage editAuthorPage(List<ISectionDataModel> authorInputData) {

        for (ISectionDataModel authorSectionDataModel : authorInputData) {
            authorSectionDataModel.setData(EditAuthorPage.class);
        }
        return this;
    }

    public AuthorPage clickSaveButton() {
        SAVE_BUTTON.should(exist, appear, enabled)
                   .scrollTo()
                   .click();
        return new AuthorPage();
    }

}
