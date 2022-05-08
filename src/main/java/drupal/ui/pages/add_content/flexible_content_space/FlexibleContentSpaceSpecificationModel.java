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
import static drupal.enums.content.FlexibleContentSpaceType.ADD_SPECIFICATION;
import static java.lang.String.format;

@Getter
@AllArgsConstructor
public class FlexibleContentSpaceSpecificationModel extends MasterPage implements ISectionDataModel, IFlexibleContentSpace {

    private static final String SPECIFICATION_LABEL_INPUT_LIST = ".form-text[id*=-%s-%s-subform-field-title]";
    private static final String SPECIFICATION_DESCRIPTION_INPUT_LIST = "textarea[id*=-%s-%s-subform-field-description]";

    private final FlexibleContentSpacePosition position;
    private final String specificationLabel;
    private final String specificationDescription;
    private int index;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        index = selectFlexibleContentSpace(ADD_SPECIFICATION, position) - 1;
        enterSpecification();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private FlexibleContentSpaceSpecificationModel enterSpecification() {
        if (specificationLabel != null) {
            $(format(SPECIFICATION_LABEL_INPUT_LIST, position.getFlexibleContentPosition(), index)).should(exist, appear, enabled)
                                                                                                   .setValue(specificationLabel);

            $(format(SPECIFICATION_DESCRIPTION_INPUT_LIST, position.getFlexibleContentPosition(), index)).should(exist, appear, enabled)
                                                                                                         .setValue(specificationDescription);
        }
        return this;
    }

}


