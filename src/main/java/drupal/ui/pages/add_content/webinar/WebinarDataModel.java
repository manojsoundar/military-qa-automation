package drupal.ui.pages.add_content.webinar;

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
import static drupal.ui.pages.add_content.webinar.WebinarPage.HOST_INPUT;
import static drupal.ui.pages.add_content.webinar.WebinarPage.WEBINAR_BODY_TEXTAREA;
import static drupal.ui.pages.add_content.webinar.WebinarPage.WEBINAR_DETAILS_BODY_FRAME;
import static drupal.ui.pages.add_content.webinar.WebinarPage.WEBINAR_ID_INPUT;
import static drupal.ui.pages.add_content.webinar.WebinarPage.WEBINAR_TIME_INPUT;
import static drupal.ui.pages.add_content.webinar.WebinarPage.WEBINAR_TITLE_INPUT;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Log4j2
@Data
@AllArgsConstructor
public class WebinarDataModel extends MasterPage implements ISectionDataModel {

    String title;
    String id;
    String time;
    String host;
    String details;

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        typeTitle(expectedClass);
        typeID(expectedClass);
        typeTime(expectedClass);
        typeHost(expectedClass);
        typeDetail(expectedClass);
        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeTitle(Class<P> expectedClass) {
        if (title != null) {
            WEBINAR_TITLE_INPUT.should(appear, exist, enabled)
                               .scrollTo()
                               .setValue(title);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeID(Class<P> expectedClass) {
        if (id != null) {
            WEBINAR_ID_INPUT.should(appear, exist, enabled)
                            .scrollTo()
                            .setValue(id);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeTime(Class<P> expectedClass) {
        if (time != null) {
            WEBINAR_TIME_INPUT.should(appear, exist, enabled)
                              .scrollTo()
                              .setValue(time);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeHost(Class<P> expectedClass) {
        if (host != null) {
            HOST_INPUT.should(appear, exist, enabled)
                      .scrollTo()
                      .setValue(host);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeDetail(Class<P> expectedClass) {
        if (details != null) {
            switchTo().frame(WEBINAR_DETAILS_BODY_FRAME);
            WEBINAR_BODY_TEXTAREA.should(appear, exist, visible)
                                 .click();
            WEBINAR_BODY_TEXTAREA.should(appear, exist)
                                 .sendKeys(details);
            switchTo().parentFrame();
        }
        return returnInstanceOf(expectedClass);
    }
}
