package drupal.ui.pages.add_content.base_installation;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static drupal.ui.pages.add_content.base_installation.CreateBaseInstallationPage.BASE_REFERENCE_CONTAINER_LIST;
import static drupal.ui.pages.add_content.base_installation.CreateBaseInstallationPage.BASE_REFERENCE_DROP_DOWN;
import static drupal.ui.pages.add_content.base_installation.CreateBaseInstallationPage.BASE_REFERENCE_INPUT;
import static drupal.ui.pages.add_content.base_installation.CreateBaseInstallationPage.MARINES_SERVICES_CHECK_BOX;
import static drupal.ui.pages.add_content.base_installation.CreateBaseInstallationPage.NAVY_SERVICES_CHECK_BOX;
import static drupal.ui.pages.add_content.base_installation.CreateBaseInstallationPage.SHORT_NAME_INPUT;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BaseReferenceShortNameModel extends MasterPage implements ISectionDataModel {


    String baseReference;
    String shortName;

    public BaseReferenceShortNameModel() {
        baseReference = "Hampton Roads Military Bases";
        shortName = "Test Base Discounts";

    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        selectBaseReference(expectedClass);
        typeShortName(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectBaseReference(Class<P> expectedClass) {

        if (baseReference != null) {
            BASE_REFERENCE_DROP_DOWN.should(exist, appear)
                                    .click();
            BASE_REFERENCE_INPUT.should(appear, enabled)
                                .setValue(baseReference);
            BASE_REFERENCE_CONTAINER_LIST.first()
                                         .should(enabled, visible)
                                         .click();
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeShortName(Class<P> expectedClass) {

        if (shortName != null) {
            SHORT_NAME_INPUT.should(visible, enabled)
                            .setValue(shortName);

            if (shortName
                    .contains("LP")) {
                MARINES_SERVICES_CHECK_BOX.click();
                NAVY_SERVICES_CHECK_BOX.click();
            }
        }
        return returnInstanceOf(expectedClass);
    }

    public BaseReferenceShortNameModel getDiscountsData() {
        return new BaseReferenceShortNameModel("Hampton Roads Military Bases", "Test Base LP");

    }

}
