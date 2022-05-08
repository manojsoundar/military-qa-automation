package drupal.ui.pages.add_content.registration_gate;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;
import static drupal.ui.pages.add_content.registration_gate.CreateRegistrationGatePage.CONTENT_RELEASE;
import static drupal.ui.pages.add_content.registration_gate.CreateRegistrationGatePage.LEFT_COLUMN_INPUT;
import static drupal.ui.pages.add_content.registration_gate.CreateRegistrationGatePage.TEXT_FORMAT_DROPDOWN;
import static drupal.ui.pages.add_content.registration_gate.CreateRegistrationGatePage.TITLE_INPUT;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class RegistrationDataModel extends MasterPage implements ISectionDataModel {

    private final boolean existingImage;
    String name;
    TextFormat textFormat;
    String leftColumnData;
    String contentRelease;

    public RegistrationDataModel() {
        name = "TestTitle" + timeStampFormat(PATTERN);
        textFormat = TextFormat.UNRESTRICTED_HTML;
        leftColumnData = "<p>Join the Largest Online</p>\n" +
                "<h1>Military Community</h1>";
        contentRelease = null;
        existingImage = true;
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        typeName(expectedClass);
        selectTextFormat(expectedClass);
        typeLeftColumnData(expectedClass);
        typeContentRelease(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeName(Class<P> expectedClass) {

        if (name != null) {
            TITLE_INPUT.should(appear, exist, enabled)
                       .setValue(name);
        }
        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P selectTextFormat(Class<P> expectedClass) {
        if (textFormat != null) {
            TEXT_FORMAT_DROPDOWN.should(exist, appear, enabled)
                                .selectOptionContainingText(textFormat.getTextFormatItem());
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeLeftColumnData(Class<P> expectedClass) {
        if (leftColumnData != null) {
            LEFT_COLUMN_INPUT.should(exist, appear, enabled)
                             .setValue(leftColumnData);
        }
        return returnInstanceOf(expectedClass);
    }


    private <P extends MasterPage> P typeContentRelease(Class<P> expectedClass) {
        if (contentRelease != null) {
            CONTENT_RELEASE.should(exist, appear, enabled)
                           .setValue(contentRelease);
        }
        return returnInstanceOf(expectedClass);
    }

    @Getter
    @AllArgsConstructor
    public enum TextFormat {
        FULL_HTML("Full HTML"),
        UNRESTRICTED_HTML("Unrestricted HTML");

        private final String textFormatItem;
    }

}