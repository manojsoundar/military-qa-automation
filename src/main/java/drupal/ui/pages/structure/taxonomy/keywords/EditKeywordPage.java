package drupal.ui.pages.structure.taxonomy.keywords;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EditKeywordPage extends AdministrationToolbar {

    private static final String URL_REGEX = Configuration.baseUrl;
    private static final SelenideElement SAVE_BUTTON = $("#edit-submit").as("save button");
    private static final SelenideElement STATUS_MESSAGE = $("div.messages--status");
    private static final SelenideElement STATUS_MESSAGE_TERM_LINK = STATUS_MESSAGE.$(".placeholder a");
    private static final SelenideElement NAME_INPUT = $("#edit-name-0-value").as("Title input field");

    public EditKeywordPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Edit Taxonomy Keyword Page not loaded.");
        log.info("Edit Taxonomy Keyword Page loaded properly.");
    }

    public EditKeywordPage editAll(List<ISectionDataModel> sectionModel) {

        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(EditKeywordPage.class);
        }
        SAVE_BUTTON.should(appear, enabled, visible)
                   .click();

        return this;
    }

    public EditKeywordPage verifyUpdatedMessage(String newName) {

        assertTrue(STATUS_MESSAGE.should(appear, exist, visible)
                                 .getText()
                                 .contains("Updated"), "Status message:" + STATUS_MESSAGE.getText() + "not displayed,should contain('Updated')");
        assertTrue(STATUS_MESSAGE_TERM_LINK.getText()
                                           .contains(newName), "Term link in Status message:" + STATUS_MESSAGE_TERM_LINK.getText() + "not displayed,should contain" + newName);
        log.info("Keyword topic edited successfully.");
        return this;
    }
}
