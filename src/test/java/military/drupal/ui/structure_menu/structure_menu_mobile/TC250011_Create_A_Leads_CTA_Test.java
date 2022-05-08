package military.drupal.ui.structure_menu.structure_menu_mobile;

import com.codeborne.selenide.Configuration;
import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import drupal.enums.content.AddContentMenu;
import drupal.enums.content.ComponentsMenu;
import drupal.enums.content.ContentMenu;
import drupal.enums.structure.StructureMenu;
import drupal.models.ISectionDataModel;
import drupal.models.UserModel;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.DrupalHomePage;
import drupal.ui.pages.DrupalLoginPage;
import drupal.ui.pages.add_content.flexible_content_space.FlexibleContentSpaceCTAModel;
import drupal.ui.pages.add_content.landing_page.AddLandingPage;
import drupal.ui.pages.add_content.landing_page.AddLandingPageModel;
import drupal.ui.pages.add_content.landing_page.ResultLandingPage;
import drupal.ui.pages.add_content.landing_page.ThumbnailImageModel;
import drupal.ui.pages.components.flexible_space.FlexibleSpaceBaseModel;
import drupal.ui.pages.components.flexible_space.FlexibleSpaceLandingPage;
import drupal.ui.pages.components.flexible_space.FlexibleSpacesPage;
import drupal.ui.pages.content.ContentPage;
import drupal.ui.pages.structure.eck_entity_types.ECKEntityTypesPage;
import drupal.ui.pages.structure.eck_entity_types.call_to_action.AddCallToActionContentPage;
import drupal.ui.pages.structure.eck_entity_types.call_to_action.AddCallToActionLeadFormPage;
import drupal.ui.pages.structure.eck_entity_types.call_to_action.CTABaseModel;
import drupal.ui.pages.structure.eck_entity_types.call_to_action.CTAButtonModel;
import drupal.ui.pages.structure.eck_entity_types.call_to_action.CTAImageModel;
import drupal.ui.pages.structure.eck_entity_types.call_to_action.CTALeadsFormModel;
import drupal.ui.pages.structure.eck_entity_types.call_to_action.ECKContentListPage;
import drupal.ui.pages.structure.eck_entity_types.call_to_action.EditCallToActionPage;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.drupal.ui.AdminUITestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static drupal.enums.content.FlexibleContentSpacePosition.BELOW;
import static drupal.enums.content.FlexibleContentSpacePosition.CONTENT;
import static drupal.models.UserModel.loadUserModel;

public class TC250011_Create_A_Leads_CTA_Test extends AdminUITestBase {

    private UserModel user;

    @BeforeMethod(alwaysRun = true, inheritGroups = false)
    public void preconditionCreateALeadsCTA() {
        user = loadUserModel("MILITARY_TEST_QA_USER");
    }

    @Test(dataProvider = "callTOActionData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = {@Attribute(key = "TestCaseId", value = "250011")})
    @TestCaseId("250011")
    public void createALeadsCTA(List<ISectionDataModel> leadFormData, List<ISectionDataModel> landingPageData) {

        logStep("Login to Drupal");
        logStep("Enter Username and Password");
        DrupalHomePage homePage = new DrupalLoginPage().militaryLogin(user);

        logStep("Hover the Structure Tab");
        logStep("Select ECK Entity Types");
        ECKEntityTypesPage eckEntityTypesPage = homePage.navigateTo(List.of(AdministrationToolbar.TrayItem.STRUCTURE, StructureMenu.ECK_ENTITY_TYPES), ECKEntityTypesPage.class);

        logStep("Find Call to Action and click Add Content in the Operations column");
        AddCallToActionContentPage addCallToActionContentPage = eckEntityTypesPage.clickEntityType(ECKEntityTypesPage.ECKEntityType.CALL_TO_ACTION, ECKEntityTypesPage.ECKEntityOperationType.ADD_CONTENT, AddCallToActionContentPage.class);

        logStep("Select Call to Action - Lead Form");
        AddCallToActionLeadFormPage addCallToActionLeadFormPage = addCallToActionContentPage.clickCallToActionItem(AddCallToActionContentPage.CallToActionItem.CALL_TO_ACTION_LEAD_FORM, AddCallToActionLeadFormPage.class);

        logStep("Enter test name in the Internal Title field");
        logStep("Default Leads Product field is Auto");
        logStep("Enter test content in the Title field");
        logStep("Type Test content in the Blurb field");
        logStep("Type Test content in Button Label");
        logStep("Click Attach an Image in the Background image field under the Images section");
        logStep("Select the 'Existing Image' link");
        logStep("Type 'Lead Form CTA' in the Media Name field and click SEARCH");
        logStep("Select the non-variant image and click SELECT IMAGES");

        logStep("Click Attach an Image in the Variant image field under the Images section");
        logStep("Select the 'Existing Image' link");
        logStep("Type 'Lead Form CTA' in the Media Name field and click SEARCH");
        logStep("Select the variant image and click SELECT IMAGES");
        logStep("Add Test Content in the C Var field");
        logStep("Click Save");
        CTABaseModel ctaBaseData = leadFormData.stream()
                                               .filter(d -> d instanceof CTABaseModel)
                                               .map(c -> (CTABaseModel) c)
                                               .findFirst()
                                               .get();
        CTAButtonModel ctaButtonData = leadFormData.stream()
                                                   .filter(d -> d instanceof CTAButtonModel)
                                                   .map(c -> (CTAButtonModel) c)
                                                   .findFirst()
                                                   .get();
        addCallToActionLeadFormPage.fillIn(leadFormData)
                                   .verifyAddedCTA(ctaBaseData, ctaButtonData);

        logStep("Validation: Go to the CTA Content List and confirm the new CTA is displaying at the top of the list");
        ECKContentListPage eckContentListPage = open(Configuration.baseUrl + "/admin/structure/eck/entity/call_to_action", ECKContentListPage.class)
                .verifyAddedCTA(ctaBaseData.getTitle());

        logStep("We need to confirm the CTA by adding it to a landing page and a sidebar");
        logStep("Add the CTA to the landing page");
        logStep("Pre-condition: create a new Flexible Content Space to add CTA to sidebar");
        List<ISectionDataModel> flexibleSpaceData = List.of(new FlexibleContentSpaceCTAModel(CONTENT, 0, ctaBaseData.getTitle()), new FlexibleSpaceBaseModel());
        FlexibleSpacesPage flexibleSpacesPage = eckContentListPage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.COMPONENTS, ComponentsMenu.FLEXIBLE_SPACE), FlexibleSpacesPage.class);
        FlexibleSpaceLandingPage flexibleSpaceLandingPage = flexibleSpacesPage.clickFlexibleSpaceTab()
                                                                              .addFlexibleSpace(flexibleSpaceData)
                                                                              .saveFlexibleSpaceLanding()
                                                                              .verifyDrupalFlexibleSpaceCreated()
                                                                              .verifyAddedNormalCTA(ctaBaseData);

        logStep("Pre-condition to create a Landing page");
        AddLandingPage addLandingPage = flexibleSpaceLandingPage.navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT, ContentMenu.ADD_CONTENT, AddContentMenu.LANDING_PAGE), AddLandingPage.class);
        AddLandingPageModel addLandingPageData = landingPageData.stream()
                                                                .filter(d -> d instanceof AddLandingPageModel)
                                                                .map(c -> (AddLandingPageModel) c)
                                                                .findFirst()
                                                                .get();

        ResultLandingPage resultLandingPage = addLandingPage.fillIn(landingPageData)
                                                            .clickSaveButton()
                                                            .validateLandingPage(addLandingPageData);

        logStep("Edit Landing Page to add CTA on landing page and on sidebar");
        logStep("At the bottom of the sidebar, click Add CTA");
        logStep("Press Save");
        logStep("Validation: Go to the OAS Homepage page and scroll down to the bottom to confirm the UI for the both the left column and the right sidebar");
        logStep("Validation: We also need to confirm the mobile version of the CTAs look exactly like the sidebar UI.");
        FlexibleSpaceBaseModel flexibleSpaceBaseData = flexibleSpaceData.stream()
                                                                        .filter(d -> d instanceof FlexibleSpaceBaseModel)
                                                                        .map(c -> (FlexibleSpaceBaseModel) c)
                                                                        .findFirst()
                                                                        .get();
        addLandingPageData.setSideBars(List.of(flexibleSpaceBaseData.getTitle()));
        EditCallToActionPage editCallToActionPage = resultLandingPage.clickEditTab()
                                                                     .editLandingPageSectionsData(List.of(new FlexibleContentSpaceCTAModel(BELOW, 0, ctaBaseData.getTitle()), addLandingPageData))
                                                                     .clickSaveButton(ResultLandingPage.class)
                                                                     .verifyCTA(ctaBaseData, ctaButtonData)
                                                                     .verifyMobileViewCTA(ctaBaseData, ctaButtonData)
                                                                     .navigateTo(List.of(AdministrationToolbar.TrayItem.STRUCTURE, StructureMenu.ECK_ENTITY_TYPES), ECKEntityTypesPage.class)
                                                                     .clickEntityType(ECKEntityTypesPage.ECKEntityType.CALL_TO_ACTION, ECKEntityTypesPage.ECKEntityOperationType.CONTENT_LIST, ECKContentListPage.class)
                                                                     .clickEditButton(EditCallToActionPage.class, -1, ctaBaseData.getTitle());

        logStep("Validate Edit CTA functionality");
        ctaBaseData.setTitle("Edit " + ctaBaseData.getTitle());
        ctaBaseData.setInternalTitle("Edit " + ctaBaseData.getInternalTitle());
        editCallToActionPage.fillIn(List.of(ctaBaseData))
                            .verifyAddedCTA(ctaBaseData.getTitle())
                            .navigateTo(List.of(AdministrationToolbar.TrayItem.CONTENT), ContentPage.class)
                            .clickContentLink(ResultLandingPage.class, addLandingPageData.getLandingPageTitle())
                            .verifyCTA(ctaBaseData, ctaButtonData)
                            .verifyMobileViewCTA(ctaBaseData, ctaButtonData);

    }

    @DataProvider
    public Object[][] callTOActionData() {
        return new Object[][]{
                {
                        List.of(
                                CTABaseModel.getLeadFormBaseData(),
                                CTAButtonModel.getLeadFormButtonData(),
                                new CTAImageModel(),
                                new CTALeadsFormModel()
                        ),
                        List.of(
                                AddLandingPageModel.getCTALandingPageData(),
                                new ThumbnailImageModel("Test Image")
                        )
                }
        };
    }

}