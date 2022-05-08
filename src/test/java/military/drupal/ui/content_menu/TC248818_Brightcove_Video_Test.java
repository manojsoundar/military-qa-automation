package military.drupal.ui.content_menu;

import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.ContentMenu;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.brightcove_video.BrightcoveVideoLandingPage;
import lombok.extern.log4j.Log4j2;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static drupal.models.UserModel.loadUserModel;

@Log4j2
public class TC248818_Brightcove_Video_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionBrightcoveVideo() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test()
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "248818")})
    @TestCaseId("248818")
    public void brightcoveVideo() {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover the brightcove video Tab");
        logStep("Click on 'BrightCove video Tab'");
        BrightcoveVideoLandingPage brightcoveVideoPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.BRIGHTCOVE_VIDEOS), BrightcoveVideoLandingPage.class);

        log.info("Brightcove video page is loaded successfully");
        brightcoveVideoPage.searchForBrightcoveVideo();

    }

}
