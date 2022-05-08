package drupal.ui.pages.add_content.ap_dashboard;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static drupal.ui.pages.add_content.ap_dashboard.APDashboardArticlePage.CATEGORIES_AVAILABLE_LIST;
import static drupal.ui.pages.add_content.ap_dashboard.APDashboardArticlePage.CATEGORY_INPUT;
import static drupal.ui.pages.add_content.ap_dashboard.APDashboardArticlePage.RELATED_TOPICS_INPUT;
import static drupal.ui.pages.add_content.ap_dashboard.APDashboardArticlePage.RELATED_TOPICS_LIST;
import static drupal.ui.pages.add_content.ap_dashboard.APDashboardArticlePage.SIDE_BAR_CONTAINER_LIST;
import static drupal.ui.pages.add_content.ap_dashboard.APDashboardArticlePage.SIDE_BAR_DEFAULT_SELECTED_WEB_ELEMENT;
import static drupal.ui.pages.add_content.ap_dashboard.APDashboardArticlePage.SIDE_BAR_INPUT;

@SuppressWarnings("ALL")
@Log4j2
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class APNewsDashBoardModel extends MasterPage implements ISectionDataModel {

    private final String searchInput;
    private final String source;
    private final String contributor;
    private final List<String> sidebar;
    private final List<String> category;
    private final List<String> relatedTopics;
    private final boolean existingImage;

    public APNewsDashBoardModel() {

        searchInput = "AP Top 25 Podcast";
        source = "Associated Press (16)";
        contributor = "RALPH D. RUSSO";
        sidebar = List.of("Test QA Automation");
        category = List.of("Daily News");
        relatedTopics = List.of("test keyword");
        existingImage = true;

    }

    public static APNewsDashBoardModel articleWithoutImage() {
        return new APNewsDashBoardModel(
                "AP Top Political News",
                "Associated Press (16)",
                null,
                List.of("Test QA Automation"),
                List.of("Daily News"),
                List.of("test keyword"),
                false
        );
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        fillSidebar(expectedClass);
        fillRelatedTopics(expectedClass);
        fillCategories(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    // TODO to review algo
    // TODO incremental syntax
    public <P extends MasterPage> P fillSidebar(Class<P> expectedClass) {
        SIDE_BAR_DEFAULT_SELECTED_WEB_ELEMENT.should(exist, appear)
                                             .click();
        log.info("Sidebar dropdown clicked");

        if (sidebar != null) {
            int i = 0;
            for (String sb : sidebar) {
                SIDE_BAR_INPUT.should(appear, enabled)
                              .setValue(sb);
                log.info("Sidebar input inserted");
                if (!SIDE_BAR_CONTAINER_LIST.isEmpty()) {
                    for (SelenideElement availableSidebar : SIDE_BAR_CONTAINER_LIST) {
                        if (availableSidebar.should(enabled, visible)
                                            .getText()
                                            .trim()
                                            .contains(sb)) {
                            availableSidebar.click();
                            log.info("Number of iteration : " + (i + 1));
                            i = i++;
                            break;
                        }
                    }
                } else {
                    log.info("Number of iteration : " + (i + 1));
                }
            }
        }
        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P fillCategories(Class<P> expectedClass) {
        if (category != null) {
            int i = 0;
            for (String cat : category) {
                CATEGORY_INPUT.should(appear, enabled)
                              .click();
                CATEGORY_INPUT.should(appear, enabled)
                              .setValue(cat);

                log.info("Category input inserted");
                if (!CATEGORIES_AVAILABLE_LIST.isEmpty()) {
                    for (SelenideElement availableCategory : CATEGORIES_AVAILABLE_LIST) {
                        if (availableCategory.should(enabled, visible)
                                             .getText()
                                             .trim()
                                             .contains(cat)) {
                            availableCategory.click();
                            log.info("Number of iteration : " + (i + 1));
                            i = i++;
                            break;
                        }
                    }
                } else {
                    log.info("Number of iteration : " + (i + 1));
                }
            }
        }
        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P fillRelatedTopics(Class<P> expectedClass) {
        if (relatedTopics != null) {
            int i = 0;
            for (String rt : relatedTopics) {
                RELATED_TOPICS_INPUT.should(enabled, visible)
                                    .setValue(rt);
                RELATED_TOPICS_INPUT.click();
                log.info("Related topics input inserted");
                Selenide.executeJavaScript("return window.jQuery != undefined && jQuery.active == 0");
                if (!RELATED_TOPICS_LIST.isEmpty()) {
                    for (SelenideElement availableRelatedTopic : RELATED_TOPICS_LIST) {
                        if (availableRelatedTopic.should(visible, enabled)
                                                 .getText()
                                                 .trim()
                                                 .contains(rt)) {
                            availableRelatedTopic.click();
                            log.info("Number of iteration : " + (i + 1));
                            i = i++;
                            break;
                        }
                    }
                } else {
                    log.info("Number of iteration : " + (i + 1));
                }
            }
        }
        return returnInstanceOf(expectedClass);
    }

}
