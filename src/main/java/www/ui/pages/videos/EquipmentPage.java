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
public class EquipmentPage extends GlobalHeaderComponent {

    private static final String URL = "/video/shock-and-awe/equipment";
    private static final SelenideElement EQUIPMENT_PAGE_TITLE = $("#bodyContent .field.name.field--label-hidden");

    public EquipmentPage() {
        assertTrue(verifyURLLoaded(URL), "Equipment page not loaded.");
        assertEquals(EQUIPMENT_PAGE_TITLE.should(exist, appear)
                                         .getText()
                                         .trim(), "Equipment",
                     "Correct page title not displayed.");
        log.info("Equipment page loaded properly.");
    }

}
