package drupal.ui.pages.add_content.article;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.ui.pages.add_content.article.CreateArticlePage.ARTICLE_TYPE_DROPDOWN;
import static drupal.ui.pages.add_content.article.CreateArticlePage.BODY_IFRAME;
import static drupal.ui.pages.add_content.article.CreateArticlePage.BODY_INPUT;
import static drupal.ui.pages.add_content.article.CreateArticlePage.SIDEBAR_DROP_DOWN;
import static drupal.ui.pages.add_content.article.CreateArticlePage.SIDE_BAR_CONTAINER_LIST;
import static drupal.ui.pages.add_content.article.CreateArticlePage.SIDE_BAR_INPUT;
import static drupal.ui.pages.add_content.article.CreateArticlePage.SUMMARY_INPUT;
import static drupal.ui.pages.add_content.article.CreateArticlePage.TITLE_INPUT;
import static drupal.ui.pages.add_content.article.EditArticlePage.PUBLISHED_CHECKBOX;
import static drupal.ui.pages.add_content.article.EditArticlePage.URL_ALIAS_CHECKBOX;
import static drupal.ui.pages.add_content.article.EditArticlePage.URL_ALIAS_INPUT;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Log4j2
public class ArticleModel extends MasterPage implements ISectionDataModel {

    protected static final String DEFAULT_SUMMARY_TEXT = "This is a test News article on Military.com. This article was built as part of the QA Automation work being completed for Military.com";

    private String title;
    private String articleType;
    private String summary;
    private String summaryBody;
    private String sidebar;
    private boolean unPublishArticle;
    private String urlAlias;

    public static ArticleModel setEverGreenArticleData() {
        return new ArticleModel(getArticleTitle(), "Evergreen", getSummaryText(), getSummaryBodyData(), "GI Bill", false, null);
    }

    public static ArticleModel setNewsArticleData() {
        return new ArticleModel(getArticleTitle(), "News", getSummaryText(), getSummaryBodyData(), "Daily News", false, null);
    }

    public static ArticleModel setAdvertorialArticleData() {
        return new ArticleModel(getArticleTitle(), "Advertorial", getSummaryText(), getSummaryBodyData(), "OAS Homepage", false, null);
    }

    public static ArticleModel setPressReleaseArticleData() {
        return new ArticleModel(getArticleTitle(), "Press Release", getSummaryText(), getSummaryBodyData(), "Press Release", false, null);
    }

    public static ArticleModel setAdvertorialNoLeadImageArticleData() {
        return new ArticleModel(getArticleTitle(), "Evergreen", getSummaryText(), getSummaryBodyData(), "Personal Finance | Sidebar", false, null);
    }

    public static ArticleModel setArticleNewsUnPublishOffDutyArticleData() {
        return new ArticleModel(getArticleTitle(), "News", getSummaryText(), getSummaryBodyData(), "Movies", false, null);
    }

    public static ArticleModel setUnPublishDailyNewsArticleData() {
        return new ArticleModel(getArticleTitle(), "News", getSummaryText(), getSummaryBodyData(), "Daily News", false, null);
    }

    public static ArticleModel setUnPublishArticleOtherData() {
        return new ArticleModel(getArticleTitle(), "Evergreen", getSummaryText(), getSummaryBodyData(), "Personal Finance | Sidebar", false, null);
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    public static String getSummaryBodyData() {

        StringBuilder builder = new StringBuilder();

        builder.append("What is Lorem Ipsum?\n");
        builder.append("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.\n");
        builder.append("It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.\n");
        builder.append("It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n");
        builder.append("Why do we use it?\n");
        builder.append("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy.\n");
        builder.append("Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\n");
        builder.append("Where does it come from?\n");
        builder.append("Contrary to popular belief, Lorem Ipsum is not simply random text.\n");
        builder.append("It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source.\n");
        builder.append("Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\n");
        builder.append("The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.\n");
        builder.append("Where can I get some?\n");
        builder.append("There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable.\n");
        builder.append("If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text.\n");
        builder.append("All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable.\n");
        builder.append("The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.");

        return builder.toString();
    }

    public static String getSummaryText() {
        return DEFAULT_SUMMARY_TEXT;
    }

    public static String getArticleTitle() {
        return "Test Article " + timeStampFormat(PATTERN);
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        fillTitle(expectedClass);
        fillArticleType(expectedClass);
        fillSummary(expectedClass);
        fillSummaryBody(expectedClass);
        fillSidebar(expectedClass);
        fillUnPublishArticle(expectedClass);
        fillURLAlias(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillTitle(Class<P> expectedClass) {
        if (title != null) {
            TITLE_INPUT.should(enabled, visible)
                       .setValue(title);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillArticleType(Class<P> expectedClass) {
        if (articleType != null) {
            ARTICLE_TYPE_DROPDOWN.should(enabled, visible)
                                 .selectOption(articleType);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillSummary(Class<P> expectedClass) {
        if (summary != null) {
            SUMMARY_INPUT.should(enabled, visible)
                         .setValue(summary);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillSummaryBody(Class<P> expectedClass) {
        if (summaryBody != null) {
            switchTo().frame(BODY_IFRAME);
            BODY_INPUT.should(enabled, visible)
                      .sendKeys(summaryBody);
            switchTo().parentFrame();
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillSidebar(Class<P> expectedClass) {
        if (sidebar != null) {
            SIDEBAR_DROP_DOWN.should(exist, appear)
                             .click();
            log.info("Sidebar dropdown clicked");

            SIDE_BAR_INPUT.should(appear, enabled)
                          .setValue(sidebar);
            if (!SIDE_BAR_CONTAINER_LIST.isEmpty()) {
                for (SelenideElement sb : SIDE_BAR_CONTAINER_LIST) {
                    if (sb.getText()
                          .trim()
                          .contains(sidebar)) {
                        sb.should(enabled, visible)
                          .click();
                        break;
                    }

                }
                log.info("Sidebar selected");
            }
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillUnPublishArticle(Class<P> expectedClass) {
        if (isUnPublishArticle()) {

            if (getDriver().getCurrentUrl()
                           .contains("off-duty")) {
                log.info("Un publish off duty article ");
            } else {
                log.info("Un publish article ");
            }

            PUBLISHED_CHECKBOX.should(exist, enabled)
                              .scrollIntoView("{behavior: 'instant', block: 'center', inline: 'center'}")
                              .click();
            log.info("Article unpublished...");
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillURLAlias(Class<P> expectedClass) {

        if (urlAlias != null) {
            URL_ALIAS_CHECKBOX.should(exist, enabled)
                              .scrollIntoView("{behavior: 'instant', block: 'center', inline: 'center'}")
                              .click();
            String urlAliasReplace = URL_ALIAS_INPUT.should(appear, exist)
                                                    .getValue()
                                                    .replace("/money/personal-finance", urlAlias);
            URL_ALIAS_INPUT.should(exist, enabled)
                           .setValue(urlAliasReplace);
        }

        return returnInstanceOf(expectedClass);
    }
}
