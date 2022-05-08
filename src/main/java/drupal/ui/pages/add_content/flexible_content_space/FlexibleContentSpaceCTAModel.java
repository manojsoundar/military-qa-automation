package drupal.ui.pages.add_content.flexible_content_space;

import drupal.enums.content.FlexibleContentSpacePosition;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static drupal.enums.content.FlexibleContentSpaceType.ADD_CTA;
import static java.lang.String.format;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;


@Getter
@AllArgsConstructor
public class FlexibleContentSpaceCTAModel extends MasterPage implements ISectionDataModel, IFlexibleContentSpace {

    private static final String FLEXIBLE_CONTENT_SPACE_CALL_TO_ACTION_INPUT = "input[id*='%s-%s-subform-field-call-to-action']";
    private final FlexibleContentSpacePosition position;
    private int index;
    private final String callToAction;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        index = selectFlexibleContentSpace(ADD_CTA, position) - 1;
        enterCallToAction();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        clickEditFlexibleContentSpace(position, index);

        return returnInstanceOf(expectedClass);
    }

    private FlexibleContentSpaceCTAModel enterCallToAction() {
        waitAjaxJQueryMet(30);
        if (callToAction != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_CALL_TO_ACTION_INPUT, position.getFlexibleContentPosition(), index)).should(appear, enabled)
                                                                                                                .setValue(callToAction);
        }

        return this;
    }

}


