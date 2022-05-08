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
import www.enums.NewsMenu;
import www.models.NavigationDataModel;
import www.ui.pages.MilitaryLandingPage;
import www.ui.pages.benefits.BenefitsHomePage;
import www.ui.pages.benefits.DirectoryPage;
import www.ui.pages.benefits.EducationPage;
import www.ui.pages.benefits.GiBillPage;
import www.ui.pages.benefits.InsurancePage;
import www.ui.pages.benefits.MilitaryPayPage;
import www.ui.pages.benefits.RetirementPage;
import www.ui.pages.benefits.ScholarshipsForVetsPage;
import www.ui.pages.benefits.TricarePage;
import www.ui.pages.benefits.VAeBenefitsPage;
import www.ui.pages.benefits.VaLoansPage;
import www.ui.pages.benefits.VeteranHealthCarePage;
import www.ui.pages.benefits.VeteranPage;
import www.ui.pages.discounts.FreebiesPage;
import www.ui.pages.news.NavyPage;

public class BenefitsTest extends MilitaryUITestBase {

    @Test(dataProvider = "benefitsNavigationData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = @Attribute(key = "TestCaseId", value = "0"))
    @TestCaseId("0")
    public <P extends MasterPage> void benefitsMenuValidNavigationTest(NavigationDataModel<P> navigation) {

        logStep("Step 1: Goto Military Page");
        logStep("Step 2: Validate Main Menu navigation functionality");
        logStep("Step 3: Validate Sub Menu navigation functionality");
        new MilitaryLandingPage().navigateTo(navigation);

    }

    @Test(dataProvider = "benefitsNavigationNegativeData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = @Attribute(key = "TestCaseId", value = "0"))
    @TestCaseId("0")
    public <P extends MasterPage> void benefitsMenuInvalidNavigationTest(NavigationDataModel<P> navigation) {

        logStep("Step 1: Goto Military Page");
        logStep("Step 2: Validate Main Menu navigation functionality");
        logStep("Step 3: Validate Sub Menu navigation functionality");

        new MilitaryLandingPage().navigateTo(navigation);

    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @DataProvider
    public Object[] benefitsNavigationData() {
        return new NavigationDataModel[]{

                new NavigationDataModel(MainMenu.BENEFITS, null, BenefitsHomePage.class),
                new NavigationDataModel(MainMenu.BENEFITS, BenefitsMenu.BENEFITS_HOME, BenefitsHomePage.class),
                new NavigationDataModel(MainMenu.BENEFITS, BenefitsMenu.DIRECTORY, DirectoryPage.class),
                new NavigationDataModel(MainMenu.BENEFITS, BenefitsMenu.MILITARY_PAY, MilitaryPayPage.class),
                new NavigationDataModel(MainMenu.BENEFITS, BenefitsMenu.GI_BILL, GiBillPage.class),
                new NavigationDataModel(MainMenu.BENEFITS, BenefitsMenu.EDUCATION, EducationPage.class),
                new NavigationDataModel(MainMenu.BENEFITS, BenefitsMenu.VETERAN, VeteranPage.class),
                new NavigationDataModel(MainMenu.BENEFITS, BenefitsMenu.TRICARE, TricarePage.class),
                new NavigationDataModel(MainMenu.BENEFITS, BenefitsMenu.SCHOLARSHIPS_FOR_VETS, ScholarshipsForVetsPage.class),
                new NavigationDataModel(MainMenu.BENEFITS, BenefitsMenu.VA_LOANS, VaLoansPage.class),
                new NavigationDataModel(MainMenu.BENEFITS, BenefitsMenu.INSURANCE, InsurancePage.class),
                new NavigationDataModel(MainMenu.BENEFITS, BenefitsMenu.RETIREMENT, RetirementPage.class),
                new NavigationDataModel(MainMenu.BENEFITS, BenefitsMenu.VA_E_BENEFITS, VAeBenefitsPage.class),
                new NavigationDataModel(MainMenu.BENEFITS, BenefitsMenu.VETERAN_HEALTHCARE, VeteranHealthCarePage.class)
        };
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @DataProvider
    public Object[] benefitsNavigationNegativeData() {
        return new NavigationDataModel[]{

                new NavigationDataModel(MainMenu.BENEFITS, NewsMenu.NAVY, NavyPage.class),
                new NavigationDataModel(MainMenu.BENEFITS, DiscountsMenu.FREEBIES, FreebiesPage.class),

        };
    }

}
