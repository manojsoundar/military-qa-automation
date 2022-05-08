package www.enums;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

@Getter
@AllArgsConstructor
public enum NewsMenu implements IEnumNavigationMenu {

    NEWS_HOME("News Home", $(".menu--main ul a[href='/daily-news']")),
    CORONAVIRUS_RESPONSE("Coronavirus Response", $(".menu--main ul a[href='/us-military-coronavirus-response']")),
    OPINION("Opinion", $(".menu--main ul a[href='/daily-news/opinion']")),
    LEFT_OF_BOOM_PODCAST("Left of Boom Podcast", $(".menu--main ul a[href='/podcasts/left-of-boom']")),
    ARMY("Army", $(".menu--main ul a[href='/army']")),
    MARINE_CORPS("Marine Corps", $(".menu--main ul a[href='/marine-corps']")),
    NAVY("Navy", $(".menu--main ul a[href='/navy']")),
    AIR_FORCE("Air Force", $(".menu--main ul a[href='/air-force']")),
    SPACE_FORCE("Space Force", $(".menu--main ul a[href='/space-force']")),
    COAST_GUARD("Coast Guard", $(".menu--main ul a[href='/coast-guard']"));

    private final String subMenuName;
    private final SelenideElement subMenuLink;

    public List<?> getList() {
        return Arrays.asList(values());
    }

}
