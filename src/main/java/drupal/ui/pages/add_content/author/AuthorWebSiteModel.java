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
import static drupal.ui.pages.add_content.author.CreateAuthorPage.WEB_SITE_LINK_TEXT;
import static drupal.ui.pages.add_content.author.CreateAuthorPage.WEB_SITE_URL;

@Getter
@AllArgsConstructor
public class AuthorWebSiteModel extends AdministrationToolbar implements ISectionDataModel, AttachAnImage {

    String url;
    String linkText;

    public AuthorWebSiteModel() {

        url = null;
        linkText = null;

    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeUrl(expectedClass);
        typeLinkText(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeUrl(Class<P> expectedClass) {
        if (url != null) {
            WEB_SITE_URL.should(appear, visible, enabled)
                        .setValue(url);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeLinkText(Class<P> expectedClass) {
        if (linkText != null) {
            WEB_SITE_LINK_TEXT.should(appear, visible, enabled)
                              .setValue(linkText);
        }
        return returnInstanceOf(expectedClass);
    }

}
