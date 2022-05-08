package drupal.ui.pages.add_content.newsletter_issue;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static drupal.ui.pages.add_content.newsletter_issue.CreateNewsLetterIssuePage.URL_ADD_ANOTHER_ITEM_BUTTON;
import static drupal.ui.pages.add_content.newsletter_issue.CreateNewsLetterIssuePage.URL_INPUT;
import static java.time.Duration.ofSeconds;
import static mgs.qa.utils.LoopUtils.doWhileConditionNotMet;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@Log4j2
public class TrackingPixelURLModel extends MasterPage implements ISectionDataModel {

    private List<String> url;

    public TrackingPixelURLModel() {
        url = List.of("https://www.military.com");
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        addTrackingPixelUrl(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P addTrackingPixelUrl(Class<P> expectedClass) {

        if (!url.isEmpty()) {
            for (int i = 0; i < url.size(); i++) {
                if (i != 0) {
                    URL_ADD_ANOTHER_ITEM_BUTTON.should(exist, appear, enabled)
                                               .scrollTo()
                                               .click();
                }
                doWhileConditionNotMet(
                        URL_ADD_ANOTHER_ITEM_BUTTON::isEnabled,
                        () -> log.info("waiting ADD_ANOTHER_ITEM being enabled ")
                );
                URL_INPUT.get(i)
                         .should(appear, ofSeconds(20))
                         .setValue(url.get(i));
            }
        }
        return returnInstanceOf(expectedClass);
    }

}
