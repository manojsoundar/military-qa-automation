package drupal.ui.pages.structure.eck_entity_types;

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
public class ECKEntityTypesPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/admin/structure/eck";
    private static final SelenideElement ECK_ENTITY_TYPES_H_1_TAG = $("#block-pagetitle h1").as("ECK Entity Types Page H1 Tag");
    private static final SelenideElement ADD_ENTITY_TYPE_BUTTON = $("#block-primaryadminactions a[href*='/add']").as("Add Entity Type Button");

    public ECKEntityTypesPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "ECK Entity Types Page not loaded.");
        assertTrue(ECK_ENTITY_TYPES_H_1_TAG.should(exist, appear)
                                           .has(text("ECK Entity Types")), "ECK entity title:" + ECK_ENTITY_TYPES_H_1_TAG.getText() + "not displayed,should contain('ECK entity types')");
        log.info("ECK Entity Types Page loaded properly.");
    }

    public <P extends MasterPage> P clickEntityType(ECKEntityType entityType, ECKEntityOperationType operationType, Class<P> expectedClass) {
        assertTrue(entityType.getEntityType()
                             .should(exist, appear)
                             .exists(),
                entityType.name() + " is not displayed.");
        log.info(entityType.name() + " is displayed");

        SelenideElement addContentDropdownButton = entityType.getEntityType()
                                                             .$x("following-sibling::td//li[@class='dropbutton-toggle']");
        addContentDropdownButton.should(visible, enabled)
                                .click();
        SelenideElement addContentButton = entityType.getEntityType()
                                                     .$x("following-sibling::td//" + operationType.getOperationEntityType() + "/a");
        addContentButton.should(visible, enabled)
                        .click();

        return returnInstanceOf(expectedClass);
    }

    @Getter
    @AllArgsConstructor
    public enum ECKEntityType {
        CALL_TO_ACTION($x("//td[text()='Call to Action']")),
        CONTENT_LIST($x("//td[text()='Content list']")),
        FLEXIBLE_SPACE($x("//td[text()='Flexible space']")),
        ICON($x("//td[text()='Icon']")),
        SLIDESHOW($x("//td[text()='Slideshow']")),
        SNIPPET($x("//td[text()='Snippet']")),
        SOURCE($x("//td[text()='Source']")),
        THYMELEAF_TEMPLATE($("//td[text()='Thymeleaf Template']")),
        WIDGET($x("//td[text()='Widget']"));

        private final SelenideElement entityType;
    }

    @Getter
    @AllArgsConstructor
    public enum ECKEntityOperationType {

        ADD_CONTENT("li[@class='add-content dropbutton-action']"),
        CONTENT_LIST("li[@class='content-list dropbutton-action secondary-action']"),
        BUNDLE_LIST("li[@class='bundle-list dropbutton-action secondary-action']"),
        EDIT("li[@class='edit dropbutton-action secondary-action']"),
        DELETE("li[@class='delete dropbutton-action secondary-action']");

        private final String operationEntityType;
    }
}
