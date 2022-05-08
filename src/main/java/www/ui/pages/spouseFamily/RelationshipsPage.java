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
public class RelationshipsPage extends GlobalHeaderComponent {

    public final String URL = "/relationships";

    @SuppressWarnings("FieldCanBeLocal")
    private final SelenideElement RELATIONSHIPS_H_1_TITLE = $(".block--page--title h1");

    public RelationshipsPage() {
        assertTrue(verifyURLLoaded(URL), "Relationships Page not loaded..");
        assertEquals(RELATIONSHIPS_H_1_TITLE.should(enabled, visible)
                                            .getText()
                                            .trim(), "Military Family Life", "Page title not displayed");
        log.info("Relationships Page loaded..");
    }

}
