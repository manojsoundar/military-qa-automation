package drupal.ui.pages.structure.eck_entity_types.call_to_action;

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
import static drupal.ui.pages.structure.eck_entity_types.call_to_action.AddCallToActionBasePage.SAVE_BUTTON;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EditCallToActionPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/call_to_action/";
    private static final SelenideElement EDIT_CALL_TO_ACTION_H_1_TAG = $("#block-pagetitle h1");

    public EditCallToActionPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Edit Call To Action Page not loaded.");
        assertTrue(EDIT_CALL_TO_ACTION_H_1_TAG.should(exist, appear)
                                              .has(text("EditCall to Action")), "Edit Call to Action title: " + EDIT_CALL_TO_ACTION_H_1_TAG.getText() + " not displayed,should contain('EditCall to Action')");
        log.info("Edit Call To Action Page loaded properly.");
    }

    public ECKContentListPage fillIn(List<ISectionDataModel> sectionModel) {

        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(EditCallToActionPage.class);
        }
        SAVE_BUTTON.should(appear, enabled, visible)
                   .scrollTo()
                   .click();

        return new ECKContentListPage();
    }
}
