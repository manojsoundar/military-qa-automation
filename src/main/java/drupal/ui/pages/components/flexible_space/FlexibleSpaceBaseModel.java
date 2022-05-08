package drupal.ui.pages.components.flexible_space;

import drupal.models.ISectionDataModel;
import drupal.ui.pages.add_content.flexible_content_space.IFlexibleContentSpace;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.ui.pages.components.flexible_space.AddFlexibleSpaceContentPage.TITLE_INPUT;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class FlexibleSpaceBaseModel extends MasterPage implements ISectionDataModel, IFlexibleContentSpace {

    String title;

    public FlexibleSpaceBaseModel() {

        title = "Test Title " + timeStampFormat(PATTERN);

    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeTitle(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeTitle(expectedClass);
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeTitle(Class<P> expectedClass) {

        if (title != null) {
            TITLE_INPUT.should(exist, appear, enabled)
                       .setValue(title);
        }
        return returnInstanceOf(expectedClass);
    }

}