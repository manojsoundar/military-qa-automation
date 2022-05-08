package drupal.ui.pages.add_content.newsletter_article;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class NewsLetterArticlePage extends AdministrationToolbar {

    private static final String URL_REGEX = "/military-report/";
    private static final SelenideElement NEWS_LETTER_ARTICLE_PAGE_H_1_TITLE = $(".block--page--title h1 span");

    public NewsLetterArticlePage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "News Letter article page not loaded.");
        log.info("News Letter article page loaded properly.");
    }

    public NewsLetterArticlePage verifyNewsLetterArticlePage(String title) {

        assertEquals(NEWS_LETTER_ARTICLE_PAGE_H_1_TITLE.should(exist, appear)
                                                       .getText()
                                                       .trim(), title,
                     "Correct page title not displayed.");

        return this;
    }

}
