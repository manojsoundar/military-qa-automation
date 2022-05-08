package drupal.ui.pages.add_content.discount;

import com.codeborne.selenide.SelenideElement;
import drupal.models.ISectionDataModel;
import drupal.ui.components.AdministrationToolbar;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class EditDiscountPage extends AdministrationToolbar {

    private static final String URL_REGEX = "/edit";
    private static final SelenideElement SAVE_BUTTON = $("#edit-submit").as("Save Button");

    public EditDiscountPage() {
        assertTrue(verifyURLLoaded(URL_REGEX), "Edit Discount Page not loaded.");
        log.info("Edit Discount Page loaded properly.");
    }

    public EditDiscountPage fillIn(List<ISectionDataModel> sectionModel) {

        for (ISectionDataModel currentSectionModel : sectionModel) {
            currentSectionModel.setData(EditDiscountPage.class);
        }

        return this;

    }

    public DiscountsPage clickSaveButton() {
        SAVE_BUTTON.should(exist, appear)
                   .scrollTo()
                   .click();

        return new DiscountsPage();
    }

}