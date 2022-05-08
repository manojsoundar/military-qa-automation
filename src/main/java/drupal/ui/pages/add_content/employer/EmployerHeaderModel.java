package drupal.ui.pages.add_content.employer;

import drupal.models.ISectionDataModel;
import drupal.models.ImageUploadModel;
import drupal.ui.components.AttachAnImage;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static drupal.ui.pages.add_content.employer.CreateEmployerPage.EMPLOYERS_NAME_INPUT;
import static drupal.ui.pages.add_content.employer.CreateEmployerPage.HEADER_ATTACH_AN_IMAGE_BUTTON;
import static drupal.ui.pages.add_content.employer.CreateEmployerPage.HEADER_BOX_TEXT_INPUT;
import static drupal.ui.pages.add_content.employer.CreateEmployerPage.HEADER_LINK_INPUT;
import static drupal.ui.pages.add_content.employer.CreateEmployerPage.HEADER_TITLE_TEXT_INPUT;
import static java.time.Duration.ofSeconds;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@SuppressWarnings("CanBeFinal")
public class EmployerHeaderModel extends MasterPage implements ISectionDataModel, AttachAnImage {

    ImageUploadModel headerImage;
    String headerTitleText;
    String headerBoxText;
    String headerLinkText;

    public EmployerHeaderModel() {
        headerImage = new ImageUploadModel("VTP header", "", "", true, false, null);
        headerTitleText = "Search Test Employer Jobs";
        headerBoxText = "Another Mission That Matters";
        headerLinkText = "https://www.military.com/daily-news";
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        attachHeaderImage(expectedClass);
        typeHeaderTitleText(expectedClass);
        typeHeaderBoxText(expectedClass);
        typeHeaderLinkText(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P attachHeaderImage(Class<P> expectedClass) {
        if (headerImage != null) {
            EMPLOYERS_NAME_INPUT.scrollTo();
            HEADER_ATTACH_AN_IMAGE_BUTTON.should(appear, ofSeconds(30))
                                         .should(enabled)
                                         .scrollIntoView(false)
                                         .click();
            waitAjaxJQueryMet(120);
            uploadOrAttachExistingImage(headerImage);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeHeaderTitleText(Class<P> expectedClass) {
        if (headerTitleText != null) {
            HEADER_TITLE_TEXT_INPUT.should(appear, exist, enabled)
                                   .setValue(headerTitleText);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeHeaderBoxText(Class<P> expectedClass) {
        if (headerBoxText != null) {
            HEADER_BOX_TEXT_INPUT.should(appear, exist, enabled)
                                 .setValue(headerBoxText);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeHeaderLinkText(Class<P> expectedClass) {
        if (headerLinkText != null) {
            HEADER_LINK_INPUT.should(appear, exist, enabled)
                             .setValue(headerLinkText);
        }
        return returnInstanceOf(expectedClass);
    }

}
