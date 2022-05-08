package drupal.ui.pages.add_content.flexible_content_space;

import drupal.enums.content.FlexibleContentSpacePosition;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static drupal.enums.content.FlexibleContentSpaceType.ADD_LIST_VIEW;
import static java.lang.String.format;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@Getter
@AllArgsConstructor
public class FlexibleContentSpaceListViewModel extends MasterPage implements ISectionDataModel, IFlexibleContentSpace {

    private static final String FLEXIBLE_CONTENT_SPACE_TITLE_INPUT_GENERIC_XPATH = "//input[contains(@id, 'edit-field-flexible-%s-%s-subform-field-title-%s-value')]";
    private static final String FLEXIBLE_CONTENT_SPACE_TITLE_INPUT = ".form-text[id*='-%s-%s-subform-field-title']";
    private static final String FLEXIBLE_CONTENT_SPACE_LIST_TYPE_DROPDOWN = "select[id*='-%s-%s-subform-field-list-view-type']";
    private static final String FLEXIBLE_CONTENT_SPACE_DISPLAY_MODE_DROPDOWN = "select[id*='-%s-%s-subform-field-display-mode']";
    private static final String FLEXIBLE_CONTENT_SPACE_CURATED_LIST_INPUT = ".form-text[id*='-%s-%s-subform-field-curated-list']";

    private static final String FLEXIBLE_CONTENT_GENERATED_LIST_KEYWORD_TERM_DROPDOWN_ARROW = "div[id*='edit_field_%s_%s_subform_field_generated_list_term'] a div>b";
    private static final String FLEXIBLE_CONTENT_GENERATED_LIST_KEYWORD_TERM_INPUT = "div[class*='-%s-%s-subform-field-generated-list-term'] > div > div > div > input";
    private static final String FLEXIBLE_CONTENT_GENERATED_LIST_KEYWORD_TERM_LIST = "div[id*='edit-field-%s-%s-subform-field-generated-list-term'] ul.chosen-results li";
    private static final String FLEXIBLE_CONTENT_GENERATED_LIST_CATEGORY_TERM_DROPDOWN_ARROW = "div[id*='edit_field_%s_%s_subform_field_generated_list_cat_term'] a div>b";
    private static final String FLEXIBLE_CONTENT_GENERATED_LIST_CATEGORY_TERM_INPUT = "div[class*='-%s-%s-subform-field-generated-list-cat-term'] > div > div > div > input";
    private static final String FLEXIBLE_CONTENT_GENERATED_LIST_CATEGORY_TERM_LIST = "div[id*='edit-field-%s-%s-subform-field-generated-list-cat-term'] ul.chosen-results li";

    private static final String FLEXIBLE_CONTENT_NO_OF_ITEMS_TO_SHOW_INPUT = "input[id*='-%s-%s-subform-field-item-quantity']";
    private static final String FLEXIBLE_CONTENT_SHOW_MORE_LINK_CHECKBOX = "input[id*='-%s-%s-subform-field-show-more-link']";
    private static final String FLEXIBLE_CONTENT_LIST_ORDER_DROPDOWN = "select[id*='-%s-%s-subform-field-list-order']";

    private static final String EDIT_BUTTON = "//div[contains(@id,'-%s-%s-')]//input[contains(@class,'button') and contains(@value,'Edit')]";


    private final FlexibleContentSpacePosition position;
    private int index;
    private final String title;
    private final ListType listType;
    private final DisplayMode displayMode;
    private final List<String> curatedList;
    private final String generatedListKeywordTerm;
    private final String generatedListCategoryTerm;
    private final String noOfItemsToShow;
    private final boolean showMoreLinksCheckbox;
    private final ListOrder listOrder;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        index = selectFlexibleContentSpace(ADD_LIST_VIEW, position) - 1;
        enterTitle().selectListType()
                         .selectDisplayMode()
                         .enterCuratedList()
                         .selectGeneratedListKeywordTerm()
                         .selectGeneratedListCategoryTerm()
                         .enterNoOfItemsToShow()
                         .checkShowMoreLinksCheckbox()
                         .selectListOrder();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();
        clickEditFlexibleContentSpace(position, index);
        enterTitle().selectListType()
                         .selectDisplayMode()
                         .enterCuratedList()
                         .selectGeneratedListKeywordTerm()
                         .selectGeneratedListCategoryTerm()
                         .enterNoOfItemsToShow()
                         .checkShowMoreLinksCheckbox()
                         .selectListOrder();
        return returnInstanceOf(expectedClass);
    }

    private FlexibleContentSpaceListViewModel enterTitle() {
        if (title != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_TITLE_INPUT, position.name()
                                                                 .toLowerCase(), index)).should(exist, appear, enabled)
                                                                                        .setValue(title);
        }
        return this;
    }

    public FlexibleContentSpaceListViewModel selectListType() {
        if (listType.getListTypeItem() != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_LIST_TYPE_DROPDOWN, position.name()
                                                                        .toLowerCase(), index)).should(appear, exist, enabled)
                                                                                               .selectOption(listType.getListTypeItem());
        }
        return this;
    }

    private FlexibleContentSpaceListViewModel selectDisplayMode() {
        if (displayMode != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_DISPLAY_MODE_DROPDOWN, position.name()
                                                                           .toLowerCase(), index)).should(appear, exist, enabled)
                                                                                                  .selectOption(displayMode.getDisplayModeItem());
        }
        return this;
    }

    private FlexibleContentSpaceListViewModel enterCuratedList() {
        if (curatedList != null) {
            for (String curatedListItem : curatedList) {
                $(format(FLEXIBLE_CONTENT_SPACE_CURATED_LIST_INPUT, position.name()
                                                                            .toLowerCase(), index)).should(exist, appear, enabled)
                                                                                                   .setValue(curatedListItem);
            }
        }
        return this;
    }

    private FlexibleContentSpaceListViewModel selectGeneratedListKeywordTerm() {
        if (generatedListKeywordTerm != null) {

            $(format(FLEXIBLE_CONTENT_GENERATED_LIST_KEYWORD_TERM_DROPDOWN_ARROW, position.name()
                                                                                          .toLowerCase(), index)).should(exist, appear, enabled)
                                                                                                                 .click();
            waitAjaxJQueryMet(150);
            $(format(FLEXIBLE_CONTENT_GENERATED_LIST_KEYWORD_TERM_INPUT, position.name()
                                                                                 .toLowerCase(), index)).should(exist, appear, enabled)
                                                                                                        .setValue(generatedListKeywordTerm);
            $$(format(FLEXIBLE_CONTENT_GENERATED_LIST_KEYWORD_TERM_LIST, position.name()
                                                                                 .toLowerCase(), index)).first()
                                                                                                        .should(enabled, visible, enabled)
                                                                                                        .scrollTo()
                                                                                                        .click();
        }
        return this;
    }

    private FlexibleContentSpaceListViewModel selectGeneratedListCategoryTerm() {
        if (generatedListCategoryTerm != null) {

            $(format(FLEXIBLE_CONTENT_GENERATED_LIST_KEYWORD_TERM_DROPDOWN_ARROW, position.name()
                                                                                          .toLowerCase(), index)).should(exist, appear, enabled)
                                                                                                                 .click();
            waitAjaxJQueryMet(150);
            $(format(FLEXIBLE_CONTENT_GENERATED_LIST_CATEGORY_TERM_INPUT, position.name()
                                                                                  .toLowerCase(), index)).should(exist, appear, enabled)
                                                                                                         .setValue(generatedListCategoryTerm);
            $$(format(FLEXIBLE_CONTENT_GENERATED_LIST_CATEGORY_TERM_LIST, position.name()
                                                                                  .toLowerCase(), index)).first()
                                                                                                         .should(enabled, visible, enabled)
                                                                                                         .scrollTo()
                                                                                                         .click();
        }
        return this;
    }

    private FlexibleContentSpaceListViewModel enterNoOfItemsToShow() {
        if (noOfItemsToShow != null) {
            $(format(FLEXIBLE_CONTENT_NO_OF_ITEMS_TO_SHOW_INPUT, position.name()
                                                                         .toLowerCase(), index)).should(exist, appear, enabled)
                                                                                                .setValue(noOfItemsToShow);
        }
        return this;
    }

    private FlexibleContentSpaceListViewModel selectListOrder() {
        if (listOrder.getListOrderItem() != null) {

            $(format(FLEXIBLE_CONTENT_LIST_ORDER_DROPDOWN, position.name()
                                                                   .toLowerCase(), index)).should(appear, exist, enabled)
                                                                                          .selectOption(listOrder.getListOrderItem());
        }
        return this;
    }

    private FlexibleContentSpaceListViewModel checkShowMoreLinksCheckbox() {
        $(format(FLEXIBLE_CONTENT_SHOW_MORE_LINK_CHECKBOX, position.name()
                                                                   .toLowerCase(), index)).should(appear, exist, enabled)
                                                                                          .setSelected(showMoreLinksCheckbox);
        return this;
    }


    @Getter
    @AllArgsConstructor
    public enum ListType {
        NONE("- None -"),
        CURATED_LIST("Curated list"),
        AUTO_GENERATED_LIST("Auto generated list");

        public final String listTypeItem;
    }

    @Getter
    @AllArgsConstructor
    public enum DisplayMode {
        NONE("- Select a value -"),
        ONE_COLUMN_LINKS("1 column links"),
        TWO_COLUMN_LIST("2 column list"),
        TWO_COLUMN_BULLETS_LINKS("2 column bullets (links)"),
        TWO_COLUMN_MEDIUM_VERTICAL("2 column, medium vertical"),
        THREE_FEATURES_TILED("3 features, tiled"),
        ARTICLE_LIST_WITH_LEFT_FLOATED_THUMBNAILS("Article list with left floated thumbnails"),
        THREE_COLUMN_THUMBNAIL_LIST_WITH_TEASERS("3 column thumbnail list with teasers"),
        THREE_COLUMN_THUMBNAIL_LIST_WITHOUT_TEASERS("3 column thumbnail list with teasers"),
        THREE_COLUMN_BULLETS_LINKS("3 column bullets (links)"),
        THUMBNAILS_ONLY("Thumbnails only"),
        ONE_COLUMN_IMPORTANT_CONTACTS("1 column Important Contacts"),
        THREE_FEATURES_ADVANCED_WITH_CONTENT("3 features advanced with content");

        public final String displayModeItem;
    }

    @Getter
    @AllArgsConstructor
    public enum ListOrder {
        NONE("- None -"),
        DEFAULT_CREATED_DATE("Default created date"),
        MODIFIED_DATE("Modified date");

        public final String listOrderItem;
    }

}