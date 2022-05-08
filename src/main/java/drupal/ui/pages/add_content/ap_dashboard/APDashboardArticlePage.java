package drupal.ui.pages.add_content.ap_dashboard;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.DATE_WITH_HYPHEN_PATTERN;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Log4j2
public class APDashboardArticlePage extends AdministrationToolbar {

    protected static final SelenideElement DISPLAY_DATE_INPUT = $("#edit-field-date-0-value-date").as("display date input");
    protected static final SelenideElement AUTHOR_INPUT = $("#edit-field-author-0-target-id").as("author input");
    protected static final SelenideElement CONTRIBUTOR_INPUT = $("#edit-field-other-author-0-value").as("contributor input");
    protected static final SelenideElement BLURB_INPUT = $("#edit-field-summary-0-value").as("blurb input");
    protected static final SelenideElement IMAGE_EDIT_BUTTON = $("#edit-field-image-current-items-0-edit-button").as("image edit button");
    protected static final SelenideElement IMAGE_NAME_INPUT = $(".js-form-item-name-0-value input").as("image name input");
    protected static final SelenideElement CAPTION_INPUT = $(".form-item-field-image-0-title input").as("caption input");
    protected static final SelenideElement AP_NEWS_ROOM_CHECKBOX = $(".form-item-field-ap-newsroom-value input").as("ap news room checkbox");
    protected static final SelenideElement IMAGE_SAVE_BUTTON = $(".ui-dialog-buttonset button").as("image save button");
    protected static final SelenideElement EXTERNAL_AP_ID_INPUT = $("#edit-field-external-ap-id-0-value").as("external-ap-id input");
    protected static final SelenideElement RELATED_TOPICS_INPUT = $("#edit-field-keywords-target-id").as("related topics input");
    protected static final ElementsCollection RELATED_TOPICS_LIST = $$(".ui-menu-item .ui-menu-item-wrapper").as("related topics list");
    protected static final SelenideElement SAVE_BUTTON = $("#edit-submit").as("Save button");
    protected static final SelenideElement SIDE_BAR_INPUT = $(".chosen-search .chosen-search-input");
    protected static final ElementsCollection SIDE_BAR_CONTAINER_LIST = $$("ul.chosen-results li");
    protected static final SelenideElement SIDE_BAR_DEFAULT_SELECTED_WEB_ELEMENT = $("a.chosen-single span");
    protected static final ElementsCollection CATEGORIES_AVAILABLE_LIST = $$("#edit_field_category_chosen .chosen-drop li").as("Categories displayed in the container after entering the Category");
    protected static final SelenideElement CATEGORY_INPUT = $("#edit_field_category_chosen input").as("Category input");
    private static final String URL_REGEX = "/article";
    private static final SelenideElement TITLE_INPUT = $("#edit-title-0-value").as("title input");
    private static final SelenideElement SOURCE_INPUT = $("#edit-field-source-0-target-id").as("source input");


    public APDashboardArticlePage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Article Page not loaded..");
        log.info("Article Page loaded");
    }

    public APDashboardArticlePage validateArticle(Map<String, String> articleData, APNewsDashBoardModel apNewsDashBoardModel) {

        assertEquals(TITLE_INPUT.should(enabled, visible)
                                .getValue()
                                .trim(), articleData.get("HEADLINE"), "Title is not displayed as expected");
        log.info("Title is displayed as expected");
        assertEquals(SOURCE_INPUT.should(enabled, visible)
                                 .getValue()
                                 .trim(), apNewsDashBoardModel.getSource(), "Source displayed not correct");
        log.info("Source displayed correct");
        assertEquals(DISPLAY_DATE_INPUT.should(enabled, visible)
                                       .getValue()
                                       .trim(), timeStampFormat(DATE_WITH_HYPHEN_PATTERN), "Date displayed correct");
        log.info("Date displayed correct");
        assertTrue(AUTHOR_INPUT.getText()
                               .isEmpty(), "Author is not empty");
        log.info("Author is empty");
        if (apNewsDashBoardModel.getContributor() != null) {
            assertEquals(CONTRIBUTOR_INPUT.should(enabled, visible, exist)
                                          .getValue()
                                          .trim(), apNewsDashBoardModel.getContributor(), "CONTRIBUTOR is not matched");
            log.info("Contributor displayed correctly");

        }
        assertFalse(BLURB_INPUT.should(enabled, visible)
                               .getText()
                               .isEmpty(), "Summary is empty");
        log.info("Summary is displayed");
        imageValidation(apNewsDashBoardModel);
        assertEquals(EXTERNAL_AP_ID_INPUT.should(enabled, visible)
                                         .getValue()
                                         .trim(), articleData.get("ITEMID"), "External ap id displayed was not correct");
        log.info("External ap id displayed");

        return this;
    }

    public APDashboardArticlePage fillCloneArticle(List<ISectionDataModel> data) {

        for (ISectionDataModel sectionData : data) {
            sectionData.setData(APDashboardArticlePage.class);
        }
        return this;
    }

    public APDashboardArticleClonePage clickSaveButton() {

        SAVE_BUTTON.should(enabled, visible, appear)
                   .click();
        log.info("Save button clicked");
        return new APDashboardArticleClonePage();

    }

    private APDashboardArticlePage imageValidation(APNewsDashBoardModel apNewsDashBoardModel) {

        if (apNewsDashBoardModel.isExistingImage()) {

            IMAGE_EDIT_BUTTON.should(enabled, visible)
                             .click();
            log.info("Image edit button clicked");
            waitAjaxJQueryMet(120);
            log.info("Loading image disappear");
            assertTrue(IMAGE_NAME_INPUT.should(enabled, visible)
                                       .getValue()
                                       .trim()
                                       .contains("ApNewsroom"), "Proper Image not displayed");
            log.info("Image name displayed");
            assertFalse(CAPTION_INPUT.should(enabled, visible)
                                     .getValue()
                                     .isEmpty(), "Caption is empty");
            log.info("Caption is not empty");
            assertTrue(AP_NEWS_ROOM_CHECKBOX.should(enabled, visible)
                                            .is(checked), "AP news room checkbox not checked");
            log.info("Ap newsroom checkbox checked");
            IMAGE_SAVE_BUTTON.should(enabled, visible, appear)
                             .click();
            log.info("Image save button clicked");

        } else {
            assertFalse(IMAGE_EDIT_BUTTON.exists(), "Image is available");
            log.info("Image is not exist");
        }
        return this;
    }

}
