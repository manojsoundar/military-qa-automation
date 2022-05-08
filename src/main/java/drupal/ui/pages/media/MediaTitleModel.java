package drupal.ui.pages.media;

import drupal.models.ISectionDataModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static drupal.ui.pages.media.AddMediaBasePage.NAME_INPUT;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
public class MediaTitleModel extends MasterPage implements ISectionDataModel {

    private String name;

    public MediaTitleModel(String name) {

        this.name = name;

    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        enterName(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P enterName(Class<P> expectedClass) {

        if (name != null) {
            NAME_INPUT.should(visible, enabled)
                      .setValue(name);
        }

        return returnInstanceOf(expectedClass);
    }

}
