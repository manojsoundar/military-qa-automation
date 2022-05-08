package drupal.enums.structure;

import com.codeborne.selenide.SelenideElement;
import drupal.enums.IEnumItemMenuNavigation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
@AllArgsConstructor
public enum BaseGuideMenu implements IEnumItemMenuNavigation {

    ADD_LINK("Add link", $("a.toolbar-icon-admin-toolbar-tools-extra-linksentity-menu-add-link-form-base-guide")),
    DELETE("Delete", $("a.toolbar-icon-admin-toolbar-tools-extra-linksentity-menu-delete-form-base-guide"));

    private final String subMenuLayer2Name;
    private final SelenideElement subMenuLayer2Link;

    @Override
    public SelenideElement getSelenideElement() {
        return subMenuLayer2Link;
    }
}