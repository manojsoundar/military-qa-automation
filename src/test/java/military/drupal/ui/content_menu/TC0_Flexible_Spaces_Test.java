package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.ComponentsMenu;
import drupal.enums.content.ContentMenu;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceImageModel;
import drupal.ui.pages.components.flexible_space.AddFlexibleSpaceContentPage;
import drupal.ui.pages.components.flexible_space.FlexibleSpaceBaseModel;
import drupal.ui.pages.components.flexible_space.FlexibleSpaceLandingPage;
import drupal.ui.pages.components.flexible_space.FlexibleSpacesPage;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static drupal.enums.content.FlexibleContentSpacePosition.CONTENT;
import static drupal.models.UserModel.loadUserModel;

public class TC0_Flexible_Spaces_Test extends AdminUITestBase {
    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateFlexibleSpaces() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(enabled = false)
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "0")})
    @TestCaseId("0")
    public void createFlexibleSpaces() {
        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over Components");
        logStep("Click on 'Flexible space (landing page sidebars)'");
        FlexibleSpacesPage flexibleSpacesPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.COMPONENTS, ComponentsMenu.FLEXIBLE_SPACE), FlexibleSpacesPage.class);

        logStep("Click on +Add Flexible space");
        AddFlexibleSpaceContentPage addFlexibleSpaceContentPage = flexibleSpacesPage.clickFlexibleSpaceTab();

        logStep("Fill in fields of Flexible space");
        logStep("Select Add Image from the list");
        logStep("Fill in fields in Image section");
        logStep("click save");
        FlexibleSpaceLandingPage flexibleSpaceLandingPage = addFlexibleSpaceContentPage.addFlexibleSpace(List.of(new FlexibleSpaceBaseModel(), new FlexibleContentSpaceImageModel(CONTENT, 0, "Test", null, "Test Image")))
                                                                                       .saveFlexibleSpaceLanding();

        logStep("Validate: Drupal Regular Flexible space is created: " + Configuration.baseUrl + "/admin/structure/eck/entity/flexible_space/####");
        flexibleSpaceLandingPage.verifyDrupalFlexibleSpaceCreated();
    }
}
