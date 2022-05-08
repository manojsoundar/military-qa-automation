package drupal.ui.pages.structure.taxonomy.keywords;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;
import mgs.qa.utils.asserts.SoftAssertMt;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;
import static org.testng.Assert.assertTrue;

@SuppressWarnings("CanBeFinal")
@Log4j2
public class KeywordsLandingPage extends AdministrationToolbar {

    public static final SelenideElement EDIT_TAB = $("div.block--tabs li a[href*='/edit']").as("Edit tab button");
    private static final String URL_REGEX = "/topics";
    private static final SelenideElement KEYWORDS_TERM_H_1_TAG = $(".block--page--title h1 div");
    private static final SelenideElement KEYWORD_STATUS_CONFIRMATION_MESSAGE = $(".content .messages.messages--status");
    private final String currentDiscountURL = getDriver().getCurrentUrl();
    SoftAssert softAssert = SoftAssertMt.getSoftAssert();

    public KeywordsLandingPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Keywords Result Page not loaded.");
        assertTrue(KEYWORDS_TERM_H_1_TAG.should(exist, appear)
                                        .has(text("Test")), "Keywords landing page title: " + KEYWORDS_TERM_H_1_TAG.getText() + "not displayed,should contain('Test')");
        log.info("Keywords Result Page loaded properly.");
    }

    public KeywordsLandingPage verifyKeywordLandingPage(KeywordsDataModel taxonomyKeywordsData) {

        assertTrue(currentDiscountURL.contains(taxonomyKeywordsData.getUrlAlias()), "Incorrect Landing Page");
        assertTrue(KEYWORDS_TERM_H_1_TAG.getText()
                                        .contains(taxonomyKeywordsData.getName()));
        return this;
    }

    public KeywordsLandingPage verifyPageSourceData(TrackingPixelModel taxonomyKeywordsData) {
        log.info("Verifying image data without accessing to PageSource");
        List<String> imageSourceList = $$x("//article/img").stream()
                                                           .map(e -> e.getAttribute("src"))
                                                           .collect(Collectors.toList());
        for (String url : taxonomyKeywordsData.getTrackingPixelUrl()) {
            softAssert.assertTrue(imageSourceList.contains(url), "Failed to find link :" + url);
        }
        softAssert.assertAll();
        return this;
    }

    public EditKeywordPage clickEditTab() {
        EDIT_TAB.should(exist, appear)
                .click();
        return new EditKeywordPage();
    }
}
