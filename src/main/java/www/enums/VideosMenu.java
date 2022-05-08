package www.enums;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

@Getter
@AllArgsConstructor
public enum VideosMenu implements IEnumNavigationMenu {

    VIDEOS_HOME("Video Home", $(".menu--main ul a[href$='/video']")),
    CATEGORIES("Categories", $(".menu--main ul a[href$='/video/category']")),
    SHOCK_AND_AWAY("Shock & Awe", $(".menu--main ul a[href$='/video/shock-and-awe']")),
    MILITARY_DOT_COM_ORIGINALS("Military.com Originals", $(".menu--main ul a[href$='/video/militarydotcom']")),
    EDITORS_PICKS("Editor's Picks", $(".menu--main ul a[href$='/video/editors-picks']")),
    MOST_POPULAR("Most Popular", $(".menu--main ul a[href$='/video/most-popular']")),
    FLIR("FLIR", $(".menu--main ul a[href$='/video/shock-and-awe/flir")),
    GUNS_AND_WEAPONS("Guns & Weapons", $(".menu--main ul a[href$='/shock-and-awe/guns-weapons']")),
    SNIPERS("Snipers", $(".menu--main ul a[href$='/video/shock-and-awe/sniper-kills']")),
    SPECIAL_OPERATIONS("Special Operations", $(".menu--main ul a[href$='/video/shock-and-awe/special-operations']")),
    EQUIPMENT("Equipment", $(".menu--main ul a[href$='/video/shock-and-awe/equipment']"));

    private final String subMenuName;
    private final SelenideElement subMenuLink;

    public List<?> getList() {
        return Arrays.asList(values());
    }

}
