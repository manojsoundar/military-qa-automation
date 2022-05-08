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
import www.enums.DiscountsMenu;
import www.enums.MainMenu;
import www.enums.MilitaryLifeMenu;
import www.enums.NewsMenu;
import www.enums.SpouseFamilyMenu;
import www.enums.VeteranJobsMenu;
import www.enums.VideosMenu;
import www.models.NavigationDataModel;
import www.ui.pages.MilitaryLandingPage;
import www.ui.pages.benefits.BenefitsHomePage;
import www.ui.pages.benefits.MilitaryPayPage;
import www.ui.pages.discounts.ApparelAndAccessoriesPage;
import www.ui.pages.discounts.LodgingPage;
import www.ui.pages.militaryLife.BaseGuidesPage;
import www.ui.pages.militaryLife.FitnessPage;
import www.ui.pages.militaryLife.JoinTheMilitaryPage;
import www.ui.pages.news.ArmyPage;
import www.ui.pages.news.CoastGuardPage;
import www.ui.pages.news.NavyPage;
import www.ui.pages.news.NewsHomePage;
import www.ui.pages.spouseFamily.RelationshipsPage;
import www.ui.pages.veteranJobs.ForEmployersPage;
import www.ui.pages.veteranJobs.VeteranJobSearchPage;
import www.ui.pages.videos.EquipmentPage;
import www.ui.pages.videos.FlirPage;
import www.ui.pages.videos.VideoHomePage;

public class MilitaryTest extends MilitaryUITestBase {

    @Test(dataProvider = "navigationData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = @Attribute(key = "TestCaseId", value = "0"))
    @TestCaseId("0")
    public <P extends MasterPage> void militaryHeaderNavigationTest(NavigationDataModel<P> navigation) {

        logStep("Step 1: Goto Military Page");
        logStep("Step 2: Validate Main Menu navigation functionality");
        logStep("Step 3: Validate Sub Menu navigation functionality");

        new MilitaryLandingPage().navigateTo(navigation);

    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @DataProvider
    public Object[] navigationData() {
        return new NavigationDataModel[]{

                new NavigationDataModel(MainMenu.BENEFITS, null, BenefitsHomePage.class),
                new NavigationDataModel(MainMenu.NEWS, null, NewsHomePage.class),
                new NavigationDataModel(MainMenu.VIDEOS, null, VideoHomePage.class),
                new NavigationDataModel(MainMenu.BENEFITS, BenefitsMenu.MILITARY_PAY, MilitaryPayPage.class),
                new NavigationDataModel(MainMenu.NEWS, NewsMenu.ARMY, ArmyPage.class),
                new NavigationDataModel(MainMenu.VETERAN_JOBS, VeteranJobsMenu.VETERAN_JOB_SEARCH, VeteranJobSearchPage.class),
                new NavigationDataModel(MainMenu.MILITARY_LIFE, MilitaryLifeMenu.JOIN_THE_MILITARY, JoinTheMilitaryPage.class),
                new NavigationDataModel(MainMenu.SPOUSE_AND_FAMILY, SpouseFamilyMenu.RELATIONSHIPS, RelationshipsPage.class),
                new NavigationDataModel(MainMenu.VIDEOS, VideosMenu.EQUIPMENT, EquipmentPage.class),
                new NavigationDataModel(MainMenu.DISCOUNTS, DiscountsMenu.APPAREL_AND_ACCESSORIES, ApparelAndAccessoriesPage.class),


        };
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @DataProvider
    public Object[] navigationNegativeData() {
        return new NavigationDataModel[]{

                new NavigationDataModel(MainMenu.BENEFITS, NewsMenu.NAVY, NavyPage.class),
                new NavigationDataModel(MainMenu.DISCOUNTS, VeteranJobsMenu.FOR_EMPLOYERS, ForEmployersPage.class),
                new NavigationDataModel(MainMenu.MILITARY_LIFE, NewsMenu.COAST_GUARD, CoastGuardPage.class),
                new NavigationDataModel(MainMenu.NEWS, VideosMenu.FLIR, FlirPage.class),
                new NavigationDataModel(MainMenu.SPOUSE_AND_FAMILY, MilitaryLifeMenu.BASE_GUIDES, BaseGuidesPage.class),
                new NavigationDataModel(MainMenu.VETERAN_JOBS, DiscountsMenu.LODGING, LodgingPage.class),
                new NavigationDataModel(MainMenu.VIDEOS, MilitaryLifeMenu.FITNESS, FitnessPage.class),

        };
    }

}
