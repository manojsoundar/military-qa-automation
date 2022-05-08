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
import static drupal.enums.content.FlexibleContentSpaceType.ADD_COLUMNS;
import static java.lang.String.format;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@Getter
@AllArgsConstructor
public class FlexibleContentSpaceColumnsModel extends MasterPage implements ISectionDataModel, IFlexibleContentSpace {

    private static final String COLUMNS_TYPE_DROPDOWN_LIST = "select.form-select[id*='%s-%s-subform-field-columns-two-type']";

    private final FlexibleContentSpacePosition position;
    private final String columnsType;
    private int index;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        index = selectFlexibleContentSpace(ADD_COLUMNS, position) - 1;
        selectColumns();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private FlexibleContentSpaceColumnsModel selectColumns() {
        waitAjaxJQueryMet(30);
        if (columnsType != null) {
            $(format(COLUMNS_TYPE_DROPDOWN_LIST, position.getFlexibleContentPosition(), index)).should(exist, appear, enabled)
                                                                                               .selectOption(columnsType);
        }
        return this;
    }

}


