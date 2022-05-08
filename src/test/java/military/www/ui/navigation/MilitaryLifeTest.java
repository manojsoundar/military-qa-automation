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
import www.enums.BenefitsMenu;
import www.enums.MainMenu;
import www.enums.MilitaryLifeMenu;
import www.enums.NewsMenu;
import www.models.NavigationDataModel;
import www.ui.pages.MilitaryLandingPage;
import www.ui.pages.benefits.RetirementPage;
import www.ui.pages.militaryLife.BaseGuidesPage;
import www.ui.pages.militaryLife.DeploymentPage;
import www.ui.pages.militaryLife.EquipmentGuidePage;
import www.ui.pages.militaryLife.FitnessPage;
import www.ui.pages.militaryLife.HomeOwnershipPage;
import www.ui.pages.militaryLife.JoinTheMilitaryPage;
import www.ui.pages.militaryLife.MilitaryLifeHomePage;
import www.ui.pages.militaryLife.MilitaryTriviaGamePage;
import www.ui.pages.militaryLife.MoneyPage;
import www.ui.pages.militaryLife.OffDutyPage;
import www.ui.pages.militaryLife.PcsRelocationPage;
import www.ui.pages.militaryLife.SpecialOperationsPage;
import www.ui.pages.news.CoastGuardPage;

public class MilitaryLifeTest extends MilitaryUITestBase {

    @Test(dataProvider = "militaryLifeNavigationData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = @Attribute(key = "TestCaseId", value = "0"))
    @TestCaseId("0")
    public <P extends MasterPage> void militaryLifeMenuValidNavigationTest(NavigationDataModel<P> navigation) {

        logStep("Step 1: Goto Military Page");
        logStep("Step 2: Validate Main Menu navigation functionality");
        logStep("Step 3: Validate Sub Menu navigation functionality");

        new MilitaryLandingPage().navigateTo(navigation);
    }

    @Test(dataProvider = "militaryLifeNavigationNegativeData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = @Attribute(key = "TestCaseId", value = "0"))
    @TestCaseId("0")
    public <P extends MasterPage> void militaryLifeMenuInvalidNavigationTest(NavigationDataModel<P> navigation) {

        logStep("Step 1: Goto Military Page");
        logStep("Step 2: Validate Main Menu navigation functionality");
        logStep("Step 3: Validate Sub Menu navigation functionality");

        new MilitaryLandingPage().navigateTo(navigation);

    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @DataProvider
    public Object[] militaryLifeNavigationData() {
        return new NavigationDataModel[]{

                new NavigationDataModel(MainMenu.MILITARY_LIFE, null, MilitaryLifeHomePage.class),
                new NavigationDataModel(MainMenu.MILITARY_LIFE, MilitaryLifeMenu.MILITARY_LIFE_HOME, MilitaryLifeHomePage.class),
                new NavigationDataModel(MainMenu.MILITARY_LIFE, MilitaryLifeMenu.JOIN_THE_MILITARY, JoinTheMilitaryPage.class),
                new NavigationDataModel(MainMenu.MILITARY_LIFE, MilitaryLifeMenu.PCS_RELOCATION, PcsRelocationPage.class),
                new NavigationDataModel(MainMenu.MILITARY_LIFE, MilitaryLifeMenu.OFF_DUTY, OffDutyPage.class),
                new NavigationDataModel(MainMenu.MILITARY_LIFE, MilitaryLifeMenu.FITNESS, FitnessPage.class),
                new NavigationDataModel(MainMenu.MILITARY_LIFE, MilitaryLifeMenu.BASE_GUIDES, BaseGuidesPage.class),
                new NavigationDataModel(MainMenu.MILITARY_LIFE, MilitaryLifeMenu.MONEY, MoneyPage.class),
                new NavigationDataModel(MainMenu.MILITARY_LIFE, MilitaryLifeMenu.HOME_OWNERSHIP, HomeOwnershipPage.class),
                new NavigationDataModel(MainMenu.MILITARY_LIFE, MilitaryLifeMenu.SPECIAL_OPERATIONS, SpecialOperationsPage.class),
                new NavigationDataModel(MainMenu.MILITARY_LIFE, MilitaryLifeMenu.EQUIPMENT_GUIDE, EquipmentGuidePage.class),
                new NavigationDataModel(MainMenu.MILITARY_LIFE, MilitaryLifeMenu.DEPLOYMENT, DeploymentPage.class),
                new NavigationDataModel(MainMenu.MILITARY_LIFE, MilitaryLifeMenu.MILITARY_TRIVIA_GAME, MilitaryTriviaGamePage.class)
        };
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @DataProvider
    public Object[] militaryLifeNavigationNegativeData() {
        return new NavigationDataModel[]{

                new NavigationDataModel(MainMenu.MILITARY_LIFE, NewsMenu.COAST_GUARD, CoastGuardPage.class),
                new NavigationDataModel(MainMenu.MILITARY_LIFE, BenefitsMenu.RETIREMENT, RetirementPage.class),

        };
    }


}
