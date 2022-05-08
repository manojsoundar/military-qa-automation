package drupal.ui.pages.add_content.contact;

import drupal.models.ISectionDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mgs.qa.base.page.MasterPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static drupal.ui.pages.add_content.contact.AddContactPage.ADD_ANOTHER_ITEM_PHONE_NUMBER_BUTTON;
import static drupal.ui.pages.add_content.contact.AddContactPage.BUILDING_NUMBER_INPUT;
import static drupal.ui.pages.add_content.contact.AddContactPage.CROSS_STREET_INPUT;
import static drupal.ui.pages.add_content.contact.AddContactPage.GEO_LOCATION_LATITUDE_INPUT;
import static drupal.ui.pages.add_content.contact.AddContactPage.GEO_LOCATION_LONGITUDE_INPUT;
import static drupal.ui.pages.add_content.contact.AddContactPage.LOCATION_DROPDOWN_1;
import static drupal.ui.pages.add_content.contact.AddContactPage.LOCATION_DROPDOWN_2;
import static drupal.ui.pages.add_content.contact.AddContactPage.LOCATION_DROPDOWN_3;
import static drupal.ui.pages.add_content.contact.AddContactPage.PHONE_NUMBER_INPUT_LIST;
import static drupal.ui.pages.add_content.contact.AddContactPage.STREET_ADDRESS_1_INPUT;
import static drupal.ui.pages.add_content.contact.AddContactPage.STREET_ADDRESS_2_INPUT;
import static drupal.ui.pages.add_content.contact.AddContactPage.ZIP_CODE_INPUT;
import static mgs.qa.utils.LoopUtils.waitAjaxJQueryMet;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class LocationModel extends MasterPage implements ISectionDataModel {

    private String streetAddress1;
    private String streetAddress2;
    private String buildingNumber;
    private String crossStreet;
    private String location;
    private String zipCode;
    private String geoLocationLatitude;
    private String geoLocationLongitude;
    private Map<String, String> phoneNumber;

    public static LocationModel getBaseGuideLocationData() {
        return new LocationModel("111 Gainsborough Square", "Suite 200 in the Street", "10A", "Corner of Gainsborough Square and Johnson Avenue", "United States,VA,Chesapeake", "23322", "36.741684", "-76.24188", Map.of("Home", "(888) 842-6328", "Mobile", "(757) 511-1230"));
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        fillStreetAddress1();
        fillStreetAddress2();
        fillBuildingNumber();
        fillCrossStreet();
        fillLocation();
        fillZipCode();
        fillGeoLocationLatitude();
        fillGeoLocationLongitude();
        fillPhoneNumber();

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private LocationModel fillStreetAddress1() {
        STREET_ADDRESS_1_INPUT.scrollIntoView("{behavior: 'instant', block: 'center', inline: 'center'}")
                              .should(visible, appear)
                              .setValue(streetAddress1);
        return this;
    }

    private LocationModel fillStreetAddress2() {
        STREET_ADDRESS_2_INPUT.should(visible, appear)
                              .setValue(streetAddress2);
        return this;
    }

    private LocationModel fillBuildingNumber() {
        BUILDING_NUMBER_INPUT.should(visible, appear)
                             .setValue(buildingNumber);
        return this;
    }

    private LocationModel fillCrossStreet() {
        CROSS_STREET_INPUT.should(visible, appear)
                          .setValue(crossStreet);
        return this;
    }

    private LocationModel fillLocation() {
        List<String> myList = new ArrayList<>(Arrays.asList(location.split(",")));
        LOCATION_DROPDOWN_1.should(appear, visible)
                           .selectOption(myList.get(0));
        if (myList.get(1) != null) {
            LOCATION_DROPDOWN_2.should(appear, visible)
                               .selectOption(myList.get(1));
        }
        if (myList.get(2) != null) {
            LOCATION_DROPDOWN_3.should(appear, visible)
                               .selectOption(myList.get(2));
        }


        return this;
    }

    private LocationModel fillZipCode() {
        ZIP_CODE_INPUT.should(visible, appear)
                      .setValue(zipCode);
        return this;
    }

    private LocationModel fillGeoLocationLatitude() {
        GEO_LOCATION_LATITUDE_INPUT.should(appear, enabled)
                                   .setValue(geoLocationLatitude);
        return this;
    }

    private LocationModel fillGeoLocationLongitude() {
        GEO_LOCATION_LONGITUDE_INPUT.should(appear, enabled)
                                    .setValue(geoLocationLongitude);
        return this;
    }

    private LocationModel fillPhoneNumber() {
        // TODO sonar java S2864 "entrySet()" should be iterated when both the key and value are needed
        // TODO algo
        for (String key : phoneNumber.keySet()) {
            if (!phoneNumber.keySet()
                            .isEmpty()) {
                PHONE_NUMBER_INPUT_LIST.last()
                                       .should(appear, enabled)
                                       .setValue(phoneNumber
                                                         .get(key));
            }
            if (PHONE_NUMBER_INPUT_LIST.size() < phoneNumber
                    .keySet()
                    .size()) {
                waitAjaxJQueryMet(120);
                ADD_ANOTHER_ITEM_PHONE_NUMBER_BUTTON.should(appear, enabled)
                                                    .click();
            }
            waitAjaxJQueryMet(120);

        }
        return this;
    }

}

