package drupal.ui.pages.add_content.flexible_content_space;

import drupal.enums.content.FlexibleContentSpacePosition;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static drupal.enums.content.FlexibleContentSpaceType.ADD_FEED;
import static java.lang.String.format;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@Getter
@AllArgsConstructor
public class FlexibleContentSpaceFeedModel extends MasterPage implements ISectionDataModel, IFlexibleContentSpace {

    private static final String FLEXIBLE_CONTENT_SPACE_FEED_TITLE_INPUT = "input.form-text[id*=-%s-%s-subform-field-title]";
    private static final String FLEXIBLE_CONTENT_SPACE_FEED_NO_OF_ITEMS_TO_SHOW_INPUT = "input[id*=-%s-%s-subform-field-item-quantity]";
    private static final String FLEXIBLE_CONTENT_SPACE_FEED_FEED_DROPDOWN = "select.form-select[id*=-%s-%s-subform-field-feed]";
    private static final String FLEXIBLE_CONTENT_SPACE_FEED_DISPLAY_MODE_DROPDOWN = "select[id*=-%s-%s-subform-field-display-mode-feed]";
    private static final String FLEXIBLE_CONTENT_SPACE_FEED_SHOW_MORE_LINK_CHECKBOX = "input[name*='_%s[%s][subform][field_show_more_link]']";

    private final FlexibleContentSpacePosition position;
    private int index;
    private final String addFeedTitle;
    private final String addFeed;
    private final String noOfItemsToShow;
    private final boolean showMoreLink;
    private final String displayMode;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        index = selectFlexibleContentSpace(ADD_FEED, position) - 1;
        enterAddBlockTitle().enterAddFeed();
        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();
        enterAddFeed().enterAddBlockTitle();
        return returnInstanceOf(expectedClass);
    }

    private FlexibleContentSpaceFeedModel enterAddBlockTitle() {
        if (addFeedTitle != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_FEED_TITLE_INPUT, position.getFlexibleContentPosition(), index)).should(exist, appear, enabled)
                                                                                                            .setValue(addFeedTitle);
        }
        return this;
    }

    private FlexibleContentSpaceFeedModel enterAddFeed() {
        if (addFeed != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_FEED_FEED_DROPDOWN, position.getFlexibleContentPosition(), index)).should(appear, exist, enabled)
                                                                                                              .selectOption(addFeed);
            waitAjaxJQueryMet(60);
            $(format(FLEXIBLE_CONTENT_SPACE_FEED_NO_OF_ITEMS_TO_SHOW_INPUT, position.getFlexibleContentPosition(), index)).should(appear, exist, enabled)
                                                                                                                          .setValue(noOfItemsToShow);
            waitAjaxJQueryMet(60);
            $(format(FLEXIBLE_CONTENT_SPACE_FEED_SHOW_MORE_LINK_CHECKBOX, position.getFlexibleContentPosition(), index)).should(appear, exist, enabled)
                                                                                                                        .setSelected(showMoreLink);
            $(format(FLEXIBLE_CONTENT_SPACE_FEED_DISPLAY_MODE_DROPDOWN, position.getFlexibleContentPosition(), index)).should(appear, exist, enabled)
                                                                                                                      .selectOption(displayMode);
        }
        return this;
    }

}


