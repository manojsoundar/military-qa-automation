package www.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServiceType {

    AIR_FORCE(1, "Air Force"),
    ARMY(2, "Army"),
    NONE(9, "None");

    private final int value;
    private final String name;
}
