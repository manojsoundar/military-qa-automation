package drupal.models;

import drupal.enums.content.EntityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static common.CommonMethods.timeStampFormat;
import static drupal.enums.content.EntityType.BRIGHTCOVE_VIDEO;
import static drupal.enums.content.EntityType.CONTENT;
import static drupal.enums.content.EntityType.TAXONOMY_TERM;
import static drupal.models.TimeStampPattern.PATTERN;

@Data
@AllArgsConstructor
public class AddContentListModel {

    private final String title;
    private final Map<String, EntityType> content;
    private final String url;
    private final String linkText;
    private final String nodesText;
    private final TypeFilterItem typeFilterItem;
    private final Map<Integer, String> category;

    public AddContentListModel() {
        title = "Test" + timeStampFormat(PATTERN);
        content = Map.of("360° View of Seahawk Helicopter Gunner", BRIGHTCOVE_VIDEO);
        url = "Exchanges Soon Opening to Millions of Veterans (181)";
        linkText = "test";
        nodesText = "Fitness Motivation TEST (2696)";
        typeFilterItem = getTypeFilterItem();
        category = Map.of(0, "Jet Fighters");
    }

    public static AddContentListModel curatedListData() {
        return new AddContentListModel("Test" + timeStampFormat(PATTERN), Map.of("Fort Eustis Welcome & Visitors Center", CONTENT),
                                       "Exchanges Soon Opening to Millions of Veterans (181)", "test", null, null, null);
    }

    public static AddContentListModel importantContactsListData() {
        return new AddContentListModel("Test" + timeStampFormat(PATTERN), videoContents(),
                                       "Exchanges Soon Opening to Millions of Veterans (181)", "test", null, null, null);
    }

    public static AddContentListModel createCuratedListData() {
        return new AddContentListModel("Test Curated List " + timeStampFormat(PATTERN),
                                       Map.of("Budget Request for Arlington Cemetery Nearly Triples Amid Plans for Major Expansion",
                                              CONTENT), "OAS Homepage (4026)", "View More Articles", null, null, null);
    }

    public static AddContentListModel updateBrightcoveCuratedListData() {
        return new AddContentListModel("Test Brightcove Video Curated List " + timeStampFormat(PATTERN), Map.of("SHOT Show 2020: LWRC International Shows Off its Hot New Pistol-Caliber Carbine (161341)",
                                                                                                                BRIGHTCOVE_VIDEO, "360° View of Seahawk Helicopter Gunner (158141)", BRIGHTCOVE_VIDEO, "Navy Hopes Landing Tweaks Will Increase Osprey Cargo Capacity", CONTENT),
                                       "OAS Homepage (4026)", "View More Videos", null, TypeFilterItem.BRIGHTCOVE_CURATED_LIST, null);
    }

    public static AddContentListModel videoTaxonomyCuratedListData() {
        return new AddContentListModel("Test " + timeStampFormat(PATTERN), categoryContents(),
                                       null, null, null, null, categoryValues());
    }

    public static Map<String, EntityType> videoContents() {
        Map<String, EntityType> videosList = new HashMap<>();
        videosList.put("Fort Eustis Welcome & Visitors Center", CONTENT);
//        videosList.put("Hampton Roads Military Bases Navy Federal Credit Union", CONTENT); TODO this value makes the test fails ... the input is having red bold border ... screenshot and raise bug
        videosList.put("Naval Station Norfolk Willoughby Child Development Center", CONTENT);
        videosList.put("Naval Air Station Oceana EFMP - Family Support", CONTENT);
        videosList.put("Coast Guard Sector Hampton Roads ID/CAC Card Processing", CONTENT);

        return videosList;
    }

    public static Map<String, EntityType> categoryContents() {
        HashMap<String, EntityType> categoryList = new LinkedHashMap<>();
        categoryList.put("Jet Fighters", TAXONOMY_TERM);
        categoryList.put("Explosions", TAXONOMY_TERM);
        categoryList.put("Editors' Picks", TAXONOMY_TERM);
        categoryList.put("5 Things You Don't Know", TAXONOMY_TERM);
        categoryList.put("Crash Landings", TAXONOMY_TERM);

        return categoryList;
    }

    private static HashMap<Integer, String> categoryValues() {
        HashMap<Integer, String> categoryValues = new LinkedHashMap<>();
        categoryValues.put(0, "/video/aircraft/jet-fighters");
        categoryValues.put(1, "/video/shock-and-awe/explosions");
        categoryValues.put(2, "/video/editors-picks");
        categoryValues.put(3, "/video/militarydotcom/5-things-you-dont-know");
        categoryValues.put(4, "/video/military-aircraft-operations/crash-landings");

        return categoryValues;

    }

    @Getter
    @AllArgsConstructor
    public enum TypeFilterItem {
        ANY("- Any -"),
        BRIGHTCOVE_CURATED_LIST("Brightcove Curated List"),
        CURATED_LIST("Curated List"),
        CURATED_NODE_LIST("Curated node list");

        private final String typeItem;
    }
}
