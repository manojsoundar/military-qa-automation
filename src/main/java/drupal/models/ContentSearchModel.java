package drupal.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import static drupal.models.ContentSearchModel.ContentType.ARTICLE;
import static drupal.models.ContentSearchModel.ContentType.DISCOUNT;
import static drupal.models.ContentSearchModel.ContentType.LANDING_PAGE;
import static drupal.models.ContentSearchModel.ContentType.REGISTRATION_GATE;

@Data
@AllArgsConstructor
public class ContentSearchModel {

    private final String title;
    private final ContentType contentType;
    private final PublishingStatus publishedStatus;
    private final ArticleType articleType;

    public ContentSearchModel() {
        title = "Test";
        contentType = ARTICLE;
        publishedStatus = published();
        articleType = ArticleType.ANY;
    }

    public static ContentSearchModel loginRegFormSearchData() {
        return new ContentSearchModel("Buddy Finder", REGISTRATION_GATE, published(), null);
    }

    public static ContentSearchModel expireDiscountData() {
        return new ContentSearchModel("Test Discount", DISCOUNT, published(), null);
    }

    public static ContentSearchModel editRegistrationGate() {
        return new ContentSearchModel("TestTitle", REGISTRATION_GATE, published(), null);
    }

    public static ContentSearchModel editArticleData() {
        return new ContentSearchModel("Test Article", null, published(), ArticleType.EVER_GREEN);
    }

    public static ContentSearchModel videoListViewData() {
        return new ContentSearchModel("Test Landing Page", LANDING_PAGE, published(), null);
    }

    public static ContentSearchModel landingPageSearchData() {
        return new ContentSearchModel("Test Landing Page", LANDING_PAGE, published(), null);
    }

    public static ContentSearchModel getEverGreenArticleData() {
        return new ContentSearchModel("Test Article", ARTICLE, published(), ArticleType.EVER_GREEN);
    }

    @Getter
    @AllArgsConstructor
    public enum ContentType {
        ANY("- Any -"),
        ARTICLE("Article"),
        AUTHOR("Author"),
        BASE_INSTALLATION("Base Installation"),
        CONTACT("Contact"),
        DISCOUNT("Discount"),
        EMPLOYER("Employer"),
        EQUIPMENT("Equipment"),
        EVENT("Event"),
        LANDING_PAGE("Landing page"),
        MERCHANT("Merchant"),
        NEWSLETTER_ARTICLE("Newsletter article"),
        NEWSLETTER_ISSUE("Newsletter issue"),
        REGISTRATION_GATE("Registration gate"),
        WEBINAR("Webinar");

        private final String contentTypeItem;
    }

    @Getter
    @AllArgsConstructor
    public enum PublishingStatus {

        ANY("- Any -"),
        PUBLISHED("Published"),
        UNPUBLISHED("Unpublished");

        private final String publishingStatusType;
    }

    public static PublishingStatus published() {
        return PublishingStatus.PUBLISHED;
    }

    @Getter
    @AllArgsConstructor
    public enum ArticleType {
        ANY("- Any -"),
        ADVERTORIAL("Advertorial"),
        BLOG("Blog"),
        EVER_GREEN("Evergreen"),
        NEWS("News"),
        PRESS_RELEASE("Press Release");

        private final String articleTypeItem;

    }
}
