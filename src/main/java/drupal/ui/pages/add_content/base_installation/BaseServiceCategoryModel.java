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
import static drupal.ui.pages.add_content.base_installation.CreateBaseInstallationPage.BASE_INSTALLATION_AVAILABLE_LIST;
import static drupal.ui.pages.add_content.base_installation.CreateBaseInstallationPage.BASE_SERVICE_CATEGORY_INPUT;
import static drupal.ui.pages.add_content.base_installation.CreateBaseInstallationPage.IS_BASE_SERVICE_CHECK_BOX;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BaseServiceCategoryModel extends MasterPage implements ISectionDataModel {


    String baseServiceCategory;

    public BaseServiceCategoryModel() {
        baseServiceCategory = "Money";

    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeBaseServiceCategory(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeBaseServiceCategory(Class<P> expectedClass) {

        if (baseServiceCategory != null) {
            IS_BASE_SERVICE_CHECK_BOX.should(visible, enabled)
                                     .click();
            BASE_SERVICE_CATEGORY_INPUT.should(appear, exist, enabled)
                                       .setValue(baseServiceCategory);

            BASE_INSTALLATION_AVAILABLE_LIST.first()
                                            .should(appear, exist)
                                            .click();
        }

        return returnInstanceOf(expectedClass);
    }

    public BaseServiceCategoryModel getDiscountsData() {
        return new BaseServiceCategoryModel(null);

    }

}
