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
import static drupal.enums.content.FlexibleContentSpaceType.ADD_THYMELEAF_TEMPLATE;
import static java.lang.String.format;

@Getter
@AllArgsConstructor
public class FlexibleContentSpaceThymeleafTemplateModel extends MasterPage implements ISectionDataModel, IFlexibleContentSpace {

    private static final String THYMELEAF_TEMPLATE_DROPDOWN_LIST = "select[id*=-%s-%s-subform-field-thymeleaf-template]";

    private final FlexibleContentSpacePosition position;
    private final String thymeleafTemplate;
    private int index;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        index = selectFlexibleContentSpace(ADD_THYMELEAF_TEMPLATE, position) - 1;
        selectThymeleafTemplate();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private FlexibleContentSpaceThymeleafTemplateModel selectThymeleafTemplate() {
        if (thymeleafTemplate != null) {
            $(format(THYMELEAF_TEMPLATE_DROPDOWN_LIST, position.getFlexibleContentPosition(), index))
                    .should(exist, appear, enabled)
                    .selectOption(thymeleafTemplate);
        }
        return this;
    }

}


