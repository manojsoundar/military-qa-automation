package drupal.ui.pages.add_content.flexible_content_space;

import com.codeborne.selenide.SelenideElement;
import drupal.enums.content.FlexibleContentSpacePosition;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static drupal.enums.content.FlexibleContentSpaceType.ADD_EMBEDDED_VIEW;
import static java.lang.String.format;

@Getter
@AllArgsConstructor
public class FlexibleContentSpaceEmbeddedViewModel extends MasterPage implements ISectionDataModel, IFlexibleContentSpace {

    private static final String EMBEDDED_VIEW_TITLE_INPUT = "input.form-text[id*=-%s-%s-subform-field-title]";
    private static final String EMBEDDED_VIEW_TEXT_FRAME = "div[id*=-%s-%s-subform-field-full-text] iframe";
    private static final String EMBEDDED_VIEW_TEXT_VIEW_INPUT = "input.form-text[id*=-%s-%s-subform-field-view]";
    private static final SelenideElement EMBEDDED_BODY_TEXTAREA = $("body.cke_editable p");

    private final FlexibleContentSpacePosition position;
    private final String embeddedViewTitle;
    private final String embeddedViewText;
    private final String embeddedViewView;
    private int index;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        // TODO java:S1117 Local variables should not shadow class fields
        int index = selectFlexibleContentSpace(ADD_EMBEDDED_VIEW, position) - 1;
        enterEmbeddedView().enterEmbeddedViewText();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private FlexibleContentSpaceEmbeddedViewModel enterEmbeddedView() {
        $(format(EMBEDDED_VIEW_TITLE_INPUT, position.getFlexibleContentPosition(), index)).should(appear, enabled)
                                                                                          .setValue(embeddedViewTitle);

        $(format(EMBEDDED_VIEW_TEXT_VIEW_INPUT, position.getFlexibleContentPosition(), index)).should(appear, enabled)
                                                                                              .setValue(embeddedViewView);
        return this;
    }

    private FlexibleContentSpaceEmbeddedViewModel enterEmbeddedViewText() {
        if (embeddedViewText != null) {
            $(format(EMBEDDED_VIEW_TEXT_FRAME, position.getFlexibleContentPosition(), index))
                    .should(appear, visible);
            switchTo().frame($(format(EMBEDDED_VIEW_TEXT_FRAME, position.getFlexibleContentPosition(), index)));
            EMBEDDED_BODY_TEXTAREA.should(appear, exist, visible)
                                  .click();
            EMBEDDED_BODY_TEXTAREA.should(appear, visible, enabled)
                                  .sendKeys(embeddedViewText);
            switchTo().parentFrame();
        }
        return this;
    }

}