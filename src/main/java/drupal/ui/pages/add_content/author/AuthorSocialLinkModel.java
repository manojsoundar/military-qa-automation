package drupal.ui.pages.add_content.author;

import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.components.AttachAnImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static drupal.ui.pages.add_content.author.CreateAuthorPage.SOCIAL_LINK_FACEBOOK;
import static drupal.ui.pages.add_content.author.CreateAuthorPage.SOCIAL_LINK_GOOGLE;
import static drupal.ui.pages.add_content.author.CreateAuthorPage.SOCIAL_LINK_TWITTER;

@Getter
@AllArgsConstructor
public class AuthorSocialLinkModel extends AdministrationToolbar implements ISectionDataModel, AttachAnImage {

    String twitterUsername;
    String facebookUrl;
    String googleUrl;

    public AuthorSocialLinkModel() {

        twitterUsername = null;
        facebookUrl = null;
        googleUrl = null;

    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeTwitterUsername(expectedClass);
        typeFacebookUrl(expectedClass);
        typeGoogleUrl(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeTwitterUsername(Class<P> expectedClass) {
        if (twitterUsername != null) {
            SOCIAL_LINK_TWITTER.should(appear, visible, enabled)
                               .setValue(twitterUsername);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeFacebookUrl(Class<P> expectedClass) {
        if (facebookUrl != null) {
            SOCIAL_LINK_FACEBOOK.should(appear, visible, enabled)
                                .setValue(facebookUrl);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeGoogleUrl(Class<P> expectedClass) {
        if (googleUrl != null) {
            SOCIAL_LINK_GOOGLE.should(appear, visible, enabled)
                              .setValue(googleUrl);
        }
        return returnInstanceOf(expectedClass);
    }

}
