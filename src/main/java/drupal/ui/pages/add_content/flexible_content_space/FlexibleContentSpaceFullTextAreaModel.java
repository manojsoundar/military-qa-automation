package drupal.ui.pages.add_content.flexible_content_space;

import com.codeborne.selenide.SelenideElement;
import drupal.enums.content.FlexibleContentSpacePosition;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AttachAnImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static drupal.enums.content.FlexibleContentSpaceType.ADD_FULL_TEXT_AREA;
import static java.lang.String.format;

@Getter
@AllArgsConstructor
public class FlexibleContentSpaceFullTextAreaModel extends MasterPage implements ISectionDataModel, IFlexibleContentSpace, AttachAnImage {

    protected static final SelenideElement FULL_TEXT_AREA_TEXTAREA = $("body.cke_editable p");
    private static final String FLEXIBLE_CONTENT_SPACE_FULL_TEXT_AREA_TITLE_INPUT = "input.form-text[id*=-%s-%s-subform-field-title]";
    private static final String FLEXIBLE_CONTENT_SPACE_FULL_TEXT_AREA_TEXT_FRAME = ".cke[id*=-%s-%s-subform-field-full-text] iframe";

    private final FlexibleContentSpacePosition position;
    private int index;
    private final String title;
    private final String fullTextAreaText;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        index = selectFlexibleContentSpace(ADD_FULL_TEXT_AREA, position) - 1;
        enterFullTextArea().enterFullTextAreaText();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private FlexibleContentSpaceFullTextAreaModel enterFullTextArea() {
        $(format(FLEXIBLE_CONTENT_SPACE_FULL_TEXT_AREA_TITLE_INPUT, position.getFlexibleContentPosition(), index)).should(appear, enabled)
                                                                                                                  .setValue(title);
        return this;
    }

    private FlexibleContentSpaceFullTextAreaModel enterFullTextAreaText() {
        if (fullTextAreaText != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_FULL_TEXT_AREA_TEXT_FRAME, position.getFlexibleContentPosition(), index)).should(appear, visible);
            switchTo().frame($(format(FLEXIBLE_CONTENT_SPACE_FULL_TEXT_AREA_TEXT_FRAME, position.getFlexibleContentPosition(), index)));
            FULL_TEXT_AREA_TEXTAREA.should(appear, exist, visible)
                                   .click();
            FULL_TEXT_AREA_TEXTAREA.should(appear, visible, enabled)
                                   .sendKeys(fullTextAreaText);
            switchTo().parentFrame();
        }
        return this;
    }

}


