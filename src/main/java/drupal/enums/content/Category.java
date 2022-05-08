package drupal.enums.content;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum Category {
    DINING_DISCOUNTS("Dining Discounts"),
    MILITARY_TRAVEL_DISCOUNTS("Military Travel Discounts"),
    WASHINGTON_DC("Travel Destinations > Washington DC"),
    HOT_DEALS("Discounts > Hot Deals"),
    AMUSEMENT_PARKS("Discounts > Arts and Recreation > Amusement Parks"),
    ORLANDO("Discounts > Travel Destinations > Orlando"),
    VIDEO("Video"),
    SHOCK_AND_AWE_WORLD_WAR_2("Video > Shock and Awe > World War II"),
    MILITARY_ORIGINALS("Video > Military.com Originals"),
    MILITARY_BRIEFS("Video > Military.com Originals > Military Briefs"),
    BENEFITS("Benefits"),
    ABOUT_US("About Us");

    private final String categoryName;

    public List<Category> getList() {
        return Arrays.asList(values());
    }
}
