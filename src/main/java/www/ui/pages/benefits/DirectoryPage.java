package www.ui.pages.benefits;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class DirectoryPage extends GlobalHeaderComponent {

    private static final String URL = "/benefits/directory";
    private static final SelenideElement BENEFITS_DIRECTORY_PARA_TITLE_NAME = $("div.block.block--page--title h1 span").as("Benefits Directory Page");

    public DirectoryPage() {
        log.info("Verify if Benefits Directory Page is displayed correctly.");
        assertTrue(verifyURLLoaded(URL), "Benefits Directory pay page not loaded");
        assertTrue(BENEFITS_DIRECTORY_PARA_TITLE_NAME.isDisplayed(), "Correct Para title not displayed.");
    }

}
