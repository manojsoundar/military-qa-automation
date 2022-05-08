package drupal.enums.content;

import com.codeborne.selenide.SelenideElement;
import drupal.enums.IEnumItemMenuNavigation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
@AllArgsConstructor
public enum MediaMenu implements IEnumItemMenuNavigation {

    ADD_MEDIA("Add media", $("a.toolbar-icon-admin-toolbar-tools-extra-linksadd-media"));

    private final String subMenuLayer1Name;
    private final SelenideElement subMenuLayer1Link;

    @Override
    public SelenideElement getSelenideElement() {
        return subMenuLayer1Link;
    }
}