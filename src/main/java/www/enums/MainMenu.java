package www.enums;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static www.enums.IEnumNavigationMenu.getList;

@Getter
@AllArgsConstructor
public enum MainMenu {

    BENEFITS(getList(BenefitsMenu.class), "Benefits", $("#header nav>ul.menu--main a[href='/benefits']")),
    NEWS(getList(NewsMenu.class), "News", $("#header nav>ul.menu--main a[href='/daily-news']")),
    VETERAN_JOBS(getList(VeteranJobsMenu.class), "Veteran Jobs", $("#header nav>ul.menu--main a[href='/veteran-jobs']")),
    MILITARY_LIFE(getList(MilitaryLifeMenu.class), "Military Life", $("#header nav>ul.menu--main a[href='/military-life']")),
    SPOUSE_AND_FAMILY(getList(SpouseFamilyMenu.class), "Spouse & Family", $("#header nav>ul.menu--main a[href='/spouse']")),
    VIDEOS(getList(VideosMenu.class), "Videos", $("#header nav>ul.menu--main a[href*='/video']")),
    DISCOUNTS(getList(DiscountsMenu.class), "Discounts", $("#header nav>ul.menu--main a[href='/discounts']"));

    private final List<?> subEnumValues;
    private final String mainMenuName;
    private final SelenideElement mainMenuLink;

}