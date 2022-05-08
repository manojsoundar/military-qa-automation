package drupal.ui.pages.add_content.employer;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.ui.pages.add_content.employer.CreateEmployerPage.BLURB_TEXT;
import static drupal.ui.pages.add_content.employer.CreateEmployerPage.BODY_FRAME;
import static drupal.ui.pages.add_content.employer.CreateEmployerPage.BODY_TEXTAREA;
import static drupal.ui.pages.add_content.employer.CreateEmployerPage.CONTENT_RELEASE_INPUT;
import static drupal.ui.pages.add_content.employer.CreateEmployerPage.EMPLOYERS_NAME_INPUT;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Log4j2
@Data
@AllArgsConstructor
public class EmployerDataModel extends MasterPage implements ISectionDataModel {

    String name;
    String blurbText;
    String bodyText;
    String contentRelease;

    public EmployerDataModel() {
        name = "Test Employer " + timeStampFormat(PATTERN);
        blurbText = "Search Test Employer Jobs";
        bodyText = "Test";
        contentRelease = null;
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeName(expectedClass);
        typeBlurbText(expectedClass);
        typeBodyText(expectedClass);
        typeContentRelease(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeName(expectedClass);
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeName(Class<P> expectedClass) {
        if (name != null) {
            EMPLOYERS_NAME_INPUT.should(appear, exist, enabled)
                                .setValue(name);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeBlurbText(Class<P> expectedClass) {
        if (blurbText != null) {
            BLURB_TEXT.should(appear, exist, enabled)
                      .scrollTo()
                      .setValue(blurbText);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeBodyText(Class<P> expectedClass) {
        if (bodyText != null) {
            switchTo().frame(BODY_FRAME);
            BODY_TEXTAREA.should(appear, exist, visible)
                         .click();
            BODY_TEXTAREA.should(appear, visible, enabled)
                         .sendKeys(bodyText);
            switchTo().parentFrame();
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeContentRelease(Class<P> expectedClass) {
        if (contentRelease != null) {
            CONTENT_RELEASE_INPUT.should(appear, exist, enabled)
                                 .setValue(contentRelease);
        }
        return returnInstanceOf(expectedClass);
    }

}
