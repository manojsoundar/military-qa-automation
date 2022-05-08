package drupal.ui.pages.components.content_list;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import java.util.List;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class CuratedNodesListModel extends MasterPage implements ISectionDataModel {

    private static final ElementsCollection NODES_INPUT = $$("input.form-text[id^='edit-field-nodes']");
    private static final SelenideElement ADD_ANOTHER_ITEM_BUTTON = $("input.button[id*='-add-more']");

    private List<String> nodes;

    public CuratedNodesListModel() {
        nodes = List.of("Fitness Motivation TEST");
    }

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        enterMoreLinkSection();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private CuratedNodesListModel enterMoreLinkSection() {
        if (nodes != null) {
            nodes.forEach(node -> {
                NODES_INPUT.last()
                           .should(enabled, visible)
                           .setValue(node);
                if (nodes.indexOf(node) <= nodes.size()) {
                    ADD_ANOTHER_ITEM_BUTTON.should(visible, enabled)
                                           .click();
                }
            });
        }

        return this;
    }

}
