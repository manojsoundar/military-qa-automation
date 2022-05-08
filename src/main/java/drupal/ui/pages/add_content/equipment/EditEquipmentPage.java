package drupal.ui.pages.add_content.equipment;


import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.components.AttachAnImage;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EditEquipmentPage extends AdministrationToolbar implements AttachAnImage {

    private static final String URL_REGEX = "/edit";
    private static final SelenideElement EDIT_EQUIPMENT_PAGE_H_1_TITLE = $("#block-pagetitle .page-title");
    private static final SelenideElement SAVE_BUTTON = $("input#edit-submit[value='Save']");

    public EditEquipmentPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Edit Equipment Page isn't loaded.");
        assertTrue(EDIT_EQUIPMENT_PAGE_H_1_TITLE.should(exist, appear)
                                                .has(text("Edit Equipment")), "Correct page title not displayed: Edit Equipment");
        log.info("Edit Equipment Page loaded properly.");
    }

    public EditEquipmentPage fillIn(List<ISectionDataModel> equipmentSectionData) {

        for (ISectionDataModel equipmentSectionDataModel : equipmentSectionData) {
            equipmentSectionDataModel.setData(EditEquipmentPage.class);
        }
        return this;
    }

    public EquipmentPage clickSaveButton() {
        SAVE_BUTTON.should(exist, appear)
                   .scrollTo()
                   .click();

        return new EquipmentPage();
    }
}
