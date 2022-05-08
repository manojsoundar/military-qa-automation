package drupal.ui.pages.add_content.flexible_content_space;

import drupal.enums.content.FlexibleContentSpacePosition;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static drupal.enums.content.FlexibleContentSpaceType.ADD_FEATURED_ARTICLE;
import static java.lang.String.format;

@Getter
@AllArgsConstructor
public class FlexibleContentSpaceFeaturedArticleModel extends MasterPage implements ISectionDataModel, IFlexibleContentSpace {

    private static final String FLEXIBLE_CONTENT_SPACE_FEATURED_ARTICLE_TITLE_INPUT = "input.form-text[id*=-%s-%s-subform-field-title]";
    private static final String FLEXIBLE_CONTENT_SPACE_FEATURED_ARTICLE_ARTICLE_INPUT = "input.form-text[id*=-%s-%s-subform-field-article]";

    private final FlexibleContentSpacePosition position;
    private int index;
    private final String featuredArticleTitle;
    private final String featuredArticleArticle;


    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        index = selectFlexibleContentSpace(ADD_FEATURED_ARTICLE, position) - 1;
        enterFeaturedArticle();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();
        clickEditFlexibleContentSpace(position, index);
        enterFeaturedArticle();
        return returnInstanceOf(expectedClass);
    }

    private FlexibleContentSpaceFeaturedArticleModel enterFeaturedArticle() {
        if (featuredArticleTitle != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_FEATURED_ARTICLE_TITLE_INPUT, position.getFlexibleContentPosition(), index)).should(appear, enabled)
                                                                                                                        .setValue(featuredArticleTitle);
        }

        if (featuredArticleArticle != null) {
            $(format(FLEXIBLE_CONTENT_SPACE_FEATURED_ARTICLE_ARTICLE_INPUT, position.getFlexibleContentPosition(), index)).should(appear, enabled)
                                                                                                                          .setValue(featuredArticleArticle);
        }
        return this;
    }

}


