package drupal.ui.pages.add_content.merchant;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.add_content.discount.DiscountDataModel;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static java.time.Duration.ofSeconds;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Log4j2
public class MerchantResultPage extends AdministrationToolbar {
    private static final String URL_REGEX = "/discounts/";
    private static final ElementsCollection DISCOUNT_LIST = $$(".block--merchant--discounts .block__title").as("List of discount in merchant page");


    public MerchantResultPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Discounts Page not loaded.");
        log.info("Discounts Page loaded properly.");
    }

    public MerchantResultPage verifyDiscountDoNotDisplayInMerchantPage(DiscountDataModel discountModel) {
        DISCOUNT_LIST.first()
                     .should(appear, ofSeconds(30))
                     .should(exist, visible);
        List<String> discountList = DISCOUNT_LIST.stream()
                                                 .map(SelenideElement::getText)
                                                 .collect(Collectors.toList());
        assertFalse(discountList.contains(discountModel.getTitle()),
                "Discount Title match was able to find");
        return this;
    }
}
