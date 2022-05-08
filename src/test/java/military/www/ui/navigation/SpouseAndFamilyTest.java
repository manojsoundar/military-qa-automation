package military.www.ui.navigation;

import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import mgs.qa.base.page.MasterPage;
import mgs.qa.testng.Priority;
import mgs.qa.testng.PriorityLevel;
import military.www.ui.MilitaryUITestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import www.enums.MainMenu;
import www.enums.MilitaryLifeMenu;
import www.enums.SpouseFamilyMenu;
import www.enums.VideosMenu;
import www.models.NavigationDataModel;
import www.ui.pages.MilitaryLandingPage;
import www.ui.pages.militaryLife.BaseGuidesPage;
import www.ui.pages.spouseFamily.MilitaryAndFamilyLifePage;
import www.ui.pages.spouseFamily.MilitaryDeploymentPage;
import www.ui.pages.spouseFamily.MilitaryMovesPage;
import www.ui.pages.spouseFamily.MilitarySpouseJobsPage;
import www.ui.pages.spouseFamily.RelationshipsPage;
import www.ui.pages.spouseFamily.SpouseAndFamilyBenefitsPage;
import www.ui.pages.videos.EditorsPicsPage;

public class SpouseAndFamilyTest extends MilitaryUITestBase {

    @Test(dataProvider = "spouseAndFamilyNavigationData")
    public <P extends MasterPage> void spouseAndFamilyMenuValidNavigationTest(NavigationDataModel<P> navigation) {
        logStep("Step 1: Goto Military Page");
        logStep("Step 2: Validate Main Menu navigation functionality");
        logStep("Step 3: Validate Sub Menu navigation functionality");

        new MilitaryLandingPage().navigateTo(navigation);

    }

    @Test(dataProvider = "spouseAndFamilyNavigationNegativeData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = @Attribute(key = "TestCaseId", value = "0"))
    @TestCaseId("0")
    public <P extends MasterPage> void spouseAndFamilyMenuInvalidNavigationTest(NavigationDataModel<P> navigation) {

        logStep("Step 1: Goto Military Page");
        logStep("Step 2: Validate Main Menu navigation functionality");
        logStep("Step 3: Validate Sub Menu navigation functionality");

        new MilitaryLandingPage().navigateTo(navigation);

    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @DataProvider
    public Object[] spouseAndFamilyNavigationData() {
        return new NavigationDataModel[]{

                new NavigationDataModel(MainMenu.SPOUSE_AND_FAMILY, null, MilitarySpouseJobsPage.class),
                new NavigationDataModel(MainMenu.SPOUSE_AND_FAMILY, SpouseFamilyMenu.MILITARY_SPOUSE_JOBS, MilitarySpouseJobsPage.class),
                new NavigationDataModel(MainMenu.SPOUSE_AND_FAMILY, SpouseFamilyMenu.MILITARY_FAMILY_LIFE, MilitaryAndFamilyLifePage.class),
                new NavigationDataModel(MainMenu.SPOUSE_AND_FAMILY, SpouseFamilyMenu.RELATIONSHIPS, RelationshipsPage.class),
                new NavigationDataModel(MainMenu.SPOUSE_AND_FAMILY, SpouseFamilyMenu.SPOUSE_FAMILY_BENEFITS, SpouseAndFamilyBenefitsPage.class),
                new NavigationDataModel(MainMenu.SPOUSE_AND_FAMILY, SpouseFamilyMenu.MILITARY_DEPLOYMENT, MilitaryDeploymentPage.class),
                new NavigationDataModel(MainMenu.SPOUSE_AND_FAMILY, SpouseFamilyMenu.MILITARY_MOVES, MilitaryMovesPage.class)

        };
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @DataProvider
    public Object[] spouseAndFamilyNavigationNegativeData() {
        return new NavigationDataModel[]{

                new NavigationDataModel(MainMenu.SPOUSE_AND_FAMILY, VideosMenu.EDITORS_PICKS, EditorsPicsPage.class),
                new NavigationDataModel(MainMenu.SPOUSE_AND_FAMILY, MilitaryLifeMenu.BASE_GUIDES, BaseGuidesPage.class),

        };
    }

}
