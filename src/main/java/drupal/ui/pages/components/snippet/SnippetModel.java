package drupal.ui.pages.components.snippet;

import drupal.models.ISectionDataModel;
import drupal.ui.components.AttachAnImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.PATTERN;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Data
public class SnippetModel extends MasterPage implements ISectionDataModel, AttachAnImage {

    private String title;
    private String displayTitle;
    private String blurb;
    private String link;

    public static SnippetModel addRegularSnippet() {
        return new SnippetModel("Test Regular Snippet " + timeStampFormat(PATTERN), null, "This is a text blurb for the creation of a snippet.", "https://www.military.com");
    }

    public static SnippetModel addNoSnippetLink() {
        return new SnippetModel("Test Regular Snippet " + timeStampFormat(PATTERN), "This is a Test Display Name " + timeStampFormat("dd/mm/yyyy"), "This is a text blurb for the creation of a snippet.", null);
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        fillTitle(expectedClass);
        fillDisplayTitle(expectedClass);
        fillBlurb(expectedClass);
        fillLink(expectedClass);
        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillTitle(Class<P> expectedClass) {
        if (title != null) {
            EditSnippetPage.TITLE_INPUT.should(enabled, visible)
                                       .setValue(title);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillBlurb(Class<P> expectedClass) {
        if (blurb != null) {
            EditSnippetPage.BLURB_INPUT.should(enabled, visible)
                                       .setValue(blurb);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillDisplayTitle(Class<P> expectedClass) {
        if (displayTitle != null) {
            EditSnippetPage.DISPLAY_TITLE_INPUT.should(enabled, visible)
                                               .setValue(displayTitle);
        }

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillLink(Class<P> expectedClass) {

        if (link != null) {
            EditSnippetPage.LINK_INPUT.should(enabled, visible)
                                      .setValue(link);
        }
        return returnInstanceOf(expectedClass);
    }

}
