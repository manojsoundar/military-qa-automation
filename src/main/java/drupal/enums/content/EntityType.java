package drupal.enums.content;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum EntityType {
    BRIGHTCOVE_VIDEO("Brightcove Video"),
    MEDIA("Media"),
    CONTENT("Content"),
    TAXONOMY_TERM("Taxonomy term"),
    SNIPPET("Snippet");

    private final String entityTypeItem;
}

