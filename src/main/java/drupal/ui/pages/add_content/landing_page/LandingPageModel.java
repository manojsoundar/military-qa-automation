package drupal.ui.pages.add_content.landing_page;

import drupal.models.ISectionDataModel;
import drupal.ui.components.AttachAnImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.ui.pages.add_content.landing_page.AddLandingPage.TITLE_INPUT;


@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class LandingPageModel extends MasterPage implements ISectionDataModel, AttachAnImage {

    private String landingPageTitle;

    public LandingPageModel() {
        landingPageTitle = "Test Landing Page " + timeStampFormat(PATTERN);
    }

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        enterLandingPageTitle();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private LandingPageModel enterLandingPageTitle() {
        TITLE_INPUT.should(exist, appear, enabled)
                   .setValue(landingPageTitle);
        return this;
    }

}
