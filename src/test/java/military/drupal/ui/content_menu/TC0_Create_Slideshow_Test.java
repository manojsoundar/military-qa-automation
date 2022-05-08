package military.drupal.ui.content_menu;

import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.ComponentsMenu;
import drupal.enums.content.ContentMenu;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.components.slideshows.SlideshowImageModel;
import drupal.ui.pages.components.slideshows.SlideshowsModel;
import drupal.ui.pages.components.slideshows.SlideshowsPage;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static drupal.models.UserModel.loadUserModel;

public class TC0_Create_Slideshow_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateSlideshow() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(enabled = false)
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "0")})
    @TestCaseId("0")
    public void createSlideshow() {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over Components");
        logStep("Click on 'SNIPPETS'");
        SlideshowsPage slideshowsPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.COMPONENTS, ComponentsMenu.SLIDESHOWS), SlideshowsPage.class);

        logStep("Click '+Add Snippet' button");
        logStep("Select Snippet -> Contains a brief snippet and link to another content, usually off-site links.");
        slideshowsPage.clickOnAddSlideshowButton()
                      .fillSlideshow(List.of(SlideshowsModel.getSlideshowData(), SlideshowImageModel.getSlideshowImageData()))
                      .clickSaveButton();

    }
}
