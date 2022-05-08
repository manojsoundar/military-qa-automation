package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.ContentMenu;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.discount.AvailableToOptionsModel;
import drupal.ui.pages.add_content.discount.CategoriesModel;
import drupal.ui.pages.add_content.discount.CreateDiscountPage;
import drupal.ui.pages.add_content.discount.DiscountDataModel;
import drupal.ui.pages.add_content.discount.DiscountsPage;
import drupal.ui.pages.add_content.discount.MilitaryIDModel;
import drupal.ui.pages.add_content.discount.PrintCouponImageModel;
import drupal.ui.pages.add_content.discount.RedeemOnlineModel;
import drupal.ui.pages.add_content.discount.RedemptionTypeModel;
import drupal.ui.pages.add_content.merchant.MerchantCategoriesModel;
import drupal.ui.pages.add_content.merchant.MerchantImageModel;
import drupal.ui.pages.add_content.merchant.MerchantPage;
import drupal.ui.pages.add_content.merchant.MerchantTitleModel;
import drupal.ui.pages.add_content.merchant.MerchantWebsiteUrlModel;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static drupal.enums.content.Category.MILITARY_TRAVEL_DISCOUNTS;
import static drupal.models.UserModel.loadUserModel;

public class TC249723_Create_Discount_Custom_Disclaimer_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateDiscountCustomDisclaimer() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "addDiscount")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249723")})
    @TestCaseId("249723")
    public void createDiscountCustomDisclaimer(List<ISectionDataModel> discountsData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Creating Discount before creating Merchant");
        MerchantPage merchantPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.MERCHANT), MerchantPage.class);
        merchantPage.addMerchant(List.of(new MerchantTitleModel(), new MerchantWebsiteUrlModel(), new MerchantImageModel(), new MerchantCategoriesModel(List.of(MILITARY_TRAVEL_DISCOUNTS))));

        logStep("Hover the Content Tab");
        logStep("Hover over Add content");
        logStep("Click on Discounts");
        CreateDiscountPage createDiscountPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.DISCOUNT), CreateDiscountPage.class);

        logStep("Enter test name in the 'Discount Title' field - use the following standard: Test Discount Disclaimer QA YYYYMMDD");
        logStep("Select Merchant");

        logStep("Enter 'Required' in the MILITARY ID field");
        logStep("Add different categories in the 'Categories' field - --Hot Deals --Amusement Parks --Orlando");
        logStep("Select 'Online' in the Redemption Type field");
        logStep("Select 'Check all options under the Available field -Active Duty, Retiree, Veteran, Reserve/National Guard, Dependent, Military.com Exclusive ");
        logStep("Type test content into the Blurb field");
        logStep("Type test content into the Body field");
        logStep("Type test url in Redeem Online field - https://www.apple.com");
        logStep("Type test content in the Redeem online label field - REDEEM Today");
        logStep("In the Print Coupon Image field, click the Attach an Image radio button. Select the Existing Image link");
        logStep("Type Coupon in the Media Name field and press Search. Select a coupon and click Select Image");
        logStep("Click Save");
        DiscountsPage discountsPage = createDiscountPage.fillIn(discountsData)
                                                        .clickSaveButton();

        logStep("Validation : Discount is created: " + Configuration.baseUrl + "/test-discounts-qa-xxxxxxxx");
        logStep("Validation : The coupon, discount code and redeem online link should all display");
        DiscountDataModel discountDataModel = discountsData.stream()
                                                           .filter(d -> d instanceof DiscountDataModel)
                                                           .map(c -> (DiscountDataModel) c)
                                                           .findFirst()
                                                           .get();
        discountsPage.verifyCreatedDiscount(discountDataModel.getTitle());

        logStep("Click the Print Coupon radio button");
        logStep("Validation : The coupon selected above should display");
        logStep("Close the coupon page to go back to the discounts page and click Copy Discount Code");
        logStep("Open a new tab and paste the code in the tab");
        logStep("Close the tab and go back to the discounts page and click the Redeem link");
        logStep("The link from Step 17 should display");
        PrintCouponImageModel couponImageData = discountsData.stream()
                                                             .filter(d -> d instanceof PrintCouponImageModel)
                                                             .map(c -> (PrintCouponImageModel) c)
                                                             .findFirst()
                                                             .get();
        RedeemOnlineModel redeemOnlineData = discountsData.stream()
                                                          .filter(d -> d instanceof RedeemOnlineModel)
                                                          .map(c -> (RedeemOnlineModel) c)
                                                          .findFirst()
                                                          .get();
        discountsPage.verifyDiscountCoupon(couponImageData)
//TODO - work on the permission for read from clipboard
//                     .verifyCouponCode(discountsData)
                     .verifyRedeemOnline(redeemOnlineData);

    }

    @DataProvider
    public Object[][] addDiscount() {
        return new Object[][]{
                {
                        List.of(
                                new MilitaryIDModel(),
                                new CategoriesModel(),
                                new RedemptionTypeModel(),
                                new AvailableToOptionsModel(),
                                new RedeemOnlineModel(),
                                new PrintCouponImageModel(),
                                new DiscountDataModel()
                        )
                }
        };
    }

}