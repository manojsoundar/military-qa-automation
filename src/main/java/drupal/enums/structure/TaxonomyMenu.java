package drupal.enums.structure;

import com.codeborne.selenide.SelenideElement;
import drupal.enums.IEnumItemMenuNavigation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
@AllArgsConstructor
public enum TaxonomyMenu implements IEnumItemMenuNavigation {

    ALL_TYPES("All types", $("a.toolbar-icon-admin-toolbar-tools-extra-linkstaxonomy-vocabulary-collection")),
    BASE_GUIDES("Base Guides", $("a.toolbar-icon-admin-toolbar-tools-extra-linksentity-taxonomy-vocabulary-overview-form-base-guides")),
    BASE_SERVICES_CATEGORIES("Base services category", $("a.toolbar-icon-admin-toolbar-tools-extra-linksentity-taxonomy-vocabulary-overview-form-base-services-category")),
    BLOGS("Blogs", $("a.toolbar-icon-admin-toolbar-tools-extra-linksentity-taxonomy-vocabulary-overview-form-blogs")),
    BRIGHTCOVE_VIDEO_TAGS("Brightcove video tags", $("a.toolbar-icon-admin-toolbar-tools-extra-linksentity-taxonomy-vocabulary-overview-form-brightcove-video-tags")),
    CATEGORIES("Categories", $("a.toolbar-icon-admin-toolbar-tools-extra-linksentity-taxonomy-vocabulary-overview-form-categories")),
    CONTENT_RELEASE("Content release", $("a.toolbar-icon-admin-toolbar-tools-extra-linksentity-taxonomy-vocabulary-overview-form-content-release")),
    KEYWORDS("Keywords", $("a.toolbar-icon-admin-toolbar-tools-extra-linksentity-taxonomy-vocabulary-overview-form-keywords")),
    LOCATION("Location", $("a.toolbar-icon-admin-toolbar-tools-extra-linksentity-taxonomy-vocabulary-overview-form-location")),
    NEWSLETTERS("Newsletters", $("a.toolbar-icon-admin-toolbar-tools-extra-linksentity-taxonomy-vocabulary-overview-form-newsletters")),
    SERVICES("Services", $("a.toolbar-icon-admin-toolbar-tools-extra-linksentity-taxonomy-vocabulary-overview-form-services"));

    private final String subMenuLayer1Name;
    private final SelenideElement subMenuLayer1Link;

    @Override
    public SelenideElement getSelenideElement() {
        return subMenuLayer1Link;
    }
}
