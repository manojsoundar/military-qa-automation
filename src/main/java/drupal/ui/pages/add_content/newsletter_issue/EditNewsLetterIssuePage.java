package drupal.ui.pages.add_content.newsletter_issue;

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
public class EditNewsLetterIssuePage extends AdministrationToolbar {

    protected static final SelenideElement PUBLISHED_CHECKBOX = $("input#edit-status-value");
    private static final String URL_REGEX = "/edit";
    private static final SelenideElement SAVE_BUTTON = $("input#edit-submit");

    public EditNewsLetterIssuePage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Edit NewsLetter Issue Page not loaded.");
        log.info("Edit NewsLetter Issue Page loaded properly.");
    }

    public NewsLetterIssueResultPage fillIn(List<ISectionDataModel> sectionModel) {

        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(EditNewsLetterIssuePage.class);
        }
        SAVE_BUTTON.should(appear, enabled, visible)
                   .click();

        return new NewsLetterIssueResultPage();
    }

}
