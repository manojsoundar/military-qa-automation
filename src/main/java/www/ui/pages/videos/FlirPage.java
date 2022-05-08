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
public class FlirPage extends GlobalHeaderComponent {

    private static final String URL = "/video/shock-and-awe/flir";
    private static final SelenideElement FLIR_PAGE_TITLE = $("#bodyContent .field.name.field--label-hidden");

    public FlirPage() {
        assertTrue(verifyURLLoaded(URL), "Flir page not loaded.");
        assertEquals(FLIR_PAGE_TITLE.should(exist, appear)
                                    .getText()
                                    .trim(), "FLIR", "Correct page title not displayed.");
        log.info("Flir page loaded properly.");

    }

}
