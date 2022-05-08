package drupal.ui.pages.add_content.flexible_content_space;

import drupal.enums.content.FlexibleContentSpacePosition;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static drupal.enums.content.FlexibleContentSpaceType.ADD_FACEBOOK_FEED_EMBED;
import static java.lang.String.format;

@Getter
@AllArgsConstructor
public class FlexibleContentSpaceFacebookFeedEmbedModel extends MasterPage implements ISectionDataModel, IFlexibleContentSpace {
    private static final String FLEXIBLE_CONTENT_SPACE_FACEBOOK_FEED_PROFILE_URL_INPUT = "input.form-text[id*=-%s-%s-subform-field-facebook-profile-url]";
    private static final String FLEXIBLE_CONTENT_SPACE_FACEBOOK_FEED_PROFILE_DISPLAY_TITLE_INPUT = "input[id*=-%s-%s-subform-field-profile-display-title]";

    private final FlexibleContentSpacePosition position;
    private int index;
    private final String addFacebookUrl;
    private final String addFacebookDisplayTitle;

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        index = selectFlexibleContentSpace(ADD_FACEBOOK_FEED_EMBED, position) - 1;
        enterAddFacebookProfileUrl().enterAddFacebookDisplayInput();
        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();
        clickEditFlexibleContentSpace(position, index);
        enterAddFacebookProfileUrl().enterAddFacebookDisplayInput();
        return returnInstanceOf(expectedClass);
    }

    private FlexibleContentSpaceFacebookFeedEmbedModel enterAddFacebookProfileUrl() {
        if (addFacebookUrl != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_FACEBOOK_FEED_PROFILE_URL_INPUT, position.getFlexibleContentPosition(), index)).should(exist, appear, enabled)
                                                                                                                           .setValue(addFacebookUrl);
        }
        return this;
    }

    private FlexibleContentSpaceFacebookFeedEmbedModel enterAddFacebookDisplayInput() {
        if (addFacebookDisplayTitle != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_FACEBOOK_FEED_PROFILE_DISPLAY_TITLE_INPUT, position.getFlexibleContentPosition(), index)).should(exist, appear, enabled)
                                                                                                                                     .setValue(addFacebookDisplayTitle);
        }
        return this;
    }
}
