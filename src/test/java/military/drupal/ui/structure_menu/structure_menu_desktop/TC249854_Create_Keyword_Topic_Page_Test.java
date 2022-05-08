package military.drupal.ui.structure_menu.structure_menu_desktop;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.structure.StructureMenu;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.structure.taxonomy.TaxonomyPage;
import drupal.ui.pages.structure.taxonomy.keywords.EditKeywordPage;
import drupal.ui.pages.structure.taxonomy.keywords.KeywordsAddTermPage;
import drupal.ui.pages.structure.taxonomy.keywords.KeywordsDataModel;
import drupal.ui.pages.structure.taxonomy.keywords.KeywordsLandingPage;
import drupal.ui.pages.structure.taxonomy.keywords.KeywordsPage;
import drupal.ui.pages.structure.taxonomy.keywords.TrackingPixelModel;
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

public class TC249854_Create_Keyword_Topic_Page_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateKeywordTopicPage() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "keywordAddTerm")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249854")})
    @TestCaseId("249854")
    public void createKeywordTopicPage(List<ISectionDataModel> taxonomyKeywordData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Structure Tab");
        logStep("Select Taxonomy");
        TaxonomyPage taxonomyPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.STRUCTURE, StructureMenu.TAXONOMY), TaxonomyPage.class);

        logStep("Find Keywords and click List Terms under the Operations column");
        KeywordsPage keywordsPage = taxonomyPage.clickTaxonomyType(TaxonomyPage.TaxonomyVocabularyType.KEYWORDS, TaxonomyPage.TaxonomyOperationType.LIST_TERMS_LINK, KeywordsPage.class);

        logStep("Click the Add Term radio button");
        KeywordsAddTermPage keywordsAddTermPage = keywordsPage.clickAddTerm();

        logStep("Type Test Content in the Name field");
        logStep("Type Test Content in the Description field");
        logStep("Add Test URL in the URL Alias field");
        logStep("Add Test URL in the URL field for the Tracking-Pixels section");
        logStep("Click Save");
        KeywordsDataModel keywordData = taxonomyKeywordData.stream()
                                                           .filter(d -> d instanceof KeywordsDataModel)
                                                           .map(c -> (KeywordsDataModel) c)
                                                           .findFirst()
                                                           .get();
        KeywordsLandingPage keywordsLandingPage = keywordsAddTermPage.fillIn(taxonomyKeywordData)
                                                                     .verifyStatusMessage(keywordData)
                                                                     .navigateToNewTermCreated(keywordData);

        logStep("Validation: Confirm the topic page is working and displaying the description");
        logStep("Confirm the pixel on the topic page by right-clicking on the page and selecting Page Source");
        logStep("Search (CTRL+F) for 'Pixel' URL to find the url from Step 10");
        logStep("Validation : Drupal Taxonomy URL " + Configuration.baseUrl + "/taxonomy/term/#####");
        TrackingPixelModel trackingPixelData = taxonomyKeywordData.stream()
                                                                  .filter(d -> d instanceof TrackingPixelModel)
                                                                  .map(c -> (TrackingPixelModel) c)
                                                                  .findFirst()
                                                                  .get();

        EditKeywordPage editKeywordPage = keywordsLandingPage.verifyKeywordLandingPage(keywordData)
                                                             .verifyPageSourceData(trackingPixelData)
                                                             .clickEditTab();

        logStep("Validate: Edit functionality");
        String newName = "Test Topic Page Keyword New" + timeStampFormat(PATTERN);
        keywordData.setName(newName);
        editKeywordPage.editAll(List.of(keywordData))
                       .verifyUpdatedMessage(newName);

    }

    @DataProvider
    public Object[][] keywordAddTerm() {
        return new Object[][]{
                {
                        List.of(
                                new KeywordsDataModel(),
                                new TrackingPixelModel()
                        )
                }
        };
    }

}