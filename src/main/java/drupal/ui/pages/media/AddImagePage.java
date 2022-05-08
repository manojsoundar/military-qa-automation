package drupal.ui.pages.media;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddImagePage extends AddMediaBasePage {

    protected static final SelenideElement ALTERNATIVE_TEXT_INPUT = $("input.form-text[id*='edit-field-image-0-alt']").as("Alternative Text input");
    protected static final SelenideElement CAPTION_TEXT_INPUT = $("input.form-text[id*='edit-field-image-0-title']").as("Caption Text input");
    protected static final SelenideElement NEWSCRED_CHECKBOX = $("#edit-field-newscred-value").as("Newscred box");
    protected static final SelenideElement AP_NEWSROOM_CHECKBOX = $("#edit-field-ap-newsroom-value").as("AP Newsroom checkbox");
    private static final String URL_REGEX = "/media/add/image";
    private static final SelenideElement IMAGE_H_1_TAG = $("#block-pagetitle h1").as("Audio Page H1 Tag");

    public AddImagePage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Image Page not loaded.");
        assertTrue(IMAGE_H_1_TAG.should(exist, appear)
                                .has(text("Add Image")), "Correct page is not displayed.");
        log.info("Image Page loaded properly.");
    }

}