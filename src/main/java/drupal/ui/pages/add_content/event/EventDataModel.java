package drupal.ui.pages.add_content.event;

import drupal.enums.content.Category;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import java.util.Collections;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.ui.pages.add_content.event.EventPage.BLURB_TEXTAREA;
import static drupal.ui.pages.add_content.event.EventPage.BODY_FRAME;
import static drupal.ui.pages.add_content.event.EventPage.BODY_TEXTAREA;
import static drupal.ui.pages.add_content.event.EventPage.CATEGORIES_AVAILABLE_LIST;
import static drupal.ui.pages.add_content.event.EventPage.CATEGORIES_CONTAINER_WEB_ELEMENT;
import static drupal.ui.pages.add_content.event.EventPage.CATEGORIES_INPUT;
import static drupal.ui.pages.add_content.event.EventPage.EMAIL_INPUT;
import static drupal.ui.pages.add_content.event.EventPage.EVENT_ID_INPUT;
import static drupal.ui.pages.add_content.event.EventPage.HEADLINE_INPUT;
import static drupal.ui.pages.add_content.event.EventPage.LINK_INPUT;
import static drupal.ui.pages.add_content.event.EventPage.PHONE_INPUT;
import static drupal.ui.pages.add_content.event.EventPage.TITLE_INPUT;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Log4j2
@Data
@AllArgsConstructor
public class EventDataModel extends MasterPage implements ISectionDataModel {

    String name;
    String headline;
    String phone;
    String email;
    String link;
    String eventID;
    Category category;
    String blurbText;
    String bodyText;

    public EventDataModel() {
        name = "Monster" + timeStampFormat(PATTERN);
        headline = "Monster";
        phone = "9784168000";
        email = "info@monster.com";
        link = "https://www.military.com";
        eventID = "123";
        category = Category.DINING_DISCOUNTS;
        blurbText = "Text blurb text";
        bodyText = "Test body text";
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeName(expectedClass);
        typeHeadline(expectedClass);
        typePhone(expectedClass);
        typeEmail(expectedClass);
        typeLink(expectedClass);
        typeEventID(expectedClass);
        typeBlurbText(expectedClass);
        typeBodyText(expectedClass);
        typeCategory(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeName(Class<P> expectedClass) {
        if (name != null) {
            TITLE_INPUT.should(appear, exist, enabled)
                       .scrollTo()
                       .setValue(name);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeHeadline(Class<P> expectedClass) {
        if (headline != null) {
            HEADLINE_INPUT.should(appear, exist, enabled)
                          .scrollTo()
                          .setValue(headline);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typePhone(Class<P> expectedClass) {
        if (phone != null) {
            PHONE_INPUT.should(appear, exist, enabled)
                       .scrollTo()
                       .setValue(phone);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeEmail(Class<P> expectedClass) {
        if (email != null) {
            EMAIL_INPUT.should(appear, exist, enabled)
                       .scrollTo()
                       .setValue(email);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeLink(Class<P> expectedClass) {
        if (link != null) {
            LINK_INPUT.should(appear, exist, enabled)
                      .scrollTo()
                      .setValue(link);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeEventID(Class<P> expectedClass) {
        if (eventID != null) {
            EVENT_ID_INPUT.should(appear, exist, enabled)
                          .scrollTo()
                          .setValue(eventID);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeBlurbText(Class<P> expectedClass) {
        if (blurbText != null) {
            BLURB_TEXTAREA.should(appear, exist, enabled)
                          .scrollTo()
                          .setValue(blurbText);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeBodyText(Class<P> expectedClass) {
        if (bodyText != null) {
            switchTo().frame(BODY_FRAME);
            BODY_TEXTAREA.should(appear, exist, visible)
                         .click();
            BODY_TEXTAREA.should(appear, visible, enabled)
                         .sendKeys(bodyText);
            switchTo().parentFrame();
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeCategory(Class<P> expectedClass) {
        if (!Collections.singletonList(category)
                        .isEmpty()) {
            for (Category categoryItem : Collections.singletonList(category)) {
                CATEGORIES_INPUT.should(exist, appear, enabled)
                                .click();
                CATEGORIES_INPUT.setValue(categoryItem.getCategoryName());
                if (CATEGORIES_CONTAINER_WEB_ELEMENT.should(appear, visible)
                                                    .isDisplayed()) {
                    CATEGORIES_AVAILABLE_LIST.first()
                                             .should(appear, exist)
                                             .click();
                }
            }
        }
        return returnInstanceOf(expectedClass);
    }

}
