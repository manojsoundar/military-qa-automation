package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.ContentMenu;
import drupal.models.ContentSearchModel;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.discount.AmusementParksPage;
import drupal.ui.pages.add_content.discount.CategoriesModel;
import drupal.ui.pages.add_content.discount.CreateDiscountPage;
import drupal.ui.pages.add_content.discount.DiscountDataModel;
import drupal.ui.pages.add_content.discount.DiscountsPage;
import drupal.ui.pages.add_content.discount.EditDiscountPage;
import drupal.ui.pages.add_content.merchant.MerchantCategoriesModel;
import drupal.ui.pages.add_content.merchant.MerchantImageModel;
import drupal.ui.pages.add_content.merchant.MerchantPage;
import drupal.ui.pages.add_content.merchant.MerchantResultPage;
import drupal.ui.pages.add_content.merchant.MerchantTitleModel;
import drupal.ui.pages.add_content.merchant.MerchantWebsiteUrlModel;
import drupal.ui.pages.content.ContentPage;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static drupal.enums.content.Category.MILITARY_TRAVEL_DISCOUNTS;
import static drupal.models.UserModel.loadUserModel;

public class TC249727_Update_Discount_Expire_A_Discount_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionUpdateDiscount() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "expireDiscountData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249727")})
    @TestCaseId("249727")
    public void updateDiscount(ContentSearchModel contentSearchModel, List<ISectionDataModel> discountsData) {
        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Creating Discount before creating Merchant");
        MerchantPage merchantPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.MERCHANT), MerchantPage.class);
        merchantPage.addMerchant(List.of(new MerchantTitleModel(), new MerchantWebsiteUrlModel(), new MerchantImageModel(), new MerchantCategoriesModel(List.of(MILITARY_TRAVEL_DISCOUNTS))));

        logStep("Create Discount entitled with merchant");
        logStep("Hover the Content Tab and click on Content Tab");
        CreateDiscountPage createDiscountPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.DISCOUNT), CreateDiscountPage.class);
        ContentPage contentPage = createDiscountPage.fillIn(discountsData)
                                                    .clickSaveButton()
                                                    .navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.CONTENT), ContentPage.class);
        logStep("In the Title field, type Test Discount and click Filter");
        logStep("Find a test Discount and in the Operations section, click Edit");
        logStep("In the End Date field, type yesterday's date");
        logStep("In the Expired Message, type Test Content");
        logStep("Click Save");
        DiscountDataModel discountDataModel = discountsData.stream()
                                                           .filter(d -> d instanceof DiscountDataModel)
                                                           .map(c -> (DiscountDataModel) c)
                                                           .findFirst()
                                                           .get();
        EditDiscountPage editDiscountPage = contentPage.fillSearchCriteriaAndFilter(contentSearchModel)
                                                       .clickContentLink(DiscountsPage.class, discountDataModel.getTitle())
                                                       .clickEditTab();
        DiscountsPage discountsPage = editDiscountPage.fillIn(List.of(DiscountDataModel.getExpireDiscountData()))
                                                      .clickSaveButton();
        logStep("Go to the Discount page and confirm the expired information is showing on the page.");
        logStep("Click the Merchant link in the Breadcrumb");

        MerchantResultPage merchantResultPage = discountsPage.verifyIsDiscountExpired();
        logStep("Confirm on the Merchant page that the discount is not displaying");
        merchantResultPage.verifyDiscountDoNotDisplayInMerchantPage(discountDataModel);
        AmusementParksPage amusementParksPage = open(Configuration.baseUrl + "/discounts/amusement-parks", AmusementParksPage.class);
        logStep("Confirm the discount is not displayed on category pages");
        amusementParksPage.verifyDiscountDoNotDisplayInAmusementParkCategoryPage(discountDataModel);
    }

    @DataProvider
    public Object[][] expireDiscountData() {
        return new Object[][]{
                {
                        ContentSearchModel.expireDiscountData(),
                        List.of(
                                CategoriesModel.expireDiscountCategory(),
                                new DiscountDataModel()
                        )
                }
        };
    }

}
