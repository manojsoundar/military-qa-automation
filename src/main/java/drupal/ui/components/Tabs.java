package drupal.ui.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Tabs {

    private static final SelenideElement TABS = $x("//div[@id='block-tabs'] | //ul[@class='menu menu--tabs']").as("Drupal Tabs");
    private static final SelenideElement VIEW_TAB = TABS.$x(".//a[text(), 'View']")
                                                        .as("View tab");
    private static final SelenideElement EDIT_TAB = TABS.$x(".//a[@href='edit']")
                                                        .as("Edit tab");
    private static final SelenideElement REVISIONS_TAB = TABS.$x(".//a[@href='revisions']")
                                                             .as("Edit tab");

}
