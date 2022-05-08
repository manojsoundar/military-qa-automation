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
import static drupal.enums.content.FlexibleContentSpaceType.ADD_BLOCK;
import static java.lang.String.format;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@Getter
@AllArgsConstructor
public class FlexibleContentSpaceBlockModel extends MasterPage implements ISectionDataModel, IFlexibleContentSpace {

    private static final String FLEXIBLE_CONTENT_SPACE_BLOCK_TITLE_INPUT = ".form-text[id*='-%s-%s-subform-field-title']";
    private static final String FLEXIBLE_CONTENT_SPACE_BLOCK_DROPDOWN = "select.form-select[id*='-%s-%s'][id*='plugin-id']";
    private static final String FLEXIBLE_CONTENT_BLOCK_APP_NEXUS_TITLE_INPUT = "input.form-text.required[id*='-%s-%s-subform-field-block']";
    private static final String FLEXIBLE_CONTENT_BLOCK_DISPLAY_TITLE_CHECKBOX = "input.form-checkbox[id*='-%s-%s-'][id*='settings-label-display']";
    private static final String FLEXIBLE_CONTENT_BLOCK_POSITION_NAME_DROPDOWN = "select.form-select[id*='-%s-%s-'][id*='settings-position']";
    private static final String FLEXIBLE_CONTENT_EDIT_DROP_TOGGLE_BUTTON = "[id*=edit-field-%s-%s] .paragraphs-dropbutton-wrapper button";
    private static final String FLEXIBLE_CONTENT_REMOVE_BUTTON = "//div[contains(@id,'-%s-%s-')]//input[contains(@class,'button') and contains(@value,'Edit')]";
    private static final String FLEXIBLE_CONTENT_CONFIRM_REMOVAL_BUTTON = "//div[contains(@id,'-%s-%s-')]//input[contains(@class,'button') and contains(@value,'Confirm removal')]";

    private final FlexibleContentSpacePosition position;
    private int index;
    private final String addBlockTitle;
    private final String blockType;
    private final String appNexusTitle;
    private final boolean displayTitleCheck;
    private final String positionName;


    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        index = selectFlexibleContentSpace(ADD_BLOCK, position) - 1;
        enterAddBlockTitle().selectAddBlockBlockType();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        clickEditFlexibleContentSpace(position, index);

        enterAddBlockTitle().selectAddBlockBlockType();

        return returnInstanceOf(expectedClass);
    }


    private FlexibleContentSpaceBlockModel enterAddBlockTitle() {
        waitAjaxJQueryMet(30);
        if (addBlockTitle != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_BLOCK_TITLE_INPUT, position.name()
                                                                       .toLowerCase(), index)).should(appear, enabled)
                                                                                              .setValue(addBlockTitle);
        }

        return this;
    }

    private FlexibleContentSpaceBlockModel selectAddBlockBlockType() {
        if (blockType != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_BLOCK_DROPDOWN, position.name()
                                                                    .toLowerCase(), index)).should(appear, exist, enabled)
                                                                                           .selectOption(blockType);
            waitAjaxJQueryMet(150);

            $(format(FLEXIBLE_CONTENT_BLOCK_APP_NEXUS_TITLE_INPUT, position.name()
                                                                           .toLowerCase(), index)).should(appear, exist, enabled)
                                                                                                  .setValue(appNexusTitle);

            $(format(FLEXIBLE_CONTENT_BLOCK_DISPLAY_TITLE_CHECKBOX, position.name()
                                                                            .toLowerCase(), index)).should(appear, exist, enabled)
                                                                                                   .setSelected(displayTitleCheck);
            if (positionName != null) {
                $(format(FLEXIBLE_CONTENT_BLOCK_POSITION_NAME_DROPDOWN, position.name()
                                                                                .toLowerCase(), index)).should(appear, exist, enabled)
                                                                                                       .selectOption(positionName);
            }
        }

        return this;
    }

}