package drupal.ui.pages.add_content.landing_page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.add_content.event.UpcomingJobFairsPage;
import drupal.ui.pages.structure.taxonomy.categories.CategoriesTermLandingPage;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EditLandingPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/edit";
    private static final SelenideElement EDIT_LANDING_PAGE_H_1_TITLE = $("#block-pagetitle h1");
    private static final SelenideElement URL_ALIAS_INPUT = $("#edit-path-0-alias");
    private static final SelenideElement SIDEBAR_DROPDOWN = $("#edit-field-sidebar-wrapper .chosen-container");
    private static final SelenideElement SIDEBAR_INPUT = $(".chosen-drop .chosen-search-input");
    private static final ElementsCollection SIDEBAR_LIST = $$("#edit_field_sidebar_chosen .chosen-results li");
    private static final SelenideElement EDIT_SIDEBAR_LINK = $("#edit-field-sidebar-wrapper .edit-link");
    private static final SelenideElement SAVE_BUTTON = $("#edit-submit");
    private static final SelenideElement BLURB_TEXT = $("#edit-field-summary-0-value").as("Blurb text input");
    private static final SelenideElement CATEGORY_INPUT = $("#edit-title-0-value").as("category input title");


    public EditLandingPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Edit Landing Page not loaded..");
        assertTrue(EDIT_LANDING_PAGE_H_1_TITLE.getText()
                                              .trim()
                                              .contains("Edit"), "Edit Landing title not displayed..");
        log.info("Edit Landing Page loaded..");
    }

    public EditLandingPage editLandingPageSectionsData(List<ISectionDataModel> landingPageSectionData) {

        for (ISectionDataModel landingPageSection : landingPageSectionData) {
            landingPageSection.editData(EditLandingPage.class);
        }
        return this;
    }

    public EditLandingPage addSidebar(String title) {

        if (title != null) {
            SIDEBAR_DROPDOWN.should(appear, exist, enabled)
                            .click();
            SIDEBAR_INPUT.should(appear, exist, enabled)
                         .setValue(title);
            if (!SIDEBAR_LIST.isEmpty()) {
                SIDEBAR_LIST.find(text(title))
                            .click();
            }
        }
        assertTrue(EDIT_SIDEBAR_LINK.should(exist, visible)
                                    .getText()
                                    .contains(title), "Sidebar not selected");
        log.info("Sidebar selected");

        return this;
    }

    public <P extends MasterPage> P clickSaveButton(Class<P> expectedClass) {
        SAVE_BUTTON.should(exist, appear, enabled)
                   .scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"center\"}")
                   .click();
        return returnInstanceOf(expectedClass);
    }

    public UpcomingJobFairsPage editEventLandingSpace(String updateBlurbText) {
        BLURB_TEXT.should(visible, appear, enabled)
                  .setValue(updateBlurbText);
        SAVE_BUTTON.should(exist, appear, enabled)
                   .scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"center\"}")
                   .click();
        return new UpcomingJobFairsPage();
    }

    public CategoriesTermLandingPage editTaxonomyCategoriesLandingSpace(String updateTitle) {
        CATEGORY_INPUT.should(visible, appear, enabled)
                      .setValue(updateTitle);
        SAVE_BUTTON.should(exist, appear, enabled)
                   .scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"center\"}")
                   .click();
        return new CategoriesTermLandingPage();
    }

    public String getUrlAlias() {

        return URL_ALIAS_INPUT.should(exist, appear)
                              .getAttribute("value");
    }

}
