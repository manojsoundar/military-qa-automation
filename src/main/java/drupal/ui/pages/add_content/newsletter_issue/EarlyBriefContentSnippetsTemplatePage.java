package drupal.ui.pages.add_content.newsletter_issue;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.WebDriverRunner.driver;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EarlyBriefContentSnippetsTemplatePage {

    private static final String URL_REGEX = "/newsletter-templates/body/early-brief";

    public EarlyBriefContentSnippetsTemplatePage() {
        log.info("Loading Contents Snippets Template");
    }

    public NewsLetterIssueResultPage verifyArticlesSnippetsLinks(NewsletterContentModel newsLetterIssueData) {

        SelenideDriver selenideDriverTemplate = new SelenideDriver(driver().config());

        try {
            selenideDriverTemplate.open(Configuration.baseUrl + URL_REGEX);
            SelenideElement leadStoryLabelImage = selenideDriverTemplate.$x("//tr[1]//tr[1]//img");
            SelenideElement leadStoryLabelLink = selenideDriverTemplate.$x("//tr[1]//tr[2]//a");
            SelenideElement triviaText = selenideDriverTemplate.$x("//tr[4]//tr[1]/td");
            SelenideElement videoImage = selenideDriverTemplate.$x("//tr[6]//tr[2]//a/img");
            SelenideElement videoThumbnail = selenideDriverTemplate.$x("//tr[6]//tr[2]/td[3]/a");
            List<String> articlesSnippetsLinksList = selenideDriverTemplate.$$x("//tr[2]//tr//a")
                                                                           .stream()
                                                                           .map(SelenideElement::getText)
                                                                           .collect(Collectors.toList());
            List<String> articlesSnippetsNames = new ArrayList<>(newsLetterIssueData.getArticlesOrSnippets()
                                                                                    .keySet());

            if (!articlesSnippetsLinksList.isEmpty()) {
                assertEquals(articlesSnippetsNames, articlesSnippetsLinksList, "Displayed links are not in the order of input");
                if (!articlesSnippetsNames.equals(articlesSnippetsLinksList)) {
                    List<String> subList = new ArrayList<>(articlesSnippetsNames);
                    subList.removeAll(articlesSnippetsLinksList);
                    log.error(format("Content/Snippet URL is Not displayed: %s ", subList));
                    throw new AssertionError(format("Content/Snippet URL is Not displayed %s ", subList));
                }
                log.info("Content/Snippet URLs are displayed");
            }

            assertTrue(leadStoryLabelImage.isDisplayed(), "Lead Story Label images Not displayed");
            assertTrue(newsLetterIssueData.getLeadStoryLabel()
                                          .contains(leadStoryLabelLink.getText()
                                                                      .trim()), "Lead Story Label Link Not displayed");
            assertTrue(newsLetterIssueData.getTrivia()
                                          .contains(triviaText.getText()), "Trivia text Not matching");
            assertTrue(videoImage.isDisplayed(), "Video Imaged Not displayed");
            assertTrue(newsLetterIssueData.getVideos()
                                          .contains(videoThumbnail.getText()
                                                                  .trim()), "Video thumbnail is Not matching");

        } finally {
            selenideDriverTemplate.close();
        }
        return new NewsLetterIssueResultPage();
    }

}
