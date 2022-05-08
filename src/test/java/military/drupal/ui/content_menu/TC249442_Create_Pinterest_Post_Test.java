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
import drupal.ui.pages.media.AddPinterestPage;
import drupal.ui.pages.media.EditMediaPage;
import drupal.ui.pages.media.MediaURLModel;
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

public class TC249442_Create_Pinterest_Post_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreatePinterestPost() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "pinterestData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249442")})
    @TestCaseId("249442")
    public void createPinterestPost(List<ISectionDataModel> pinterestData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover the Media Tab");
        logStep("Hover Add Media");
        logStep("Click on 'Pinterest'");
        AddPinterestPage addPinterestPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.MEDIA, MediaMenu.ADD_MEDIA, AddMediaMenu.PINTEREST), AddPinterestPage.class);

        logStep("Enter the test name in Name field");
        logStep("Enter the pinterest URL into Pinterest URL field");
        logStep("Click save");
        logStep("Validate: Drupal Pinterest Media is created and displayed at - " + Configuration.baseUrl + "/admin/content/media");
        logStep("Validate: Edit media functionality");
        MediaURLModel pinterestModel = pinterestData.stream()
                                                    .filter(d -> d instanceof MediaURLModel)
                                                    .map(c -> (MediaURLModel) c)
                                                    .findFirst()
                                                    .get();
        EditMediaPage editMediaPage = addPinterestPage.fillIn(pinterestData)
                                                      .clickSaveButton()
                                                      .verifyAddedMedia(pinterestModel.getName())
                                                      .fillSearchCriteriaAndFilterMedia(MediaModel.getPinterestData(), pinterestModel.getName())
                                                      .clickMediaLink(pinterestModel.getName())
                                                      .verifyMediaLandingPage(pinterestModel.getName())
                                                      .clickEditTab();

        pinterestModel.setName("Test Pinterest Post " + timeStampFormat(PATTERN));
        pinterestModel.setMediaURL(null);
        editMediaPage.fillIn(List.of(pinterestModel))
                     .clickSaveButton()
                     .verifyMediaUpdatedMessage(pinterestModel.getName());

    }

    @DataProvider
    public Object[][] pinterestData() {
        return new Object[][]{
                {
                        List.of(
                                MediaURLModel.getPinterestData()
                        )
                }
        };
    }

}
