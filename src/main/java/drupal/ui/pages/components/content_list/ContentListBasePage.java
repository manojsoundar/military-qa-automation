package drupal.ui.pages.components.content_list;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class ContentListBasePage extends AdministrationToolbar {

    protected static final SelenideElement TITLE_INPUT = $("input#edit-title-0-value");
    protected static final SelenideElement URL_INPUT = $("input#edit-field-link-0-uri");
    protected static final SelenideElement LINK_INPUT = $("input#edit-field-link-0-title");
    protected static final SelenideElement CONTENT_LIST_SAVE_BUTTON = $("input#edit-submit[value=Save]");

}
