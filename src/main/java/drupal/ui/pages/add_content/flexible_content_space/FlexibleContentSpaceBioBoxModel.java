package drupal.ui.pages.add_content.flexible_content_space;

import drupal.enums.content.FlexibleContentSpacePosition;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static drupal.enums.content.FlexibleContentSpaceType.ADD_BIO_BOX;
import static java.lang.String.format;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@Getter
@AllArgsConstructor
public class FlexibleContentSpaceBioBoxModel extends MasterPage implements ISectionDataModel, IFlexibleContentSpace {

    private static final String BIO_BOX_TITLE_INPUT = "input[id*=edit-field-flexible-%s-%s-subform-field-title]";
    private static final String BIO_BOX_AUTHOR_INPUT = "input.form-text[id*=edit-field-flexible-%s-%s-subform-field-author]";

    private final FlexibleContentSpacePosition position;
    private final String bioBoxTitle;
    private final String bioBoxAuthor;
    private int index;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        index = selectFlexibleContentSpace(ADD_BIO_BOX, position) - 1;
        enterBioBox();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private FlexibleContentSpaceBioBoxModel enterBioBox() {
        waitAjaxJQueryMet(30);
        if (bioBoxTitle != null) {
            $(format(BIO_BOX_TITLE_INPUT, position.name()
                                                  .toLowerCase(), index)).should(appear, enabled)
                                                                         .setValue(bioBoxTitle);
        }
        if (bioBoxAuthor != null) {
            $(format(BIO_BOX_AUTHOR_INPUT, position.name()
                                                   .toLowerCase(), index)).should(appear, enabled)
                                                                          .setValue(bioBoxAuthor);
        }

        return this;
    }

}


