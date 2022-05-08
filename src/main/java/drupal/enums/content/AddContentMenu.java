package drupal.enums.content;

import com.codeborne.selenide.SelenideElement;
import drupal.enums.IEnumItemMenuNavigation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
@AllArgsConstructor
public enum AddContentMenu implements IEnumItemMenuNavigation {

    ARTICLE("Article", $("a.toolbar-icon-admin-toolbar-tools-extra-linksnode-add-article")),
    AUTHOR("Author", $("a.toolbar-icon-admin-toolbar-tools-extra-linksnode-add-author")),
    BASE_INSTALLATION("Base Installation", $("a.toolbar-icon-admin-toolbar-tools-extra-linksnode-add-base-installation")),
    CONTACT("Contact", $("a.toolbar-icon-admin-toolbar-tools-extra-linksnode-add-contact")),
    DISCOUNT("Discount", $("a.toolbar-icon-admin-toolbar-tools-extra-linksnode-add-discount")),
    EMPLOYER("Employer", $("a.toolbar-icon-admin-toolbar-tools-extra-linksnode-add-employer")),
    EQUIPMENT("Equipment", $("a.toolbar-icon-admin-toolbar-tools-extra-linksnode-add-equipment")),
    EVENT("Event", $("a.toolbar-icon-admin-toolbar-tools-extra-linksnode-add-event")),
    LANDING_PAGE("Landing page", $("a.toolbar-icon-admin-toolbar-tools-extra-linksnode-add-landing-page")),
    MERCHANT("Merchant", $("a.toolbar-icon-admin-toolbar-tools-extra-linksnode-add-merchant")),
    NEWSLETTER_ARTICLE("Newsletter article", $("a.toolbar-icon-admin-toolbar-tools-extra-linksnode-add-newsletter-article")),
    NEWSLETTER_ISSUE("Newsletter issue", $("a.toolbar-icon-admin-toolbar-tools-extra-linksnode-add-newsletter-issue")),
    REGISTRATION_GATE("Registration gate", $("a.toolbar-icon-admin-toolbar-tools-extra-linksnode-add-registration-gate")),
    WEBINAR("Webinar", $("a.toolbar-icon-admin-toolbar-tools-extra-linksnode-add-webinar"));

    private final String subMenuLayer1Name;
    private final SelenideElement subMenuLayer1Link;

    @Override
    public SelenideElement getSelenideElement() {
        return subMenuLayer1Link;
    }
}
