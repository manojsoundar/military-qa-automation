package www.enums;

import com.codeborne.selenide.SelenideElement;

import java.util.Arrays;
import java.util.List;

public interface IEnumNavigationMenu {

    static <T extends Enum<T>> List<T> getList(Class<T> aEnum) {
        return Arrays.asList(aEnum.getEnumConstants());
    }

    SelenideElement getSubMenuLink();

    List<?> getList();

}
