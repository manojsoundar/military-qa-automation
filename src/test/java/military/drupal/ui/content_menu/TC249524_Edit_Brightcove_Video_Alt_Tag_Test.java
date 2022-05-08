package military.drupal.ui.content_menu;

import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.ContentMenu;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.brightcove_video.BrightcoveResultPage;
import drupal.ui.pages.brightcove_video.BrightcoveVideoLandingPage;
import drupal.ui.pages.brightcove_video.EditBrightcoveVideoPage;
import drupal.ui.pages.brightcove_video.VideoModel;
import lombok.extern.log4j.Log4j2;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static drupal.models.UserModel.loadUserModel;

@Log4j2
public class TC249524_Edit_Brightcove_Video_Alt_Tag_Test extends AdminUITestBase {
    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionEditBrightcoveVideoAltTag() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test()
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249524")})
    @TestCaseId("249524")
    public void editBrightcoveVideoAltTag() {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover the brightcove video Tab");
        logStep("Click on 'BrightCove video Tab'");
        BrightcoveVideoLandingPage brightcoveVideoPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.BRIGHTCOVE_VIDEOS), BrightcoveVideoLandingPage.class);

        logStep("Select existing Brightcove video from grid");
        logStep("Pick random video from grid and click on item selected");
        BrightcoveResultPage brightcoveResultPage = brightcoveVideoPage.clickVideo();

        logStep("Select 'Edit' in the Drupal page admin bar above the title");
        EditBrightcoveVideoPage editBrightcoveVideoPage = brightcoveResultPage.clickEditTab();
        String videoTitle = editBrightcoveVideoPage.getVideoTitle();

        logStep("Under the IMAGES section, type in the Alt Tag in the Alt Tag field");
        logStep("Press Save!");
        logStep("Verify Video updated!!");
        editBrightcoveVideoPage.editBrightcoveVideoPageSectionsData(List.of(new VideoModel(videoTitle, null, null)))
                               .clickSaveButton()
                               .verifyBrightcoveVideoUpdated();
    }
}