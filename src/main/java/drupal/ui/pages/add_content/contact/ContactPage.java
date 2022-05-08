package drupal.ui.pages.add_content.contact;

import com.codeborne.selenide.SelenideElement;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class ContactPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/contact/";
    private static final SelenideElement CONTACT_PAGE_H_1_TITLE = $(".block--page--title h1 span");
    private static final SelenideElement UPDATED_STATUS_MESSAGE = $(".messages--status");
    private static final SelenideElement EDIT_BUTTON = $x(".//*[@class='menu menu--tabs']//a[contains(@href,'edit')]").as("edit button");

    public ContactPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Contact page not loaded.");
        log.info("Contact page loaded properly.");
    }

    public ContactPage verifyContactPage(String contactTitle) {

        assertEquals(CONTACT_PAGE_H_1_TITLE.should(exist, appear)
                                           .getText()
                                           .trim(), contactTitle,
                     "Displayed title : " + CONTACT_PAGE_H_1_TITLE.getText()
                                                                  .trim() + " , Expected title :" + contactTitle);
        assertTrue(verifyURLLoaded(URL_REGEX + contactTitle.toLowerCase()
                                                           .replace(" ", "-")), "Contact page not loaded.");
        return this;
    }

    public EditContactPage clickEditTab() {

        EDIT_BUTTON.should(exist, enabled, visible)
                   .click();
        return new EditContactPage();
    }

    public void validateEditedContact(ContactModel contactModel) {

        assertEquals(CONTACT_PAGE_H_1_TITLE.should(exist, appear)
                                           .getText()
                                           .trim(), contactModel.getContactTitle(),
                     "Displayed title: " + CONTACT_PAGE_H_1_TITLE.getText()
                                                                 .trim() + ", Expected updated title : " + contactModel.getContactTitle());
        log.info("Contact title is updated");
        assertTrue(UPDATED_STATUS_MESSAGE.should(visible, appear)
                                         .getText()
                                         .contains("updated"), "Contact updated message isn't displayed");

        log.info("Success massage displayed after edit");
    }

}
