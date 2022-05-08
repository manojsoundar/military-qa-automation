package drupal.ui.pages.media;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddAudioPage extends AddMediaBasePage {

    private static final String URL_REGEX = "/media/add/audio";
    private static final SelenideElement AUDIO_H_1_TAG = $("#block-pagetitle h1").as("Audio Page H1 Tag");

    public AddAudioPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Audio Page not loaded.");
        assertTrue(AUDIO_H_1_TAG.should(exist, appear)
                                .has(text("Add Audio")), "Correct page is not displayed.");
        log.info("Audio Page loaded properly.");
    }

}
