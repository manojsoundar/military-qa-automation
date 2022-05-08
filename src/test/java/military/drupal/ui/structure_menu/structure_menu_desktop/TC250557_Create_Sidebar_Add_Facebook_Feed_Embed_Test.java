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
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceFacebookFeedEmbedModel;
import drupal.ui.pages.components.flexible_space.AddFlexibleSpaceContentPage;
import drupal.ui.pages.components.flexible_space.FlexibleSpaceBaseModel;
import drupal.ui.pages.components.flexible_space.FlexibleSpacesPage;
import drupal.ui.pages.structure.eck_entity_types.ECKEntityTypesPage;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static common.CommonMethods.timeStampFormat;
import static drupal.enums.content.FlexibleContentSpacePosition.CONTENT;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.models.UserModel.loadUserModel;

public class TC250557_Create_Sidebar_Add_Facebook_Feed_Embed_Test extends AdminUITestBase {
    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateSidebarFacebookEmbed() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "flexibleSpaceData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "250557")})
    @TestCaseId("250557")
    public void createSidebarWithFacebookFeedEmbed(List<ISectionDataModel> listViewItemData, List<ISectionDataModel> editFbItemData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Structure Tab");
        logStep("Select ECK Entity Types");
        ECKEntityTypesPage eckEntityTypesPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.STRUCTURE, StructureMenu.ECK_ENTITY_TYPES), ECKEntityTypesPage.class);

        logStep("Go to FLEXIBLE SPACE and select 'Content List' Button under the OPERATIONS column");
        FlexibleSpacesPage flexibleSpacesPage = eckEntityTypesPage.clickEntityType(ECKEntityTypesPage.ECKEntityType.FLEXIBLE_SPACE, ECKEntityTypesPage.ECKEntityOperationType.CONTENT_LIST, FlexibleSpacesPage.class);

        logStep("Click Add Flexible space button and navigate to 'Add Flexible space content' page");
        AddFlexibleSpaceContentPage addFlexibleSpaceContentPage = flexibleSpacesPage.clickFlexibleSpaceTab();

        logStep("Enter title input for 'Add Flexible space content' page");
        logStep("click 'Add Facebook Feed Embed'");
        logStep("Fill in field of 'Add facebook feed embed' component fields");
        logStep("click save");
        logStep("Validate: Drupal Regular Flexible space is created: " + Configuration.baseUrl + "/admin/structure/eck/entity/flexible_space/####");
        FlexibleContentSpaceFacebookFeedEmbedModel flexibleContentSpaceFacebookFeedEmbedModel = listViewItemData.stream()
                                                                                                                .filter(d -> d instanceof FlexibleContentSpaceFacebookFeedEmbedModel)
                                                                                                                .map(c -> (FlexibleContentSpaceFacebookFeedEmbedModel) c)
                                                                                                                .findFirst()
                                                                                                                .get();
        FlexibleContentSpaceFacebookFeedEmbedModel editFacebookFeedEmbedModel = editFbItemData.stream()
                                                                                              .filter(d -> d instanceof FlexibleContentSpaceFacebookFeedEmbedModel)
                                                                                              .map(c -> (FlexibleContentSpaceFacebookFeedEmbedModel) c)
                                                                                              .findFirst()
                                                                                              .get();
        addFlexibleSpaceContentPage.addFlexibleSpace(listViewItemData)
                                   .saveFlexibleSpaceLanding()
                                   .verifyFlexibleSpaceFacebookFeedEmbed(flexibleContentSpaceFacebookFeedEmbedModel)
                                   .clickEditTab()
                                   .editFlexibleSpaceData(editFbItemData)
                                   .saveFlexibleSpaceLanding()
                                   .verifyFlexibleSpaceFacebookFeedEmbed(editFacebookFeedEmbedModel);
    }

    @DataProvider
    public Object[][] flexibleSpaceData() {
        return new Object[][]{
                {List.of(new FlexibleContentSpaceFacebookFeedEmbedModel(CONTENT, 0, "https://www.facebook.com/test/123", "Test Content"),
                         new FlexibleSpaceBaseModel()),
                        List.of(new FlexibleContentSpaceFacebookFeedEmbedModel(CONTENT, 0, "https://www.facebook.com/test/123", "Edit Test Content"),
                                new FlexibleSpaceBaseModel("Edit Test Title " + timeStampFormat(PATTERN)))
                }
        };
    }
}
