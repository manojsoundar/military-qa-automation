package drupal.ui.pages.add_content.newsletter_issue;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.DATE_PATTERN;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.ui.pages.add_content.newsletter_issue.CreateNewsLetterIssuePage.DISPLAY_DATE_INPUT;
import static drupal.ui.pages.add_content.newsletter_issue.CreateNewsLetterIssuePage.EMAIL_SUBJECT_INPUT;
import static drupal.ui.pages.add_content.newsletter_issue.CreateNewsLetterIssuePage.NEWSLETTER_HEADER_TEXTAREA;
import static drupal.ui.pages.add_content.newsletter_issue.CreateNewsLetterIssuePage.NEWSLETTER_PRE_HEADER_TEXTAREA;
import static drupal.ui.pages.add_content.newsletter_issue.CreateNewsLetterIssuePage.SUBJECT_INPUT;
import static drupal.ui.pages.add_content.newsletter_issue.EditNewsLetterIssuePage.PUBLISHED_CHECKBOX;
import static org.testng.Assert.assertFalse;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class NewsletterIssueBaseModel extends MasterPage implements ISectionDataModel {

    private String subject;
    private String displayDate;
    private String emailSubject;
    private String newsLetterHeader;
    private String newsLetterPreHeader;
    private boolean isPublish;

    public NewsletterIssueBaseModel() {
        subject = "Test News Letter Issue " + timeStampFormat(PATTERN);
        displayDate = timeStampFormat(DATE_PATTERN);
        emailSubject = "";
        newsLetterHeader = "This is the Military Report Newsletter Header field";
        newsLetterPreHeader = "This is the Military Report Newsletter Pre-Header field";
        isPublish = true;
    }

    public static NewsletterIssueBaseModel getMilitaryReportData() {

        return new NewsletterIssueBaseModel("Test MR NewsLetter " + timeStampFormat(PATTERN),
                                            timeStampFormat(DATE_PATTERN), null, null, null, true);
    }

    public static NewsletterIssueBaseModel getMilitaryReportWithOverridesData() {

        return new NewsletterIssueBaseModel("Test MR NewsLetter Overrides " + timeStampFormat(PATTERN), timeStampFormat(DATE_PATTERN), null,
                                            "This is the Military Report Newsletter Pre-Header field", "This is the Military Report Newsletter Header field", true);
    }

    public static NewsletterIssueBaseModel getEarlyBriefData() {

        return new NewsletterIssueBaseModel("Test EB NewsLetter " + timeStampFormat(PATTERN),
                                            timeStampFormat(DATE_PATTERN), null, null, null, true);
    }

    public static NewsletterIssueBaseModel getEarlyBriefWithOverridesData() {

        return new NewsletterIssueBaseModel("Test EB NewsLetter Overrides " + timeStampFormat(PATTERN), timeStampFormat(DATE_PATTERN), null,
                                            "This is the Early Brief with Overrides Newsletter Pre-Header field", "This is the Early Brief with Overrides Newsletter Header field", true);
    }

    public static NewsletterIssueBaseModel getArmyActiveDutyInsiderData() {

        return new NewsletterIssueBaseModel("Test Army AD Insiders Newsletter " + timeStampFormat(PATTERN),
                                            timeStampFormat(DATE_PATTERN), null, null, null, true);
    }

    public static NewsletterIssueBaseModel getArmyActiveDutyInsiderOverridesData() {

        return new NewsletterIssueBaseModel("Test Army AD Insiders Newsletter Overrides " + timeStampFormat(PATTERN), timeStampFormat(DATE_PATTERN), null,
                                            "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"templateHeader\" style=\"background-color: #FFFFFF; width: 100%;\">\n" + "<tr>\n" +
                                                    "<td><a href=\"https://www.military.com/money/pcs-dity-move?ESRC=ad.nl\"><img class=\"fullWidthLogo\" style=\"width: 100%; height: auto; display:block;\" src=\"https://images.military.com/sites/default/files/2021-04/pcs-special-edition-header-army.jpg\" alt=\"Military.com Special\" border=\"0\" /></a>",
                                            "This is Army Active Duty Insider with Overrides Newsletter Pre-Header field", true);
    }

    public static NewsletterIssueBaseModel getNewsletterIssueEditData() {
        return new NewsletterIssueBaseModel("Test NewsLetter Issue " + timeStampFormat(PATTERN), null, null, null, null, false);
    }

    private <P extends MasterPage> P typeSubject(Class<P> expectedClass) {

        if (subject != null) {
            SUBJECT_INPUT.should(exist, appear, enabled)
                         .scrollTo()
                         .setValue(subject);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeDisplayDate(Class<P> expectedClass) {

        if (displayDate != null) {
            DISPLAY_DATE_INPUT.should(exist, appear, enabled)
                              .scrollTo()
                              .sendKeys(displayDate);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeEmailSubject(Class<P> expectedClass) {

        if (emailSubject != null) {
            EMAIL_SUBJECT_INPUT.should(exist, appear, enabled)
                               .setValue(emailSubject);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeNewsletterHeader(Class<P> expectedClass) {

        if (newsLetterHeader != null) {
            NEWSLETTER_HEADER_TEXTAREA.should(exist, appear, enabled)
                                      .setValue(newsLetterHeader);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeNewsletterPreHeader(Class<P> expectedClass) {

        if (newsLetterPreHeader != null) {
            NEWSLETTER_PRE_HEADER_TEXTAREA.should(exist, appear, enabled)
                                          .setValue(newsLetterPreHeader);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P unPublishNewsletterIssue(Class<P> expectedClass) {
        if (PUBLISHED_CHECKBOX.isSelected()) {
            PUBLISHED_CHECKBOX.should(exist, enabled)
                              .scrollTo()
                              .click();
            assertFalse(PUBLISHED_CHECKBOX.isSelected(), "Published checkbox not unchecked");
        }

        return returnInstanceOf(expectedClass);
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();

        typeSubject(expectedClass);
        typeDisplayDate(expectedClass);
        typeEmailSubject(expectedClass);
        typeNewsletterHeader(expectedClass);
        typeNewsletterPreHeader(expectedClass);
        if (!isPublish) {
            unPublishNewsletterIssue(expectedClass);
        }

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }
}

