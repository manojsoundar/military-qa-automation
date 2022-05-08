package www.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import www.enums.IEnumNavigationMenu;
import www.enums.MainMenu;

import javax.annotation.Nullable;

@Data
@AllArgsConstructor
public class NavigationDataModel<P> {

    protected MainMenu mainMenuItem;
    protected @Nullable
    IEnumNavigationMenu subMenuItem;
    protected Class<P> expectedClass;

}
