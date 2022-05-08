package drupal.enums.content;

import com.codeborne.selenide.SelenideElement;
import drupal.enums.IEnumItemMenuNavigation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
@AllArgsConstructor
public enum AddMediaMenu implements IEnumItemMenuNavigation {

    AUDIO("Audio", $("a.toolbar-icon-admin-toolbar-tools-extra-linksmedia-add-audio")),
    BRIGHTCOVE_VIDEO("Brightcove video", $("a.toolbar-icon-admin-toolbar-tools-extra-linksmedia-add-brightcove-video")),
    DOCUMENT("Document", $("a.toolbar-icon-admin-toolbar-tools-extra-linksmedia-add-document")),
    FACEBOOK("Facebook", $("a.toolbar-icon-admin-toolbar-tools-extra-linksmedia-add-facebook")),
    IMAGE("Image", $("a.toolbar-icon-admin-toolbar-tools-extra-linksmedia-add-image")),
    INSTAGRAM("Instagram", $("a.toolbar-icon-admin-toolbar-tools-extra-linksmedia-add-instagram")),
    PINTEREST("Pinterest", $("a.toolbar-icon-admin-toolbar-tools-extra-linksmedia-add-pinterest")),
    TWITTER("Twitter", $("a.toolbar-icon-admin-toolbar-tools-extra-linksmedia-add-twitter")),
    YOUTUBE_VIDEO("YouTube video", $("toolbar-icon-admin-toolbar-tools-extra-linksmedia-add-youtube"));

    private final String subMenuLayer2Name;
    private final SelenideElement subMenuLayer2Link;

    @Override
    public SelenideElement getSelenideElement() {
        return subMenuLayer2Link;
    }
}