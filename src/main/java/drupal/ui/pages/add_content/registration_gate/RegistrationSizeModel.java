package drupal.ui.pages.add_content.registration_gate;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static drupal.ui.pages.add_content.registration_gate.CreateRegistrationGatePage.SIZE_DROPDOWN;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Log4j2
@Data
@AllArgsConstructor
public class RegistrationSizeModel extends MasterPage implements ISectionDataModel {

    FontSize fontSize;

    public RegistrationSizeModel() {
        this.fontSize = FontSize.DEFAULT;
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        selectRegistrationSize(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectRegistrationSize(Class<P> expectedClass) {
        if (fontSize != null) {
            SIZE_DROPDOWN.should(exist, appear, enabled)
                         .selectOptionContainingText(fontSize.getSizeTypeItem());
        }
        return returnInstanceOf(expectedClass);
    }

    public RegistrationSizeModel setSmallFontSize() {
        return new RegistrationSizeModel(FontSize.SMALL);
    }

    @Getter
    @AllArgsConstructor
    public enum FontSize {
        DEFAULT("Default"),
        SMALL("Small");

        private final String sizeTypeItem;
    }

}
