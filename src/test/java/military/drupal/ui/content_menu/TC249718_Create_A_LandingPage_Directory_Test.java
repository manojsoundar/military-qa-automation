package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.Category;
import drupal.enums.content.ContentMenu;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListOfLinksModel;
import drupal.ui.pages.add_content.landing_page.AddLandingPage;
import drupal.ui.pages.add_content.landing_page.AddLandingPageModel;
import drupal.ui.pages.add_content.landing_page.ResultLandingPage;
import lombok.extern.log4j.Log4j2;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static common.CommonMethods.timeStampFormat;
import static drupal.enums.content.FlexibleContentSpacePosition.ABOVE;
import static drupal.models.TimeStampPattern.DATE_WITH_HYPHEN_PATTERN;
import static drupal.models.UserModel.loadUserModel;
import static drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListOfLinksModel.getBlockMilitaryPay;
import static drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListOfLinksModel.getBlockTricare;

@Log4j2
public class TC249718_Create_A_LandingPage_Directory_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionLandingPageWithComponents() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "testData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249718")})
    public void landingPageWithDirectory(List<ISectionDataModel> landingPageData, List<ISectionDataModel> editLandingPageData) {
        logStep("1. Login to Drupal");
        logStep("2. Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("3. Hover over Content Tab");
        logStep("4. Hover over Add Content");
        logStep("5. Select Landing Page");
        AddLandingPage addLandingPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.LANDING_PAGE), AddLandingPage.class);
        logStep("Enter test name in the Title field");
        logStep("In the Flexible Content Space (Above Body) section, click the Down arrow and select Add List of Links");
        logStep("In the Lists section, type Test Content in the URL & Link Text fields (PARENT LINK Section)");
        logStep("Click the Down arrow and select Add List of Links");
        logStep("In the Lists section, type Test Content in the URL & Link Text fields (PARENT LINK Section)");
        logStep("In the Links section (below the PARENT LINK section), type Test Content in the URL & Link Text fields ");
        logStep("In the Links section (below the PARENT LINK section), type Test Content in the URL & Link Text fields ");
        logStep("Click the Down arrow and select Add List of Links");
        logStep("In the Links section (below the PARENT LINK section), type Test Content in the URL & Link Text fields");
        logStep("In the Links section (below the PARENT LINK section), type Test Content in the URL & Link Text fields");
        logStep("In the Links section (below the PARENT LINK section), type Test Content in the URL & Link Text fields");
        logStep("In the Links section (below the PARENT LINK section), type Test Content in the URL & Link Text fields");
        logStep("In the Links section (below the PARENT LINK section), type Test Content in the URL & Link Text fields");
        logStep("Click the Down arrow and select Add List of Links");
        logStep("In the Links section (below the PARENT LINK section), type Test Content in the URL & Link Text fields");
        logStep("In the Links section (below the PARENT LINK section), type Test Content in the URL & Link Text fields");
        logStep("In the Links section (below the PARENT LINK section), type Test Content in the URL & Link Text fields");
        logStep("In the Links section (below the PARENT LINK section), type Test Content in the URL & Link Text fields");
        logStep("In the Links section (below the PARENT LINK section), type Test Content in the URL & Link Text fields");
        logStep("In the Links section (below the PARENT LINK section), type Test Content in the URL & Link Text fields");
        logStep("In the Links section (below the PARENT LINK section), type Test Content in the URL & Link Text fields");
        logStep("In the Flexible content space (below body), click the Down arrow and select Block");
        logStep("Enter test data");
        logStep("Select Test content in the Sidebar field");
        logStep("Benefits > Sidebar");
        logStep("Select Test Content in the Category field");
        logStep("Benefits > Active Duty Benefits");
        logStep("CLick Save");
        logStep("Validate Landing Page");
        logStep("Validate: Drupal Regular Flexible space is created: " + Configuration.baseUrl + "category-name/landing-page/####");
        ResultLandingPage resultLandingPage = addLandingPage.fillIn(landingPageData)
                                                            .clickSaveButton()
                                                            .verifyLandingPageUrl(landingPageData.stream()
                                                                                                 .filter(d -> d instanceof AddLandingPageModel)
                                                                                                 .map(c -> (AddLandingPageModel) c)
                                                                                                 .findFirst()
                                                                                                 .get());
        FlexibleContentSpaceListOfLinksModel flexibleSpaceLinkListModel = editLandingPageData.stream()
                                                                                             .filter(d -> d instanceof FlexibleContentSpaceListOfLinksModel)
                                                                                             .map(c -> (FlexibleContentSpaceListOfLinksModel) c)
                                                                                             .findFirst()
                                                                                             .get();

        resultLandingPage.clickEditTab()
                         .editLandingPageSectionsData(editLandingPageData)
                         .clickSaveButton(ResultLandingPage.class)
                         .verifyListOfLinkFlexibleSpace(flexibleSpaceLinkListModel);

    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
                {
                        List.of(
                                new AddLandingPageModel("Test Directory Landing " + timeStampFormat(DATE_WITH_HYPHEN_PATTERN), true, null, null, List.of("Benefits"), null, List.of(Category.BENEFITS), null),
                                new FlexibleContentSpaceListOfLinksModel(ABOVE, 0, "Test Content", "Memorial Benefits (221)", "Test memorial Benefits", null),
                                new FlexibleContentSpaceListOfLinksModel(ABOVE, 0, "Test Content", "Military Legal Matters (1001)", "Test military legal matters", Map.of("Legal Assistance & JAG (1491)", "Test legal assistance/JAG")),
                                new FlexibleContentSpaceListOfLinksModel(ABOVE, 0, "Test Content", null, null, getBlockMilitaryPay()),
                                new FlexibleContentSpaceListOfLinksModel(ABOVE, 0, "Test Content", null, null, getBlockTricare())
                        ),
                        List.of(new FlexibleContentSpaceListOfLinksModel(ABOVE, 0, "Edited Test Content 1", "Memorial Benefits (221)", "Test memorial Benefits", null),
                                new FlexibleContentSpaceListOfLinksModel(ABOVE, 1, "Edited Test Content 2", "Military Legal Matters (1001)", "Test military legal matters", Map.of("Legal Assistance & JAG (1491)", "Test legal assistance/JAG")))
                }
        };
    }
}
