package drupal.ui.pages.add_content.article;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static drupal.ui.pages.add_content.article.CreateArticlePage.GATE_CONTENT_INPUT;
import static drupal.ui.pages.add_content.article.CreateArticlePage.GATE_CONTENT_SECTION;
import static drupal.ui.pages.add_content.article.CreateArticlePage.REGISTRATION_GATE_LIST;
import static java.time.Duration.ofSeconds;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Log4j2
public class GateContentModel extends MasterPage implements ISectionDataModel {

    private String gateContent;

    public static GateContentModel setGateContentData() {
        return new GateContentModel("Test Gate");
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {

        fillGateContent(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P fillGateContent(Class<P> expectedClass) {

        if (gateContent != null) {
            GATE_CONTENT_SECTION.should(exist, enabled)
                                .scrollIntoView("{behavior: 'instant', block: 'center', inline: 'center'}")
                                .click();
            GATE_CONTENT_INPUT.should(exist, enabled)
                              .scrollIntoView("{behavior: 'instant', block: 'center', inline: 'center'}")
                              .click();
            GATE_CONTENT_INPUT.should(exist, enabled)
                              .setValue(gateContent);
            waitAjaxJQueryMet(300);
            REGISTRATION_GATE_LIST.first()
                                  .should(appear, ofSeconds(30))
                                  .should(enabled, visible)
                                  .click();

        }

        return returnInstanceOf(expectedClass);
    }
}
