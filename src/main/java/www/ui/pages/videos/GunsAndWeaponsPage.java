package www.ui.pages.videos;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class GunsAndWeaponsPage extends GlobalHeaderComponent {

    private static final String URL = "/video/shock-and-awe/guns-weapons";
    private static final SelenideElement GUNS_AND_WEAPONS_PAGE_TITLE = $("#bodyContent .field.name.field--label-hidden");

    public GunsAndWeaponsPage() {
        assertTrue(verifyURLLoaded(URL), "Guns and Weapons page not loaded.");
        assertEquals(GUNS_AND_WEAPONS_PAGE_TITLE.should(exist, appear)
                                                .getText()
                                                .trim(), "Guns and Weapons",
                     "Correct page title not displayed.");
        log.info("Guns and Weapons page loaded properly.");

    }

}
