package drupal.enums.structure;

import com.codeborne.selenide.SelenideElement;
import drupal.enums.IEnumItemMenuNavigation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
@AllArgsConstructor
public enum StructureMenu implements IEnumItemMenuNavigation {

    ECK_ENTITY_TYPES("ECK Entity Types", $("a.toolbar-icon-eck-entity-type-admin-structure-settings")),
    MENUS("Menus", $("a.toolbar-icon-entity-menu-collection")),
    TAXONOMY("Taxonomy", $("a.toolbar-icon-entity-taxonomy-vocabulary-collection")),
    BRIGHTCOVE_VIDEO_SETTINGS("Brightcove Video settings", $("a.toolbar-icon-brightcove-video-settings")),
    BRIGHTCOVE_PLAYLIST_SETTINGS("Brightcove Playlist settings", $("a.toolbar-icon-brightcove-playlist-settings"));

    private final String mainMenuName;
    private final SelenideElement mainMenuLink;

    @Override
    public SelenideElement getSelenideElement() {
        return mainMenuLink;
    }
}
