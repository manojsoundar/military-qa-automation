package military.drupal.ui.content_menu;

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
import drupal.ui.pages.media.AddImagePage;
import drupal.ui.pages.media.AddMediaPage;
import drupal.ui.pages.media.EditMediaPage;
import drupal.ui.pages.media.ImageModel;
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
public class TC249691_Create_Image_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateImage() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "imageData")
    @Priority(level = PriorityLevel.HIGH)
    @TestCaseId("249691")
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249691")})
    public void createImage(List<ISectionDataModel> imageData) {
        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over Components");
        logStep("Click on 'Content'");
        logStep("Click on 'Media'");
        logStep("Select 'Add Media'");
        AddMediaPage addMediaPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.MEDIA, MediaMenu.ADD_MEDIA), AddMediaPage.class);
        logStep("Select and click on 'Image'");
        AddImagePage imagePage = addMediaPage.clickMediaItem(AddMediaPage.MediaItem.IMAGE, AddImagePage.class);

        logStep("Enter test name in the Name field");
        logStep("Click Choose Image in the IMAGE file");
        logStep("Copy and Paste Image Name into Alternative Text and Caption Fields");
        logStep("Click Save");
        logStep("Validate: Edit media functionality");
        MediaUploadModel imageUploadData = imageData.stream()
                                                    .filter(d -> d instanceof MediaUploadModel)
                                                    .map(c -> (MediaUploadModel) c)
                                                    .findFirst()
                                                    .get();
        EditMediaPage editMediaPage = imagePage.fillIn(imageData)
                                               .clickSaveButton()
                                               .verifyAddedMedia(imageUploadData.getName())
                                               .fillSearchCriteriaAndFilterMedia(MediaModel.getImageData(), imageUploadData.getName())
                                               .clickMediaLink(imageUploadData.getName())
                                               .verifyMediaLandingPage(imageUploadData.getName())
                                               .clickEditTab();

        imageUploadData.setName("Test Image " + timeStampFormat(PATTERN));
        imageUploadData.setFilePath(null);
        editMediaPage.fillIn(List.of(imageUploadData))
                     .clickSaveButton()
                     .verifyMediaUpdatedMessage(imageUploadData.getName());
    }

    @DataProvider
    public Object[][] imageData() {
        return new Object[][]{
                {
                        List.of(
                                MediaUploadModel.getImageData(),
                                new ImageModel()
                        )
                }
        };
    }

}
