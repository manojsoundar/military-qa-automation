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
import www.enums.NewsMenu;
import www.enums.VideosMenu;
import www.models.NavigationDataModel;
import www.ui.pages.MilitaryLandingPage;
import www.ui.pages.benefits.VAeBenefitsPage;
import www.ui.pages.news.AirForcePage;
import www.ui.pages.news.ArmyPage;
import www.ui.pages.news.CoastGuardPage;
import www.ui.pages.news.CoronavirusResponsePage;
import www.ui.pages.news.LeftOfBoomPodcastPage;
import www.ui.pages.news.MarineCorpsPage;
import www.ui.pages.news.NavyPage;
import www.ui.pages.news.NewsHomePage;
import www.ui.pages.news.OpinionPage;
import www.ui.pages.news.SpaceForcePage;
import www.ui.pages.videos.FlirPage;

public class NewsTest extends MilitaryUITestBase {

    @Test(dataProvider = "newsNavigationData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = @Attribute(key = "TestCaseId", value = "0"))
    @TestCaseId("0")
    public <P extends MasterPage> void newsMenuValidNavigationTest(NavigationDataModel<P> navigation) {

        logStep("Step 1: Goto Military Page");
        logStep("Step 2: Validate Main Menu navigation functionality");
        logStep("Step 3: Validate Sub Menu navigation functionality");

        new MilitaryLandingPage().navigateTo(navigation);

    }

    @Test(dataProvider = "newsNavigationNegativeData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = @Attribute(key = "TestCaseId", value = "0"))
    @TestCaseId("0")
    public <P extends MasterPage> void newsMenuInvalidNavigationTest(NavigationDataModel<P> navigation) {

        logStep("Step 1: Goto Military Page");
        logStep("Step 2: Validate Main Menu navigation functionality");
        logStep("Step 3: Validate Sub Menu navigation functionality");

        new MilitaryLandingPage().navigateTo(navigation);

    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @DataProvider
    public Object[] newsNavigationData() {
        return new NavigationDataModel[]{

                new NavigationDataModel(MainMenu.NEWS, null, NewsHomePage.class),
                new NavigationDataModel(MainMenu.NEWS, NewsMenu.NEWS_HOME, NewsHomePage.class),
                new NavigationDataModel(MainMenu.NEWS, NewsMenu.CORONAVIRUS_RESPONSE, CoronavirusResponsePage.class),
                new NavigationDataModel(MainMenu.NEWS, NewsMenu.OPINION, OpinionPage.class),
                new NavigationDataModel(MainMenu.NEWS, NewsMenu.LEFT_OF_BOOM_PODCAST, LeftOfBoomPodcastPage.class),
                new NavigationDataModel(MainMenu.NEWS, NewsMenu.ARMY, ArmyPage.class),
                new NavigationDataModel(MainMenu.NEWS, NewsMenu.MARINE_CORPS, MarineCorpsPage.class),
                new NavigationDataModel(MainMenu.NEWS, NewsMenu.NAVY, NavyPage.class),
                new NavigationDataModel(MainMenu.NEWS, NewsMenu.AIR_FORCE, AirForcePage.class),
                new NavigationDataModel(MainMenu.NEWS, NewsMenu.SPACE_FORCE, SpaceForcePage.class),
                new NavigationDataModel(MainMenu.NEWS, NewsMenu.COAST_GUARD, CoastGuardPage.class),

        };
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @DataProvider
    public Object[] newsNavigationNegativeData() {
        return new NavigationDataModel[]{

                new NavigationDataModel(MainMenu.NEWS, BenefitsMenu.VA_E_BENEFITS, VAeBenefitsPage.class),
                new NavigationDataModel(MainMenu.NEWS, VideosMenu.FLIR, FlirPage.class),

        };
    }

}
