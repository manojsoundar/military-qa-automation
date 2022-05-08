package drupal.ui.pages.add_content.equipment;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EquipmentPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/equipment/";
    private static final SelenideElement EQUIPMENT_PAGE_H_1_TITLE = $(".block--page--title h1 span");
    private static final SelenideElement EDIT_TAB = $("ul.menu.menu--tabs a[href*='edit']");
    private static final ElementsCollection EQUIPMENT_LINK_LIST = $$(".view__content li a");

    public EquipmentPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Equipment page not loaded.");
        log.info("Equipment page loaded properly.");
    }

    public EquipmentPage verifyEquipmentPage(String title) {
        assertEquals(EQUIPMENT_PAGE_H_1_TITLE.should(exist, appear)
                                             .getText()
                                             .trim(), title,
                     "Correct page title not displayed.");
        return this;
    }

    public EditEquipmentPage clickEditTab() {

        EDIT_TAB.should(exist, appear, enabled)
                .click();
        log.info("EDIT link clicked");
        return new EditEquipmentPage();
    }

    public EquipmentPage clickOnCategoryLink(String testCategory) {
        $x(format("//a[text()= \"%s\"]", testCategory.trim())).click();
        return new EquipmentPage();
    }

    public EquipmentPage verifyEquipmentCategoryPageAndClick(String testCategory) {
        assertTrue(verifyURLLoaded(URL_REGEX + "ordnance"), "Discounts Category Page not loaded.");
        EQUIPMENT_LINK_LIST.find(text(testCategory))
                           .should(appear, exist)
                           .click();
        return new EquipmentPage();
    }

/*    public EquipmentPage verifyPixelUrlsInCreatedPageSource(TaxonomyCategoriesModel equipmentCategoryPageModel) {
        // TODO disagree to check in the page source.
        SoftAssert softAssert = SoftAssertMt.getSoftAssert();
        for (String pixelUrl : equipmentCategoryPageModel.getTrackingPixelUrl()) {
            softAssert.assertTrue(getWebDriver().getPageSource()
                                                .contains(pixelUrl), "Page Source doesn't contain Pixel url: " + pixelUrl);
        }
        softAssert.assertAll();
        return this;}
 */
}
