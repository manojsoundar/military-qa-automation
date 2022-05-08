package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.AddMediaMenu;
import drupal.enums.content.ContentMenu;
import drupal.enums.content.MediaMenu;
import drupal.models.ISectionDataModel;
import drupal.models.MediaModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.media.AddFacebookPage;
import drupal.ui.pages.media.EditMediaPage;
import drupal.ui.pages.media.MediaLandingPage;
import drupal.ui.pages.media.MediaPage;
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
public class TC248568_Create_Facebook_Post_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateFacebookPost() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "mediaData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "248568")})
    @TestCaseId("248568")
    public void createFacebookPost(List<ISectionDataModel> facebookData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover the Media Tab");
        logStep("Hover Add Media");
        logStep("Click on 'Facebook'");
        AddFacebookPage addFacebookPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.MEDIA, MediaMenu.ADD_MEDIA, AddMediaMenu.FACEBOOK), AddFacebookPage.class);

        logStep("Enter the test name in Name field");
        logStep("Enter the fbURL into Facebook URL field");
        logStep("Click save");
        logStep("Validate: Drupal Facebook Media is created and displayed at - " + Configuration.baseUrl + "/admin/content/media");
        MediaURLModel facebookModel = facebookData.stream()
                                                  .filter(d -> d instanceof MediaURLModel)
                                                  .map(c -> (MediaURLModel) c)
                                                  .findFirst()
                                                  .get();
        MediaPage mediaPage = addFacebookPage.fillIn(facebookData)
                                             .clickSaveButton()
                                             .verifyAddedMedia(facebookModel.getName());

        logStep("Validate: Edit media functionality");
        MediaLandingPage mediaLandingPage = mediaPage.fillSearchCriteriaAndFilterMedia(MediaModel.getFacebookData(), facebookModel.getName())
                                                     .clickMediaLink(facebookModel.getName());
        EditMediaPage editMediaPage = mediaLandingPage.verifyMediaLandingPage(facebookModel.getName())
                                                      .clickEditTab();
        facebookModel.setName("Test Facebook Post " + timeStampFormat(PATTERN));
        facebookModel.setMediaURL(null);
        editMediaPage.fillIn(List.of(facebookModel))
                     .clickSaveButton()
                     .verifyMediaUpdatedMessage(facebookModel.getName());

    }

    @DataProvider
    public Object[][] mediaData() {
        return new Object[][]{
                {
                        List.of(
                                MediaURLModel.getFacebookData()
                        )
                }
        };
    }

}
