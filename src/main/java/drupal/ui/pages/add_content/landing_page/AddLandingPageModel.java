package drupal.ui.pages.add_content.landing_page;

import drupal.enums.content.Category;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.ui.pages.add_content.landing_page.AddLandingPage.BODY_FRAME;
import static drupal.ui.pages.add_content.landing_page.AddLandingPage.BODY_TEXTAREA;
import static drupal.ui.pages.add_content.landing_page.AddLandingPage.CATEGORIES_AVAILABLE_LIST;
import static drupal.ui.pages.add_content.landing_page.AddLandingPage.CATEGORY_INPUT;
import static drupal.ui.pages.add_content.landing_page.AddLandingPage.CONTENT_RELEASE_INPUT;
import static drupal.ui.pages.add_content.landing_page.AddLandingPage.CONTENT_RELEASE_LIST;
import static drupal.ui.pages.add_content.landing_page.AddLandingPage.HIDE_BREAD_CRUMB_INPUT;
import static drupal.ui.pages.add_content.landing_page.AddLandingPage.KEYWORDS_INPUT;
import static drupal.ui.pages.add_content.landing_page.AddLandingPage.KEYWORDS_LIST;
import static drupal.ui.pages.add_content.landing_page.AddLandingPage.SIDEBAR_DROPDOWN;
import static drupal.ui.pages.add_content.landing_page.AddLandingPage.SIDEBAR_SEARCH_INPUT;
import static drupal.ui.pages.add_content.landing_page.AddLandingPage.SIDEBAR_SEARCH_RESULTS;
import static drupal.ui.pages.add_content.landing_page.AddLandingPage.SUMMARY_INPUT;
import static drupal.ui.pages.add_content.landing_page.AddLandingPage.TITLE_INPUT;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AddLandingPageModel extends MasterPage implements ISectionDataModel {

    protected static final String DEFAULT_BODY_DATA = "You already know why Veterans Day is important. You are the service members, veterans or family and friends of veterans who stay up-to-date on military news, pay, benefits, fitness and veteran jobs.\n" +
            "Related: 2021 Veterans Day Deals, Discounts and Freebies\n" +
            "Veterans Day is a time for us to pay our respects to those who have served. For one day, we stand united in respect for you, our veterans.\n" +
            "This holiday started as a day to reflect upon the heroism of those who died in our country's service and was originally called Armistice Day." +
            " It fell on Nov. 11 because that is the anniversary of the signing of the Armistice that ended World War I. " +
            "However, in 1954, the holiday was changed to \"Veterans Day\" in order to account for all veterans in all wars.";

    protected static final String DEFAULT_CURATE_LIST_BODY = "The Shooting, Hunting and Outdoor Trade Show, known as SHOT Show, is taking place Jan. 21-24 in Las Vegas in 2020. With more than 60,000 attendees, the trade show is the largest of its kind in the world. Military.com is on the ground to bring you the latest updates on new tactical arms, clothing and gear. See all of our stories and videos from the show below.";

    protected static final String DEFAULT_SUMMARY_DATA = "Daily U.S. military news updates including military gear and equipment, breaking news, international news and more.";

    private String landingPageTitle;
    private boolean hideBreadCrumbStatus;
    private String blurbOrSummary;
    private String body;
    private List<String> sideBars;
    private List<String> keywords;
    private List<Category> category;
    private List<String> contentRelease;

    public AddLandingPageModel() {
        landingPageTitle = "Test Landing Page " + timeStampFormat(PATTERN);
        hideBreadCrumbStatus = true;
        blurbOrSummary = getSummaryData();
        body = getBodyData();
        sideBars = List.of("Test Drupal 88 Sidebar");
        keywords = null;
        category = List.of(Category.BENEFITS);
        contentRelease = null;
    }

    public static String getBodyData() {
        return DEFAULT_BODY_DATA;
    }

    public static String getCurateListBody() {
        return DEFAULT_CURATE_LIST_BODY;
    }

    public static String getSummaryData() {
        return DEFAULT_SUMMARY_DATA;
    }

    public static AddLandingPageModel getCTALandingPageData() {
        return new AddLandingPageModel("Test Landing Page " + timeStampFormat(PATTERN), false, null, null, null, null, null, null);
    }

    public static AddLandingPageModel taxonomyCategoryTier1PreCondition() {
        return new AddLandingPageModel("Test Article Category " + timeStampFormat(PATTERN), false, "Test Summary", null, null, null, null, null);
    }

    public static AddLandingPageModel taxonomyCategorySubChannelPreCondition() {
        return new AddLandingPageModel("Test Article Category " + timeStampFormat(PATTERN), false, "Test Summary", null, null, null, List.of(Category.ABOUT_US), null);
    }

    public static AddLandingPageModel getLandingPageData() {
        return new AddLandingPageModel("Test Landing Page " + timeStampFormat(PATTERN), false, null, null, null, null, null, null);
    }

    public static AddLandingPageModel getSideBarBlockData() {
        return new AddLandingPageModel("Test Landing Page " + timeStampFormat(PATTERN), false, null, null, null, null, null, null);
    }

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        enterLandingPageTitle().checkHideBreadCrumbCheckbox()
                               .enterBlurbOrSummary()
                               .enterLandingPageBody()
                               .selectSideBars()
                               .enterKeywords()
                               .chooseCategory()
                               .enterContentRelease();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();
        enterLandingPageTitle();
        return returnInstanceOf(expectedClass);
    }

    private AddLandingPageModel enterLandingPageTitle() {
        TITLE_INPUT.should(exist, appear, enabled)
                   .setValue(landingPageTitle);
        return this;
    }

    private AddLandingPageModel checkHideBreadCrumbCheckbox() {
        if (isHideBreadCrumbStatus()) {
            HIDE_BREAD_CRUMB_INPUT.should(exist, appear, enabled)
                                  .setSelected(true);
        }
        return this;
    }

    private AddLandingPageModel enterBlurbOrSummary() {
        if (blurbOrSummary != null) {
            SUMMARY_INPUT.should(exist, appear, enabled)
                         .setValue(blurbOrSummary);
        }
        return this;
    }

    private AddLandingPageModel enterLandingPageBody() {
        if (body != null) {
            SUMMARY_INPUT.scrollTo();
            BODY_FRAME.should(appear, visible);
            switchTo().frame(BODY_FRAME);
            BODY_TEXTAREA.should(appear, exist, visible)
                         .click();
            BODY_TEXTAREA.should(appear, visible, enabled)
                         .sendKeys(body);
            switchTo().parentFrame();
        }
        return this;
    }

    private AddLandingPageModel chooseCategory() {
        if (category != null) {
            for (Category categoryItem : category) {
                CATEGORY_INPUT.should(appear, enabled)
                              .click();
                CATEGORY_INPUT.setValue(categoryItem.getCategoryName());

                if (!CATEGORIES_AVAILABLE_LIST.isEmpty()) {
                    CATEGORIES_AVAILABLE_LIST.first()
                                             .should(enabled, visible)
                                             .click();
                }
            }
        }
        return this;
    }

    private AddLandingPageModel selectSideBars() {
        if (sideBars != null) {
            for (String sidebar : sideBars) {
                SUMMARY_INPUT.scrollTo();
                SIDEBAR_DROPDOWN.should(enabled, visible)
                                .click();
                SIDEBAR_SEARCH_INPUT.should(enabled, visible)
                                    .setValue(sidebar);

                if (!SIDEBAR_SEARCH_RESULTS.isEmpty()) {
                    SIDEBAR_SEARCH_RESULTS.first()
                                          .should(enabled, visible)
                                          .click();
                }
            }
        }
        return this;
    }

    private AddLandingPageModel enterKeywords() {
        if (keywords != null) {
            for (String keyword : keywords) {
                SIDEBAR_DROPDOWN.scrollTo();
                KEYWORDS_INPUT.should(enabled, visible)
                              .setValue(keyword);
                if (!KEYWORDS_LIST.isEmpty()) {
                    KEYWORDS_LIST.first()
                                 .should(enabled, visible)
                                 .click();
                }
            }
        }
        return this;
    }

    private AddLandingPageModel enterContentRelease() {
        if (contentRelease != null) {
            for (String contentReleaseItem : contentRelease) {
                CONTENT_RELEASE_INPUT.should(enabled, visible)
                                     .setValue(contentReleaseItem);
                if (!CONTENT_RELEASE_LIST.isEmpty()) {
                    CONTENT_RELEASE_LIST.first()
                                        .should(enabled, visible)
                                        .click();
                }
            }
        }
        return this;
    }

}
