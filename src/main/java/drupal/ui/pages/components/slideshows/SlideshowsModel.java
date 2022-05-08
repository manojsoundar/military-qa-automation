package drupal.ui.pages.components.slideshows;


import com.codeborne.selenide.Condition;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Data
public class SlideshowsModel extends MasterPage implements ISectionDataModel {

    private String title;
    private String caption;

    public static SlideshowsModel getSlideshowData() {
        return new SlideshowsModel("Test Slideshow" + timeStampFormat(PATTERN), "Test");
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        fillTitle(expectedClass);
        fillCaption(expectedClass);
        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();
        fillTitle(expectedClass);
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillTitle(Class<P> expectedClass) {
        if (title != null) {
            AddSlideshowContentPage.TITLE_INPUT.should(Condition.enabled, Condition.visible)
                                               .setValue(title);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillCaption(Class<P> expectedClass) {
        if (caption != null) {
            AddSlideshowContentPage.CAPTION_INPUT.should(Condition.enabled, Condition.visible)
                                                 .setValue(title);
        }
        return returnInstanceOf(expectedClass);
    }

}
