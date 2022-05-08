package drupal.ui.pages.add_content.webinar;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static drupal.ui.pages.add_content.webinar.WebinarPage.SAVE_BUTTON;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EditWebinarPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/edit";
    private static final SelenideElement EDIT_LANDING_PAGE_H_1_TITLE = $("#block-pagetitle h1");

    public EditWebinarPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Edit Landing Page not loaded..");
        assertTrue(EDIT_LANDING_PAGE_H_1_TITLE.getText()
                                              .trim()
                                              .contains("Edit"), "Edit Landing title not displayed..");
        log.info("Edit Landing Page loaded..");
    }

    public WebinarVeteranEmploymentPage editWebinarPage(List<ISectionDataModel> webinarData) {

        for (ISectionDataModel webinarSection : webinarData) {
            webinarSection.setData(EditWebinarPage.class);
        }
        SAVE_BUTTON.should(exist, appear)
                   .scrollIntoView(true)
                   .click();
        return new WebinarVeteranEmploymentPage();
    }
}
