package drupal.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewsCredModel {

    private final String searchValue;
    private final String published;
    private final String reviewed;
    private final String startDate;
    private final String endDate;

}
