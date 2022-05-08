package drupal.ui.pages.add_content.event;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static common.CommonMethods.getFutureDate;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.DATE_PATTERN;
import static drupal.models.TimeStampPattern.TIME_PATTERN;
import static drupal.ui.pages.add_content.event.EventPage.DISPLAY_TIME_CHECKBOX;
import static drupal.ui.pages.add_content.event.EventPage.END_DATE_INPUT;
import static drupal.ui.pages.add_content.event.EventPage.END_DATE_TIME_INPUT;
import static drupal.ui.pages.add_content.event.EventPage.START_DATE_INPUT;
import static drupal.ui.pages.add_content.event.EventPage.START_DATE_TIME_INPUT;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Log4j2
@Data
@AllArgsConstructor
public class EventDateTimeModel extends MasterPage implements ISectionDataModel {

    String startDate;
    String startDateTime;
    String endDate;
    String endDateTime;
    boolean displayTime;

    public EventDateTimeModel() {
        startDate = timeStampFormat(DATE_PATTERN);
        startDateTime = timeStampFormat(TIME_PATTERN);
        endDate = getFutureDate(DATE_PATTERN, 5);
        endDateTime = timeStampFormat(TIME_PATTERN);
        displayTime = true;
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        selectDisplayTime(expectedClass);
        typeStartDate(expectedClass);
        typeStartDateTime(expectedClass);
        typeEndDate(expectedClass);
        typeEndDateTime(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectDisplayTime(Class<P> expectedClass) {
        if (Boolean.TRUE.equals(displayTime)) {
            DISPLAY_TIME_CHECKBOX.should(visible, enabled)
                                 .scrollIntoView(false)
                                 .click();
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeStartDate(Class<P> expectedClass) {
        if (startDate != null) {
            START_DATE_INPUT.should(enabled, visible, appear)
                            .scrollTo()
                            .sendKeys(startDate);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeStartDateTime(Class<P> expectedClass) {
        if (startDateTime != null) {
            START_DATE_TIME_INPUT.should(enabled, visible, appear)
                                 .sendKeys(startDateTime);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeEndDate(Class<P> expectedClass) {
        if (endDate != null) {
            END_DATE_INPUT.should(enabled, visible, appear)
                          .sendKeys(endDate);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeEndDateTime(Class<P> expectedClass) {
        if (endDateTime != null) {
            END_DATE_TIME_INPUT.should(enabled, visible, appear)
                               .sendKeys(endDateTime);
        }
        return returnInstanceOf(expectedClass);
    }

    public EventDateTimeModel typeDateTimeVirtualJob() {
        return new EventDateTimeModel(timeStampFormat(DATE_PATTERN), timeStampFormat(TIME_PATTERN),
                                      getFutureDate(DATE_PATTERN, 10), timeStampFormat(TIME_PATTERN), false);

    }

}
