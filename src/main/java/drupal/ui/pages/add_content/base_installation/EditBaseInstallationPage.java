package drupal.ui.pages.add_content.base_installation;

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
public class EditBaseInstallationPage extends AdministrationToolbar implements AttachAnImage {

    private static final String URL_REGEX = "/edit";
    private static final SelenideElement EDIT_BASE_INSTALLATION_H_1_TAG = $("#block-pagetitle h1.page-title em").as("Edit Merchant Page H1 Tag");
    private static final SelenideElement SAVE_INPUT = $("input[id=edit-submit]").as("Save button");

    public EditBaseInstallationPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Edit Base installation Page isn't loaded.");
        EDIT_BASE_INSTALLATION_H_1_TAG.should(exist, appear)
                                      .shouldHave(text("Edit Base installation"));
        log.info("Edit Base installation Page loaded properly.");
    }

    public BaseInstallationPage editBaseInstallationPage(List<ISectionDataModel> sectionModel) {
        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(EditBaseInstallationPage.class);
        }
        return clickSaveButton();
    }

    public BaseInstallationPage clickSaveButton() {
        SAVE_INPUT.should(exist, appear)
                  .scrollTo()
                  .click();

        return new BaseInstallationPage();
    }
}
