package drupal.ui.pages.add_content.employer;


import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EditEmployerPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/edit";
    private static final SelenideElement SAVE_BUTTON = $("input#edit-submit");

    public EditEmployerPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Edit Article Page not loaded.");
        log.info("Edit Employer Page loaded properly.");
    }

    public EmployerPage editEmployerPage(List<ISectionDataModel> sectionModel) {
        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.editData(EditEmployerPage.class);
        }
        SAVE_BUTTON.should(appear, enabled, visible)
                   .click();

        return new EmployerPage();
    }
}
