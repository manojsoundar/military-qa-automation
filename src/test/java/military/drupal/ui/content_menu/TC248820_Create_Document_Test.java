package military.drupal.ui.content_menu;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.ContentMenu;
import drupal.enums.content.MediaMenu;
import drupal.models.ISectionDataModel;
import drupal.models.MediaModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.media.AddDocumentPage;
import drupal.ui.pages.media.AddMediaPage;
import drupal.ui.pages.media.EditMediaPage;
import drupal.ui.pages.media.MediaUploadModel;
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
public class TC248820_Create_Document_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateDocument() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "documentData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "248820")})
    @TestCaseId("248820")
    public void createDocument(List<ISectionDataModel> documentData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Content Tab");
        logStep("Hover the Media Tab");
        logStep("Click on 'Add Media'");
        AddMediaPage addMediaPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.MEDIA, MediaMenu.ADD_MEDIA), AddMediaPage.class);

        logStep("Click Document");
        AddDocumentPage addDocumentPage = addMediaPage.clickMediaItem(AddMediaPage.MediaItem.DOCUMENT, AddDocumentPage.class);

        logStep("Enter the test name in Name field (ex: Test Document File[time/date stamp]");
        logStep("Click Choose File and locate test document file");
        logStep("Click save");
        logStep("Validate: Drupal Document Link is created and displayed at - " + Configuration.baseUrl + "/admin/content/media ");
        logStep("Validate: Edit media functionality");
        MediaUploadModel documentUploadData = documentData.stream()
                                                          .filter(d -> d instanceof MediaUploadModel)
                                                          .map(c -> (MediaUploadModel) c)
                                                          .findFirst()
                                                          .get();
        EditMediaPage editMediaPage = addDocumentPage.fillIn(documentData)
                                                     .clickSaveButton()
                                                     .verifyAddedMedia(documentUploadData.getName())
                                                     .fillSearchCriteriaAndFilterMedia(MediaModel.getDocumentData(), documentUploadData.getName())
                                                     .clickMediaLink(documentUploadData.getName())
                                                     .verifyMediaLandingPage(documentUploadData.getName())
                                                     .clickEditTab();

        documentUploadData.setName("Test Doc File " + timeStampFormat(PATTERN));
        documentUploadData.setFilePath(null);
        editMediaPage.fillIn(List.of(documentUploadData))
                     .clickSaveButton()
                     .verifyMediaUpdatedMessage(documentUploadData.getName());

    }

    @DataProvider
    public Object[][] documentData() {
        return new Object[][]{
                {
                        List.of(
                                MediaUploadModel.getDocumentData()
                        )
                }
        };
    }

}
