package drupal.enums.content;

import com.codeborne.selenide.SelenideElement;
import drupal.enums.IEnumItemMenuNavigation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
@AllArgsConstructor
public enum ComponentsMenu implements IEnumItemMenuNavigation {

    CONTENT_LISTS("Content lists", $("a.toolbar-icon-mil-base-eck-entity-content-list-list")),
    FLEXIBLE_SPACE("Flexible space (landing page sidebars)", $("a.toolbar-icon-mil-base-eck-entity-flexible-space-list")),
    SLIDESHOWS("Slideshows", $("a.toolbar-icon-mil-base-eck-entity-slideshow-list")),
    SNIPPETS("Snippets", $("a.toolbar-icon-mil-base-eck-entity-snippet-list")),
    WIDGETS("Widgets", $("a.toolbar-icon-mil-base-eck-entity-widget-list"));

    private final String subMenuLayer1Name;
    private final SelenideElement subMenuLayer1Link;

    @Override
    public SelenideElement getSelenideElement() {
        return subMenuLayer1Link;
    }
}
