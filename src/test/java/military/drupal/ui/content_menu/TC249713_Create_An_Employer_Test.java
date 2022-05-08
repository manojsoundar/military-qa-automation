package military.drupal.ui.content_menu;

import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.ContentMenu;
import drupal.enums.content.FlexibleContentSpacePosition;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.employer.CreateEmployerPage;
import drupal.ui.pages.add_content.employer.EmployerDataModel;
import drupal.ui.pages.add_content.employer.EmployerHeaderModel;
import drupal.ui.pages.add_content.employer.EmployerLogoImageModel;
import drupal.ui.pages.add_content.employer.EmployerPage;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceFullTextAreaModel;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceSocialLinkModel;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceVideoModel;
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
public class TC249713_Create_An_Employer_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateAnEmployer() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "addEmployer")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249713")})
    @TestCaseId("249713")

    public void createAnEmployer(List<ISectionDataModel> employerDataModel) {
        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over Add content");
        logStep("Click on Employer");
        CreateEmployerPage createEmployerPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.EMPLOYER), CreateEmployerPage.class);

        logStep("Enter test name in the Title field -> Example: Test Employer " + timeStampFormat("yyyyMMdd"));
        logStep("Test Image Select Existing Image Type VTP header Click on Select Image");
        logStep("Enter Title bar Back round color and Title bar text color");
        logStep("Fill CTA Box section values");
        logStep("Test Image Select Existing Image Type VTP logo Click on Select Image");
        logStep("Fill Full text area values in Flexible content space(below body) section");
        logStep("Test Video Select Existing Video(Brightcove) Click on Select any available video");
        logStep("Fill Full text area values in Flexible content space(sidebar) section");
        logStep("Fill Add Social Link section values");
        logStep("Validate :Newly created employer page is loaded");
        EmployerDataModel employerData = employerDataModel.stream()
                                                          .filter(d -> d instanceof EmployerDataModel)
                                                          .map(c -> (EmployerDataModel) c)
                                                          .findFirst()
                                                          .get();
        logStep("Flexible space content data");
        EmployerPage employerPage = createEmployerPage.createEmployer(employerDataModel)
                                                      .verifyCreatedEmployer(employerData);
        logStep("Validation : Employer is created");
        logStep("Created Employer page, click the Edit link and return to the Edit Employer Page");
        logStep("Validate Name input field is not empty and click Save");
        logStep("Validate: Edited Drupal Employer page");
        String updateTitle = "Edited Test Employer " + timeStampFormat(PATTERN);
        employerData.setName(updateTitle);
        employerData.setBlurbText(null);
        employerData.setBodyText(null);
        employerPage.clickEditTab()
                    .editEmployerPage(List.of(employerData))
                    .verifyUpdatedMessage(updateTitle);
    }

    @DataProvider
    public Object[][] addEmployer() {
        return new Object[][]{
                {
                        List.of(
                                new FlexibleContentSpaceFullTextAreaModel(FlexibleContentSpacePosition.BELOW, 0, "About Us", "Founded by a WW I Veteran, Dr. Homer Stryker, Stryker is one of the world's leading medical technology companies and is dedicated to helping healthcare professionals perform their jobs more efficiently while enhancing patient care. At the heart of what we do and believe is making healthcare better. We do this by collaborating with our customers to develop innovative products and services that ultimately improve the lives of patients."),
                                new FlexibleContentSpaceVideoModel(FlexibleContentSpacePosition.BELOW, 0, "Fighter Jets Test Mobile Aircraft Arresting System in Undisclosed Location", "", false, false),
                                new FlexibleContentSpaceFullTextAreaModel(FlexibleContentSpacePosition.BELOW, 0, "Test Employer - Veteran Commitment", "Press 'Source' first include HTML Code\n" +
                                        "<drupal-entity data-align=\"left\" data-embed-button=\"image\" data-entity-embed-display=\"view_mode:media.full\" data-entity-embed-display-settings=\"{&quot;link_url&quot;:&quot;&quot;}\" data-entity-type=\"media\" data-entity-uuid=\"626139a1-a84e-48dd-a381-1372301a9b5e\"></drupal-entity>\n" +
                                        "<p><a href=\"https://secure.adnxs.com/clktrb?id=744967&amp;t=2\" target=\"_blank\">Stryker’s Veterans Association (SVA)</a> strives to make Stryker a preferred destination for top veteran talent and drive military employee engagement. Our initiatives have always focused on teamwork, reflecting the team focus found within the military. SVA activities also emphasize helping others, which ties directly to Stryker’s mission, “Together with our customers, we are driven to make healthcare better.”</p>\n" +
                                        "<p>We understand the importance of having a strong employee connection with team and community. We also understand how a veteran workforce can positively impact an employer and the region. Click <a href=\"https://secure.adnxs.com/clktrb?id=744968&amp;t=2\" target=\"_blank\">here</a> for more information about Stryker’s inclusive culture.</p>\n"),
                                new FlexibleContentSpaceFullTextAreaModel(FlexibleContentSpacePosition.SIDEBAR, 0, "Get Social", null),
                                new FlexibleContentSpaceSocialLinkModel(FlexibleContentSpacePosition.SIDEBAR, 0, "Generic icon People", "https://www.military.com/benefits", "Test Employer Blog"),
                                new FlexibleContentSpaceSocialLinkModel(FlexibleContentSpacePosition.SIDEBAR, 0, "Generic icon Megaphone", "https://www.military.com/veteran-jobs", "Test Employer Careers"),
                                new FlexibleContentSpaceSocialLinkModel(FlexibleContentSpacePosition.SIDEBAR, 0, "Facebook", "https://www.facebook.com", "Test Employer Social Site"),
                                new FlexibleContentSpaceSocialLinkModel(FlexibleContentSpacePosition.SIDEBAR, 0, "YouTube", "https://www.youtube.com", "Test Employer Videos"),
                                new EmployerDataModel(), new EmployerLogoImageModel(), new EmployerHeaderModel()
                        )
                }
        };
    }

}
