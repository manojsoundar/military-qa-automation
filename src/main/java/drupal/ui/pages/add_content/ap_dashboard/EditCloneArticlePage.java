package drupal.ui.pages.add_content.ap_dashboard;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EditCloneArticlePage extends AdministrationToolbar {

    private static final String URL_REGEX = "/edit";
    private static final SelenideElement SAVE_BUTTON = $("input#edit-submit");
    private static final SelenideElement EDIT_CLONE_ARTICLE_PAGE_TITLE = $(".page-title em");

    public EditCloneArticlePage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Edit Clone Article Page not loaded");
        assertTrue(EDIT_CLONE_ARTICLE_PAGE_TITLE.getText()
                                                .trim()
                                                .contains("Edit Article"), "Edit Clone Article Page title loaded");
        log.info("Edit Clone Article Page loaded");
    }

    public EditCloneArticlePage editCloneArticle(List<ISectionDataModel> data) {
        for (ISectionDataModel sectionData : data) {
            sectionData.setData(EditCloneArticlePage.class);
        }
        return this;
    }

    public APDashboardArticleClonePage clickSaveButton() {
        SAVE_BUTTON.should(exist, enabled)
                   .scrollTo()
                   .click();
        log.info("Edit Clone Article clicked save button");
        return new APDashboardArticleClonePage();
    }
}
