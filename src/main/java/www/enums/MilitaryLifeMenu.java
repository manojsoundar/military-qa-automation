package www.enums;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

@Getter
@AllArgsConstructor
public enum MilitaryLifeMenu implements IEnumNavigationMenu {

    MILITARY_LIFE_HOME("Military Life Home", $("#header .menu--main ul>li>a[href='/military-life']")),
    JOIN_THE_MILITARY("Join The Military", $("#header .menu--main ul>li>a[href='/join-armed-forces']")),
    PCS_RELOCATION("PCS& Relocation", $("#header .menu--main ul>li>a[href='/pcs']")),
    OFF_DUTY("Off Duty", $("#header .menu--main ul>li>a[href='/off-duty']")),
    FITNESS("Fitness", $("#header .menu--main ul>li>a[href='/military-fitness']")),
    BASE_GUIDES("Base Guides", $("#header .menu--main ul>li>a[href='/base-guide']")),
    MONEY("Money", $("#header .menu--main ul>li>a[href='/money']")),
    HOME_OWNERSHIP("Home Ownership", $("#header .menu--main ul>li>a[href='/money/home-ownership']")),
    SPECIAL_OPERATIONS("Special Operations", $("#header .menu--main ul>li>a[href='/special-operations']")),
    EQUIPMENT_GUIDE("Equipment Guide", $("#header .menu--main ul>li>a[href='/equipment']")),
    DEPLOYMENT("Deployment", $("#header .menu--main ul>li>a[href='/deployment']")),
    MILITARY_TRIVIA_GAME("Military Trivia game", $("#header .menu--main ul>li>a[href='/trivia']"));

    private final String subMenuName;
    private final SelenideElement subMenuLink;

    public List<?> getList() {
        return Arrays.asList(values());
    }

}
