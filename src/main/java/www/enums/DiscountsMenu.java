package www.enums;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

@Getter
@AllArgsConstructor
public enum DiscountsMenu implements IEnumNavigationMenu {

    DISCOUNTS_HOME("Discounts Home", $(".menu--main ul a[href='/discounts']")),
    CATEGORIES("Categories", $(".menu--main ul a[href='/discounts/category")),
    MERCHANTS("Merchants", $(".menu--main ul a[href='/discounts/merchants']")),
    DISCOUNTS_OF_THE_MONTH("Discounts of the Month", $(".menu--main ul a[href='/discounts/hot-deals")),
    TRAVEL("Travel", $(".menu--main ul a[href='/discounts/travel")),
    APPAREL_AND_ACCESSORIES("Apparel and Accessories", $(".menu--main ul a[href='/discounts/apparel-and-accessories")),
    FREEBIES("Freebies", $(".menu--main ul a[href='/discounts/freebies']")),
    LODGING("Lodging", $(".menu--main ul a[href='/discounts/lodging']")),
    SKI_RESORTS("Help and Feedback", $(".menu--main ul a[href='/discounts/ski-resorts']"));

    private final String subMenuName;
    private final SelenideElement subMenuLink;

    public List<?> getList() {
        return Arrays.asList(values());
    }

}
