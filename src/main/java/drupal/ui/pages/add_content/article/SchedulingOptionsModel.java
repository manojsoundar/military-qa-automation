package drupal.ui.pages.add_content.article;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.DATE_PATTERN;
import static drupal.models.TimeStampPattern.TIME_PATTERN;
import static drupal.ui.pages.add_content.article.CreateArticlePage.PUBLISH_ON_DATE;
import static drupal.ui.pages.add_content.article.CreateArticlePage.PUBLISH_ON_TIME;
import static drupal.ui.pages.add_content.article.CreateArticlePage.SCHEDULING_OPTIONS_LINK;
import static drupal.ui.pages.add_content.article.CreateArticlePage.UN_PUBLISH_ON_DATE;
import static drupal.ui.pages.add_content.article.CreateArticlePage.UN_PUBLISH_ON_TIME;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SchedulingOptionsModel extends MasterPage implements ISectionDataModel {

    private String unPublishOnDate;
    private String unPublishOnTime;
    private String publishOnDate;
    private String publishOnTime;

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {

        clickSchedulingOptions(expectedClass);
        fillUnPublishOnData(expectedClass);
        fillPublishOnData(expectedClass);


        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P clickSchedulingOptions(Class<P> expectedClass) {

        SCHEDULING_OPTIONS_LINK.should(exist, visible, enabled)
                               .click();
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillUnPublishOnData(Class<P> expectedClass) {

        if (unPublishOnDate != null) {
            UN_PUBLISH_ON_DATE.should(exist, enabled)
                              .sendKeys(unPublishOnDate);
        }
        if (unPublishOnTime != null) {
            UN_PUBLISH_ON_TIME.should(exist, enabled)
                              .sendKeys(unPublishOnTime);
        }

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillPublishOnData(Class<P> expectedClass) {

        if (publishOnDate != null) {
            PUBLISH_ON_DATE.should(exist, enabled)
                           .sendKeys(publishOnDate);
        }
        if (publishOnTime != null) {
            PUBLISH_ON_TIME.should(exist, enabled)
                           .sendKeys(publishOnTime);
        }

        return returnInstanceOf(expectedClass);
    }

    public static SchedulingOptionsModel getUnPublishData() {
        return new SchedulingOptionsModel(timeStampFormat(DATE_PATTERN), timeStampFormat(TIME_PATTERN), null, null);
    }

    public static SchedulingOptionsModel getPublishData() {
        return new SchedulingOptionsModel(null, null, timeStampFormat(DATE_PATTERN), timeStampFormat(TIME_PATTERN));
    }


}