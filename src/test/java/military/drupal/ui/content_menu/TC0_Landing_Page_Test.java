package military.drupal.ui.content_menu;

import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.ContentMenu;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.landing_page.AddLandingPage;
import drupal.ui.pages.add_content.landing_page.AddLandingPageModel;
import drupal.ui.pages.add_content.landing_page.ThumbnailImageModel;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static drupal.models.UserModel.loadUserModel;

public class TC0_Landing_Page_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateLandingPage() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(enabled = false, dataProvider = "testData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = @Attribute(key = "TestCaseId", value = "0"))
    @TestCaseId("0")
    public void createLandingPage(List<ISectionDataModel> landingPageData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over Add Content");
        logStep("Click on Landing Page");
        AddLandingPage addLandingPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.LANDING_PAGE), AddLandingPage.class);

        logStep("Create Landing page");
        logStep("Validate Landing Page");
        AddLandingPageModel addLandingPageData = landingPageData.stream()
                                                                .filter(d -> d instanceof AddLandingPageModel)
                                                                .map(c -> (AddLandingPageModel) c)
                                                                .findFirst()
                                                                .get();
        addLandingPage.fillIn(landingPageData)
                      .clickSaveButton()
                      .validateLandingPage(addLandingPageData);

    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
                {
                        List.of(
                                AddLandingPageModel.getCTALandingPageData(),
                                new ThumbnailImageModel("Test Image")
                        )
                }
        };
    }

}
