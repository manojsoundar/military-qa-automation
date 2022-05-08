package drupal.ui.pages.add_content.employer;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static java.time.Duration.ofSeconds;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class CreateEmployerPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/add/employer";
    private static final SelenideElement CREATE_EMPLOYER_PAGE_H_1_TAG = $("#block-pagetitle h1").as("Create Employer Page H1 Tag");
    protected static final SelenideElement EMPLOYERS_NAME_INPUT = $("#edit-title-0-value").as("Employers input");
    protected static final SelenideElement HEADER_ATTACH_AN_IMAGE_BUTTON = $("input#edit-field-header-image-entity-browser-entity-browser-open-modal").as("Attach an Image Button-Header image");
    protected static final SelenideElement HEADER_TITLE_TEXT_INPUT = $("#edit-field-jobbox-title-text-0-value").as("Header title text input");
    protected static final SelenideElement HEADER_BOX_TEXT_INPUT = $("#edit-field-jobbox-box-text-0-value").as("Header box text input");
    protected static final SelenideElement HEADER_LINK_INPUT = $("#edit-field-jobbox-link-0-uri").as("Header link input");
    protected static final SelenideElement LOGO_ATTACH_AN_IMAGE_BUTTON = $("input#edit-field-image-entity-browser-entity-browser-open-modal").as("Attach an Image Button-logo field");
    private static final SelenideElement SAVE_BUTTON = $("#edit-submit").as("Save Button");
    protected static final SelenideElement BLURB_TEXT = $("textarea.form-textarea[name*='field_summary']").as("Blurb text input");
    protected static final SelenideElement BODY_FRAME = $("div#cke_1_contents iframe.cke_reset");
    protected static final SelenideElement BODY_TEXTAREA = $("body.cke_editable p").as("Body Input");
    protected static final SelenideElement CONTENT_RELEASE_INPUT = $("input#edit-field-release-target-id").as("Content release input");


    public CreateEmployerPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Create Employer Page not loaded..");
        assertEquals(CREATE_EMPLOYER_PAGE_H_1_TAG.getText()
                                                 .trim(), "Create Employer", "Create Employer Page title not displayed..");
        log.info("Create Employer Page loaded");
    }

    public EmployerPage createEmployer(List<ISectionDataModel> sectionModel) {
        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(CreateEmployerPage.class);
        }
        SAVE_BUTTON.should(appear, ofSeconds(30))
                   .scrollTo()
                   .click();
        return new EmployerPage();
    }
}
