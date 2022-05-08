package drupal.ui.pages.add_content.equipment;

import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import java.util.Map;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static drupal.ui.pages.add_content.equipment.AddEquipmentPage.ADD_SPECIFICATIONS_BUTTON;
import static drupal.ui.pages.add_content.equipment.AddEquipmentPage.BODY_TOP_WEB_ELEMENT;
import static drupal.ui.pages.add_content.equipment.AddEquipmentPage.SPECIFICATIONS_DESCRIPTION_INPUT;
import static drupal.ui.pages.add_content.equipment.AddEquipmentPage.SPECIFICATIONS_LABEL_INPUT;

@Getter
@AllArgsConstructor
public class SpecificationsModel extends AdministrationToolbar implements ISectionDataModel {

    private final Map<String, String> specifications;

    public SpecificationsModel() {
        specifications = Map.of("Manufacturer:", "BAE Systems");
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        setSpecification();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private SpecificationsModel setSpecification() {
        if (specifications != null) {
            BODY_TOP_WEB_ELEMENT.should(appear, visible)
                                .scrollTo();
            for (Map.Entry<String, String> specificationEntrySet : specifications.entrySet()) {
                SPECIFICATIONS_LABEL_INPUT.should(appear, visible, enabled)
                                          .setValue(specificationEntrySet
                                                            .getKey());
                SPECIFICATIONS_DESCRIPTION_INPUT.should(appear, enabled)
                                                .setValue(specificationEntrySet
                                                                  .getValue());
                ADD_SPECIFICATIONS_BUTTON.should(appear, visible, enabled)
                                         .click();
            }
        }
        return this;
    }

}
