package drupal.ui.pages.add_content.flexible_content_space;

import drupal.enums.content.FlexibleContentSpacePosition;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$$;
import static drupal.enums.content.FlexibleContentSpaceType.ADD_SOCIAL_LINK;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

// TODO check usage callSuper=false
@Data
@AllArgsConstructor
public class FlexibleContentSpaceSocialLinkModel extends MasterPage implements ISectionDataModel, IFlexibleContentSpace {

    private static final String FLEXIBLE_CONTENT_SPACE_SOCIAL_LINK_ICON_DROPDOWN = "select.form-select[id*=-%s-%s-subform-field-icon]";
    private static final String FLEXIBLE_CONTENT_SPACE_SOCIAL_LINK_URL_INPUT = "input[id*=-%s-%s-subform-field-link][id*=uri]";
    private static final String FLEXIBLE_CONTENT_SPACE_SOCIAL_LINK_TEXT_INPUT = "input[id*=-%s-%s-subform-field-link][id*=title]";

    private final FlexibleContentSpacePosition position;
    private int index;
    private String socialLinkIcon;
    private String socialLinkUrl;
    private String socialLinkLinkText;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        index = selectFlexibleContentSpace(ADD_SOCIAL_LINK, position) - 1;
        selectSocialLinkIcon();
        enterSocialLikLinkSectionData();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        clickEditFlexibleContentSpace(position, index);
        selectSocialLinkIcon();
        enterSocialLikLinkSectionData();
        return returnInstanceOf(expectedClass);
    }

    private FlexibleContentSpaceSocialLinkModel selectSocialLinkIcon() {
        $$(format(FLEXIBLE_CONTENT_SPACE_SOCIAL_LINK_ICON_DROPDOWN, position.name()
                                                                            .toLowerCase(), index)).last()
                                                                                                   .should(appear, ofSeconds(30))
                                                                                                   .selectOptionContainingText(socialLinkIcon);

        return this;
    }

    private FlexibleContentSpaceSocialLinkModel enterSocialLikLinkSectionData() {
        $$(format(FLEXIBLE_CONTENT_SPACE_SOCIAL_LINK_URL_INPUT, position.getFlexibleContentPosition()
                                                                        .toLowerCase(), index)).get(0)
                                                                                               .should(enabled, ofSeconds(20))
                                                                                               .setValue(socialLinkUrl);

        $$(format(FLEXIBLE_CONTENT_SPACE_SOCIAL_LINK_TEXT_INPUT, position.getFlexibleContentPosition()
                                                                         .toLowerCase(), index)).get(0)
                                                                                                .should(appear, ofSeconds(30))
                                                                                                .setValue(socialLinkLinkText);
        return this;
    }

}

