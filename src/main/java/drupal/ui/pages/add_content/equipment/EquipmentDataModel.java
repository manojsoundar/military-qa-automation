package drupal.ui.pages.add_content.equipment;

import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;
import static common.CommonMethods.timeStampFormat;
import static drupal.models.TimeStampPattern.DATE_PATTERN;
import static drupal.ui.pages.add_content.equipment.AddEquipmentPage.BLURB_TEXT_AREA;
import static drupal.ui.pages.add_content.equipment.AddEquipmentPage.BODY_FRAME;
import static drupal.ui.pages.add_content.equipment.AddEquipmentPage.BODY_FRAME_INPUT;
import static drupal.ui.pages.add_content.equipment.AddEquipmentPage.CURRENT_RELEASE_INPUT;
import static drupal.ui.pages.add_content.equipment.AddEquipmentPage.TITLE_INPUT;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class EquipmentDataModel extends AdministrationToolbar implements ISectionDataModel {

    private String title;
    private String blurb;
    private String body;
    private String contentRelease;

    public EquipmentDataModel() {
        title = "Equipment " + timeStampFormat(DATE_PATTERN);
        blurb = "Test Content: Mission: Ship-to-Shore Troop Transport";
        body = "Test Content:\n" +
                "Manufacturer: United Defense\n" +
                "Service: USMC\n" +
                "Armament: Mk 19 40 mm grenade launcher or M242 Bushmaster 25mm gun; M2HB .50-caliber machine gun\n" +
                "Engine: Detroit Diesel 8V-53T (P-7), Cummins VT 400 903 (P-7A1)\n" +
                "Range: 300 miles; 20 nm in water\n" +
                "Speed: 20 mph off-road, 45 mph surfaced road, 8 mph water\n" +
                "Crew: 3 crew, 25 Marines\n" +
                "From ship to shore to objective, no equipment better defines the distinction and purpose of Marine Corps expeditionary capabilities than the AAV-7 Amphibious Assault Vehicle. Designed to assault any shoreline from the well decks of Navy assault ships, AAVs are highly mobile, tracked armored amphibious vehicles that transport Marines and cargo to and through hostile territory.\n" +
                "The AAVP7A1 is an armored assault amphibious full-tracked landing vehicle. The vehicle carries troops in water operations from ship to shore, through rough water and surf zone. It also carries troops to inland objectives after ashore.";
        contentRelease = null;
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        enterTitle();
        enterBlurb();
        enterBody();
        enterCurrentRelease();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    public EquipmentDataModel enterTitle() {
        if (title != null) {
            TITLE_INPUT.should(appear, enabled)
                       .setValue(title);
        }
        return this;
    }

    public EquipmentDataModel enterBlurb() {
        if (blurb != null) {
            BLURB_TEXT_AREA.should(appear, enabled)
                           .setValue(blurb);
        }
        return this;
    }

    public EquipmentDataModel enterBody() {
        if (body != null) {
            BLURB_TEXT_AREA.should(appear, enabled)
                           .scrollTo();
            switchTo().frame(BODY_FRAME);
            BODY_FRAME_INPUT.should(appear, exist, visible)
                            .click();
            BODY_FRAME_INPUT.sendKeys(body);
            switchTo().parentFrame();
        }
        return this;
    }

    public EquipmentDataModel enterCurrentRelease() {
        if (body != null) {
            CURRENT_RELEASE_INPUT.should(appear, enabled)
                                 .scrollTo()
                                 .setValue(contentRelease);
        }
        return this;
    }

}
