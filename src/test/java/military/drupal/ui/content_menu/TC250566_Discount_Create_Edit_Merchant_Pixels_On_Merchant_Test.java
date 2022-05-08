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
import drupal.ui.pages.add_content.discount.DiscountDataModel;
import drupal.ui.pages.add_content.discount.DiscountsPage;
import drupal.ui.pages.add_content.discount.MilitaryIDModel;
import drupal.ui.pages.add_content.discount.RedemptionTypeModel;
import drupal.ui.pages.add_content.discount.TrackingPixelModel;
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

public class TC250566_Discount_Create_Edit_Merchant_Pixels_On_Merchant_Test extends AdminUITestBase {
    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateDiscount() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "createEditDiscount")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "250566")})
    @TestCaseId("250566")
    public void discountCreateEditMerchantPixelsOnMerchant(List<ISectionDataModel> discountsData) {
        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Creating Merchant before creating Discount");
        MerchantPage merchantPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.MERCHANT), MerchantPage.class);
        merchantPage.addMerchant(List.of(new MerchantTitleModel(), new MerchantWebsiteUrlModel(), new MerchantCategoriesModel(List.of(MILITARY_TRAVEL_DISCOUNTS)), new MerchantImageModel()));

        logStep("Hover the Content Tab");
        logStep("Hover over Add content");
        logStep("Click on Discounts");
        CreateDiscountPage createDiscountPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.DISCOUNT), CreateDiscountPage.class);

        logStep("Enter test name in the 'Discount Title' field - use the following standard: Test Discount QA YYYYMMDD");
        logStep("Select Merchant");

        logStep("Enter 'Required' in the MILITARY ID field");
        logStep("Add different categories in the 'Categories' field - --Hot Deals --Amusement Parks --Orlando");
        logStep("Select 'In Store' in the Redemption Type field");
        logStep("Select 'Shopping Tip' in the Deal Type Field");
        logStep("Select 'Check all options under the Available field -Active Duty, Retiree, Veteran, Reserve/National Guard, Dependent, Military.com Exclusive ");
        logStep("Type test content into the Blurb field");
        logStep("Type test content into the Body field");
        logStep("Click Save");
        DiscountsPage discountsPage = createDiscountPage.fillIn(discountsData)
                                                        .clickSaveButton();

        logStep("Validation : Discount is created: " + Configuration.baseUrl + "/test-discounts-qa-xxxxxxxx");
        logStep("Go to the test url and right click page source - " + Configuration.baseUrl + "/discounts/test-discounts-qa-xxxxxxxx");
        logStep("Search (CTRL+F) the page source code and type pixel to get search results");
        logStep("Confirm the pixel urls are displaying in Page source");
        DiscountDataModel discountDataModel = discountsData.stream()
                                                           .filter(d -> d instanceof DiscountDataModel)
                                                           .map(c -> (DiscountDataModel) c)
                                                           .findFirst()
                                                           .get();
        TrackingPixelModel trackingPixelData = discountsData.stream()
                                                            .filter(d -> d instanceof TrackingPixelModel)
                                                            .map(c -> (TrackingPixelModel) c)
                                                            .findFirst()
                                                            .get();

        discountsPage.verifyCreatedDiscount(discountDataModel.getTitle())
                     .verifyPageSourceData(trackingPixelData);

        discountDataModel.setTitle("Edit " + discountDataModel.getTitle());
        trackingPixelData.setTrackingPixelUrl(List.of("https://www.military.com/?pixel=impression", "https://www.military.com/?pixel=click", "https://www.military2.com/"));
        discountsPage.clickEditTab()
                     .fillIn(List.of(discountDataModel, trackingPixelData))
                     .clickSaveButton()
                     .verifyEditedDiscount(discountDataModel.getTitle())
                     .verifyPageSourceData(trackingPixelData);
    }

    @DataProvider
    public Object[][] createEditDiscount() {
        return new Object[][]{
                {
                        List.of(
                                new MilitaryIDModel(),
                                new CategoriesModel(),
                                RedemptionTypeModel.addPixelRedemptionType(),
                                new DealTypeModel(),
                                new AvailableToOptionsModel(),
                                new TrackingPixelModel(),
                                new DiscountDataModel()
                        )
                }
        };
    }
}
