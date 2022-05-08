package drupal.ui.pages.structure.eck_entity_types.source;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.ui.pages.structure.eck_entity_types.source.AddSourcesPage.TITLE_INPUT;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class AddSourcesContentDataModel extends MasterPage implements ISectionDataModel {

    private String title;

    public AddSourcesContentDataModel() {
        title = "Test Source " + timeStampFormat(PATTERN);
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        enterTitle(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P enterTitle(Class<P> expectedClass) {
        if (title != null) {
            TITLE_INPUT.should(appear, exist, enabled)
                       .scrollIntoView("{behavior: 'instant', block: 'center', inline: 'center'}")
                       .setValue(title);
        }

        return returnInstanceOf(expectedClass);
    }

}
