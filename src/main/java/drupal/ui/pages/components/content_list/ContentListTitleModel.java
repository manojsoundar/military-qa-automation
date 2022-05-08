package drupal.ui.pages.components.content_list;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ContentListTitleModel extends MasterPage implements ISectionDataModel {

    private static final SelenideElement ADD_BRIGHTCOVE_TITLE = $("input#edit-title-0-value");

    private String title;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        enterTitle();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private ContentListTitleModel enterTitle() {
        if (title != null) {
            ADD_BRIGHTCOVE_TITLE.should(enabled, visible)
                                .setValue(title);
        }
        return this;
    }

}
