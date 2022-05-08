package drupal.ui.pages.components.content_list;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Getter
@AllArgsConstructor
public class MoreLinkModel extends MasterPage implements ISectionDataModel {

    private static final SelenideElement MORE_LINK_URL = $("input#edit-field-link-0-uri");
    private static final SelenideElement MORE_LINK_LINK_TEXT = $("input#edit-field-link-0-title");

    private final String url;
    private final String linkText;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        enterMoreLinkSection();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private MoreLinkModel enterMoreLinkSection() {
        if (url != null) {
            MORE_LINK_URL.should(enabled, visible)
                         .setValue(url);
        }

        if (linkText != null) {
            MORE_LINK_LINK_TEXT.should(enabled, visible)
                               .setValue(linkText);
        }
        return this;
    }

}
