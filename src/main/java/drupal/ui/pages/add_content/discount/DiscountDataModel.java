package drupal.ui.pages.add_content.discount;

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
import static common.CommonMethods.getFutureDate;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.DATE_PATTERN;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.BLURB_TEXTAREA;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.BODY_FRAME;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.BODY_TEXTAREA;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.COUPON_CODE_INPUT;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.DISCOUNT_TITLE_INPUT;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.END_DATE_INPUT;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.EXPIRED_MESSAGE_FRAME;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.EXPIRED_MESSAGE_TEXTAREA;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.MERCHANT_AVAILABLE_LIST;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.MERCHANT_CONTAINER_WEB_ELEMENT;
import static drupal.ui.pages.add_content.discount.CreateDiscountPage.MERCHANT_INPUT;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class DiscountDataModel extends MasterPage implements ISectionDataModel {

    private String title;
    private String merchantName;
    private String blurbSummary;
    private String body;
    private String couponCode;
    private String endDate;
    private String expireText;

    public DiscountDataModel() {
        title = "Test Discount QA " + timeStampFormat(PATTERN);
        merchantName = "Test Merchant";
        blurbSummary = "This is a test discount created by the MGS QA Automation team. This discount can be deleted if needed.";
        body = "Walt Disney World Resort is saluting U.S. military personnel by offering promotional theme park tickets.\n" +
                "\n" +
                "2021 Offer\n" +
                "Choose between a 4-day or 5-day Disney Military Promotional Ticket.\n" +
                "\n" +
                "5-Day Military Promotional Tickets\n" +
                "\n" +
                "Purchase now through December 17, 2021\n" +
                "\n" +
                "With the Park Hopper Option: $315 plus tax\n" +
                "With the Park Hopper Plus Option: $345 plus tax\n" +
                "4-Day Military Promotional Tickets\n" +
                "\n" +
                "Purchase now through December 17, 2021\n" +
                "\n" +
                "With the Park Hopper Option: $296 plus tax\n" +
                "With the Park Hopper Plus Option: $326 plus tax\n" +
                "Receive admission for 4 or 5 days when you visit during the period from February 15, 2021 through December 17, 2021.\n" +
                "\n" +
                "You can also purchase the Memory Maker product for a special price of $98 through December 17, 2021. Visit the Walt Disney World website for more details.";
        couponCode = "MILITARYCOUPON" + timeStampFormat(DATE_PATTERN);
        endDate = getFutureDate(DATE_PATTERN, 10);
        expireText = null;
    }

    public static String addExpireText() {
        return "Test Content\n" +
                "\n" +
                "This discount has expired or is no longer available. \n" +
                "\n" +
                "Not to worry, we've got hundreds more military deals & discounts for you:\n" +
                "\n" +
                "    Military Discounts on Travel: Airlines, Hotels, Rental Cars & More\n" +
                "    Disney Discounts for Military Families\n" +
                "    10 Great Freebies for Military & Families\n" +
                "    The Best Amusement Park Military Discounts";
    }

    public static DiscountDataModel getExpireDiscountData() {
        return new DiscountDataModel(null, null, null, null, null, getFutureDate(DATE_PATTERN, -1), addExpireText());
    }

    public static DiscountDataModel getEditDiscountData() {
        return new DiscountDataModel("Test Discount Edit " + timeStampFormat(PATTERN), null, null, null, null, null, null);
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeTitle(expectedClass);
        selectMerchant(expectedClass);
        typeBlurbSummary(expectedClass);
        typeBody(expectedClass);
        typeCouponCode(expectedClass);
        typeEndDate(expectedClass);
        typeExpiredMessage(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeTitle(Class<P> expectedClass) {

        if (title != null) {
            DISCOUNT_TITLE_INPUT.should(exist, appear, enabled)
                                .setValue(title);
        }

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectMerchant(Class<P> expectedClass) {

        if (merchantName != null) {
            MERCHANT_INPUT.should(exist, appear, enabled)
                          .setValue(merchantName);
            if (MERCHANT_CONTAINER_WEB_ELEMENT.should(appear, visible)
                                              .isDisplayed()) {
                MERCHANT_AVAILABLE_LIST.first()
                                       .should(appear, exist)
                                       .click();
            }
        }

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeBlurbSummary(Class<P> expectedClass) {

        if (blurbSummary != null) {
            BLURB_TEXTAREA.should(exist, appear, enabled)
                          .setValue(blurbSummary);
        }

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeBody(Class<P> expectedClass) {

        if (body != null) {
            switchTo().frame(BODY_FRAME);
            BODY_TEXTAREA.should(appear, exist, visible)
                         .click();
            BODY_TEXTAREA.should(appear, visible, enabled)
                         .sendKeys(body);
            switchTo().parentFrame();
        }

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeCouponCode(Class<P> expectedClass) {

        if (couponCode != null) {
            COUPON_CODE_INPUT.should(exist, appear, enabled)
                             .setValue(couponCode);
        }

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeEndDate(Class<P> expectedClass) {

        if (endDate != null) {
            END_DATE_INPUT.should(exist, appear, enabled)
                          .scrollTo()
                          .sendKeys(endDate);
        }

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeExpiredMessage(Class<P> expectedClass) {

        if (expireText != null) {
            switchTo().frame(EXPIRED_MESSAGE_FRAME);
            EXPIRED_MESSAGE_TEXTAREA.should(appear, exist, visible)
                                    .click();
            EXPIRED_MESSAGE_TEXTAREA.should(appear, visible, enabled)
                                    .sendKeys(expireText);
            switchTo().parentFrame();
        }

        return returnInstanceOf(expectedClass);
    }

}
