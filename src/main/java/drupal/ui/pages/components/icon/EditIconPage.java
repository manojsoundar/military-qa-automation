package drupal.ui.pages.components.icon;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static drupal.ui.pages.components.icon.AddIconPage.SAVE_BUTTON;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EditIconPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/edit";
    private static final SelenideElement EDIT_ICON_CONTENT_H_1_TAG = $("#block-pagetitle h1");

    public EditIconPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Edit Icon content Page not loaded.");
        assertEquals(EDIT_ICON_CONTENT_H_1_TAG.should(exist, appear)
                                              .getText(), "Edit Icon", "Correct page is not displayed.");
        log.info("Edit Icon content Page loaded properly.");
    }

    public EditIconPage fillIn(List<ISectionDataModel> sectionModel) {

        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(EditIconPage.class);
        }
        return this;
    }

    public IconResultPage clickSaveButton() {
        SAVE_BUTTON.should(appear, enabled, visible)
                   .click();
        return new IconResultPage();
    }
}
