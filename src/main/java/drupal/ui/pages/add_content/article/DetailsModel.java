package drupal.ui.pages.add_content.article;

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
import static com.codeborne.selenide.Condition.visible;
import static drupal.ui.pages.add_content.article.CreateArticlePage.CATEGORIES_AVAILABLE_LIST;
import static drupal.ui.pages.add_content.article.CreateArticlePage.CATEGORIES_INPUT;
import static drupal.ui.pages.add_content.article.CreateArticlePage.RELATED_TOPICS_INPUT;
import static drupal.ui.pages.add_content.article.CreateArticlePage.RELATED_TOPICS_LIST;
import static drupal.ui.pages.add_content.article.EditArticlePage.CATEGORIES_CLOSE_ICONS_LIST;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@Log4j2
public class DetailsModel extends MasterPage implements ISectionDataModel {

    private List<String> categories;
    private List<String> relatedKeywords;
    boolean deleteCategories;

    public static DetailsModel setEverGreenData() {
        return new DetailsModel(List.of("GI Bill", "Education", "Money"), List.of("GI Bill", ",Spouse Employment", ",Military Retirement"), false);
    }

    public static DetailsModel setNewsArticleData() {
        return new DetailsModel(List.of("Daily News"), List.of(",Military Headlines", ",Marine Corps Uniforms", ",Military Retirement"), false);
    }

    public static DetailsModel setAdvertorialArticleData() {
        return new DetailsModel(List.of("VA Loans"), List.of(",VA Loan", ",Life Insurance", ",Military Retirement"), false);
    }

    public static DetailsModel setPressReleaseArticleData() {
        return new DetailsModel(null, List.of(",test keyword"), false);
    }

    public static DetailsModel setAdvertorialNoLeadImageArticleData() {
        return new DetailsModel(List.of("VA Loans"), List.of(",VA Loan", ",Life Insurance", ",Military Retirement"), false);
    }

    public static DetailsModel setArticleNewsUnPublishOffDutyArticleData() {
        return new DetailsModel(List.of("Off Duty > Movies"), List.of("Military Headlines", ",Movies"), false);
    }

    public static DetailsModel setUnPublishDailyNewsArticleData() {
        return new DetailsModel(List.of("Daily News"), List.of("Military Headlines", ",Marine Corps Topics", ",Military Retirement"), false);
    }

    public static DetailsModel setUnPublishArticleOtherData() {
        return new DetailsModel(List.of("Money > Personal Finance"), List.of("Military Headlines", ",Marine Corps Topics", ",Military Retirement"), false);
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        fillCategories(expectedClass);
        fillRelatedKeywords(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillCategories(Class<P> expectedClass) {

        if (isDeleteCategories()) {
            CATEGORIES_CLOSE_ICONS_LIST.forEach(SelenideElement::click);
        }

        if (categories != null) {
            for (String category : categories) {
                CATEGORIES_INPUT.should(appear, enabled)
                                .click();
                CATEGORIES_INPUT.setValue(category);

                if (!CATEGORIES_AVAILABLE_LIST.isEmpty()) {
                    // TODO review algo with break usage
                    for (SelenideElement availableCategory : CATEGORIES_AVAILABLE_LIST) {
                        if (availableCategory.getText()
                                             .trim()
                                             .contains(category)) {
                            availableCategory.should(enabled, visible)
                                             .click();
                            break;
                        }
                    }
                }

                log.info("Category selected");
            }
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillRelatedKeywords(Class<P> expectedClass) {
        if (relatedKeywords != null) {
            for (String relatedTopic : relatedKeywords) {
                RELATED_TOPICS_INPUT.should(enabled, visible)
                                    .sendKeys(relatedTopic);
                waitAjaxJQueryMet(150);
                if (!RELATED_TOPICS_LIST.isEmpty()) {
                    for (SelenideElement availableRelatedKeyword : RELATED_TOPICS_LIST) {
                        if (availableRelatedKeyword.getText()
                                                   .trim()
                                                   .contains(relatedTopic)) {
                            availableRelatedKeyword.should(enabled, visible)
                                                   .click();
                            break;
                        }
                    }
                }
                log.info("Related topic selected");
            }
        }
        return returnInstanceOf(expectedClass);
    }

}
