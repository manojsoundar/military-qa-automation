package drupal.ui.pages.add_content.flexible_content_space;

import drupal.enums.content.FlexibleContentSpacePosition;
import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static drupal.enums.content.FlexibleContentSpaceType.ADD_SNIPPET_NO_LINK;
import static java.lang.String.format;

@Getter
@AllArgsConstructor
public class FlexibleContentSpaceSnippetNoLinkModel extends MasterPage implements ISectionDataModel, IFlexibleContentSpace {

    private static final String FLEXIBLE_CONTENT_SPACE_SNIPPET_NO_LINK_LABEL_INPUT = "input[id*='edit-field-%s-%s-subform-field-content-snippet']";

    private final FlexibleContentSpacePosition position;
    private int index;
    private final String contentSnippetLabel;

    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        index = selectFlexibleContentSpace(ADD_SNIPPET_NO_LINK, position) - 1;
        enterSnippetNoLinkLabel();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();
        clickEditFlexibleContentSpace(position, index);
        enterSnippetNoLinkLabel();
        return returnInstanceOf(expectedClass);
    }

    private FlexibleContentSpaceSnippetNoLinkModel enterSnippetNoLinkLabel() {
        $(format(FLEXIBLE_CONTENT_SPACE_SNIPPET_NO_LINK_LABEL_INPUT, position.name()
                                                                             .toLowerCase(), index)).should(exist, appear, enabled)
                                                                                                    .setValue(contentSnippetLabel);

        return this;
    }

}


