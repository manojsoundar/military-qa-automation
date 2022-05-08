package www.ui.components;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class Breadcrumb {

    private static final SelenideElement NAVIGATION_BREADCRUMB_WRAPPER = $("nav.breadcrumb").as("Navigation Breadcrumb");
    private static final SelenideElement HOME_LINK = NAVIGATION_BREADCRUMB_WRAPPER.$("[aria-label=Home]")
                                                                                  .as("Breadcrumb Home Link");

}
