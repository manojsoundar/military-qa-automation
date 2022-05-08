package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.attribute.Attributes;
import com.epam.reportportal.annotations.attribute.MultiValueAttribute;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.ContentMenu;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.merchant.MerchantCategoriesModel;
import drupal.ui.pages.add_content.merchant.MerchantDataModel;
import drupal.ui.pages.add_content.merchant.MerchantImageModel;
import drupal.ui.pages.add_content.merchant.MerchantPage;
import drupal.ui.pages.add_content.merchant.MerchantSpecialStatusModel;
import drupal.ui.pages.add_content.merchant.MerchantTitleModel;
import drupal.ui.pages.add_content.merchant.MerchantTrackingPixelModel;
import drupal.ui.pages.add_content.merchant.MerchantWebsiteUrlModel;
import lombok.extern.log4j.Log4j2;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import www.ui.pages.discounts.DiscountsHomePage;

import java.util.List;

import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.models.UserModel.loadUserModel;

@Log4j2
public class Create_Merchant_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateMerchant() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "merchantData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(multiValueAttributes = {@MultiValueAttribute(key = "TestCaseIds", values = {"249479", "249485", "249486"})})
    public void createMerchant(String testCaseId, List<ISectionDataModel> merchantData) {

        logStep("Running TestCase Id: " + testCaseId);

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over Add content");
        logStep("Select 'MERCHANT'");
        MerchantPage merchantPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.MERCHANT), MerchantPage.class);
        MerchantTitleModel merchantTitleModel = merchantData.stream()
                                                            .filter(d -> d instanceof MerchantTitleModel)
                                                            .map(c -> (MerchantTitleModel) c)
                                                            .findFirst()
                                                            .get();
        logStep("Enter test name in the 'Merchant Title' field");
        logStep("Test Name: " + merchantTitleModel.getTitle());
        logStep("Enter Merchant's URL in the 'URL' field -> Merchant URL Example - https://www.applebees.com");
        MerchantWebsiteUrlModel merchantWebsiteUrlModel = merchantData.stream()
                                                                      .filter(d -> d instanceof MerchantWebsiteUrlModel)
                                                                      .map(c -> (MerchantWebsiteUrlModel) c)
                                                                      .findFirst()
                                                                      .get();
        logStep("Enter the text associated with the link in the 'Link Text' field -> Link Text Example - " + merchantWebsiteUrlModel.getLinkText());
        MerchantSpecialStatusModel merchantSpecialStatusModel = merchantData.stream()
                                                                            .filter(d -> d instanceof MerchantSpecialStatusModel)
                                                                            .map(c -> (MerchantSpecialStatusModel) c)
                                                                            .findFirst()
                                                                            .get();

        logStep("Select" + merchantSpecialStatusModel.getSpecialStatus() + " in the Special Status field");
        logStep("Select Attach an image in the Related Field");
        logStep("Select the 'Existing Image' tab");
        logStep("Select the Applebee's image -> This is the only image that will display in the image search results");
        logStep("Type Applebee's in the Media Name field, then click on Search -> Image results will display below the search fields");
        logStep("Add different categories in the 'Categories' field -> Image results will display below the search fields");
        logStep("Type and select the following categories: --Military Travel Discounts --Dining Discounts --Washington DC");
        logStep("Click save");
        logStep("Validate: Drupal Document link is created: " + Configuration.baseUrl + "/discounts/" + merchantTitleModel.getTitle()
                                                                                                                          .replace(" ", "-"));
        logStep("Validate: Go to the Merchant Page and confirm the " + merchantSpecialStatusModel.getSpecialStatus() + " icon is displaying");
        DiscountsHomePage discountsHomePage = merchantPage.addMerchant(merchantData)
                                                          .verifyDiscountsHomePageTitle(merchantTitleModel.getTitle())
                                                          .verifySpecialStatusField(merchantSpecialStatusModel.getSpecialStatus());
        logStep("Edit functionality");
        String updateTitle = "Edited Test Merchant QA " + timeStampFormat(PATTERN);
        merchantTitleModel.setTitle(updateTitle);
        discountsHomePage.clickOnEditTab()
                         .editMerchantPage(List.of(merchantTitleModel))
                         .verifyUpdatedStatusConfirmation(updateTitle);
    }

    @DataProvider
    public Object[][] merchantData() {
        return new Object[][]{
                {
                        "TC_249479",
                        List.of(
                                new MerchantTitleModel(),
                                new MerchantWebsiteUrlModel(),
                                new MerchantDataModel(),
                                new MerchantSpecialStatusModel(),
                                new MerchantImageModel(),
                                new MerchantCategoriesModel(),
                                new MerchantTrackingPixelModel()
                        )
                },
                {
                        "TC_249485",
                        List.of(
                                new MerchantTitleModel(),
                                new MerchantWebsiteUrlModel(),
                                new MerchantDataModel(),
                                new MerchantSpecialStatusModel(MerchantSpecialStatusModel.SpecialStatus.US_MILITARY_VETERAN_OWNED_BUSINESS),
                                new MerchantImageModel(),
                                new MerchantCategoriesModel(),
                                new MerchantTrackingPixelModel()
                        )
                },
                {
                        "TC_249486",
                        List.of(
                                new MerchantTitleModel(),
                                new MerchantWebsiteUrlModel(),
                                new MerchantDataModel(),
                                new MerchantSpecialStatusModel(MerchantSpecialStatusModel.SpecialStatus.MILITARY_SPOUSE_OWNED_BUSINESS),
                                new MerchantImageModel(),
                                new MerchantCategoriesModel(),
                                new MerchantTrackingPixelModel()
                        )
                }
        };
    }

}

