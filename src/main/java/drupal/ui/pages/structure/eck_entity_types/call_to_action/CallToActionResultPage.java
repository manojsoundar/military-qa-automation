package drupal.ui.pages.structure.eck_entity_types.call_to_action;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class CallToActionResultPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/entity/call_to_action/";
    private static final SelenideElement CALL_TO_ACTION_RESULT_PAGE_H_1_TAG = $(".page-title div");
    private static final SelenideElement BUTTON_LABEL_TEXT = $x("//div[contains(@class,'field--name-field-button-label')]/div[@class='field__item'] | //div[contains(@class,'field--name-field-button-label')]");
    private static final SelenideElement BLURB_TEXT = $x("//div[contains(@class,'field--name-field-blurb')]/div[@class='field__item'] | //div[contains(@class,'field--name-field-blurb')]");

    public CallToActionResultPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Call To Action Result Page not loaded.");
        log.info("Call To Action Result Page loaded properly.");
    }

    public CallToActionResultPage verifyAddedCTA(CTABaseModel ctaBaseData, CTAButtonModel ctaButtonData) {

        assertEquals(CALL_TO_ACTION_RESULT_PAGE_H_1_TAG.should(exist, visible)
                                                       .getText(), ctaBaseData.getTitle(), "CTA Title" + CALL_TO_ACTION_RESULT_PAGE_H_1_TAG.getText() + "not displayed,should contain" + ctaBaseData.getTitle());
        assertTrue(BLURB_TEXT.should(enabled, visible)
                             .getText()
                             .contains(ctaBaseData.getBlurb()), "Blurb Text of CTA Result page:" + BLURB_TEXT.getText() + "not displayed,should contain" + ctaBaseData.getBlurb());
        assertTrue(BUTTON_LABEL_TEXT.should(exist, visible)
                                    .getText()
                                    .contains(ctaButtonData.getButtonLabel()), "Button label text of CTA result page:" + BUTTON_LABEL_TEXT.getText() + "not displayed,should contain" + ctaButtonData.getButtonLabel());
        return this;
    }
}
