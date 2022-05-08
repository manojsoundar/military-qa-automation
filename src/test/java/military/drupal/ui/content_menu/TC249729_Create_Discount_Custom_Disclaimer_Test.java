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
import drupal.ui.pages.add_content.discount.DealTypeModel;
import drupal.ui.pages.add_content.discount.DisclaimerModel;
import drupal.ui.pages.add_content.discount.DiscountDataModel;
import drupal.ui.pages.add_content.discount.DiscountsPage;
import drupal.ui.pages.add_content.discount.MilitaryIDModel;
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

public class TC249729_Create_Discount_Custom_Disclaimer_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateDiscountCustomDisclaimer() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "addDiscount")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249729")})
    @TestCaseId("249729")
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
        logStep("Select 'Shopping Tip' in the Deal Type Field");
        logStep("Select 'Check all options under the Available field -Active Duty, Retiree, Veteran, Reserve/National Guard, Dependent, Military.com Exclusive ");
        logStep("Type test content into the Blurb field");
        logStep("Type test content into the Body field");
        logStep("Type test url in Redeem Online field - https://www.apple.com");
        logStep("Type test content in the Redeem online label field - REDEEM Today");
        logStep("Click Customize Disclaimer and add test content in the Disclaimer Heading and Disclaimer Blurb fields");
        logStep("Click Save");
        DiscountsPage discountsPage = createDiscountPage.fillIn(discountsData)
                                                        .clickSaveButton();
        DiscountDataModel discountDataModel = discountsData.stream()
                                                           .filter(d -> d instanceof DiscountDataModel)
                                                           .map(c -> (DiscountDataModel) c)
                                                           .findFirst()
                                                           .get();
        DisclaimerModel disclaimerData = discountsData.stream()
                                                      .filter(d -> d instanceof DisclaimerModel)
                                                      .map(c -> (DisclaimerModel) c)
                                                      .findFirst()
                                                      .get();

        logStep("Validation : Discount is created: " + Configuration.baseUrl + "/test-discounts-qa-xxxxxxxx");
        discountsPage.verifyCreatedDiscount(discountDataModel.getTitle())
                     .verifyDisclaimerAdded(disclaimerData);

        logStep("Click the Edit link (above the Discounts headline) and return to the Drupal Admin page");
        logStep("Click the Customize Disclaimer link and check the Hide Disclaimer box");
        logStep("Click Save");
        logStep("Go to the Test Discount page " + Configuration.baseUrl + "/discounts/test-discount-disclaimer-qa-7721");
        logStep("Confirm there is no disclaimer on the Discounts page");
        disclaimerData.setHideDisclaimer(true);
        disclaimerData.setDisclaimerHeading(null);
        disclaimerData.setDisclaimerBlurb(null);
        discountsPage.clickEditTab()
                     .fillIn(List.of(disclaimerData))
                     .clickSaveButton()
                     .verifyDisclaimerHidden();

    }

    @DataProvider
    public Object[][] addDiscount() {
        return new Object[][]{
                {
                        List.of(
                                new MilitaryIDModel(),
                                new CategoriesModel(),
                                new RedemptionTypeModel(),
                                new DealTypeModel(),
                                new AvailableToOptionsModel(),
                                new RedeemOnlineModel(),
                                new DisclaimerModel(),
                                new DiscountDataModel()
                        )
                }
        };
    }

}