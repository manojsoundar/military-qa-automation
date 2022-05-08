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
import drupal.ui.pages.media.AddAudioPage;
import drupal.ui.pages.media.AddMediaPage;
import drupal.ui.pages.media.EditMediaPage;
import drupal.ui.pages.media.MediaUploadModel;
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
public class TC248819_Create_Audio_File_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateAudioFile() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "audioData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "248819")})
    @TestCaseId("248819")
    public void createAudioFile(List<ISectionDataModel> audioData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover the Media Tab");
        logStep("Click on 'Add Media'");
        AddMediaPage addMediaPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.MEDIA, MediaMenu.ADD_MEDIA), AddMediaPage.class);

        logStep("Select Audio");
        AddAudioPage addAudioPage = addMediaPage.clickMediaItem(AddMediaPage.MediaItem.AUDIO, AddAudioPage.class);

        logStep("Enter the test name in Name field (ex: Test Audio File[time/date stamp]");
        logStep("Click Choose File and locate test audio file");
        logStep("Click save");
        logStep("Validate: Drupal Audio Link is created and displayed at - " + Configuration.baseUrl + "/admin/content/media ");
        logStep("Validate: Edit media functionality");
        MediaUploadModel audioUploadData = audioData.stream()
                                                    .filter(d -> d instanceof MediaUploadModel)
                                                    .map(c -> (MediaUploadModel) c)
                                                    .findFirst()
                                                    .get();
        EditMediaPage editMediaPage = addAudioPage.fillIn(audioData)
                                                  .clickSaveButton()
                                                  .verifyAddedMedia(audioUploadData.getName())
                                                  .fillSearchCriteriaAndFilterMedia(MediaModel.getAudioData(), audioUploadData.getName())
                                                  .clickMediaLink(audioUploadData.getName())
                                                  .verifyMediaLandingPage(audioUploadData.getName())
                                                  .clickEditTab();

        audioUploadData.setName("Test Audio File " + timeStampFormat(PATTERN));
        audioUploadData.setFilePath(null);
        editMediaPage.fillIn(List.of(audioUploadData))
                     .clickSaveButton()
                     .verifyMediaUpdatedMessage(audioUploadData.getName());

    }

    @DataProvider
    public Object[][] audioData() {
        return new Object[][]{
                {
                        List.of(
                                MediaUploadModel.getAudioData()
                        )
                }
        };
    }

}
