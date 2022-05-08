package drupal.ui.pages.add_content.newsletter_issue;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.WebDriverRunner.driver;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

@Log4j2
public class InsiderArmyActiveContentsSnippetsTemplatePage {

    private static final String URL_REGEX = "/newsletter-templates/body/insider-army-active";

    public InsiderArmyActiveContentsSnippetsTemplatePage() {
        log.info("Loading Contents Snippets Template");
    }

    public NewsLetterIssueResultPage verifyArticlesSnippetsLinks(NewsletterContentModel newsLetterIssueData) {

        SelenideDriver selenideDriverTemplate = new SelenideDriver(driver().config());

        try {
            selenideDriverTemplate.open(Configuration.baseUrl + URL_REGEX);
            List<String> articlesSnippetsLinksList = selenideDriverTemplate.$$x("//tr[3]/td//tr/td/a")
                                                                           .stream()
                                                                           .map(SelenideElement::getText)
                                                                           .collect(Collectors.toList());
            ElementsCollection videosLinkList = selenideDriverTemplate.$$x("//tbody/tr[5]/td/table/tbody/tr/td[3]/a");
            ElementsCollection videosImagesList = selenideDriverTemplate.$$x("//tr[5]/td/table/tbody/tr/td[1]/a/img[@xt='SPIMAGE']");

            List<String> articlesSnippetsNames = new ArrayList<>(newsLetterIssueData.getArticlesOrSnippets()
                                                                                    .keySet());

            assertEquals(videosLinkList.size(), 3, "Videos links count is not 3");
            assertEquals(videosImagesList.size(), 3, "Videos Images count is not 3");

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
        } finally {
            selenideDriverTemplate.close();
        }
        return new NewsLetterIssueResultPage();
    }

}
