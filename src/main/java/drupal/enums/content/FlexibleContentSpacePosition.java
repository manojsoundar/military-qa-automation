package drupal.enums.content;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.enums.ComparableEnum;

import static com.codeborne.selenide.Selenide.$;

@Getter
@AllArgsConstructor
public enum FlexibleContentSpacePosition implements ComparableEnum {
    TOP("top", $("div#edit-field-flexible-top-wrapper .dropbutton-arrow")),
    ABOVE("above", $("div#edit-field-flexible-above-wrapper .dropbutton-arrow")),
    BELOW("below", $("div#edit-field-flexible-below-wrapper .dropbutton-arrow")),
    SECONDARY("secondary", $("div#edit-field-flexible-secondary-wrapper .dropbutton-arrow")),
    COLUMN_CONTENT("secondary", $(".field--name-field-columns-two-content .dropbutton-arrow")),
    CONTENT("content", $("div#edit-field-content-wrapper .dropbutton-arrow")),
    SIDEBAR("sidebar", $("div#edit-field-flexible-below-wrapper .dropbutton-arrow")),
    ABOVE_FOLD("above-fold", $("div#edit-field-flexible-above-wrapper .dropbutton-arrow"));

    private final String flexibleContentPosition;
    private final SelenideElement flexibleContentPositionElement;

}