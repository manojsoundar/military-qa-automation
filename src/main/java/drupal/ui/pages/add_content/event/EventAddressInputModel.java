package drupal.ui.pages.add_content.event;

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
import static drupal.ui.pages.add_content.event.EventPage.CITY_INPUT;
import static drupal.ui.pages.add_content.event.EventPage.COUNTRY_SELECTOR;
import static drupal.ui.pages.add_content.event.EventPage.STATE_SELECTOR;
import static drupal.ui.pages.add_content.event.EventPage.STREET_ADDRESS_INPUT;
import static drupal.ui.pages.add_content.event.EventPage.ZIPCODE_INPUT;

@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = false)
@Log4j2
@Data
@AllArgsConstructor
public class EventAddressInputModel extends MasterPage implements ISectionDataModel {

    String country;
    String streetAddress;
    String city;
    String state;
    String zipcode;

    public EventAddressInputModel() {
        country = "United States";
        streetAddress = "Test Address";
        city = "Test city";
        state = "Massachusetts";
        zipcode = "02493";
    }

    @Override
    public <P extends MasterPage> P setData(Class<P> expectedClass) {
        whichClassAndMethod();
        selectCountry(expectedClass);
        typeStreetAddress(expectedClass);
        typeCity(expectedClass);
        selectState(expectedClass);
        typeZipcode(expectedClass);

        return returnInstanceOf(expectedClass);
    }

    public <P extends MasterPage> P editData(Class<P> expectedClass) {
        whichClassAndMethod();

        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectCountry(Class<P> expectedClass) {
        if (country != null) {
            COUNTRY_SELECTOR.should(appear, exist, enabled)
                            .scrollTo()
                            .selectOption(country);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeStreetAddress(Class<P> expectedClass) {
        if (streetAddress != null) {
            STREET_ADDRESS_INPUT.should(appear, exist, enabled)
                                .scrollTo()
                                .setValue(streetAddress);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeCity(Class<P> expectedClass) {
        if (city != null) {
            CITY_INPUT.should(appear, exist, enabled)
                      .setValue(city);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P selectState(Class<P> expectedClass) {
        if (state != null) {
            STATE_SELECTOR.should(enabled, visible)
                          .selectOption(state);
        }
        return returnInstanceOf(expectedClass);
    }

    private <P extends MasterPage> P typeZipcode(Class<P> expectedClass) {
        if (zipcode != null) {
            ZIPCODE_INPUT.should(appear, exist, enabled)
                         .setValue(zipcode);
        }
        return returnInstanceOf(expectedClass);
    }

}
