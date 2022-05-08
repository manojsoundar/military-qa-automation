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
import drupal.ui.pages.media.AddInstagramPage;
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
public class TC249421_Create_Instagram_Post_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateInstagramPost() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "mediaData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249421")})
    @TestCaseId("249421")
    public void createInstagramPost(List<ISectionDataModel> instagramData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover the Media Tab");
        logStep("Hover Add Media");
        logStep("Click on 'Instagram'");
        AddInstagramPage addInstagramPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.MEDIA, MediaMenu.ADD_MEDIA, AddMediaMenu.INSTAGRAM), AddInstagramPage.class);

        logStep("Enter the test name in Name field");
        logStep("Enter the instagramURL into InstagramURL URL field");
        logStep("Click save");
        logStep("Validate: Drupal Instagram Media is created and displayed at - " + Configuration.baseUrl + "/admin/content/media");
        logStep("Validate: Edit media functionality");
        MediaURLModel instagramModel = instagramData.stream()
                                                    .filter(d -> d instanceof MediaURLModel)
                                                    .map(c -> (MediaURLModel) c)
                                                    .findFirst()
                                                    .get();
        EditMediaPage editMediaPage = addInstagramPage.fillIn(instagramData)
                                                      .clickSaveButton()
                                                      .verifyAddedMedia(instagramModel.getName())
                                                      .fillSearchCriteriaAndFilterMedia(MediaModel.getInstagramData(), instagramModel.getName())
                                                      .clickMediaLink(instagramModel.getName())
                                                      .verifyMediaLandingPage(instagramModel.getName())
                                                      .clickEditTab();

        instagramModel.setName("Test Instagram Post " + timeStampFormat(PATTERN));
        instagramModel.setMediaURL(null);
        editMediaPage.fillIn(List.of(instagramModel))
                     .clickSaveButton()
                     .verifyMediaUpdatedMessage(instagramModel.getName());

    }

    @DataProvider
    public Object[][] mediaData() {
        return new Object[][]{
                {
                        List.of(
                                MediaURLModel.getInstagramData()
                        )
                }
        };
    }

}
