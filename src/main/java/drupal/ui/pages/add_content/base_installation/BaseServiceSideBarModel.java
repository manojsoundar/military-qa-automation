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
import static drupal.ui.pages.add_content.base_installation.CreateBaseInstallationPage.SIDEBAR_DROP_DOWN;
import static drupal.ui.pages.add_content.base_installation.CreateBaseInstallationPage.SIDE_BAR_CONTAINER_LIST;
import static drupal.ui.pages.add_content.base_installation.CreateBaseInstallationPage.SIDE_BAR_INPUT;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BaseServiceSideBarModel extends MasterPage implements ISectionDataModel {


    String sideBar;

    public BaseServiceSideBarModel() {

        sideBar = "Base Guide | Default Sidebar";

    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        selectSideBar(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectSideBar(Class<P> expectedClass) {
        if (sideBar != null) {
            SIDEBAR_DROP_DOWN.should(exist, appear)
                             .click();
            SIDE_BAR_INPUT.should(appear, enabled)
                          .setValue(sideBar);
            SIDE_BAR_CONTAINER_LIST.first()
                                   .should(enabled, visible)
                                   .click();
        }
        return returnInstanceOf(expectedClass);
    }

}
