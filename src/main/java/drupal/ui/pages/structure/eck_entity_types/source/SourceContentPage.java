package drupal.ui.pages.structure.eck_entity_types.source;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class SourceContentPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/source";
    private static final SelenideElement SOURCE_CONTENT_H_1_TAG = $("#block-pagetitle h1 em");
    private static final SelenideElement ADD_SOURCE_BUTTON = $(".action-links .button-action");
    private static final SelenideElement PAGINATION_LAST_LINK = $(".pager__items .pager__item--last a");
    private static final ElementsCollection SOURCE_TITLE_LIST = $$("td>a[href*='source']");


    public SourceContentPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Sources Content Page not loaded.");
        assertTrue(SOURCE_CONTENT_H_1_TAG.should(exist, appear)
                                         .has(text("Source")), "Source content title: " + SOURCE_CONTENT_H_1_TAG.getText() + "not displayed,should contain('Source')");
        log.info("Sources Content Page loaded properly.");
    }

    public SourceContentPage navigateToLastPage() {
        PAGINATION_LAST_LINK.should(appear, exist, enabled)
                            .scrollTo()
                            .click();
        return this;
    }

    public SourcePage clickContentLink(String title) {
        SOURCE_TITLE_LIST.find(text(title))
                         .should(appear, exist)
                         .click();
        log.info("Click on Content for the given title");
        return new SourcePage();
    }
}
