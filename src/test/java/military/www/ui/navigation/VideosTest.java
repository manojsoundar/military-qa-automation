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
import www.enums.NewsMenu;
import www.enums.VideosMenu;
import www.models.NavigationDataModel;
import www.ui.pages.MilitaryLandingPage;
import www.ui.pages.militaryLife.FitnessPage;
import www.ui.pages.news.AirForcePage;
import www.ui.pages.videos.CategoriesPage;
import www.ui.pages.videos.EditorsPicsPage;
import www.ui.pages.videos.EquipmentPage;
import www.ui.pages.videos.FlirPage;
import www.ui.pages.videos.GunsAndWeaponsPage;
import www.ui.pages.videos.MilitaryDotComOriginalsPage;
import www.ui.pages.videos.MostPopularPage;
import www.ui.pages.videos.ShockAndAwePage;
import www.ui.pages.videos.SnipersPage;
import www.ui.pages.videos.SpecialOperationsPage;
import www.ui.pages.videos.VideoHomePage;

public class VideosTest extends MilitaryUITestBase {

    @Test(dataProvider = "videosNavigationData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = @Attribute(key = "TestCaseId", value = "0"))
    @TestCaseId("0")
    public <P extends MasterPage> void videosMenuValidNavigationTest(NavigationDataModel<P> navigation) {

        logStep("Step 1: Goto Military Page");
        logStep("Step 2: Validate Main Menu navigation functionality");
        logStep("Step 3: Validate Sub Menu navigation functionality");

        new MilitaryLandingPage().navigateTo(navigation);
    }

    @Test(dataProvider = "videosNavigationNegativeData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = @Attribute(key = "TestCaseId", value = "0"))
    @TestCaseId("0")
    public <P extends MasterPage> void videosMenuInvalidNavigationTest(NavigationDataModel<P> navigation) {

        logStep("Step 1: Goto Military Page");
        logStep("Step 2: Validate Main Menu navigation functionality");
        logStep("Step 3: Validate Sub Menu navigation functionality");

        new MilitaryLandingPage().navigateTo(navigation);

    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @DataProvider
    public Object[] videosNavigationData() {
        return new NavigationDataModel[]{

                new NavigationDataModel(MainMenu.VIDEOS, null, VideoHomePage.class),
                new NavigationDataModel(MainMenu.VIDEOS, VideosMenu.VIDEOS_HOME, VideoHomePage.class),
                new NavigationDataModel(MainMenu.VIDEOS, VideosMenu.CATEGORIES, CategoriesPage.class),
                new NavigationDataModel(MainMenu.VIDEOS, VideosMenu.SHOCK_AND_AWAY, ShockAndAwePage.class),
                new NavigationDataModel(MainMenu.VIDEOS, VideosMenu.MILITARY_DOT_COM_ORIGINALS, MilitaryDotComOriginalsPage.class),
                new NavigationDataModel(MainMenu.VIDEOS, VideosMenu.EDITORS_PICKS, EditorsPicsPage.class),
                new NavigationDataModel(MainMenu.VIDEOS, VideosMenu.MOST_POPULAR, MostPopularPage.class),
                new NavigationDataModel(MainMenu.VIDEOS, VideosMenu.FLIR, FlirPage.class),
                new NavigationDataModel(MainMenu.VIDEOS, VideosMenu.GUNS_AND_WEAPONS, GunsAndWeaponsPage.class),
                new NavigationDataModel(MainMenu.VIDEOS, VideosMenu.SNIPERS, SnipersPage.class),
                new NavigationDataModel(MainMenu.VIDEOS, VideosMenu.SPECIAL_OPERATIONS, SpecialOperationsPage.class),
                new NavigationDataModel(MainMenu.VIDEOS, VideosMenu.EQUIPMENT, EquipmentPage.class)
        };
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @DataProvider
    public Object[] videosNavigationNegativeData() {
        return new NavigationDataModel[]{
                new NavigationDataModel(MainMenu.VIDEOS, MilitaryLifeMenu.FITNESS, FitnessPage.class),
                new NavigationDataModel(MainMenu.VIDEOS, NewsMenu.AIR_FORCE, AirForcePage.class),
        };
    }

}
