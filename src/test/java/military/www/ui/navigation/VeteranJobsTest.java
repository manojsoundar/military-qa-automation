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
import www.enums.VeteranJobsMenu;
import www.models.NavigationDataModel;
import www.ui.pages.MilitaryLandingPage;
import www.ui.pages.benefits.GiBillPage;
import www.ui.pages.discounts.LodgingPage;
import www.ui.pages.veteranJobs.CareerAdvicePage;
import www.ui.pages.veteranJobs.ForEmployersPage;
import www.ui.pages.veteranJobs.MilitarySkillsTranslatorPage;
import www.ui.pages.veteranJobs.SecurityClearanceJobsPage;
import www.ui.pages.veteranJobs.TransitionCenterPage;
import www.ui.pages.veteranJobs.UpcomingJobFairsPage;
import www.ui.pages.veteranJobs.UploadYourResumePage;
import www.ui.pages.veteranJobs.VetFriendlyEmployersPage;
import www.ui.pages.veteranJobs.VeteranTalentPoolPage;

public class VeteranJobsTest extends MilitaryUITestBase {

    @Test(dataProvider = "veteranJobsNavigationData")
    public <P extends MasterPage> void veteranJobsMenuValidNavigationTest(NavigationDataModel<P> navigation) {

        logStep("Step 1: Goto Military Page");
        logStep("Step 2: Validate Main Menu navigation functionality");
        logStep("Step 3: Validate Sub Menu navigation functionality");

        new MilitaryLandingPage().navigateTo(navigation);
    }

    @Test(dataProvider = "veteranJobsNavigationNegativeData")
    @Priority(level = PriorityLevel.HIGH)
    @Attributes(attributes = @Attribute(key = "TestCaseId", value = "0"))
    @TestCaseId("0")
    public <P extends MasterPage> void veteranJobsMenuInvalidNavigationTest(NavigationDataModel<P> navigation) {

        logStep("Step 1: Goto Military Page");
        logStep("Step 2: Validate Main Menu navigation functionality");
        logStep("Step 3: Validate Sub Menu navigation functionality");

        new MilitaryLandingPage().navigateTo(navigation);

    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @DataProvider
    public Object[] veteranJobsNavigationData() {
        return new NavigationDataModel[]{

                new NavigationDataModel(MainMenu.VETERAN_JOBS, null, UploadYourResumePage.class),
                new NavigationDataModel(MainMenu.VETERAN_JOBS, VeteranJobsMenu.UPLOAD_YOUR_RESUME, UploadYourResumePage.class),
                new NavigationDataModel(MainMenu.VETERAN_JOBS, VeteranJobsMenu.MILITARY_SKILLS_TRANSLATOR, MilitarySkillsTranslatorPage.class),
                new NavigationDataModel(MainMenu.VETERAN_JOBS, VeteranJobsMenu.VET_FRIENDLY_EMPLOYERS, VetFriendlyEmployersPage.class),
                new NavigationDataModel(MainMenu.VETERAN_JOBS, VeteranJobsMenu.VETERAN_TALENT_POOL, VeteranTalentPoolPage.class),
                new NavigationDataModel(MainMenu.VETERAN_JOBS, VeteranJobsMenu.TRANSITION_CENTER, TransitionCenterPage.class),
                new NavigationDataModel(MainMenu.VETERAN_JOBS, VeteranJobsMenu.SECURITY_CLEARANCE_JOBS, SecurityClearanceJobsPage.class),
                new NavigationDataModel(MainMenu.VETERAN_JOBS, VeteranJobsMenu.UPCOMING_JOB_FAIRS, UpcomingJobFairsPage.class),
                new NavigationDataModel(MainMenu.VETERAN_JOBS, VeteranJobsMenu.FOR_EMPLOYERS, ForEmployersPage.class),
                new NavigationDataModel(MainMenu.VETERAN_JOBS, VeteranJobsMenu.CAREER_ADVICE, CareerAdvicePage.class),

        };
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @DataProvider
    public Object[] veteranJobsNavigationNegativeData() {
        return new NavigationDataModel[]{

                new NavigationDataModel(MainMenu.VETERAN_JOBS, DiscountsMenu.LODGING, LodgingPage.class),
                new NavigationDataModel(MainMenu.VETERAN_JOBS, BenefitsMenu.GI_BILL, GiBillPage.class),

        };
    }

}
