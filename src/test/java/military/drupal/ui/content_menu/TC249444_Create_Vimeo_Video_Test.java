package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.ContentMenu;
import drupal.enums.content.MediaMenu;
import drupal.models.ISectionDataModel;
import drupal.models.MediaModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.media.AddMediaPage;
import drupal.ui.pages.media.AddYouTubeVideoPage;
import drupal.ui.pages.media.EditMediaPage;
import drupal.ui.pages.media.MediaURLModel;
import lombok.extern.log4j.Log4j2;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.models.UserModel.loadUserModel;

@Log4j2
public class TC249444_Create_Vimeo_Video_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateVimeoVideo() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "vimeoData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249444")})
    @TestCaseId("249444")
    public void createVimeoVideo(List<ISectionDataModel> vimeoData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover the Media Tab");
        logStep("Click on 'Add Media'");
        AddMediaPage addMediaPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.MEDIA, MediaMenu.ADD_MEDIA), AddMediaPage.class);

        logStep("Select YOUTUBE VIDEO");
        AddYouTubeVideoPage addYouTubeVideoPage = addMediaPage.clickMediaItem(AddMediaPage.MediaItem.YOUTUBE_VIDEO, AddYouTubeVideoPage.class);

        logStep("Enter the test name in Name field");
        logStep("Enter Vimeo Video URL in the Post URL field");
        logStep("Click save");
        logStep("Validate: Drupal Vimeo Video Link is created and displayed at - " + Configuration.baseUrl + "/admin/content/media ");
        logStep("Validate: Edit media functionality");
        MediaURLModel vimeoModel = vimeoData.stream()
                                            .filter(d -> d instanceof MediaURLModel)
                                            .map(c -> (MediaURLModel) c)
                                            .findFirst()
                                            .get();
        EditMediaPage editMediaPage = addYouTubeVideoPage.fillIn(vimeoData)
                                                         .clickSaveButton()
                                                         .verifyAddedMedia(vimeoModel.getName())
                                                         .fillSearchCriteriaAndFilterMedia(MediaModel.getYouTubeData(), vimeoModel.getName())
                                                         .clickMediaLink(vimeoModel.getName())
                                                         .verifyMediaLandingPage(vimeoModel.getName())
                                                         .clickEditTab();

        vimeoModel.setName("Test Vimeo Video " + timeStampFormat(PATTERN));
        vimeoModel.setMediaURL(null);
        editMediaPage.fillIn(List.of(vimeoModel))
                     .clickSaveButton()
                     .verifyMediaUpdatedMessage(vimeoModel.getName());
    }

    @DataProvider
    public Object[][] vimeoData() {
        return new Object[][]{
                {
                        List.of(
                                MediaURLModel.getVimeoData()
                        )
                }
        };
    }

}


