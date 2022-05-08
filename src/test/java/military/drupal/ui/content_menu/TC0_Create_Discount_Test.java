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
import drupal.ui.pages.add_content.discount.CreateDiscountPage;
import drupal.ui.pages.add_content.discount.DealTypeModel;
import drupal.ui.pages.add_content.discount.DiscountDataModel;
import drupal.ui.pages.add_content.discount.DiscountsPage;
import drupal.ui.pages.add_content.discount.MilitaryIDModel;
import drupal.ui.pages.add_content.discount.RedemptionTypeModel;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static drupal.models.UserModel.loadUserModel;

public class TC0_Create_Discount_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateDiscount() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(enabled = false, dataProvider = "addDiscount")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "0")})
    @TestCaseId("0")
    public void createDiscount(List<ISectionDataModel> discountsData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over Add content");
        logStep("Click on Discounts");
        CreateDiscountPage createDiscountPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.DISCOUNT), CreateDiscountPage.class);

        logStep("Fill in the Create discount page and click save");
        DiscountDataModel discountDataModel = discountsData.stream()
                                                           .filter(d -> d instanceof DiscountDataModel)
                                                           .map(c -> (DiscountDataModel) c)
                                                           .findFirst()
                                                           .get();
        DiscountsPage discountsPage = createDiscountPage.fillIn(discountsData)
                                                        .clickSaveButton();

        logStep("Validation : Discount is created: " + Configuration.baseUrl + "/discounts/####");
        discountsPage.verifyCreatedDiscount(discountDataModel.getTitle());
    }

    @DataProvider
    public Object[][] addDiscount() {
        return new Object[][]{
                {
                        List.of(
                                new MilitaryIDModel(),
                                new RedemptionTypeModel(),
                                new DealTypeModel(),
                                new DiscountDataModel()
                        )
                }
        };
    }

}
