package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.ContentMenu;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceBlockModel;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListViewModel;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceSlideshowModel;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceVideoModel;
import drupal.ui.pages.add_content.landing_page.AddLandingPage;
import drupal.ui.pages.add_content.landing_page.AddLandingPageModel;
import drupal.ui.pages.add_content.landing_page.ResultLandingPage;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static common.CommonMethods.timeStampFormat;
import static drupal.enums.content.FlexibleContentSpacePosition.ABOVE;
import static drupal.enums.content.FlexibleContentSpacePosition.BELOW;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.models.UserModel.loadUserModel;
import static drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListViewModel.DisplayMode.THREE_COLUMN_THUMBNAIL_LIST_WITHOUT_TEASERS;
import static drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListViewModel.DisplayMode.THREE_FEATURES_ADVANCED_WITH_CONTENT;
import static drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListViewModel.ListOrder.NONE;
import static drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceListViewModel.ListType.CURATED_LIST;
import static drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceSlideshowModel.getLandingPageWithComponentsData;
import static java.lang.String.format;

public class TC250023_Create_a_Landing_Page_with_Components_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateLandingPageWithComponents() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "testData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "250023")})
    public void createLandingPageWithComponents(List<ISectionDataModel> landingPageData) {

        logStep("1. Login to Drupal");
        logStep("2. Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("3. Hover over Content Tab");
        logStep("4. Hover over Add Content");
        logStep("5. Select Landing Page");
        AddLandingPage addLandingPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.LANDING_PAGE), AddLandingPage.class);

        logStep("6. Enter test name in the Title field.");
        logStep("Example: Component Landing Page YYYYMMDD Test Component Landing Page 20210701");
        logStep("7. Click Hide Breadcrumb");
        logStep("8. Click 'Attach an Image' in the Thumbnail Images field");
        logStep("9. Click the Upload Image hyperlink");
        logStep("10. Upload an image (attached image link in comments)");
        logStep("the image should display as a thumbnail in the field once uploaded");
        logStep("11. In the Flexible Content Space (Above Body), click Add List View");
        logStep("A new List view component will display");
        logStep("12. LISTVIEW #1  Add Test Content to the Listview");
        logStep("Test Content: Title -Test Top Stories 3 Features Advanced. List Type - Curated List");
        logStep("13. Click the Down arrow and select Add Block  -> A new Block component will display");
        logStep("14. In the Block field, select AppNexus");
        logStep("15. Uncheck 'Display Title' and select Mobile Middle - 320x50, 300x250 in the Position Name ad");
        logStep("16. Click the Down arrow and select Add List View -> A new List view component will display");
        logStep("17. LISTVIEW #2 Add Test Content to the Listview");
        logStep("18. Add Test Content to the Blurb (Summary) field");
        logStep("Test Content- Daily U.S. military news updates including military gear and equipment, breaking news, international news and more.");
        logStep("19. Add Test Content to the Body field");
        logStep("20. In the Flexible Content Space (Below Body), click the Down arrow and select Add Video");
        logStep("21. Click the Select Video button");
        logStep("22. Click the Existing Videos (Brightcove) hyperlink");
        logStep("23. Select any video in search and click on Select Video");
        logStep("24. Click the Down arrow and select Add Slideshow -> The Slideshow component will display");
        logStep("25. Click Add New Slideshow");
        logStep("26. Click Attach an image");
        logStep("27. Click the Upload Image hyperlink and the Select Files button");
        logStep("28. Upload images (attached images in Comments) and click Save Image");
        logStep("The Image upload popup will close and the images will display in the Images field.");
        logStep("29. Click Create Slideshow");
        logStep("30. Select Test Drupal 88 Sidebar in the Sidebar field");
        logStep("30. Select Test Drupal 88 Sidebar in the Sidebar field");
        logStep("31. Select Test Category  -> Test Category - Benefits");
        logStep("32. Click Save");
        logStep(format("Validation: Drupal Landing Page - Directory is created: %s/category-name/landing-page-name.html, %s/benefits/active-duty-benefits-YYYYMMDD", Configuration.baseUrl, Configuration.baseUrl));
        ResultLandingPage resultLandingPage = addLandingPage.fillIn(landingPageData)
                                                            .clickSaveButton()
                                                            .verifyLandingPageUrl(landingPageData.stream()
                                                                                                 .filter(d -> d instanceof AddLandingPageModel)
                                                                                                 .map(c -> (AddLandingPageModel) c)
                                                                                                 .findFirst()
                                                                                                 .get());

        logStep("Verify Edit functionality");
        AddLandingPageModel landingPageModel = landingPageData.stream()
                                                              .filter(d -> d instanceof AddLandingPageModel)
                                                              .map(c -> (AddLandingPageModel) c)
                                                              .findFirst()
                                                              .get();
        landingPageModel.setLandingPageTitle("Test Landing Page - Edit " + timeStampFormat(PATTERN));
        resultLandingPage.clickEditTab()
                         .editLandingPageSectionsData(List.of(landingPageModel))
                         .clickSaveButton(ResultLandingPage.class)
                         .validateLandingPage(landingPageModel);
    }


    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
                {
                        List.of(
                                new AddLandingPageModel(),
                                new FlexibleContentSpaceListViewModel(ABOVE, 0, "Test Top Stories 3 Features Advanced " + timeStampFormat(PATTERN), CURATED_LIST, THREE_FEATURES_ADVANCED_WITH_CONTENT, List.of("Homepage Carousel Curated List (415)"), null, null, "5", false, NONE),
                                new FlexibleContentSpaceBlockModel(ABOVE, 0, null, "AppNexus", "AppNexus", false, "Mobile Middle - 320x50, 300x250"),
                                new FlexibleContentSpaceListViewModel(ABOVE, 0, "Test More Military Headlines", CURATED_LIST, THREE_COLUMN_THUMBNAIL_LIST_WITHOUT_TEASERS, List.of("Homepage: Military Headlines (1147)"), null, null, "12", false, NONE),
                                new FlexibleContentSpaceVideoModel(BELOW, 0, "Add Video title" + timeStampFormat(PATTERN), null, false, false),
                                new FlexibleContentSpaceSlideshowModel(BELOW, 0, true, "Slideshow " + timeStampFormat(PATTERN), null, getLandingPageWithComponentsData(), false, null)
                        )
                }
        };
    }

}
