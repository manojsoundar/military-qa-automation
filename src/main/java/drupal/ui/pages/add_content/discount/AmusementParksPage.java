package drupal.ui.pages.add_content.discount;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AmusementParksPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/amusement-parks";
    private static final ElementsCollection DISCOUNT_LIST = $$(".thumbnail--large-vertical span span").as("list of discounts");

    public AmusementParksPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Discounts Page not loaded.");
        log.info("Discounts Page loaded properly.");
    }

    public AmusementParksPage verifyDiscountDoNotDisplayInAmusementParkCategoryPage(DiscountDataModel discountModel) {

        List<String> discountList = DISCOUNT_LIST.stream()
                                                 .map(SelenideElement::getText)
                                                 .collect(Collectors.toList());
        assertFalse(discountList.contains(discountModel.getTitle()),
                    "Discount Title match was able to find");
        return this;
    }

}
