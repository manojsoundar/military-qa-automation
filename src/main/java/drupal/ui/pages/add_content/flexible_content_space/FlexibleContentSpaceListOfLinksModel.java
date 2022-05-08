package drupal.ui.pages.add_content.flexible_content_space;

import drupal.enums.content.FlexibleContentSpacePosition;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static drupal.enums.content.FlexibleContentSpaceType.ADD_LIST_OF_LINKS;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;
import static mgs.qa.utils.LoopUtils.doWhileConditionNotMet;

@SuppressWarnings("SimplifyStreamApiCallChains")
@Getter
@AllArgsConstructor
@Log4j2
public class FlexibleContentSpaceListOfLinksModel extends MasterPage implements ISectionDataModel, IFlexibleContentSpace {

    private static final String FLEXIBLE_CONTENT_SPACE_TITLE_INPUT = "input[id*='%s-%s-subform-field-title']";
    private static final String FLEXIBLE_CONTENT_SPACE_PARENT_LINK_URL_INPUT = "input.form-text[id*='%s-%s-subform-field-lists-0-subform-field-link-0-uri']";
    private static final String FLEXIBLE_CONTENT_SPACE_PARENT_LINK_LINK_TEXT_INPUT = "input.form-text[id*='%s-%s-subform-field-lists-0-subform-field-link-0-title']";
    private static final String FLEXIBLE_CONTENT_SPACE_LINKS_URL_INPUT = "input.form-text[id*='%s-%s-subform-field-lists-0-subform-field-links'][name*='[uri]']";
    private static final String FLEXIBLE_CONTENT_SPACE_LINKS_LINK_TEXT_INPUT = "input.form-text[id*='%s-%s-subform-field-lists-0-subform-field-links'][name*='[title]";
    private static final String LINK_ADD_ANOTHER_ITEM_BUTTON = "input[id*='%s-%s-subform-field-lists-0-subform-field-links-add-more']";

    private final FlexibleContentSpacePosition position;
    private int index;
    private final String title;
    private final String parentLinkUrl;
    private final String parentLinkLinkText;
    private final Map<String, String> linkUrls;

    public static Map<String, String> getBlockMilitaryPay() {
        Map<String, String> linkUrl = new LinkedHashMap<>();
        linkUrl.put("/benefits/military-pay", "Test military pay");
        linkUrl.put("/benefits/military-pay/allowances", "Test allowances");
        linkUrl.put("/benefits/military-pay/basic-pay", "Test Basic Pay");
        linkUrl.put("/benefits/military-pay/basic-allowance-for-housing", "Test BAH");
        linkUrl.put("/benefits/military-pay/charts", "Test Pay charts");
        return linkUrl;
    }

    public static Map<String, String> getBlockTricare() {
        Map<String, String> linkUrl = new LinkedHashMap<>();
        linkUrl.put("/benefits/tricare", "Test Tricare");
        linkUrl.put("/benefits/tricare/dental", "Test tricare dental");
        linkUrl.put("/benefits/tricare/extra", "Test tricare extra");
        linkUrl.put("/benefits/tricare/other-programs", "Test Tricare's Other Programs");
        linkUrl.put("/benefits/tricare/prime", "Test tricare prime");
        linkUrl.put("/benefits/tricare/pharmacy", "Test Tricare Pharmacy");
        linkUrl.put("/benefits/tricare/standard", "Test tricare standard");
        return linkUrl;
    }

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        index = selectFlexibleContentSpace(ADD_LIST_OF_LINKS, position) - 1;
        enterTitle();
        enterParentLinkFields();
        enterLinksFields();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();
        clickEditFlexibleContentSpace(position, index);
        enterTitle();
        return returnInstanceOf(expectedClass);
    }

    private FlexibleContentSpaceListOfLinksModel enterTitle() {
        if (title != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_TITLE_INPUT, position.name()
                                                                 .toLowerCase(), index)).should(appear, enabled)
                                                                                        .setValue(title);
        }
        return this;
    }

    private FlexibleContentSpaceListOfLinksModel enterParentLinkFields() {
        if (parentLinkUrl != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_PARENT_LINK_URL_INPUT, position.name()
                                                                           .toLowerCase(), index)).should(appear, enabled)
                                                                                                  .setValue(parentLinkUrl);

        }
        if (parentLinkLinkText != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_PARENT_LINK_LINK_TEXT_INPUT, position.name()
                                                                                 .toLowerCase(), index)).should(appear, enabled)
                                                                                                        .setValue(parentLinkLinkText);
        }
        return this;
    }

    private FlexibleContentSpaceListOfLinksModel enterLinksFields() {
        if (linkUrls != null) {
            @SuppressWarnings("SimplifyStreamApiCallChains")
            List<Map.Entry<String, String>> entryList = linkUrls.entrySet()
                                                                .stream()
                                                                .collect(Collectors.toList());
            for (int i = 0; i < linkUrls.size(); i++) {
                if (i != 0) {
                    $(format(LINK_ADD_ANOTHER_ITEM_BUTTON, position.name()
                                                                   .toLowerCase(), index))
                            .should(exist, appear, enabled)
                            .click();
                    doWhileConditionNotMet(
                            () -> $(format(LINK_ADD_ANOTHER_ITEM_BUTTON, position.name()
                                                                                 .toLowerCase(), index)).isEnabled(),
                            () -> log.info("waiting ADD_ANOTHER_ITEM being enabled ")
                    );
                }
                $$(format(FLEXIBLE_CONTENT_SPACE_LINKS_URL_INPUT, position.name()
                                                                          .toLowerCase(), index)).get(i)
                                                                                                 .should(enabled, ofSeconds(20))
                                                                                                 .setValue(entryList.get(i)
                                                                                                                    .getKey());

                $$(format(FLEXIBLE_CONTENT_SPACE_LINKS_LINK_TEXT_INPUT, position.name()
                                                                                .toLowerCase(), index)).get(i)
                                                                                                       .should(enabled, ofSeconds(20))
                                                                                                       .setValue(entryList.get(i)
                                                                                                                          .getValue());
            }
        }
        return this;
    }

}


