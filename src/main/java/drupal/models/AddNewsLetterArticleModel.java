package drupal.models;

import lombok.Data;

import java.util.Map;

@Data
public class AddNewsLetterArticleModel {

    private final String title;
    private final String source;
    private final String blurb;
    private final String body;
    private final Map<String, String> category;
    private final String relatedTopics;
    private final String contentRelease;

    public AddNewsLetterArticleModel() {
        title = "Test Newsletter Article";
        source = "";
        blurb = "";
        body = "Newsletter Article Body";
        category = Map.of("Category", "Benefits");
        relatedTopics = "";
        contentRelease = "";
    }

}
