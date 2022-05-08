package drupal.ui.pages.components.flexible_space;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EditFlexibleSpacePage extends AdministrationToolbar {

    private static final String URL_REGEX = "/edit";
    private static final SelenideElement EDIT_FLEXIBLE_SPACE_H_1_TAG = $("#block-pagetitle h1.page-title");
    private static final SelenideElement SAVE_BUTTON = $("#edit-submit");

    // TODO to delete if it won't be used ?
    private static final String FLEXIBLE_CONTENT_EDIT_DROP_TOGGLE_BUTTON = "[id*=edit-field-%s-%s] .paragraphs-dropbutton-wrapper button";
    private static final String FLEXIBLE_CONTENT_REMOVE_BUTTON = "//div[contains(@id,'-%s-%s-')]//input[contains(@class,'button') and contains(@value,'Edit')]";
    private static final String FLEXIBLE_CONTENT_CONFIRM_REMOVAL_BUTTON = "//div[contains(@id,'-%s-%s-')]//input[contains(@class,'button') and contains(@value,'Confirm removal')]";

    public EditFlexibleSpacePage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Edit Flexible Space Page not loaded..");
        assertEquals(EDIT_FLEXIBLE_SPACE_H_1_TAG.getText()
                                                .trim(), "Edit Flexible space", "Edit Flexible Space page title not loaded..");
        log.info("Edit Flexible Spaces Page loaded");
    }

    public EditFlexibleSpacePage fillIn(List<ISectionDataModel> sectionModel) {

        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(EditFlexibleSpacePage.class);
        }

        return this;
    }

    public EditFlexibleSpacePage editFlexibleSpaceData(List<ISectionDataModel> sectionModel) {

        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.editData(EditFlexibleSpacePage.class);
        }

        return this;
    }

    public FlexibleSpaceLandingPage saveFlexibleSpaceLanding() {

        SAVE_BUTTON.should(visible, enabled)
                   .click();
        waitAjaxJQueryMet(300);

        return new FlexibleSpaceLandingPage();
    }

}
