package drupal.ui.pages.media;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddDocumentPage extends AddMediaBasePage {

    private static final String URL_REGEX = "/media/add/document";
    private static final SelenideElement DOCUMENT_HEADER_TAG = $("#block-pagetitle h1").as("Document Page Header Tag");

    public AddDocumentPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Document Page not loaded.");
        assertTrue(DOCUMENT_HEADER_TAG.should(exist, appear)
                                      .has(text("Add Document")), "Correct document page is not displayed.");
        log.info("Media-document Page loaded properly.");
    }

}
