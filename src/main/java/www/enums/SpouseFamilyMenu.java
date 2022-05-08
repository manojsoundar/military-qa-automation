package www.enums;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

@AllArgsConstructor
@Getter
public enum SpouseFamilyMenu implements IEnumNavigationMenu {

    MILITARY_SPOUSE_JOBS("Military Spouse Jobs", $(".menu--main ul a[href='/spouse/career-advancement']").as("Military Spouse Jobs")),
    MILITARY_FAMILY_LIFE("Military & Family Life", $(".menu--main ul a[href='/spouse/military-life']").as("Military & Family Life")),
    RELATIONSHIPS("Relationships", $(".menu--main ul a[href='/spouse/relationships']").as("Relationships")),
    SPOUSE_FAMILY_BENEFITS("Spouse & Family Benefits", $(".menu--main ul a[href='/spouse/military-benefits']").as("Spouse & Family Benefits")),
    MILITARY_DEPLOYMENT("Military Deployment", $(".menu--main ul a[href='/spouse/military-deployment']").as("Military Deployment")),
    MILITARY_MOVES("Military Moves", $(".menu--main ul a[href='/spouse/military-relocation']").as("Military Moves"));

    private final String subMenuName;
    private final SelenideElement subMenuLink;

    public List<?> getList() {
        return Arrays.asList(values());
    }

}
