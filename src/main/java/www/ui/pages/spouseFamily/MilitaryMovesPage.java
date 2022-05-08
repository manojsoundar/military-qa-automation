package www.ui.pages.spouseFamily;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import www.ui.components.GlobalHeaderComponent;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class MilitaryMovesPage extends GlobalHeaderComponent {

    public final String URL = "/military-relocation";
    @SuppressWarnings("FieldCanBeLocal")
    private final SelenideElement MILITARY_RELOCATION_H_1_TITLE = $(".block--page--title h1");

    public MilitaryMovesPage() {
        assertTrue(verifyURLLoaded(URL), "Military Moves Page not loaded..");
        assertEquals(MILITARY_RELOCATION_H_1_TITLE.should(enabled, visible)
                                                  .getText()
                                                  .trim(), "Military Moves", "Page title not displayed");
        log.info("Military Moves Page loaded..");
    }

}
