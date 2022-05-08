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
public class EditorsPicsPage extends GlobalHeaderComponent {

    private static final String URL = "/video/editors-picks";
    private static final SelenideElement EDITORS_PICS_PAGE_TITLE = $("#bodyContent .field.name.field--label-hidden");

    public EditorsPicsPage() {
        assertTrue(verifyURLLoaded(URL), "Military.com Originals page not loaded.");
        assertEquals(EDITORS_PICS_PAGE_TITLE.should(exist, appear)
                                            .getText()
                                            .trim(), "Editors' Picks",
                     "Correct page title not displayed.");
        log.info("Military.com Originals page loaded properly.");

    }

}
