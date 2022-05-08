package drupal.ui.pages.add_content.author;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AuthorPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/author";
    private static final SelenideElement AUTHOR_PAGE_H_1_TAG = $(".block--page--title  h1").as("Author Page H1 Tag");
    private static final SelenideElement EDIT_TAB = $("ul.menu.menu--tabs a[href*='edit']");
    private static final SelenideElement UPDATED_STATUS_MESSAGE = $(".messages--status");
    private static final SelenideElement UPDATED_MESSAGE_LINK = UPDATED_STATUS_MESSAGE.$(".placeholder a");

    public AuthorPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Author Page not loaded..");
        assertTrue(AUTHOR_PAGE_H_1_TAG.getText()
                                      .trim()
                                      .contains("Test"), "AuthorPage title not displayed..");
        log.info("Author Page loaded..");
    }

    public AuthorPage verifyAuthor(AuthorModel authorModel) {

        assertTrue(getDriver().getCurrentUrl()
                              .endsWith(authorModel.getName()
                                                   .toLowerCase()
                                                   .replace(" ", "-")
                                                   .trim()), "URL not contains Authors name");
        log.info(format("URL : %s contains : %s", getDriver().getCurrentUrl(), authorModel.getName()
                                                                                          .toLowerCase()
                                                                                          .replace(" ", "-")
                                                                                          .trim()));

        return this;
    }

    public EditAuthorPage clickEditTab() {

        EDIT_TAB.should(exist, appear, enabled)
                .click();
        log.info("EDIT link clicked");
        return new EditAuthorPage();
    }

    public void validateEditedAuthor(String editedName) {
        assertTrue(UPDATED_STATUS_MESSAGE.should(visible, appear)
                                         .getText()
                                         .contains("updated"), "Updated message is not displayed");
        assertTrue(UPDATED_MESSAGE_LINK.getText()
                                       .contains(editedName), "Updated term Status message not displayed with correct term name");

        log.info("Updated massage displayed after edit");
    }

}
