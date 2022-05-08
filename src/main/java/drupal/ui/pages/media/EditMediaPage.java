package drupal.ui.pages.media;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static drupal.ui.pages.media.AddMediaBasePage.SAVE_BUTTON;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EditMediaPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/edit";
    private static final SelenideElement EDIT_MEDIA_PAGE_H_1_TAG = $("#block-pagetitle h1");

    public EditMediaPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Edit Media Page not loaded.");
        assertTrue(EDIT_MEDIA_PAGE_H_1_TAG.should(exist, appear)
                                          .has(text("Edit")), "Correct page is not displayed.");
        log.info("Edit Media Page loaded properly.");

    }

    public EditMediaPage fillIn(List<ISectionDataModel> sectionData) {

        for (ISectionDataModel currentSectionModel : sectionData) {
            currentSectionModel.setData(EditMediaPage.class);
        }

        return this;
    }

    public MediaPage clickSaveButton() {
        SAVE_BUTTON.should(exist, visible, enabled)
                   .scrollTo()
                   .click();
        return new MediaPage();
    }

}
