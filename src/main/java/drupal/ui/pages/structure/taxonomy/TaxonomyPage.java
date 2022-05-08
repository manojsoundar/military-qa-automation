package drupal.ui.pages.structure.taxonomy;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.assertTrue;

@Log4j2
public class TaxonomyPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/admin/structure/taxonomy";
    private static final SelenideElement TAXONOMY_PAGE_H_1_TAG = $("#block-pagetitle h1").as("Taxonomy Page H1 Tag");

    public TaxonomyPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Taxonomy Page not loaded.");
        assertTrue(TAXONOMY_PAGE_H_1_TAG.should(exist, appear)
                                        .has(text("Taxonomy")), "Taxonomy title: " + TAXONOMY_PAGE_H_1_TAG.getText() + "not displayed,should contain('Taxonomy')");
        log.info("Taxonomy Page loaded properly.");
    }

    public <P extends MasterPage> P clickTaxonomyType(TaxonomyVocabularyType taxonomyVocabularyType, TaxonomyOperationType taxonomyOperationType, Class<P> expectedClass) {
        assertTrue(taxonomyVocabularyType.getTaxonomyType()
                                         .should(exist, appear)
                                         .exists(), taxonomyVocabularyType.name() + " is not displayed.");
        log.info(taxonomyVocabularyType.name() + " is displayed");

        taxonomyVocabularyType.getTaxonomyType()
                              .$x("following-sibling::td//" + taxonomyOperationType.getOperationType())
                              .should(visible, enabled)
                              .click();

        return returnInstanceOf(expectedClass);
    }

    @Getter
    @AllArgsConstructor
    public enum TaxonomyVocabularyType {

        BASE_GUIDES($x("//td[text()='Base Guides']")),
        BASE_SERVICES_CATEGORY($x("//td[text()='Base services category']")),
        BLOGS($x("//td[text()='Blogs']")),
        BRIGHTCOVE_VIDEO_TAGS($x("//td[text()='Brightcove video tags']")),
        CATEGORIES($x("//td[text()='Categories']")),
        CONTENT_RELEASE($x("//td[text()='Content release']")),
        KEYWORDS($x("//td[text()='Keywords']")),
        LOCATION($("//td[text()='Location']")),
        NEWSLETTERS($x("//td[text()='Newsletters']")),
        SERVICES($x("//td[text()='Services']")),
        VETERAN_INDUSTRIES($x("//td[text()='Veteran Industries']"));

        private final SelenideElement taxonomyType;
    }

    @Getter
    @AllArgsConstructor
    public enum TaxonomyOperationType {

        LIST_TERMS_LINK("li//a[text()='List terms']"),
        ADD_TERMS_LINK("li//a[text()='Add terms']");

        private final String operationType;
    }
}
