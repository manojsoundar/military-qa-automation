package drupal.enums.structure;

import com.codeborne.selenide.SelenideElement;
import drupal.enums.IEnumItemMenuNavigation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
@AllArgsConstructor
public enum MenusMenu implements IEnumItemMenuNavigation {

    ADD_MENU("Add menu", $("a.toolbar-icon-admin-toolbar-tools-extra-linksentity-menu-add-form")),
    ALL_MENU("All menu", $("a.toolbar-icon-admin-toolbar-tools-extra-linksentity-menu-collection")),
    ABOUT_MILITARY_DOT_COM("About Military.com", $("a.toolbar-icon-admin-toolbar-tools-extra-linksentity-menu-edit-form-about-military")),
    ADMINISTRATION("Administration", $("a.toolbar-icon-admin-toolbar-tools-extra-linksentity-menu-edit-form-admin")),
    AUXILIARY_FOOTER_LINKS("Auxiliary Footer Links", $("a.toolbar-icon-admin-toolbar-tools-extra-linksentity-menu-edit-form-aux-footer-links")),
    BASE_GUIDE("Base Guide", $("a.toolbar-icon-admin-toolbar-tools-extra-linksentity-menu-edit-form-base-guide")),
    BASE_GUIDE_LIST("Base Guide List", $("toolbar-icon-admin-toolbar-tools-extra-linksentity-menu-edit-form-base-guide-list")),
    BASE_GUIDES_TAXONOMY("Base Guide Taxonomy", $("a.toolbar-icon-admin-toolbar-tools-extra-linksentity-menu-edit-form-base-guides-taxonomy")),
    DEPRECATED_FOOTER_NAVIGATION("DEPRECATED - Footer navigation", $("a.toolbar-icon-admin-toolbar-tools-extra-linksentity-menu-edit-form-footer")),
    INSURANCE_FOOTER("Insurance Footer", $("a.toolbar-icon-admin-toolbar-tools-extra-linksentity-menu-edit-form-insurance-footer")),
    MAIN_NAVIGATION("Main Navigation", $("a.toolbar-icon-admin-toolbar-tools-extra-linksentity-menu-edit-form-main")),
    MILITARY_DOT_COM_NETWORK("Military.com Network", $("a.toolbar-icon-admin-toolbar-tools-extra-linksentity-menu-edit-form-military-network"));

    private final String subMenuLayer1Name;
    private final SelenideElement subMenuLayer1Link;

    @Override
    public SelenideElement getSelenideElement() {
        return subMenuLayer1Link;
    }
}
