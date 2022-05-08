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
import static drupal.enums.content.FlexibleContentSpaceType.ADD_CALL_TO_ACTION_OLD;
import static java.lang.String.format;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@Getter
@AllArgsConstructor
public class FlexibleContentSpaceCallToActionOldModel extends MasterPage implements ISectionDataModel, IFlexibleContentSpace {

    private static final String FLEXIBLE_CONTENT_SPACE_LINK_URL_INPUT = "input.form-text[id*='-%s-%s-subform-field-link-0-uri']";
    private static final String FLEXIBLE_CONTENT_SPACE_LINK_LINK_TEXT_INPUT = ".form-type-textfield input[id*='-%s-%s-subform-field-link']";
    private static final String FLEXIBLE_CONTENT_SPACE_CTA_OLD_FRAME = "div[id*='-%s-%s-subform-field-full-text'] iframe";
    private static final SelenideElement FLEXIBLE_CONTENT_SPACE_CTA_OLD_TEXT_TEXTAREA = $("body.cke_editable p");

    private final FlexibleContentSpacePosition position;
    private final String ctaOldText;
    private final String linkUrl;
    private final String linkText;
    private int index;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        index = selectFlexibleContentSpace(ADD_CALL_TO_ACTION_OLD, position) - 1;
        enterCallToActionOldText().enterLinkSection();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private FlexibleContentSpaceCallToActionOldModel enterLinkSection() {
        waitAjaxJQueryMet(30);
        if (linkUrl != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_LINK_URL_INPUT, position.name()
                                                                    .toLowerCase(), index)).should(appear, enabled)
                                                                                           .setValue(linkUrl);
        }
        if (linkText != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_LINK_LINK_TEXT_INPUT, position.name()
                                                                          .toLowerCase(), index)).should(appear, enabled)
                                                                                                 .setValue(linkText);
        }
        return this;
    }

    private FlexibleContentSpaceCallToActionOldModel enterCallToActionOldText() {
        if (ctaOldText != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_CTA_OLD_FRAME, position.name()
                                                                   .toLowerCase(), index)).should(appear, visible);
            switchTo().frame($(format(FLEXIBLE_CONTENT_SPACE_CTA_OLD_FRAME, position.name()
                                                                                    .toLowerCase(), index)));
            FLEXIBLE_CONTENT_SPACE_CTA_OLD_TEXT_TEXTAREA.should(appear, exist, visible)
                                                        .click();
            FLEXIBLE_CONTENT_SPACE_CTA_OLD_TEXT_TEXTAREA.should(appear, visible, enabled)
                                                        .sendKeys(ctaOldText);
            switchTo().parentFrame();
        }
        return this;
    }

}


