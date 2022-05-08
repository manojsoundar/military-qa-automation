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
import static com.codeborne.selenide.Selenide.switchTo;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.ui.pages.add_content.base_installation.CreateBaseInstallationPage.BASE_INSTALLATION_TITLE_INPUT;
import static drupal.ui.pages.add_content.base_installation.CreateBaseInstallationPage.BLURB_FRAME;
import static drupal.ui.pages.add_content.base_installation.CreateBaseInstallationPage.BLURB_TEXT_AREA;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BaseInstallationModel extends MasterPage implements ISectionDataModel {

    String title;
    String blurb;

    public BaseInstallationModel() {
        title = "Test Base Discounts " + timeStampFormat(PATTERN);

        blurb = "<p><img alt=\"\" src=\"http://images.military.com/media/production/base-guides/overview-forthood-money.jpg\" /></p>\n"
                + "<p>Where can you get discounts? Does your credit union have ATMs nearby? Need a car loan in a hurry? Who's offering the best interest rates?</p>\n"
                + "<p>This is the place to find out how to get the most \"bang for your buck\" whether you're buying a home or planning for life after the Navy or Marine Corps.</p>\n";
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        enterTitle(expectedClass);
        enterBlurb(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P enterTitle(Class<P> expectedClass) {
        if (title != null) {
            BASE_INSTALLATION_TITLE_INPUT.should(visible, enabled)
                                         .setValue(title);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P enterBlurb(Class<P> expectedClass) {
        if (blurb != null) {
            switchTo().frame(BLURB_FRAME);
            BLURB_TEXT_AREA.should(appear, exist, visible)
                           .click();
            BLURB_TEXT_AREA.should(appear, visible, enabled)
                           .sendKeys(blurb);
            switchTo().parentFrame();
        }
        return returnInstanceOf(expectedClass);
    }

}
