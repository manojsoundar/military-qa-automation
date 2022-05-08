package drupal.enums.configuration;

import com.codeborne.selenide.SelenideElement;
import drupal.enums.IEnumItemMenuNavigation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
@AllArgsConstructor
public enum ConfigurationMenu implements IEnumItemMenuNavigation {

    MEDIA("Media", $("a.toolbar-icon-system-admin-config-media"));

    private final String mainMenuName;
    private final SelenideElement mainMenuLink;

    @Override
    public SelenideElement getSelenideElement() {
        return mainMenuLink;
    }
}
