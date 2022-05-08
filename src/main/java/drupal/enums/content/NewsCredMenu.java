package drupal.enums.content;

import com.codeborne.selenide.SelenideElement;
import drupal.enums.IEnumItemMenuNavigation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
@AllArgsConstructor
public enum NewsCredMenu implements IEnumItemMenuNavigation {

    NEWS_CRED_ADMIN("NewsCred Admin", $("a.toolbar-icon-mil-newscred-newscred-admin"));

    private final String subMenuLayer1Name;
    private final SelenideElement subMenuLayer1Link;

    @Override
    public SelenideElement getSelenideElement() {
        return subMenuLayer1Link;
    }
}
