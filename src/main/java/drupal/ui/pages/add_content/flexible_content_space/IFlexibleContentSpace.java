package drupal.ui.pages.add_content.flexible_content_space;

import com.codeborne.selenide.Condition;
import drupal.enums.content.FlexibleContentSpacePosition;
import drupal.enums.content.FlexibleContentSpaceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

public interface IFlexibleContentSpace {

    Logger LOG = LogManager.getLogger(IFlexibleContentSpace.class);
    String DROPDOWN_ARROW_WEB_ELEMENT = "//div[@id='-%s-add-more-wrapper' or contains(@id, '-%s-add-more-wrapper')] //div[@class='dropbutton-wrapper dropbutton-multiple'][not(parent::div[@class='paragraphs-dropbutton-wrapper'])] //li[@class='dropbutton-toggle']/button";
    String FLEXIBLE_CONTENT_EDIT_BUTTON = "//div[contains(@id,'-%s-%s-')]//input[contains(@class,'button') and contains(@value,'Edit')]";

    default int selectFlexibleContentSpace(FlexibleContentSpaceType contentType, FlexibleContentSpacePosition position) {
        LOG.debug("before number of sub section: {}", $$x("//div[@id='edit-field-flexible-" + position.getFlexibleContentPosition() + "-wrapper']//table/tbody//tr"));
        $x(String.format(DROPDOWN_ARROW_WEB_ELEMENT, position.getFlexibleContentPosition(), position.getFlexibleContentPosition())).scrollIntoView("{behavior: 'instant', block: 'center', inline: 'center'}")
                                                                                                                                   .should(exist, visible, enabled)
                                                                                                                                   .click();
        contentType.getContentTypeLinkElement()
                   .find(Condition.attributeMatching("id", ".*" + position.getFlexibleContentPosition() + ".*"))
                   .should(exist, visible, enabled)
                   .click();
        waitAjaxJQueryMet(120);
        // TODO IntelliJ syntax comparison String ; String values are compared using '==': use the "equals" method if value comparison was intended
        if (contentType.is(FlexibleContentSpaceType.ADD_LIST_OF_LINKS) && position.getFlexibleContentPosition() == "content") {
            return $$x("//div[@id='edit-field-" + position.getFlexibleContentPosition() + "-wrapper']").size();
        }
        if (contentType.is(FlexibleContentSpaceType.ADD_LIST_OF_LINKS)) {
            return $$x("//div[@id='edit-field-flexible-" + position.getFlexibleContentPosition() + "-wrapper']//table/tbody[descendant::tr and not(descendant::table)]").size();
        }
        return $$x("//div[contains(@id,'-" + position.getFlexibleContentPosition() + "-wrapper')]//table/tbody//tr").size();
    }

    default void clickEditFlexibleContentSpace(FlexibleContentSpacePosition position, int index) {
        $x(format(FLEXIBLE_CONTENT_EDIT_BUTTON, position.name()
                                                        .toLowerCase(), index)).should(appear, exist, enabled).click();
    }
}
