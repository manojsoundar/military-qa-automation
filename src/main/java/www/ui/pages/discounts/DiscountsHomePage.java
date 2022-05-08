package www.ui.pages.discounts;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.pages.add_content.merchant.EditMerchantPage;
import drupal.ui.pages.add_content.merchant.MerchantSpecialStatusModel;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Log4j2
public class DiscountsHomePage extends GlobalHeaderComponent {

    private static final String URL_REGEX = "/discounts";
    private static final SelenideElement DISCOUNTS_HOME_PAGE_TITLE = $("div.block--page--title span").as("Page title");
    private static final SelenideElement SPECIAL_STATUS_FIELD_WEB_ELEMENT = $(".field--special-status.field--label-hidden").as("Special status web element");
    private static final SelenideElement EDIT_TAB = $("ul.menu.menu--tabs a[href$='/edit']").as("Edit tab");
    private static final SelenideElement UPDATED_STATUS_MESSAGE_WEB_ELEMENT = $("div.messages.messages--status a").as("Status Message");

    public DiscountsHomePage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Discounts Home page isn't loaded.");
        log.info("Discounts Home Page loaded properly.");
    }

    public DiscountsHomePage verifyDiscountsHomePageTitle(String title) {
        String titleAsUrl = "";
        if (title != null) {
            assertFalse(DISCOUNTS_HOME_PAGE_TITLE.getText()
                                                 .isEmpty(), "Verification value is empty");
            assertEquals(DISCOUNTS_HOME_PAGE_TITLE
                    .should(exist, appear)
                    .getText()
                    .trim(), title, "Correct page title not displayed.");
            if (title.contains(" ")) {
                titleAsUrl = title.replace(" ", "-");
            }
            assertTrue(verifyURLLoaded(URL_REGEX + "/" + titleAsUrl.toLowerCase()), "Discounts page Url isn't as expected.");
        }

        return this;
    }

    public DiscountsHomePage verifySpecialStatusField(MerchantSpecialStatusModel.SpecialStatus specialStatusType) {
        if (specialStatusType != null) {
            assertEquals(SPECIAL_STATUS_FIELD_WEB_ELEMENT.should(exist, appear)
                                                         .getText(), specialStatusType.getSpecialStatusTypeItem());
        }
        return this;
    }

    public EditMerchantPage clickOnEditTab() {
        EDIT_TAB.should(exist, appear, enabled)
                .click();
        return new EditMerchantPage();
    }

    public DiscountsHomePage verifyUpdatedStatusConfirmation(String updatedTitle) {
        assertTrue(UPDATED_STATUS_MESSAGE_WEB_ELEMENT.should(appear, visible)
                                                     .has(text(updatedTitle)), "Merchant title has been updated");
        return this;
    }

}
