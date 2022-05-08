package drupal.ui.pages.structure.taxonomy.categories;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import drupal.ui.pages.add_content.landing_page.EditLandingPage;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class CategoriesTermLandingPage extends AdministrationToolbar {

    public static final SelenideElement EDIT_TAB = $("div.block--tabs li a[href*='/edit']").as("Edit tab button");
    private static final String URL_REGEX = Configuration.baseUrl;
    private static final SelenideElement CATEGORIES_TERM_H_1_TAG = $(".block--page--title h1 span");
    private static final SelenideElement UPDATED_TITLE = $(".messages--status a").as("Updated title link");
    private final String currentDiscountURL = getDriver().getCurrentUrl();

    public CategoriesTermLandingPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Categories Term Result Page not loaded.");
        log.info("Categories Term Result Page loaded properly.");
    }

    public CategoriesTermLandingPage verifyLandingPage(String urlAlias, String title) {

        assertTrue(currentDiscountURL.contains(urlAlias), "Incorrect Landing Page");
        assertTrue(CATEGORIES_TERM_H_1_TAG.getText()
                                          .contains(title));
        return this;
    }

    public CategoriesTermLandingPage verifyUpdatedData(String title) {
        assertTrue(UPDATED_TITLE.getText()
                                .contains(title), "Title was not updated!!");
        return this;
    }

    public EditLandingPage clickOnEditTab() {
        EDIT_TAB.should(exist, appear)
                .click();
        return new EditLandingPage();
    }

}
