package drupal.enums.content;

import com.codeborne.selenide.SelenideElement;
import drupal.enums.IEnumItemMenuNavigation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
@AllArgsConstructor
public enum ContentMenu implements IEnumItemMenuNavigation {

    CONTENT("Add content", $("a.toolbar-icon-system-admin-content")),
    ADD_CONTENT("Add content", $("a.toolbar-icon-admin-toolbar-tools-extra-linksnode-add")),
    COMPONENTS("Components", $("a.toolbar-icon-mil-base-eck-entity-type-list")),
    FILES("Files", $("a.toolbar-icon-admin-toolbar-tools-extra-linksview-files")),
    MEDIA("Media", $("a.toolbar-icon-admin-toolbar-tools-extra-linksmedia-page")),
    NEWSCRED("NewsCred", $("a.toolbar-icon-mil-newscred-newscred")),
    BRIGHTCOVE_VIDEOS("Brightcove Videos", $("a.toolbar-icon-entity-brightcove-video-collection")),
    BRIGHTCOVE_PLAYLISTS("Brightcove Playlists", $("a.toolbar-icon-entity-brightcove-playlist-collection")),
    AP_DASHBOARD("AP Dashboard", $("a.toolbar-icon-ap-newsroom-clone-admin"));

    private final String mainMenuName;
    private final SelenideElement mainMenuLink;

    @Override
    public SelenideElement getSelenideElement() {
        return mainMenuLink;
    }
}
