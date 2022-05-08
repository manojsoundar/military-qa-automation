package drupal.enums.content;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EquipmentCategory {

    ELECTRONICS("Electronics"),
    MILITARY_VEHICLES("Military Vehicles"),
    ELECTRONICS_RADARS("Equipment > Electronics > Radars"),
    MILITARY_AIRCRAFT_BOMBERS("Equipment > Military Aircraft > Bombers"),
    ORDNANCE("Equipment > Ordnance");

    private final String categoryName;
}