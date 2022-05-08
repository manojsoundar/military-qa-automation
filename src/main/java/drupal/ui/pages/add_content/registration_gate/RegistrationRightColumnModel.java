package drupal.ui.pages.add_content.registration_gate;

import drupal.enums.content.FlexibleContentSpaceType;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;
import static drupal.ui.pages.add_content.registration_gate.CreateRegistrationGatePage.RIGHT_COLUMN_CONTENT_DROP_DOWN_ARROW;
import static drupal.ui.pages.add_content.registration_gate.CreateRegistrationGatePage.RIGHT_COLUMN_IFRAME;
import static drupal.ui.pages.add_content.registration_gate.CreateRegistrationGatePage.RIGHT_COLUMN_TEXT_AREA_INPUT;
import static drupal.ui.pages.add_content.registration_gate.CreateRegistrationGatePage.RIGHT_COLUMN_TEXT_AREA_TITLE_INPUT;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Log4j2
@Data
@AllArgsConstructor
public class RegistrationRightColumnModel extends MasterPage implements ISectionDataModel {

    FlexibleContentSpaceType rightColumnContentType;
    String rightColumnTitleText;
    String rightColumnData;

    public RegistrationRightColumnModel() {
        rightColumnContentType = FlexibleContentSpaceType.ADD_FULL_TEXT_AREA;
        rightColumnTitleText = "Military News, Pay Charts, Benefits, Jobs & Discounts";
        rightColumnData = "Join over 10 Million Military.com members to make the most of your military service and experience";

    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        selectRightColumnContentType(expectedClass);
        fillRightColumnData(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectRightColumnContentType(Class<P> expectedClass) {
        if (rightColumnContentType != null) {
            RIGHT_COLUMN_CONTENT_DROP_DOWN_ARROW.should(exist, visible, enabled)
                                                .click();
            rightColumnContentType.getContentTypeLinkElement()
                                  .first()
                                  .should(exist, visible, enabled)
                                  .click();
        }

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillRightColumnData(Class<P> expectedClass) {
        if (rightColumnTitleText != null) {
            RIGHT_COLUMN_TEXT_AREA_TITLE_INPUT.should(exist, appear, enabled)
                                              .setValue(rightColumnTitleText);
        }
        if (rightColumnData != null) {
            switchTo().frame(RIGHT_COLUMN_IFRAME);
            RIGHT_COLUMN_TEXT_AREA_INPUT.should(appear, exist, visible)
                                        .click();
            RIGHT_COLUMN_TEXT_AREA_INPUT.sendKeys(rightColumnData);
            switchTo().parentFrame();
        }
        return returnInstanceOf(expectedClass);
    }

}
