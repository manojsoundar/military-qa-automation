package www.enums;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

@Getter
@AllArgsConstructor
public enum VeteranJobsMenu implements IEnumNavigationMenu {

    VETERAN_JOB_SEARCH("Veteran Job Search", $(".menu--main ul a[href='/veteran-jobs']").as("Veteran Job Search")),
    UPLOAD_YOUR_RESUME("Upload Your Resume", $(".menu--main ul a[href*='/resumes/'][target='_self']").as("Upload Your Resume")),
    MILITARY_SKILLS_TRANSLATOR("Military Skills Translator", $(".menu--main ul a[href='/veteran-jobs/skills-translator']").as("Military Skills Translator")),
    VET_FRIENDLY_EMPLOYERS("Vet Friendly Employers", $(".menu--main ul a[href='/veteran-employers']").as("Vet Friendly Employers")),
    VETERAN_TALENT_POOL("Veteran Talent Pool", $(".menu--main ul a[href='/veteran-talent-pool']").as("Veteran Talent Pool")),
    TRANSITION_CENTER("Transition Center", $(".menu--main ul a[href='/military-transition']").as("Transition Center")),
    SECURITY_CLEARANCE_JOBS("Security Clearance Jobs", $(".menu--main ul a[href='/veteran-jobs/security-clearance-jobs']").as("Security Clearance Jobs")),
    UPCOMING_JOB_FAIRS("Upcoming Job Fairs", $(".menu--main ul a[href='/veteran-jobs/career-advice/job-hunting/upcoming-job-fairs']").as("Upcoming Job Fairs")),
    FOR_EMPLOYERS("For Employers", $(".menu--main ul a[href='/hiring-veterans']").as("For Employers")),
    CAREER_ADVICE("career-advice", $(".menu--main ul a[href='/veteran-jobs/career-advice']").as("career-advice"));

    private final String subMenuName;
    private final SelenideElement subMenuLink;

    public List<?> getList() {
        return Arrays.asList(values());
    }

}
