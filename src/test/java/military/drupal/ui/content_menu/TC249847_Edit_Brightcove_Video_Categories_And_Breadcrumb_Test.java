package military.drupal.ui.content_menu;

import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.ContentMenu;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.brightcove_video.BrightcoveResultPage;
import drupal.ui.pages.brightcove_video.BrightcoveVideoLandingPage;
import drupal.ui.pages.brightcove_video.DetailsCategoriesModel;
import drupal.ui.pages.brightcove_video.EditBrightcoveVideoPage;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static drupal.enums.content.Category.MILITARY_BRIEFS;
import static drupal.enums.content.Category.MILITARY_ORIGINALS;
import static drupal.enums.content.Category.SHOCK_AND_AWE_WORLD_WAR_2;
import static drupal.enums.content.Category.VIDEO;
import static drupal.models.UserModel.loadUserModel;

public class TC249847_Edit_Brightcove_Video_Categories_And_Breadcrumb_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionEditBrightcoveVideo() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "editData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249847")})
    @TestCaseId("249847")
    public void editBrightcoveVideo(List<ISectionDataModel> categoriesSectionData) {
        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover the brightcove video Tab");
        logStep("Click on 'BrightCove video Tab'");
        BrightcoveVideoLandingPage brightcoveVideoPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.BRIGHTCOVE_VIDEOS), BrightcoveVideoLandingPage.class);

        logStep("Select existing Brightcove video from grid");
        logStep("Pick video from grid and click on item selected");
        BrightcoveResultPage brightcoveResultPage = brightcoveVideoPage.clickVideo();

        logStep("Select 'Edit' in the Drupal page admin bar above the title");
        logStep("In the Details section, update the Categories field with Test Categories");
        logStep("In the Breadcrumb category, update the field one of the Test Categories");
        logStep("Press Save!");
        logStep("Go to the Video page to confirm: " +
                        "The breadcrumb now includes the Military Briefs hyperlink The Categories directly below the " +
                        "video's description includes the categories listed");
        EditBrightcoveVideoPage editBrightcoveVideoPage = brightcoveResultPage.clickEditTab();
        brightcoveResultPage = editBrightcoveVideoPage.editBrightcoveVideoPageSectionsData(categoriesSectionData)
                                                      .clickSaveButton();
        brightcoveResultPage.verifyVideoCategoriesAndBreadcrumbFieldsUpdated(categoriesSectionData.stream()
                                                                                                  .filter(d -> d instanceof DetailsCategoriesModel)
                                                                                                  .map(c -> (DetailsCategoriesModel) c)
                                                                                                  .findFirst()
                                                                                                  .get());
    }

    @DataProvider
    public Object[][] editData() {
        return new Object[][]{
                {
                        List.of(
                                new DetailsCategoriesModel(
                                        List.of(VIDEO, SHOCK_AND_AWE_WORLD_WAR_2, MILITARY_ORIGINALS, MILITARY_BRIEFS),
                                        DetailsCategoriesModel.BreadcrumbCategory.VIDEO_MILITARY_MILITARY_BRIEFS,
                                        null
                                )
                        )
                },
        };
    }

}
