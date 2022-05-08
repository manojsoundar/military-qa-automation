package drupal.ui.pages.structure.eck_entity_types.call_to_action;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Log4j2
public class ECKContentListPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/entity/call_to_action";
    private static final SelenideElement ECK_CONTENT_LIST_PAGE_H_1_TAG = $("#block-pagetitle h1");
    private static final SelenideElement INTERNAL_TITLE_INPUT = $("edit-field-internal-title-value");
    private static final SelenideElement DISPLAY_TITLE_INPUT = $("#edit-title");
    private static final SelenideElement TYPE_DROPDOWN = $("#edit-type");
    private static final SelenideElement APPLY_BUTTON = $("#edit-submit-eck-call-to-action");
    private static final ElementsCollection CTA_TITLE_LIST = $$("td.views-field-title");
    private static final ElementsCollection EDIT_BUTTON_LIST = $$("td.views-field-operations");

    public ECKContentListPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "ECK Content List Page not loaded.");
        assertTrue(ECK_CONTENT_LIST_PAGE_H_1_TAG.should(exist, appear)
                                                .has(text("ECK")), "ECK Content Page title:" + ECK_CONTENT_LIST_PAGE_H_1_TAG.getText() + "not displayed,should contain('ECK')");
        log.info("ECK Content List Page Page loaded properly.");
    }

    public ECKContentListPage fillSearchCriteriaAndFilterECKContentList(CTAType ctaType, String title, String internalTitle) {

        if (internalTitle != null) {
            INTERNAL_TITLE_INPUT.should(exist, appear, enabled)
                                .setValue(internalTitle);
        }
        if (title != null) {
            DISPLAY_TITLE_INPUT.should(exist, appear, enabled)
                               .setValue(title);
        }
        if (ctaType != null) {
            TYPE_DROPDOWN.should(exist, appear, enabled)
                         .selectOptionContainingText(ctaType.getCtaTypeItem());
        }
        APPLY_BUTTON.should(exist, appear, enabled)
                    .click();

        return this;
    }

    public <P extends MasterPage> P clickEditButton(Class<P> expectedPage, int index, String titleText) {

        if (index < 0 && titleText != null) {
            index = CTA_TITLE_LIST.stream()
                                  .map(SelenideElement::getText)
                                  .collect(Collectors.toList())
                                  .indexOf(titleText);
            if (index == -1) {
                log.error(titleText + " does not match in any column");
                throw new AssertionError(titleText + " does not match in any column");
            }
        }
        EDIT_BUTTON_LIST.get(index)
                        .should(visible, enabled)
                        .click();
        log.info("Clicked on edit button, index: " + index);

        return returnInstanceOf(expectedPage);
    }

    public ECKContentListPage verifyAddedCTA(String title) {
        log.info("Verify if added CTA is displayed in Media table");
        assertFalse(CTA_TITLE_LIST.isEmpty(), "CTA Title List" + CTA_TITLE_LIST.isEmpty() + "is empty");
        assertTrue(CTA_TITLE_LIST.find(exactText(title))
                                 .isDisplayed(),
                   "CTA title list:" + CTA_TITLE_LIST.find(exactText(title))
                                                     .isDisplayed() + "not displayed,should contain('ECK')");
        log.info("Added CTA " + title + " is displayed in the list");
        return this;
    }

    @Getter
    @AllArgsConstructor
    public enum CTAType {

        ANY("- Any -"),
        CALL_TO_ACTION_LEAD_FORM("Call to Action - Lead Form"),
        CALL_TO_ACTION_NL_SIGNUP("Call to Action - NL Signup"),
        CALL_TO_ACTION_NORMAL("Call to Action - Normal");

        private final String ctaTypeItem;
    }
}
