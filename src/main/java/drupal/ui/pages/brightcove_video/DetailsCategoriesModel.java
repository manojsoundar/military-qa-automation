package drupal.ui.pages.brightcove_video;

import com.codeborne.selenide.SelenideElement;
import drupal.enums.content.Category;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static drupal.ui.pages.brightcove_video.EditBrightcoveVideoPage.BREADCRUMB_CATEGORY_DROPDOWN;
import static drupal.ui.pages.brightcove_video.EditBrightcoveVideoPage.CATEGORIES_AVAILABLE_LIST;
import static drupal.ui.pages.brightcove_video.EditBrightcoveVideoPage.CATEGORIES_CONTAINER_WEB_ELEMENT;
import static drupal.ui.pages.brightcove_video.EditBrightcoveVideoPage.CATEGORIES_INPUT;
import static drupal.ui.pages.brightcove_video.EditBrightcoveVideoPage.LONG_DESCRIPTION_INPUT;
import static drupal.ui.pages.brightcove_video.EditBrightcoveVideoPage.RELATED_TOPICS_INPUT;
import static drupal.ui.pages.brightcove_video.EditBrightcoveVideoPage.REMOVE_CATEGORY_CLOSE_ICONS_LIST;

@Getter
@AllArgsConstructor
public class DetailsCategoriesModel extends MasterPage implements ISectionDataModel {

    private final List<Category> categoriesList;
    private final BreadcrumbCategory breadcrumbCategory;
    private final String relatedTopics;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        addCategories();
        selectBreadcrumbCategory();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private DetailsCategoriesModel addCategories() {
        if (!categoriesList.isEmpty()) {
            LONG_DESCRIPTION_INPUT.should(appear)
                                  .scrollTo();
            if (!REMOVE_CATEGORY_CLOSE_ICONS_LIST.isEmpty()) {
                REMOVE_CATEGORY_CLOSE_ICONS_LIST.forEach(SelenideElement::click);
            }
            for (Category category : categoriesList) {
                CATEGORIES_INPUT.should(exist, appear, enabled)
                                .click();
                CATEGORIES_INPUT.setValue(category.getCategoryName());
                if (CATEGORIES_CONTAINER_WEB_ELEMENT.should(appear, visible)
                                                    .isDisplayed()) {
                    CATEGORIES_AVAILABLE_LIST.first()
                                             .should(appear, exist)
                                             .click();
                }
            }
        }
        return this;
    }

    public DetailsCategoriesModel selectBreadcrumbCategory() {
        if (breadcrumbCategory.getBreadcrumbCategoryItem() != null) {
            BREADCRUMB_CATEGORY_DROPDOWN.should(exist, appear, enabled)
                                        .selectOptionContainingText(breadcrumbCategory.getBreadcrumbCategoryItem());
        }
        return this;
    }

    public DetailsCategoriesModel enterRelatedTopics() {
        if (relatedTopics != null) {
            RELATED_TOPICS_INPUT.should(exist, appear, enabled)
                                .selectOptionContainingText(relatedTopics);
        }
        return this;
    }


    @Getter
    @AllArgsConstructor
    public enum BreadcrumbCategory {
        NONE("- None -"),
        BENEFITS("Benefits"),
        BENEFITS_MEMORIAL_BENEFITS("Benefits > Memorial Benefits"),
        VIDEO_MILITARY_LIFE("Video > Military Life"),
        VIDEO_MILITARY_MILITARY_BRIEFS("Video > Military.com Originals > Military Briefs"),
        MILITARY_UNIFORMS("Video > Military Life > Uniforms");

        private final String breadcrumbCategoryItem;
    }

}
