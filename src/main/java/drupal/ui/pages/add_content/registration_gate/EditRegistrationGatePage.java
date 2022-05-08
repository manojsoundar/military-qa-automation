package drupal.ui.pages.add_content.registration_gate;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EditRegistrationGatePage extends AdministrationToolbar {
    private static final String URL_REGEX = "/edit";
    private static final SelenideElement SAVE_BUTTON = $("#edit-submit").as("Save Button");
    private static final SelenideElement REGISTRATION_GATE_H1_TAG = $("#block-pagetitle h1").as("H1 tag");

    public EditRegistrationGatePage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Edit Registration Gate title: " + REGISTRATION_GATE_H1_TAG.getText() + "not displayed, should contains('Edit Registration gate')");
        log.info("Edit registration gate page loaded properly.");
    }

    public EditRegistrationGatePage editRegistrationForm(List<ISectionDataModel> sectionModel) {
        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(EditRegistrationGatePage.class);
        }
        return this;
    }

    public <P extends MasterPage> P clickSaveButton(Class<P> expectedPage) {
        SAVE_BUTTON.should(exist, appear, enabled)
                   .click();
        return returnInstanceOf(expectedPage);
    }
}
