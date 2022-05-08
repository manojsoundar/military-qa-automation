package drupal.ui.pages.structure.eck_entity_types.call_to_action;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddCallToActionContentPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/call_to_action/content/add";
    private static final SelenideElement ADD_CALL_TO_ACTION_H_1_TAG = $("#block-pagetitle h1 em.placeholder");
    private static final ElementsCollection CALL_TO_ACTION_LINK_LIST = $$("#block-mainpagecontent a span");

    public AddCallToActionContentPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Add Call To Action Content-Normal Page not loaded.");
        assertTrue(ADD_CALL_TO_ACTION_H_1_TAG.should(exist, appear)
                                             .has(text("Call to Action")), "Call to Action title: " + ADD_CALL_TO_ACTION_H_1_TAG.getText() + "not displayed,should contain('Call to Action')");
        log.info("Add Call To Action Content Page loaded properly.");
    }

    public <P extends MasterPage> P clickCallToActionItem(CallToActionItem callToActionItem, Class<P> expectedClass) {

        assertTrue(callToActionItem.getCallToActionItemLink()
                                   .should(exist, appear)
                                   .exists(), callToActionItem.name() + " is not displayed.");
        log.info(callToActionItem.name() + " is displayed");
        callToActionItem.getCallToActionItemLink()
                        .should(visible, enabled)
                        .click();

        return returnInstanceOf(expectedClass);
    }

    @Getter
    @AllArgsConstructor
    public enum CallToActionItem {

        CALL_TO_ACTION_LEAD_FORM($("a[href*='lead_form']")),
        CALL_TO_ACTION_NL_SIGNUP($("a[href*='signup']")),
        CALL_TO_ACTION_NORMAL($("a[href*='normal']"));

        private final SelenideElement callToActionItemLink;
    }
}
