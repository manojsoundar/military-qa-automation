package drupal.ui.pages.add_content.webinar;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class WebinarPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/node/add/webinar";
    private static final SelenideElement WEBINAR_H_1_TAG = $("#block-pagetitle h1.page-title");
    protected static final SelenideElement WEBINAR_TITLE_INPUT = $("input[id=edit-title-0-value]");
    protected static final SelenideElement WEBINAR_ID_INPUT = $("input[id=edit-field-webinar-id-0-value]");
    protected static final SelenideElement WEBINAR_TIME_INPUT = $("input[id=edit-field-webinar-time-0-value]");
    protected static final SelenideElement HOST_INPUT = $("input[id=edit-field-host-0-value]");
    protected static final SelenideElement WEBINAR_DETAILS_BODY_FRAME = $("div#cke_1_contents iframe.cke_reset");
    protected static final SelenideElement WEBINAR_BODY_TEXTAREA = $("body.cke_editable p").as("Body Input");
    protected static final SelenideElement SAVE_BUTTON = $("#edit-submit");
    protected static final SelenideElement ATTACH_AN_IMAGE_BUTTON = $(".form-wrapper input[name*='field_lead_image_entity_browser_entity_browser']").as("Attach an Image Button");

    public WebinarPage() {

        assertTrue(verifyURLLoaded(URL_REGEX), "Create Webinar Page isn't loaded.");
        assertEquals(WEBINAR_H_1_TAG.should(exist, appear)
                                    .getText(), "Create Webinar", "Create Webinar title:" + WEBINAR_H_1_TAG.getText() + "not displayed,should contain ('Create Webinar')");
        log.info("Create Webinar Page loaded properly.");
    }

    public WebinarPage addWebinar(List<ISectionDataModel> sectionModel) {

        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(WebinarPage.class);
        }

        return this;
    }

    public WebinarVeteranEmploymentPage clickSaveButton() {

        SAVE_BUTTON.should(exist, appear)
                   .scrollIntoView(true)
                   .click();
        waitAjaxJQueryMet(120);

        return new WebinarVeteranEmploymentPage();
    }
}
