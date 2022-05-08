package drupal.ui.pages.add_content.registration_gate;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.components.AttachAnImage;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class CreateRegistrationGatePage extends AdministrationToolbar implements AttachAnImage {

    protected static final SelenideElement REGISTRATION_PAGE_TITLE = $("#block-pagetitle h1").as("Create Registration Page");
    protected static final SelenideElement TITLE_INPUT = $("#edit-title-0-value").as("Title text box");
    protected static final SelenideElement SIZE_DROPDOWN = $("#edit-field-size").as("Font size drop down list");
    protected static final SelenideElement TEXT_FORMAT_DROPDOWN = $("#edit-field-body-0-format--2").as("text format drop down");
    protected static final SelenideElement REGISTRATION_FORM_DROPDOWN = $("#edit-field-registration-form-0-form-id").as("Registration drop down");
    protected static final SelenideElement ATTACH_AN_IMAGE_BUTTON = $(".form-wrapper input[name*='field_image_']").as("Attach an Image Button");
    protected static final SelenideElement LEFT_COLUMN_INPUT = $("#edit-field-body-0-value").as("Left column input");
    protected static final SelenideElement RIGHT_COLUMN_CONTENT_DROP_DOWN_ARROW = $(".dropbutton-arrow").as("Content drop button arrow");
    protected static final SelenideElement RIGHT_COLUMN_TEXT_AREA_TITLE_INPUT = $(".form-wrapper input[name*='field_title']").as("Content title input box");
    protected static final SelenideElement RIGHT_COLUMN_TEXT_AREA_INPUT = $("html body.cke_editable").as("Left column input");
    protected static final SelenideElement RIGHT_COLUMN_IFRAME = $("iframe.cke_reset").as("left column iframe");
    protected static final SelenideElement CONTENT_RELEASE = $("#edit-field-release-target-id").as("Content release input");
    protected static final SelenideElement SAVE_BUTTON = $("#edit-submit").as("Save Button");
    private static final String URL_REGEX = "/node/add/registration_gate";

    public CreateRegistrationGatePage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Create Registration page not loaded.");
        assertTrue(REGISTRATION_PAGE_TITLE.should(exist, appear)
                                          .shouldHave(text("Create Registration gate"))
                                          .exists(), "Create Registration title:" + REGISTRATION_PAGE_TITLE.getText() + "not displayed, should contains('Create Registration gate')");
        log.info("Create Registration gate page loaded properly.");
    }

    public LandingRegistrationGatePage createRegistrationGate(List<ISectionDataModel> sectionModel) {
        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(CreateRegistrationGatePage.class);
        }
        SAVE_BUTTON.should(exist, appear, enabled)
                   .click();
        return new LandingRegistrationGatePage();
    }
}
