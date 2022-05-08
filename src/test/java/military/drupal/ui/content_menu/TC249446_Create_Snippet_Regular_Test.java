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
import drupal.ui.pages.components.snippet.EditImageModel;
import drupal.ui.pages.components.snippet.SnippetImageModel;
import drupal.ui.pages.components.snippet.SnippetModel;
import drupal.ui.pages.components.snippet.SnippetPage;
import drupal.ui.pages.components.snippet.SnippetRegularPage;
import drupal.ui.pages.components.snippet.SnippetsPage;
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

public class TC249446_Create_Snippet_Regular_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateSnippetRegular() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "test")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "249446")})
    @TestCaseId("249446")
    public void createSnippetRegular(List<ISectionDataModel> data) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover over Components");
        logStep("Click on 'SNIPPETS'");
        SnippetsPage snippetsPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.COMPONENTS, ComponentsMenu.SNIPPETS), SnippetsPage.class);

        logStep("Click '+Add Snippet' button");
        logStep("Select Snippet -> Contains a brief snippet and link to another content, usually off-site links.");
        SnippetRegularPage addSnippetRegularPage = snippetsPage.clickAddSnippetButton()
                                                               .clickOnSnippetLink();

        logStep("Enter test name in the Title field -> Example: Test Regular Snippet 043003192012");
        logStep("Enter test text in the Blurb (for summary) field -> Test Text Example - This is a text blurb for the creation of a new test snippet (regular type).");
        logStep("Add an https URL in the Link field -> Example URL - https://www.military.com");
        logStep("Test Image Select Existing Image Type flag-salute-silhouette Click on Select Image");
        logStep("Click save");
        logStep("11. Upload an image in the Image field");
        logStep("Validate: Drupal Regular Snippet is created: " + Configuration.baseUrl + "/admin/structure/eck/entity/snippet/#####");
        SnippetPage snippetPage = addSnippetRegularPage.fillRegularSnippet(data)
                                                       .clickSaveButton()
                                                       .verifyDrupalRegularSnippetCreated();

        logStep("Edit Snippet functionality");
        SnippetModel snippetModel = data.stream()
                                        .filter(d -> d instanceof SnippetModel)
                                        .map(c -> (SnippetModel) c)
                                        .findFirst()
                                        .get();
        snippetModel.setTitle("Edit title " + timeStampFormat(PATTERN));
        snippetModel.setLink("https://www.edit.com");
        snippetModel.setBlurb("Edit blurb");
        snippetModel.setDisplayTitle(null);

        snippetPage.verifyDrupalRegularSnippetCreated()
                   .clickEditTab()
                   .editAllSnippet(List.of(snippetModel, EditImageModel.getEditImageData()))
                   .clickSaveButton()
                   .verifyEditSnippet(snippetModel);
    }

    @DataProvider
    public Object[][] test() {
        return new Object[][]{
                {
                        List.of(
                                SnippetModel.addRegularSnippet(),
                                SnippetImageModel.getSnippetImageData()
                        )
                }
        };
    }

}
