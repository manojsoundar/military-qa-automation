package www.ui.pages.militaryLife;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class MilitaryTriviaGamePage extends GlobalHeaderComponent {

    private static final String URL = "/trivia";
    private static final SelenideElement MILITARY_TRIVIA_PARA_TITLE = $("div#paragraph-48481 > div > div > div > div.pageTitle").as("Military Trivia Page Para Title");

    public MilitaryTriviaGamePage() {
        assertTrue(verifyURLLoaded(URL), "Page not loaded");
        MILITARY_TRIVIA_PARA_TITLE.should(appear);

        log.info("Verify if Page Para title is displayed correctly.");
        assertTrue(MILITARY_TRIVIA_PARA_TITLE.isDisplayed(), "Correct Military Trivia Para title not displayed.");

    }

}
