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
public class SnipersPage extends GlobalHeaderComponent {

    private static final String URL = "/video/shock-and-awe/sniper-kills";
    private static final SelenideElement SNIPERS_PAGE_TITLE = $("#bodyContent .field.name.field--label-hidden");

    public SnipersPage() {
        assertTrue(verifyURLLoaded(URL), "Snipers page not loaded.");
        assertEquals(SNIPERS_PAGE_TITLE.should(exist, appear)
                                       .getText()
                                       .trim(), "Snipers",
                     "Correct page title not displayed.");
        log.info("Snipers");
    }

}
