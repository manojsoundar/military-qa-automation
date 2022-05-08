package drupal.ui.pages.add_content.discount;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.add_content.merchant.MerchantResultPage;
import lombok.extern.log4j.Log4j2;
import mgs.qa.utils.asserts.SoftAssertMt;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.driver;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@SuppressWarnings("CanBeFinal")
@Log4j2
public class DiscountsPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/discounts/";
    private static final SelenideElement DISCOUNTS_H_1_TAG = $("div.block--page--title span").as("Discounts Page H1 Tag");
    private static final SelenideElement EDIT_TAB = $("ul.menu.menu--tabs a[href*='edit']");
    private static final SelenideElement STATUS_MESSAGE = $(".messages--status");
    private static final SelenideElement TITLE_STATUS_MESSAGE = STATUS_MESSAGE.$("em");
    private static final SelenideElement EXPIRE_MESSAGE = $("#bodyContent p:nth-child(3)").as("expired message text");
    private static final SelenideElement DISCLAIMER_HEADING_TEXT = $(".well.text--center label");
    private static final SelenideElement DISCLAIMER_BLURB_TEXT = $(".well.text--center p");
    private static final ElementsCollection DISCLAIMER_SECTION_LIST = $$(".well.text--center");
    private static final SelenideElement PRINT_COUPON_BUTTON = $("a.button[href*='jpg']");
    private static final SelenideElement REDEEM_ONLINE_LINK = $("div.field.field--link a.button");
    private static final SelenideElement COPY_DISCOUNT_CODE_LINK = $(".field--coupon-code a");
    private static final SelenideElement DISCOUNT_CODE_TEXT = $(".field--coupon-code span");
    private static final SelenideElement MERCHANT_LINK = $(".block--breadcrumb li:nth-child(3) a").as("Merchant Link");
    private final String currentDiscountURL = getDriver().getCurrentUrl();
    SoftAssert softAssert = SoftAssertMt.getSoftAssert();

    public DiscountsPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Discounts Page not loaded.");
        log.info("Discounts Page loaded properly.");
    }

    public DiscountsPage verifyCreatedDiscount(String discountTitle) {

        assertTrue(DISCOUNTS_H_1_TAG.should(exist, appear)
                                    .has(text(discountTitle)), format("Correct H1 Title message not displayed : %s", DISCOUNTS_H_1_TAG.getText()));
        assertTrue(TITLE_STATUS_MESSAGE.should(exist, appear)
                                       .has(text(discountTitle)), format("Title Status message not displayed correctly : %s", TITLE_STATUS_MESSAGE.getText()));
        assertTrue(verifyURLLoaded(URL_REGEX + discountTitle.toLowerCase()
                                                            .replace(" ", "-")), "Discount page not loaded.");
        return this;
    }

    public DiscountsPage verifyEditedDiscount(String discountTitle) {

        assertTrue(DISCOUNTS_H_1_TAG.should(exist, appear)
                                    .has(text(discountTitle)), format("Correct H1 Title message not displayed : %s", DISCOUNTS_H_1_TAG.getText()));
        assertTrue(TITLE_STATUS_MESSAGE.should(exist, appear)
                                       .has(text(discountTitle)), format("Title Status message not displayed correctly : %s", TITLE_STATUS_MESSAGE.getText()));
        return this;
    }

    public DiscountsPage verifyPageSourceData(TrackingPixelModel trackingPixelData) {

        log.info("Verifying image data without accessing to PageSource");
        List<String> imageSourceList = $$x("//article/img").stream()
                                                           .map(e -> e.getAttribute("src"))
                                                           .collect(Collectors.toList());
        for (String url : trackingPixelData.getTrackingPixelUrl()) {
            softAssert.assertTrue(imageSourceList.contains(url),
                                  format("Failed to find link %s", url));
            log.info(format("Page contains %s", url));
        }
        softAssert.assertAll();
        return this;
    }

    public DiscountsPage verifyDisclaimerAdded(DisclaimerModel disclaimerData) {

        assertTrue(DISCLAIMER_HEADING_TEXT.should(exist, appear)
                                          .has(text(disclaimerData.getDisclaimerHeading())), "Correct Disclaimer Heading not displayed.");
        assertTrue(DISCLAIMER_BLURB_TEXT.should(exist, appear)
                                        .has(text(disclaimerData.getDisclaimerBlurb())), "Correct Disclaimer Blurb not displayed.");
        log.info("Disclaimer section is Displayed");

        return this;
    }

    public DiscountsPage verifyDisclaimerHidden() {

        assertTrue(STATUS_MESSAGE.should(exist, appear)
                                 .has(text("updated")), "Discounts Update Status message not displayed");
        assertTrue(DISCLAIMER_SECTION_LIST.isEmpty(), "Disclaimer section not hidden");
        log.info("Disclaimer section is Hidden");

        return this;
    }

    public DiscountsPage verifyDiscountCoupon(PrintCouponImageModel imageData) {

        PRINT_COUPON_BUTTON.scrollIntoView("{behavior: 'instant', block: 'center', inline: 'center'}")
                           .should(exist, appear, enabled)
                           .click();
        assertTrue(getDriver().getCurrentUrl()
                              .contains(imageData.getPrintCouponImage()
                                                 .getMediaName()), "Coupon image not matching");
        log.info("Coupon Image displayed correctly");
        open(currentDiscountURL);

        return this;
    }

    public DiscountsPage verifyCouponCode(DiscountDataModel discountsData) {

        DISCOUNT_CODE_TEXT.should(exist, appear, enabled);
        assertTrue(DISCOUNT_CODE_TEXT.getText()
                                     .contains(discountsData.getCouponCode()), "Coupon code not matching");
        COPY_DISCOUNT_CODE_LINK.should(exist, appear, enabled)
                               .click();
        String localClipboardData = this.getCBContents();
        assertEquals(localClipboardData, discountsData.getCouponCode(), "Coupon Code copied is not matching");
        SelenideDriver selenideDriverTemplate = new SelenideDriver(driver().config());
        try {
            selenideDriverTemplate.open("https://www.google.com/search?q=" + localClipboardData);
            assertTrue(selenideDriverTemplate.getWebDriver()
                                             .getCurrentUrl()
                                             .contains(discountsData.getCouponCode()), "Pasted Coupon code is not matching");
            log.info("Copy Coupon code works correctly");
        } finally {
            selenideDriverTemplate.close();
        }

        return this;
    }

    //TODO : not to use UIUtilsJavascript, use Selenide.executeJavaScript()
    public String getCBContents() {
        executeJavaScript("async function getCBContents() { try { window.cb = await navigator.clipboard.readText(); console.log(\"Pasted content: \", window.cb); } catch (err) { console.error(\"Failed to read clipboard contents: \", err); window.cb = \"Error : \" + err; } } getCBContents();");
        Object content = executeJavaScript("return window.cb;");
        return Objects.isNull(content) ? "null" : content.toString();
    }

    public DiscountsPage verifyRedeemOnline(RedeemOnlineModel redeemOnlineData) {

        assertTrue(REDEEM_ONLINE_LINK.getText()
                                     .contains(redeemOnlineData.getRedeemOnlineLabelText()), "Correct Redeem Online label not displayed");
        REDEEM_ONLINE_LINK.should(exist, appear, enabled)
                          .pressEnter();
        assertTrue(getDriver().getCurrentUrl()
                              .contains(redeemOnlineData.getRedeemOnlineURL()), "Correct Redeem Online URL not displayed");
        open(currentDiscountURL);
        log.info("Redeem Online Label and URL displayed correctly");

        return this;
    }

    public MerchantResultPage verifyIsDiscountExpired() {
        assertTrue(STATUS_MESSAGE.should(exist, appear)
                                 .has(text("updated")), "Discounts Update Status message not displayed");
        assertTrue(EXPIRE_MESSAGE.should(exist, appear)
                                 .has(text("This discount has expired or is no longer available.")), "Failed to find expire message");
        MERCHANT_LINK.should(exist, appear, enabled)
                     .click();

        return new MerchantResultPage();
    }

    public EditDiscountPage clickEditTab() {

        EDIT_TAB.should(exist, appear, enabled)
                .click();
        log.info("EDIT link clicked");
        return new EditDiscountPage();
    }

}
