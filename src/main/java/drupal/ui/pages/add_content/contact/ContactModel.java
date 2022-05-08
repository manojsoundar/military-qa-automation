package drupal.ui.pages.add_content.contact;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.ui.pages.add_content.contact.AddContactPage.BASE_OLD_ID_INPUT;
import static drupal.ui.pages.add_content.contact.AddContactPage.BASE_REFERENCE_INPUT;
import static drupal.ui.pages.add_content.contact.AddContactPage.BASE_REFERENCE_INPUT_LIST;
import static drupal.ui.pages.add_content.contact.AddContactPage.CONTACT_TITLE_INPUT;
import static drupal.ui.pages.add_content.contact.AddContactPage.DESCRIPTION_FRAME;
import static drupal.ui.pages.add_content.contact.AddContactPage.DESCRIPTION_FRAME_INPUT;
import static drupal.ui.pages.add_content.contact.AddContactPage.HOURS_INPUT;
import static drupal.ui.pages.add_content.contact.AddContactPage.KEYWORD_INPUT;
import static drupal.ui.pages.add_content.contact.AddContactPage.SIDE_BAR_CONTAINER_LIST;
import static drupal.ui.pages.add_content.contact.AddContactPage.SIDE_BAR_DEFAULT_SELECTED_WEB_ELEMENT;
import static drupal.ui.pages.add_content.contact.AddContactPage.SIDE_BAR_INPUT;
import static drupal.ui.pages.add_content.contact.AddContactPage.WEBSITE_INPUT;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ContactModel extends MasterPage implements ISectionDataModel {

    private String contactTitle;
    private String description;
    private String hours;
    private String webSite;
    private String sideBar;
    private String baseReference;
    private String keywords;
    private String baseOldId;

    public static ContactModel getBaseGuideContactData() {
        return new ContactModel("Test Base Contact" + timeStampFormat(PATTERN), "contact description", "M-F 9am-5:30pm; Sa 9am-2pm; Su Closed", "https://www.navyfederal.org", "Base Guide | Default Sidebar", "Hampton Roads Military Bases", null, "118A");
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        fillContactTitle(expectedClass);
        fillDescription(expectedClass);
        fillHours(expectedClass);
        fillWebSite(expectedClass);
        fillSideBar(expectedClass);
        fillBaseReference(expectedClass);
        fillKeywords(expectedClass);
        fillBaseOldId(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillContactTitle(Class<P> expectedClass) {
        CONTACT_TITLE_INPUT.should(appear, enabled)
                           .setValue(contactTitle);
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillDescription(Class<P> expectedClass) {
        switchTo().frame(DESCRIPTION_FRAME);
        DESCRIPTION_FRAME_INPUT.should(appear, exist, visible)
                               .click();
        DESCRIPTION_FRAME_INPUT.sendKeys(description);
        switchTo().parentFrame();
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillHours(Class<P> expectedClass) {
        HOURS_INPUT.should(appear, enabled)
                   .setValue(hours);
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillWebSite(Class<P> expectedClass) {
        WEBSITE_INPUT.scrollIntoView("{behavior: 'instant', block: 'center', inline: 'center'}")
                     .should(appear, visible, enabled)
                     .setValue(webSite);
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillSideBar(Class<P> expectedClass) {
        SIDE_BAR_DEFAULT_SELECTED_WEB_ELEMENT.should(exist, appear)
                                             .click();
        if (sideBar != null) {
            SIDE_BAR_INPUT.should(appear, enabled)
                          .setValue(sideBar);

            if (SIDE_BAR_CONTAINER_LIST.first()
                                       .should(appear, exist)
                                       .exists()) {
                SIDE_BAR_CONTAINER_LIST.first()
                                       .click();
            }
        }

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillBaseReference(Class<P> expectedClass) {
        BASE_REFERENCE_INPUT.should(appear, enabled)
                            .setValue(baseReference);
        BASE_REFERENCE_INPUT_LIST.first()
                                 .should(enabled, visible)
                                 .click();
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillKeywords(Class<P> expectedClass) {
        KEYWORD_INPUT.should(appear, enabled)
                     .setValue(keywords);
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillBaseOldId(Class<P> expectedClass) {
        BASE_OLD_ID_INPUT.should(appear, enabled)
                         .setValue(baseOldId);
        return returnInstanceOf(expectedClass);
    }

}
