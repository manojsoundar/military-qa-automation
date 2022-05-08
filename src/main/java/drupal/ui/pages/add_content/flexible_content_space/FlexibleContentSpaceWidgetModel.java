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
import static drupal.enums.content.FlexibleContentSpaceType.ADD_WIDGET;

@Getter
@AllArgsConstructor
public class FlexibleContentSpaceWidgetModel extends MasterPage implements ISectionDataModel, IFlexibleContentSpace {

    private static final String WIDGET_INPUT_LIST = "input.form-text[id*=-%s-%s-subform-field-widget]";

    private final FlexibleContentSpacePosition position;
    private int index;
    private final String widget;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        index = selectFlexibleContentSpace(ADD_WIDGET, position) - 1;
        enterWidget();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();
        clickEditFlexibleContentSpace(position, index);
        enterWidget();
        return returnInstanceOf(expectedClass);
    }

    private FlexibleContentSpaceWidgetModel enterWidget() {
        if (widget != null) {
            $(String.format(WIDGET_INPUT_LIST, position.getFlexibleContentPosition(), index)).should(exist, appear, enabled)
                                                                                             .setValue(widget);
        }
        return this;
    }

}


