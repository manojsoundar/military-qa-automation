package drupal.ui.pages.add_content.article;

import com.codeborne.selenide.ElementsCollection;
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
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EditArticlePage extends AdministrationToolbar {

    protected static final SelenideElement PUBLISHED_CHECKBOX = $("input#edit-status-value");
    protected static final SelenideElement URL_ALIAS_CHECKBOX = $("input.form-checkbox[id*='pathauto']").as("Check box");
    protected static final SelenideElement URL_ALIAS_INPUT = $("[id^='edit-path'] input.form-text[id*='path']").as("Text box");
    protected static final ElementsCollection CATEGORIES_CLOSE_ICONS_LIST = $$(".form-item-field-category .search-choice-close");
    private static final String URL_REGEX = "/edit";
    private static final SelenideElement SAVE_BUTTON = $("input#edit-submit");
    private static final ElementsCollection CATEGORIES_AVAILABLE_LIST = $$("#edit_field_category_chosen ul.chosen-results li").as("Category list");
    private static final SelenideElement CATEGORIES_INPUT = $("#edit_field_category_chosen input.chosen-search-input");

    public EditArticlePage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Edit Article Page not loaded.");
        log.info("Edit Article Page loaded properly.");
    }

    public String editAliasUrlArticle(String newUrlAlias) {

        URL_ALIAS_CHECKBOX.should(appear, exist, enabled)
                          .setSelected(false);
        String urlAlias = URL_ALIAS_INPUT.should(appear, exist)
                                         .getValue();
        URL_ALIAS_INPUT.should(exist, enabled)
                       .setValue(newUrlAlias);
        return urlAlias;
    }

    public EditArticlePage editArticle(List<ISectionDataModel> data) {

        for (ISectionDataModel sectionData : data) {
            sectionData.setData(EditArticlePage.class);
        }
        return this;
    }

    public <P extends MasterPage> P clickSaveButton(Class<P> expectedClass) {
        SAVE_BUTTON.should(exist, enabled)
                   .scrollTo()
                   .click();
        return returnInstanceOf(expectedClass);
    }
}
