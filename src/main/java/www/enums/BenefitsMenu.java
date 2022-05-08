package www.enums;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

@Getter
@AllArgsConstructor
public enum BenefitsMenu implements IEnumNavigationMenu {

    BENEFITS_HOME("Benefits Home", $(".menu--main ul>li>a[href='/benefits']")),
    DIRECTORY("Benefits Directory", $(".menu--main ul>li>a[href='/benefits/directory']")),
    MILITARY_PAY("Military Pay", $(".menu--main ul>li>a[href='/benefits/military-pay']")),
    GI_BILL("GI Bill", $(".menu--main ul>li>a[href='/education/gi-bill']")),
    EDUCATION("Education", $(".menu--main ul>li>a[href='/education']")),
    VETERAN("Veteran Benefits", $(".menu--main ul>li>a[href='/benefits/veteran-benefits']")),
    VA_E_BENEFITS("VA eBenefits", $(".menu--main ul>li>a[href*='the-ebenefits-program.html']")),
    VETERAN_HEALTHCARE("Veteran Health Care", $(".menu--main ul>li>a[href='/benefits/veterans-health-care']")),
    TRICARE("Tricare", $(".menu--main ul>li>a[href='/benefits/tricare']")),
    SCHOLARSHIPS_FOR_VETS("Scholarships for Vets", $(".menu--main ul>li>a[href*='/scholarships-for-veterans']")),
    VA_LOANS("VA Loans", $(".menu--main ul>li>a[href='/money/retirement']")),
    INSURANCE("Insurance", $(".menu--main ul>li>a[href='/money/insurance']")),
    RETIREMENT("Retirement", $(".menu--main ul>li>a[href='/money/retirement']"));

    private final String subMenuName;
    private final SelenideElement subMenuLink;

    public List<?> getList() {
        return Arrays.asList(values());
    }

}
