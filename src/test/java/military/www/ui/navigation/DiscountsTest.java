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
import www.enums.DiscountsMenu;
import www.enums.MainMenu;
import www.enums.SpouseFamilyMenu;
import www.enums.VeteranJobsMenu;
import www.models.NavigationDataModel;
import www.ui.pages.MilitaryLandingPage;
import www.ui.pages.discounts.ApparelAndAccessoriesPage;
import www.ui.pages.discounts.DiscountsHomePage;
import www.ui.pages.discounts.DiscountsOfTheMonthPage;
import www.ui.pages.discounts.FreebiesPage;
import www.ui.pages.discounts.LodgingPage;
import www.ui.pages.discounts.MerchantsPage;
import www.ui.pages.discounts.SkiResortsPage;
import www.ui.pages.discounts.TravelPage;
import www.ui.pages.spouseFamily.MilitaryMovesPage;
import www.ui.pages.veteranJobs.ForEmployersPage;
import www.ui.pages.videos.CategoriesPage;

public class DiscountsTest extends MilitaryUITestBase {

    @Test(dataProvider = "discountsNavigationData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = @Attribute(key = "TestCaseId", value = "0"))
    @TestCaseId("0")
    public <P extends MasterPage> void discountsMenuValidNavigationTest(NavigationDataModel<P> navigation) {

        logStep("Step 1: Goto Military Page");
        logStep("Step 2: Validate Main Menu navigation functionality");
        logStep("Step 3: Validate Sub Menu navigation functionality");
        new MilitaryLandingPage().navigateTo(navigation);
    }

    @Test(dataProvider = "discountsNavigationNegativeData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = @Attribute(key = "TestCaseId", value = "0"))
    @TestCaseId("0")
    public <P extends MasterPage> void discountsMenuInvalidNavigationTest(NavigationDataModel<P> navigation) {

        logStep("Step 1: Goto Military Page");
        logStep("Step 2: Validate Main Menu navigation functionality");
        logStep("Step 3: Validate Sub Menu navigation functionality");

        new MilitaryLandingPage().navigateTo(navigation);

    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @DataProvider
    public Object[] discountsNavigationData() {
        return new NavigationDataModel[]{

                new NavigationDataModel(MainMenu.DISCOUNTS, null, DiscountsHomePage.class),
                new NavigationDataModel(MainMenu.DISCOUNTS, DiscountsMenu.DISCOUNTS_HOME, DiscountsHomePage.class),
                new NavigationDataModel(MainMenu.DISCOUNTS, DiscountsMenu.CATEGORIES, CategoriesPage.class),
                new NavigationDataModel(MainMenu.DISCOUNTS, DiscountsMenu.MERCHANTS, MerchantsPage.class),
                new NavigationDataModel(MainMenu.DISCOUNTS, DiscountsMenu.DISCOUNTS_OF_THE_MONTH, DiscountsOfTheMonthPage.class),
                new NavigationDataModel(MainMenu.DISCOUNTS, DiscountsMenu.TRAVEL, TravelPage.class),
                new NavigationDataModel(MainMenu.DISCOUNTS, DiscountsMenu.APPAREL_AND_ACCESSORIES, ApparelAndAccessoriesPage.class),
                new NavigationDataModel(MainMenu.DISCOUNTS, DiscountsMenu.FREEBIES, FreebiesPage.class),
                new NavigationDataModel(MainMenu.DISCOUNTS, DiscountsMenu.LODGING, LodgingPage.class),
                new NavigationDataModel(MainMenu.DISCOUNTS, DiscountsMenu.SKI_RESORTS, SkiResortsPage.class)
        };
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @DataProvider
    public Object[] discountsNavigationNegativeData() {
        return new NavigationDataModel[]{

                new NavigationDataModel(MainMenu.DISCOUNTS, SpouseFamilyMenu.MILITARY_MOVES, MilitaryMovesPage.class),
                new NavigationDataModel(MainMenu.DISCOUNTS, VeteranJobsMenu.FOR_EMPLOYERS, ForEmployersPage.class),

        };
    }

}
