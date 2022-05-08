package www.ui.pages.militaryLife;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class DeploymentPage extends GlobalHeaderComponent {

    private static final String URL = "/deployment";
    private static final SelenideElement DEPLOYMENT_PARA_TITLE = $("div#pagewrapper > div > div >main > div > div.block.block--page--title > h1 > span").as("Deployment Page Para Title");

    public DeploymentPage() {
        assertTrue(verifyURLLoaded(URL), "Page not loaded");
        DEPLOYMENT_PARA_TITLE.should(appear);

        log.info("Verify if Page Para title is displayed correctly.");
        assertTrue(DEPLOYMENT_PARA_TITLE.isDisplayed(), "Correct deployment Para title not displayed.");


    }

}
