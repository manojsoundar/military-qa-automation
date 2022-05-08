package www.ui.pages.veteranJobs;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


@Log4j2
public class MilitarySkillsTranslatorPage extends GlobalHeaderComponent {

    private static final String URL = "/skills-translator";

    @SuppressWarnings("FieldCanBeLocal")
    private final SelenideElement SKILLS_TRANSLATOR_H_1_TITLE = $(".block--page--title h1");

    public MilitarySkillsTranslatorPage() {
        assertTrue(verifyURLLoaded(URL), "Military Skills Translator Page not loaded..");
        assertEquals(SKILLS_TRANSLATOR_H_1_TITLE.should(visible, enabled)
                                                .getText()
                                                .trim(), "Military Skills Translator", "Page title not displayed");
        log.info("Military Skills Translator Page loaded..");

    }

}
