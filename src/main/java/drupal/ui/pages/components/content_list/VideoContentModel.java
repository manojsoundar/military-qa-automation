package drupal.ui.pages.components.content_list;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.enums.content.EntityType;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static drupal.enums.content.EntityType.TAXONOMY_TERM;
import static drupal.ui.pages.components.content_list.AddCuratedListPage.CURATED_LIST_ARTICLES_SELECT;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@Getter
@AllArgsConstructor
public class VideoContentModel extends MasterPage implements ISectionDataModel {

    private static final ElementsCollection ENTITY_TYPE_DROPDOWN = $$("select.dynamic-entity-reference-entity-type");
    private static final ElementsCollection ENTITY_TYPE_VALUE_INPUT = $$("input.form-text[id*='content']");
    private static final SelenideElement ADD_ANOTHER_ITEM_BUTTON = $("input.button[id*='-content-add-more']");

    private final Map<String, EntityType> videoContent;

    public static VideoContentModel getVideoTaxonomyCuratedList() {
        return new VideoContentModel(Map.of("Jet Fighters", TAXONOMY_TERM, "Explosions", TAXONOMY_TERM, "Editors' Picks", TAXONOMY_TERM,
                                            "5 Things You Don't Know", TAXONOMY_TERM));

    }

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        enterVideoContentSection();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    public VideoContentModel enterVideoContentSection() {

        @SuppressWarnings("SimplifyStreamApiCallChains")
        List<Map.Entry<String, EntityType>> entityTypeEntryList = videoContent.entrySet()
                                                                              .stream()
                                                                              .collect(Collectors.toList());
        for (int i = 0; i < videoContent.size(); i++) {
            if (i != 0) {
                ADD_ANOTHER_ITEM_BUTTON.should(exist, appear, enabled)
                                       .scrollTo()
                                       .click();
                waitAjaxJQueryMet(120);
            }
            ENTITY_TYPE_DROPDOWN.get(i)
                                .should(enabled, visible)
                                .selectOption(entityTypeEntryList.get(i)
                                                                 .getValue()
                                                                 .getEntityTypeItem());
            ENTITY_TYPE_VALUE_INPUT.get(i)
                                   .should(enabled, visible)
                                   .setValue(entityTypeEntryList.get(i)
                                                                .getKey());
            waitAjaxJQueryMet(300);
            if ($(".ui-menu.ui-widget .ui-menu-item").isDisplayed()) {
                CURATED_LIST_ARTICLES_SELECT.last()
                                            .scrollIntoView("{behavior: 'instant', block: 'center', inline: 'center'}")
                                            .click();
            }
        }

        return this;
    }

}
