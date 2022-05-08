package drupal.ui.pages.add_content.base_installation;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static drupal.ui.pages.add_content.base_installation.CreateBaseInstallationPage.GEO_LATITUDE_INPUT;
import static drupal.ui.pages.add_content.base_installation.CreateBaseInstallationPage.GEO_LONGITUDE_INPUT;
import static drupal.ui.pages.add_content.base_installation.CreateBaseInstallationPage.LOCATION_DROP_DOWN;
import static drupal.ui.pages.add_content.base_installation.CreateBaseInstallationPage.LOCATION_STREET_ADDRESS1_INPUT;
import static drupal.ui.pages.add_content.base_installation.CreateBaseInstallationPage.LOCATION_STREET_ADDRESS2_INPUT;
import static drupal.ui.pages.add_content.base_installation.CreateBaseInstallationPage.STATE_DROP_DOWN;
import static drupal.ui.pages.add_content.base_installation.CreateBaseInstallationPage.TERRITORY_DROP_DOWN;
import static drupal.ui.pages.add_content.base_installation.CreateBaseInstallationPage.ZIP_CODE_INPUT;

@SuppressWarnings("ALL")
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BaseServiceLocationModel extends MasterPage implements ISectionDataModel {

    String streetAddress1;
    String streetAddress2;
    String location;
    String territory;
    String state;
    String zipCode;
    String geoLocationLatitude;
    String geoLocationLongitude;

    public BaseServiceLocationModel() {
        streetAddress1 = "22 Lincoln Street";
        streetAddress2 = "Suite 200";
        location = "United States";
        territory = "VA";
        state = "Hampton";
        zipCode = "23669";
        geoLocationLatitude = "36.850505";
        geoLocationLongitude = "-76.285629";

    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        enterStreetAddressOne(expectedClass);
        enterStreetAddressTwo(expectedClass);
        selectLocation(expectedClass);
        selectTerritory(expectedClass);
        selectState(expectedClass);
        enterZipCode(expectedClass);
        enterLatitude(expectedClass);
        enterLongitude(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P enterStreetAddressOne(Class<P> expectedClass) {
        if (streetAddress1 != null) {
            LOCATION_STREET_ADDRESS1_INPUT.should(visible, enabled)
                                          .setValue(streetAddress1);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P enterStreetAddressTwo(Class<P> expectedClass) {
        if (streetAddress2 != null) {
            LOCATION_STREET_ADDRESS2_INPUT.should(visible, enabled)
                                          .setValue(streetAddress2);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectLocation(Class<P> expectedClass) {
        if (location != null) {
            LOCATION_DROP_DOWN.should(visible, enabled)
                              .selectOption(location);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectTerritory(Class<P> expectedClass) {
        if (territory != null) {
            TERRITORY_DROP_DOWN.should(visible, enabled)
                               .selectOption(territory);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectState(Class<P> expectedClass) {
        if (state != null) {
            STATE_DROP_DOWN.should(visible, enabled)
                           .selectOption(state);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P enterZipCode(Class<P> expectedClass) {
        if (zipCode != null) {
            ZIP_CODE_INPUT.should(visible, enabled)
                          .setValue(zipCode);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P enterLatitude(Class<P> expectedClass) {
        if (geoLocationLatitude != null) {
            GEO_LATITUDE_INPUT.should(visible, enabled)
                              .setValue(geoLocationLatitude);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P enterLongitude(Class<P> expectedClass) {
        if (geoLocationLongitude != null) {
            GEO_LONGITUDE_INPUT.should(visible, enabled)
                               .setValue(geoLocationLongitude);
        }
        return returnInstanceOf(expectedClass);
    }

}
