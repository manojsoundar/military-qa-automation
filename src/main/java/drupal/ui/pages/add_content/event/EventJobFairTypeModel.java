package drupal.ui.pages.add_content.event;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static drupal.ui.pages.add_content.event.EventPage.JOB_FAIR_TYPE_SELECTOR;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Log4j2
@Data
@AllArgsConstructor
public class EventJobFairTypeModel extends MasterPage implements ISectionDataModel {

    JobFairType jobFairType;

    public EventJobFairTypeModel() {
        jobFairType = JobFairType.LOCATION_JOB;
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        selectJobFairType(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectJobFairType(Class<P> expectedClass) {
        if (jobFairType != null) {
            JOB_FAIR_TYPE_SELECTOR.should(exist, appear, enabled)
                                  .scrollTo()
                                  .selectOptionContainingText(jobFairType.getTypeItem());
        }
        return returnInstanceOf(expectedClass);
    }

    public EventJobFairTypeModel selectVirtualJobLocation() {
        return new EventJobFairTypeModel(JobFairType.VIRTUAL_JOB);
    }

    @Getter
    @AllArgsConstructor
    public enum JobFairType {
        NONE("- None -"),
        LOCATION_JOB("At a location (fill in address below)"),
        VIRTUAL_JOB("Virtual job fair");

        private final String typeItem;
    }

}
