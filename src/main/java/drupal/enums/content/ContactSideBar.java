package drupal.enums.content;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum ContactSideBar {

    NONE(0, "- None -"),
    HOMEPAGE(1, "Homepage | Sidebar"),
    BENEFITS(6, "Benefits | Sidebar"),
    EQUIPMENT(16, "Equipment | Sidebar"),
    EDUCATION(21, "Education | Sidebar");

    private final int value;
    private final String sideBarName;

    public List<ContactSideBar> getList() {
        return Arrays.asList(values());
    }

}
