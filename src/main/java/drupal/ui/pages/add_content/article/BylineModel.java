package drupal.ui.pages.add_content.article;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.DATE_PATTERN;
import static drupal.ui.pages.add_content.article.CreateArticlePage.AUTHOR_INPUT;
import static drupal.ui.pages.add_content.article.CreateArticlePage.AUTHOR_ITEM_LIST;
import static drupal.ui.pages.add_content.article.CreateArticlePage.CONTRIBUTOR_INPUT;
import static drupal.ui.pages.add_content.article.CreateArticlePage.DISPLAY_DATE_INPUT;
import static drupal.ui.pages.add_content.article.CreateArticlePage.SOURCE_INPUT;
import static drupal.ui.pages.add_content.article.CreateArticlePage.SOURCE_ITEM_LIST;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BylineModel extends MasterPage implements ISectionDataModel {

    protected static final String DEFAULT_SOURCE_DATA = "Military.com";

    private final String source;
    private final String displayDate;
    private final String author;
    private final String contributor;

    public static BylineModel setEvergreenArticleData() {
        return new BylineModel(getSourceData(), timeStampFormat(DATE_PATTERN), "Ho Lin", null);
    }

    public static BylineModel setNewsArticleData() {
        return new BylineModel(getSourceData(), timeStampFormat(DATE_PATTERN), "Sarah Blansett", "Joseph Little and Peter Gates");
    }

    public static BylineModel setAdvertorialArticleData() {
        return new BylineModel(getSourceData(), timeStampFormat(DATE_PATTERN), null, "Joseph Little, Robert Cotton, and Peter Gates");
    }

    public static BylineModel setPressReleaseArticleData() {
        return new BylineModel(getSourceData(), timeStampFormat(DATE_PATTERN), null, "Joseph Little, Robert Cotton, and Peter Gates");
    }

    public static BylineModel setAdvertorialNoLeadImageArticleData() {
        return new BylineModel(getSourceData(), timeStampFormat(DATE_PATTERN), "Sarah Blansett", "Joseph Little and Peter Gates");
    }

    public static BylineModel setArticleNewsUnPublishOffDutyArticleData() {
        return new BylineModel(getSourceData(), timeStampFormat(DATE_PATTERN), "Sarah Blansett", "Joseph Little and Peter Gates");
    }

    public static BylineModel setUnPublishDailyNewsArticleData() {
        return new BylineModel(getSourceData(), timeStampFormat(DATE_PATTERN), "Sarah Blansett", "Joseph Little and Peter Gates");
    }

    public static BylineModel setUnPublishArticleOtherData() {
        return new BylineModel(getSourceData(), timeStampFormat(DATE_PATTERN), "Sarah Blansett", "Joseph Little and Peter Gates");
    }

    public static String getSourceData() {
        return DEFAULT_SOURCE_DATA;
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        fillSource(expectedClass);
        fillDisplayDate(expectedClass);
        fillAuthor(expectedClass);
        fillContributor(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillSource(Class<P> expectedClass) {
        if (source != null) {
            SOURCE_INPUT.should(enabled, visible)
                        .setValue(source);
            if (!SOURCE_ITEM_LIST.isEmpty()) {
                for (SelenideElement sourceItem : SOURCE_ITEM_LIST) {
                    if (sourceItem.getText()
                                  .trim()
                                  .contains(source)) {
                        sourceItem.should(enabled, visible)
                                  .click();
                        break;
                    }
                }
            }
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillDisplayDate(Class<P> expectedClass) {
        if (displayDate != null) {
            DISPLAY_DATE_INPUT.should(enabled, visible)
                              .sendKeys(displayDate);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillAuthor(Class<P> expectedClass) {
        if (author != null) {
            AUTHOR_INPUT.should(enabled, visible)
                        .setValue(author);
            if (!AUTHOR_ITEM_LIST.isEmpty()) {
                for (SelenideElement authorItem : AUTHOR_ITEM_LIST) {
                    if (authorItem.getText()
                                  .contains(author)) {
                        authorItem.should(enabled, visible)
                                  .click();
                        break;
                    }
                }
            }
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillContributor(Class<P> expectedClass) {
        if (contributor != null) {
            CONTRIBUTOR_INPUT.should(enabled, visible)
                             .setValue(contributor);
        }
        return returnInstanceOf(expectedClass);

    }
}
