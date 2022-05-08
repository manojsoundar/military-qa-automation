package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.ComponentsMenu;
import drupal.enums.content.ContentMenu;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.components.slideshows.SlideShowResultPage;
import drupal.ui.pages.components.slideshows.SlideshowImageModel;
import drupal.ui.pages.components.slideshows.SlideshowsModel;
import drupal.ui.pages.components.slideshows.SlideshowsPage;
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

public class TC249657_Create_A_Slideshow_Existing_Images_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateSlideshow() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "addSlideshow")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249657")})
    @TestCaseId("249657")
    public void createSlideshow(List<ISectionDataModel> data) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over Components");
        logStep("Click on 'Slideshows'");
        SlideshowsPage slideshowsPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.COMPONENTS, ComponentsMenu.SLIDESHOWS), SlideshowsPage.class);

        logStep("Click 'Add Slideshow' button");
        logStep("Create Slideshow with Title Name,Caption and Existing Image with all available images");
        logStep("Click Save!");

        SlideShowResultPage slideshowResultPage = slideshowsPage.clickOnAddSlideshowButton()
                                                                .fillSlideshow(data)
                                                                .clickSaveButton();

        SlideshowsModel slideshowsModel = data.stream()
                                              .filter(d -> d instanceof SlideshowsModel)
                                              .map(c -> (SlideshowsModel) c)
                                              .findFirst()
                                              .get();
        logStep("Validate: Drupal Slideshow page is created: " + Configuration.baseUrl + "/admin/structure/eck/entity/slideshow/###");
        slideshowResultPage.verifyDrupalSlideShowCreated(slideshowsModel);

        slideshowsModel.setTitle("Edit title " + timeStampFormat(PATTERN));
        slideshowsModel.setCaption("Edit caption");
        slideshowResultPage.clickEditTab()
                           .editAllSlideShow(List.of(slideshowsModel))
                           .clickSaveButton()
                           .verifyUpdatedSlideshow(slideshowsModel);
    }

    @DataProvider
    public Object[][] addSlideshow() {
        return new Object[][]{
                {
                        List.of(
                                SlideshowsModel.getSlideshowData(),
                                SlideshowImageModel.getSlideshowImageData()
                        )
                }
        };
    }

}
